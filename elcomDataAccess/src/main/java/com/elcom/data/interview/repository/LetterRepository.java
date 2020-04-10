/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.repository;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.ChangeFiledLetterDTO;
import com.elcom.model.dto.ChangeStatusLetterDTO;
import com.elcom.model.dto.InterviewLetterDTO;
import com.elcom.model.dto.interview.InterviewLetterDataPaging;
import com.elcom.util.StringUtils;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Admin
 */
public class LetterRepository extends BaseRepository implements IUpsertRepository<Letter> {

    public LetterRepository(Session session) {
        super(session);
    }

    @Override
    public void upsert(Letter item) {
        this.session.saveOrUpdate(item);
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public InterviewLetterDataPaging findLetter(Integer status, Long jobId,
            String applyName, Long interviewId, String _group, Integer procedureType,
            String procedureName, Integer type, Long companyId, String startDate,
            String endDate, int page, int rowsPerPage) {
        InterviewLetterDataPaging result = new InterviewLetterDataPaging();
        if (page == 0) {
            page = 1;
        }
        if (rowsPerPage == 0) {
            rowsPerPage = 20; //Default
        }
        try {
            String condition = "";

            if (companyId != null && !companyId.equals(0L)) {
                condition += " AND l.company_id = :companyId ";
            }
            if (type != null && type == 1) {
                condition += " AND l.status < 3 ";
                condition += " AND l.appointment_time >= now() ";
            }
            if (status != null && status != -1) {
                condition += " AND l.status = :status ";
            }
            if (jobId != null && !jobId.equals(0L)) {
                condition += " AND l.job_id = :jobId ";
            }
            if (!StringUtils.isNullOrEmpty(applyName)) {
                condition += " AND upper(u2.full_name) LIKE upper(:applyName) ";
            }
            if (interviewId != null && !interviewId.equals(0L)) {
                condition += " AND upper(p.user_interview) = :interviewId ";
            }
            if (!StringUtils.isNullOrEmpty(_group)) {
                condition += " AND upper(j._group) LIKE upper(:_group) ";
            }
            if (procedureType != null && procedureType > 0) {
                condition += " AND p.type = :procedureType ";
            }
            if (!StringUtils.isNullOrEmpty(procedureName)) {
                condition += " AND upper(p.name) LIKE upper(:procedureName) ";
            }
            if (!StringUtils.isNullOrEmpty(startDate)) {
                condition += " AND STR_TO_DATE(l.appointment_time, '%Y-%m-%d %H:%i:%s') >= STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s')";
            }
            if (!StringUtils.isNullOrEmpty(endDate)) {
                condition += " AND STR_TO_DATE(l.appointment_time, '%Y-%m-%d %H:%i:%s') <= STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s')";
            }
            String orderBy = " order by l.appointment_time desc ";
            if(type != null && type == 1){
                orderBy = " order by l.appointment_time asc ";
            }
            String tables = " letter l INNER JOIN job j ON l.job_id = j.id "
                    + " INNER JOIN procedures p ON l.procedure_id = p.id "
                    + " INNER JOIN user u1 ON p.user_interview = u1.id "
                    + " LEFT JOIN user u2 ON l.user_to = u2.id ";
            String sql = " SELECT l.job_id, l.user_to, p.type FROM " + tables + " WHERE 1 = 1 " + condition;
            NativeQuery query = this.session.createNativeQuery(sql);
            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("companyId", companyId);
            }
            if (status != null && status != -1) {
                query.setParameter("status", status);
            }
            if (jobId != null && !jobId.equals(0L)) {
                query.setParameter("jobId", jobId);
            }
            if (!StringUtils.isNullOrEmpty(applyName)) {
                query.setParameter("applyName", '%' + applyName + '%');
            }
            if (interviewId != null && !interviewId.equals(0L)) {
                query.setParameter("interviewId", interviewId);
            }
            if (!StringUtils.isNullOrEmpty(_group)) {
                query.setParameter("_group", '%' + _group + '%');
            }
            if (procedureType != null && procedureType > 0) {
                query.setParameter("procedureType", procedureType);
            }
            if (!StringUtils.isNullOrEmpty(procedureName)) {
                query.setParameter("procedureName", '%' + procedureName + '%');
            }
            if (!StringUtils.isNullOrEmpty(startDate)) {
                query.setParameter("startDate", startDate + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(endDate)) {
                query.setParameter("endDate", endDate + " 23:59:59");
            }
            //result.setTotalRows((BigInteger) query.uniqueResult());
            List<Object> totalList = query.list();
            result.setTotalRows(new BigInteger(totalList.size() + ""));

            sql = " SELECT l.id, l.job_id as jobId, l.appointment_time as appointmentTime, "
                    + " l.email, l.link_cv as linkCv, l.user_to as userTo, l.procedure_id as procedureId, "
                    + " l.status, l.user_view as userView, l.interview_view as interviewView, "
                    + " l.created_at AS createdAt, j.name as jobName, j._group as jobGroup, u2.id as applyId,"
                    + " u2.full_name as applyName, u2.email as applyEmail, p.name as procedureName,"
                    + " p.type as procedureType, u1.id as interviewId, u1.full_name as interviewName,"
                    + " u1.email as interviewEmail, l.password FROM " + tables + " WHERE 1 = 1 "
                    + condition + orderBy;
            query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("jobId", StandardBasicTypes.LONG);
            query.addScalar("appointmentTime", StandardBasicTypes.TIMESTAMP);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("linkCv", StandardBasicTypes.STRING);
            query.addScalar("userTo", StandardBasicTypes.LONG);
            query.addScalar("procedureId", StandardBasicTypes.LONG);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("userView", StandardBasicTypes.INTEGER);
            query.addScalar("interviewView", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("jobName", StandardBasicTypes.STRING);
            query.addScalar("jobGroup", StandardBasicTypes.STRING);
            query.addScalar("applyId", StandardBasicTypes.LONG);
            query.addScalar("applyName", StandardBasicTypes.STRING);
            query.addScalar("applyEmail", StandardBasicTypes.STRING);
            query.addScalar("procedureName", StandardBasicTypes.STRING);
            query.addScalar("procedureType", StandardBasicTypes.INTEGER);
            query.addScalar("interviewId", StandardBasicTypes.LONG);
            query.addScalar("interviewName", StandardBasicTypes.STRING);
            query.addScalar("interviewEmail", StandardBasicTypes.STRING);
//            query.addScalar("doTest", StandardBasicTypes.INTEGER);
            query.addScalar("password", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(InterviewLetterDTO.class));
            query.setFirstResult((page - 1) * rowsPerPage);
            query.setMaxResults(rowsPerPage);

            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("companyId", companyId);
            }
            if (status != null && status != -1) {
                query.setParameter("status", status);
            }
            if (jobId != null && !jobId.equals(0L)) {
                query.setParameter("jobId", jobId);
            }
            if (!StringUtils.isNullOrEmpty(applyName)) {
                query.setParameter("applyName", '%' + applyName + '%');
            }
            if (interviewId != null && !interviewId.equals(0L)) {
                query.setParameter("interviewId", interviewId);
            }
            if (!StringUtils.isNullOrEmpty(_group)) {
                query.setParameter("_group", '%' + _group + '%');
            }
            if (procedureType != null && procedureType > 0) {
                query.setParameter("procedureType", procedureType);
            }
            if (!StringUtils.isNullOrEmpty(procedureName)) {
                query.setParameter("procedureName", '%' + procedureName + '%');
            }
            if (!StringUtils.isNullOrEmpty(startDate)) {
                query.setParameter("startDate", startDate + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(endDate)) {
                query.setParameter("endDate", endDate + " 23:59:59");
            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<InterviewLetterDTO>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public InterviewLetterDataPaging findLetterForUser(Long userId, Integer status, Long jobId,
            Long interviewId, Integer procedureType, Long companyId, Integer doTest,
            Integer type, String startDate, String endDate, int page, int rowsPerPage) {
        InterviewLetterDataPaging result = new InterviewLetterDataPaging();
        if (page == 0) {
            page = 1;
        }
        if (rowsPerPage == 0) {
            rowsPerPage = 20; //Default
        }
        try {
            String condition = "";
            if (type != null && type == 1) {
                condition += " AND l.status <3 ";
                condition += " AND l.appointment_time >= now() ";
            }
            condition += " AND l.user_to = :userId ";
            if (status != null && status != -1) {
                condition += " AND l.status = :status ";
            }
            if (jobId != null && !jobId.equals(0L)) {
                condition += " AND l.job_id = :jobId ";
            }
            if (interviewId != null && !interviewId.equals(0L)) {
                condition += " AND upper(p.user_interview) = :interviewId ";
            }
            if (procedureType != null && procedureType > 0) {
                condition += " AND p.type = :procedureType ";
            }
            if (companyId != null && !companyId.equals(0L)) {
                condition += " AND upper(j.company_id) = :companyId ";
            }
            if (!StringUtils.isNullOrEmpty(startDate)) {
                condition += " AND STR_TO_DATE(l.appointment_time, '%Y-%m-%d %H:%i:%s') >= STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s')";
            }
            if (!StringUtils.isNullOrEmpty(endDate)) {
                condition += " AND STR_TO_DATE(l.appointment_time, '%Y-%m-%d %H:%i:%s') <= STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s')";
            }
//            if (doTest != null && doTest != -1) {
//                condition += " having doTest = :doTest ";
//            }
            String orderBy = " order by l.appointment_time desc ";
            if(type != null && type == 1){
                orderBy = " order by l.appointment_time asc ";
            }
            String tables = " letter l INNER JOIN job j ON l.job_id = j.id"
                    + " INNER JOIN company co ON co.id = j.company_id "
                    + " INNER JOIN procedures p ON l.procedure_id = p.id "
                    + " INNER JOIN user u1 ON p.user_interview = u1.id "
                    + " LEFT JOIN user u2 ON l.user_to = u2.id ";
            String sql = " SELECT l.job_id, l.user_to, p.type FROM " + tables + " WHERE 1 = 1 " + condition;
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("userId", userId);
            if (status != null && status != -1) {
                query.setParameter("status", status);
            }
            if (jobId != null && !jobId.equals(0L)) {
                query.setParameter("jobId", jobId);
            }
            if (interviewId != null && !interviewId.equals(0L)) {
                query.setParameter("interviewId", interviewId);
            }
            if (procedureType != null && procedureType > 0) {
                query.setParameter("procedureType", procedureType);
            }
            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("companyId", companyId);
            }
            if (!StringUtils.isNullOrEmpty(startDate)) {
                query.setParameter("startDate", startDate + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(endDate)) {
                query.setParameter("endDate", endDate + " 23:59:59");
            }
//            if (doTest != null && doTest != -1) {
//                query.setParameter("doTest", doTest);
//            }
            List<Object> totalList = query.list();
            result.setTotalRows(new BigInteger(totalList.size() + ""));

            sql = " SELECT l.id, l.job_id as jobId, l.appointment_time as appointmentTime, "
                    + " l.email, l.link_cv as linkCv, l.user_to as userTo, l.procedure_id as procedureId, "
                    + " l.status, l.user_view as userView, l.interview_view as interviewView, "
                    + " l.created_at AS createdAt, j.name as jobName, j._group as jobGroup, u2.id as applyId,"
                    + " u2.full_name as applyName, u2.email as applyEmail, p.name as procedureName,"
                    + " p.type as procedureType, u1.id as interviewId, u1.full_name as interviewName,"
                    + " u1.email as interviewEmail, co.name as companyName, l.company_id as companyId, "
                    + " l.password FROM " + tables + " WHERE 1 = 1 "
                    + condition + orderBy;
            query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("jobId", StandardBasicTypes.LONG);
            query.addScalar("appointmentTime", StandardBasicTypes.TIMESTAMP);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("linkCv", StandardBasicTypes.STRING);
            query.addScalar("userTo", StandardBasicTypes.LONG);
            query.addScalar("procedureId", StandardBasicTypes.LONG);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("userView", StandardBasicTypes.INTEGER);
            query.addScalar("interviewView", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("jobName", StandardBasicTypes.STRING);
            query.addScalar("jobGroup", StandardBasicTypes.STRING);
            query.addScalar("applyId", StandardBasicTypes.LONG);
            query.addScalar("applyName", StandardBasicTypes.STRING);
            query.addScalar("applyEmail", StandardBasicTypes.STRING);
            query.addScalar("procedureName", StandardBasicTypes.STRING);
            query.addScalar("procedureType", StandardBasicTypes.INTEGER);
            query.addScalar("interviewId", StandardBasicTypes.LONG);
            query.addScalar("interviewName", StandardBasicTypes.STRING);
            query.addScalar("interviewEmail", StandardBasicTypes.STRING);
            query.addScalar("companyName", StandardBasicTypes.STRING);
//            query.addScalar("doTest", StandardBasicTypes.INTEGER);
            query.addScalar("companyId", StandardBasicTypes.LONG);
            query.addScalar("password", StandardBasicTypes.STRING);

            query.setResultTransformer(Transformers.aliasToBean(InterviewLetterDTO.class));
            query.setFirstResult((page - 1) * rowsPerPage);
            query.setMaxResults(rowsPerPage);

            query.setParameter("userId", userId);
            if (status != null && status != -1) {
                query.setParameter("status", status);
            }
            if (jobId != null && !jobId.equals(0L)) {
                query.setParameter("jobId", jobId);
            }
            if (interviewId != null && !interviewId.equals(0L)) {
                query.setParameter("interviewId", interviewId);
            }
            if (procedureType != null && procedureType > 0) {
                query.setParameter("procedureType", procedureType);
            }
            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("companyId", companyId);
            }
            if (!StringUtils.isNullOrEmpty(startDate)) {
                query.setParameter("startDate", startDate + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(endDate)) {
                query.setParameter("endDate", endDate + " 23:59:59");
            }
//            if (doTest != null && doTest != -1) {
//                query.setParameter("doTest", doTest);
//            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<InterviewLetterDTO>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateLetterStatus(ChangeStatusLetterDTO item) {
        //Code here
        try {
            String sql = "Update letter set " + item.getField() + " = :param WHERE id = :id ";
            if (item.getCompanyId() != null && !item.getCompanyId().equals(0L)) {
                sql += " AND company_id = :companyId ";
            }
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("param", item.getValue());
            query.setParameter("id", item.getId());
            if (item.getCompanyId() != null && !item.getCompanyId().equals(0L)) {
                query.setParameter("companyId", item.getCompanyId());
            }
            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLetterUserId(Letter item) {
        //Code here
        try {
            String sql = "Update letter set user_to = :user_to WHERE email = :email";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("user_to", item.getUserTo());
            query.setParameter("email", item.getEmail());
            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> findInterviewForUser(Long userTo) {
        try {
            String sql = " SELECT distinct(u.id), u.email, u.avatar, u.full_name as fullName "
                    + " FROM letter l INNER JOIN procedures p ON l.procedure_id = p.id "
                    + " INNER JOIN user u ON u.id = p.user_interview "
                    + " WHERE l.user_to = :userTo "
                    + " ORDER BY u.email ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("avatar", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.setParameter("userTo", userTo);
            query.setResultTransformer(Transformers.aliasToBean(User.class));

            Object lst = query.list();
            if (lst != null) {
                return (List<User>) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Job> findJobForUser(Long userTo) {
        try {
            String sql = " SELECT j.id, j.name FROM letter l INNER JOIN job j ON l.job_id = j.id "
                    + " WHERE l.user_to = :userTo ORDER BY j.name ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("name", StandardBasicTypes.STRING);
            query.setParameter("userTo", userTo);
            query.setResultTransformer(Transformers.aliasToBean(Job.class));

            Object lst = query.list();
            if (lst != null) {
                return (List<Job>) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateLetterField(ChangeFiledLetterDTO item) {
        //Code here
        try {
            String sql = "Update letter set " + item.getField() + " = :param WHERE id = :id ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("param", item.getValue());
            query.setParameter("id", item.getId());
            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLetterStatus2(Long comapanyId, Long jobId, Long userTo, Integer status) {
        //Code here
        try {
            String sql = "Update letter set status = :status WHERE company_id = :comapanyId "
                    + " AND job_id = :jobId AND user_to = :userTo ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("status", status);
            query.setParameter("comapanyId", comapanyId);
            query.setParameter("jobId", jobId);
            query.setParameter("userTo", userTo);

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public InterviewLetterDTO getDetailLetter(Long id) {
        try {
            String condition = "";
            condition += " AND l.id = :id ";
            String orderBy = " order by l.appointment_time asc";
            String tables = " letter l INNER JOIN job j ON l.job_id = j.id"
                    + " INNER JOIN procedures p ON l.procedure_id = p.id "
                    + " INNER JOIN user u1 ON p.user_interview = u1.id "
                    + " LEFT JOIN user u2 ON l.user_to = u2.id ";
            String sql = " SELECT l.id, l.job_id as jobId, l.appointment_time as appointmentTime, "
                    + " l.email, l.link_cv as linkCv, l.user_to as userTo, l.procedure_id as procedureId, "
                    + " l.status, l.user_view as userView, l.interview_view as interviewView, "
                    + " l.created_at AS createdAt, j.name as jobName, j._group as jobGroup, u2.id as applyId,"
                    + " u2.full_name as applyName, u2.email as applyEmail, p.name as procedureName,"
                    + " p.type as procedureType, u1.id as interviewId, u1.full_name as interviewName,"
                    + " u1.email as interviewEmail, l.company_name as companyName, l.company_id as companyId, "
                    + " u2.mobile as applyMobile FROM " + tables + " WHERE 1 = 1 "
                    + condition + orderBy;
            NativeQuery query = this.session.createNativeQuery(sql);
            query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("jobId", StandardBasicTypes.LONG);
            query.addScalar("appointmentTime", StandardBasicTypes.TIMESTAMP);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("linkCv", StandardBasicTypes.STRING);
            query.addScalar("userTo", StandardBasicTypes.LONG);
            query.addScalar("procedureId", StandardBasicTypes.LONG);
            query.addScalar("status", StandardBasicTypes.INTEGER);
            query.addScalar("userView", StandardBasicTypes.INTEGER);
            query.addScalar("interviewView", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("jobName", StandardBasicTypes.STRING);
            query.addScalar("jobGroup", StandardBasicTypes.STRING);
            query.addScalar("applyId", StandardBasicTypes.LONG);
            query.addScalar("applyName", StandardBasicTypes.STRING);
            query.addScalar("applyEmail", StandardBasicTypes.STRING);
            query.addScalar("procedureName", StandardBasicTypes.STRING);
            query.addScalar("procedureType", StandardBasicTypes.INTEGER);
            query.addScalar("interviewId", StandardBasicTypes.LONG);
            query.addScalar("interviewName", StandardBasicTypes.STRING);
            query.addScalar("interviewEmail", StandardBasicTypes.STRING);
            query.addScalar("companyName", StandardBasicTypes.STRING);
            query.addScalar("companyId", StandardBasicTypes.LONG);
            query.addScalar("applyMobile", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(InterviewLetterDTO.class));

            query.setParameter("id", id);

            Object lst = query.uniqueResult();
            if (lst != null) {
                return (InterviewLetterDTO) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateLetterStatus3(Long id, Long userTo, Long jobId, Integer whereStatus, int status) {
        //Code here
        try {
            String sql = "Update letter set status = :status WHERE id = :id "
                    + " AND job_id = :jobId AND user_to = :userTo ";
            if (whereStatus != null) {
                sql += " AND status < :whereStatus ";
            }
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("status", status);
            query.setParameter("id", id);
            query.setParameter("jobId", jobId);
            query.setParameter("userTo", userTo);
            if (whereStatus != null) {
                query.setParameter("whereStatus", whereStatus);
            }

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLetterProcedure(Letter item) {
        //Code here
        try {
            String sql = "Update letter set procedure_id = :procedureId WHERE id = :id ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("procedureId", item.getProcedureId());
            query.setParameter("id", item.getId());

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
