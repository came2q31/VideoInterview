package com.elcom.data.interview.repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Job;
import com.elcom.data.interview.entity.QuestionJob;
import com.elcom.data.interview.entity.dto.JobDataPaging;
import com.elcom.data.interview.entity.dto.QuestionJobDTO;
import com.elcom.data.repository.IUpsertRepository;

public class JobRepository extends BaseRepository implements IUpsertRepository<Job> {

    public JobRepository(Session session) {
        super(session);
    }

    @SuppressWarnings({"rawtypes", "deprecation", "unchecked"})
    public JobDataPaging findAll(Long companyId, Integer type) {
        JobDataPaging result = new JobDataPaging();
        try {
            String sql = " SELECT id, name FROM job WHERE status = 1 AND company_id = :companyId ";
            if(type != null && type == 0){
                sql += " AND end_date > current_date() ";
            }
            NativeQuery query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("name", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(Job.class));
            query.setParameter("companyId", companyId);

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<Job>) lst);
                
                result.setTotalRows(new BigInteger(result.getDataRows().size() + ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //TODO code sẵn trước
    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public JobDataPaging findAllJoin(Long companyId, Long careerId, int page, int rowsPerPage) {

        JobDataPaging result = new JobDataPaging();

        if (page == 0) {
            page = 1;
        }
        if (rowsPerPage == 0) {
            rowsPerPage = 20; //Default
        }
        try {

            String condition = "";
            if (careerId != null) {
                condition += " AND career_id = :careerId ";
            }

            String tables = " Job ";

            String sql = " SELECT COUNT(id) FROM " + tables + " WHERE company_id = :companyId " + condition;
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameter("companyId", companyId);
            if (careerId != null) {
                query.setParameter("careerId", careerId);
            }

            result.setTotalRows((BigInteger) query.uniqueResult());

            sql = " SELECT q.id, q.career_id as careerId, q.company_id as companyId, q.question, q.level_id as levelId "
                    + " , q.question_type as questionType, q.created_at as createdAt "
                    + "  FROM " + tables + " WHERE company_id = :companyId " + condition;

            query = this.session.createNativeQuery(sql);

            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("careerId", StandardBasicTypes.LONG);
            query.addScalar("companyId", StandardBasicTypes.LONG);
            query.addScalar("question", StandardBasicTypes.STRING);
            query.addScalar("levelId", StandardBasicTypes.LONG);
            query.addScalar("questionType", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.setResultTransformer(Transformers.aliasToBean(Job.class));
            query.setFirstResult((page - 1) * rowsPerPage);
            query.setMaxResults(rowsPerPage);

            query.setParameter("companyId", companyId);
            if (careerId != null) {
                query.setParameter("career_id", careerId);
            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<Job>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void upsert(Job item) {
        this.session.saveOrUpdate(item);
    }

    public boolean insertQuestionJob(QuestionJobDTO item) {

        for (QuestionJob obj : item.getData()) {

            this.session.saveOrUpdate(obj);
        }

        return true;
    }
}
