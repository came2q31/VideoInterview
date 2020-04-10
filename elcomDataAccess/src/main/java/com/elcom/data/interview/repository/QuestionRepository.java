package com.elcom.data.interview.repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Answer;
import com.elcom.data.interview.entity.Question;
import com.elcom.data.interview.entity.dto.QuestionDataPaging;
import com.elcom.data.interview.entity.dto.QuestionInsertDTO;
import com.elcom.data.repository.IUpsertRepository;
import java.util.ArrayList;

public class QuestionRepository extends BaseRepository implements IUpsertRepository<Question> {

    public QuestionRepository(Session session) {
        super(session);
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public QuestionDataPaging findAll(Long companyId, Long careerId, Long levelId,
            Integer questionType, Long careerIdPackage, int page, int rowsPerPage) {
        QuestionDataPaging result = new QuestionDataPaging();
        if (page == 0) {
            page = 1;
        }
        if (rowsPerPage == 0) {
            rowsPerPage = 20; //Default
        }
        try {
            String condition = "";
//            if (careerId != null) {
//                if (careerId.equals(0L)) {
//                    condition += " AND (q.company_id = 0 OR q.company_id = :company_id) ";
//                } else {
//                    condition += " AND (q.career_id = :career_id AND q.company_id = :company_id) ";
//                    if (careerIdPackage != null && !careerIdPackage.equals(0L)) {
//                        condition += " OR (q.company_id = 0 and q.career_id = :careerIdPackage) ";
//                    }
//                }
//            } else {
//                condition += " AND q.company_id = :company_id ";
//            }

            //c� mua 1 g�i theo l?nh v?c g� ?� g� ?�
            if (careerIdPackage != null && careerIdPackage.intValue() > 0) {
                condition += " AND ((q.career_id = :careerIdPackage AND q.company_id = 0) OR (q.career_id = :career_id AND q.company_id = :company_id)) ";
            } else {
                condition += " AND q.career_id = :career_id AND q.company_id in (0,:company_id) ";
            }
            condition += " AND q.career_id = :career_id1 ";

            if (levelId != null && !levelId.equals(0L)) {
                condition += " AND q.level_id = :level_id ";
            }
            if (questionType != null && questionType > 0) {
                condition += " AND q.question_type = :question_type ";
            }
            String tables = " question q "
                    + " inner join career c on c.id = q.career_id inner join level l on l.id = q.level_id";
            String sql = " SELECT COUNT(q.id) FROM " + tables + " WHERE 1 = 1 " + condition;
            NativeQuery query = this.session.createNativeQuery(sql);
//            if (careerId != null) {
//                if (careerId.equals(0L)) {
//                    query.setParameter("company_id", companyId);
//                } else {
//                    query.setParameter("career_id", careerId);
//                    query.setParameter("company_id", companyId);
//                    if (careerIdPackage != null && !careerIdPackage.equals(0L)) {
//                        query.setParameter("careerIdPackage", careerIdPackage);
//                    }
//                }
//            } else {
//                query.setParameter("company_id", companyId);
//            }

            //c� mua 1 g�i theo l?nh v?c g� ?� g� ?�
            if (careerIdPackage != null && careerIdPackage.intValue() > 0) {
                query.setParameter("careerIdPackage", careerIdPackage);
            }
            query.setParameter("career_id", careerId);
            query.setParameter("company_id", companyId);
            query.setParameter("career_id1", careerId);

            if (levelId != null && !levelId.equals(0L)) {
                query.setParameter("level_id", levelId);
            }
            if (questionType != null && questionType > 0) {
                query.setParameter("question_type", questionType);
            }
            result.setTotalRows((BigInteger) query.uniqueResult());

            sql = " SELECT q.id, q.career_id as careerId, q.company_id as companyId, q.question, q.level_id as levelId "
                    + " , q.question_type as questionType, q.created_at as createdAt, c.name as careerName, l.name as levelName "
                    + "  FROM " + tables + " WHERE 1 = 1 " + condition;

            query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("careerId", StandardBasicTypes.LONG);
            query.addScalar("companyId", StandardBasicTypes.LONG);
            query.addScalar("question", StandardBasicTypes.STRING);
            query.addScalar("levelId", StandardBasicTypes.LONG);
            query.addScalar("questionType", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("careerName", StandardBasicTypes.STRING);
            query.addScalar("levelName", StandardBasicTypes.STRING);
            query.setResultTransformer(Transformers.aliasToBean(Question.class));
            query.setFirstResult((page - 1) * rowsPerPage);
            query.setMaxResults(rowsPerPage);

//            if (careerId != null) {
//                if (careerId.equals(0L)) {
//                    query.setParameter("company_id", companyId);
//                } else {
//                    query.setParameter("career_id", careerId);
//                    query.setParameter("company_id", companyId);
//                    if (careerIdPackage != null && !careerIdPackage.equals(0L)) {
//                        query.setParameter("careerIdPackage", careerIdPackage);
//                    }
//                }
//            } else {
//                query.setParameter("company_id", companyId);
//            }
            if (careerIdPackage != null && careerIdPackage.intValue() > 0) {
                query.setParameter("careerIdPackage", careerIdPackage);
            }
            query.setParameter("career_id", careerId);
            query.setParameter("company_id", companyId);
            query.setParameter("career_id1", careerId);

            if (levelId != null && !levelId.equals(0L)) {
                query.setParameter("level_id", levelId);
            }
            if (questionType != null && questionType > 0) {
                query.setParameter("question_type", questionType);
            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<Question>) lst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public QuestionDataPaging findAllByQuestionIdLst(String questionIdLst, Long careerId) {
        QuestionDataPaging result = new QuestionDataPaging();
        String[] arr = null;
        if (questionIdLst.indexOf(",") != -1) {
            arr = questionIdLst.split(",");
        } else {
            arr = new String[]{questionIdLst};
        }
        try {

            String condition = "";
            if (careerId != null) {
                condition += " AND q.career_id = :career_id ";
            }
            String sql = " SELECT q.id, q.question, q.question_type as questionType, "
                    + " q.career_id as careerId, q.level_id as levelId, "
                    + " GROUP_CONCAT((a.answer) SEPARATOR '###') AS answerLst"
                    + " FROM question q LEFT JOIN answer a ON q.id = a.question_id WHERE q.id IN (:answerLst) "
                    + condition + " GROUP BY q.id ORDER BY q.question ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameterList("answerLst", arr);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("question", StandardBasicTypes.STRING);
            query.addScalar("questionType", StandardBasicTypes.INTEGER);
            query.addScalar("careerId", StandardBasicTypes.LONG);
            query.addScalar("levelId", StandardBasicTypes.LONG);
            query.setResultTransformer(Transformers.aliasToBean(Question.class));

            if (careerId != null) {
                query.setParameter("career_id", careerId);
            }

            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<Question>) lst);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public QuestionDataPaging findAllByQuestionIdLst2(String questionIdLst, Long careerId, Integer type) {
        QuestionDataPaging result = new QuestionDataPaging();
        String[] arr = null;
        if (questionIdLst.indexOf(",") != -1) {
            arr = questionIdLst.split(",");
        } else {
            arr = new String[]{questionIdLst};
        }
        try {

            String condition = "";
            if (careerId != null) {
                condition += " AND q.career_id = :career_id ";
            }
            String sql = " SELECT q.id, q.question, q.question_type as questionType, "
                    + " q.career_id as careerId, q.level_id as levelId, "
                    + " a.id as anwserId, a.answer, a.is_true as isTrue "
                    + " FROM question q LEFT JOIN answer a ON q.id = a.question_id "
                    + " WHERE q.id IN (:answerLst) " + condition + " ORDER BY q.question ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameterList("answerLst", arr);
            if (careerId != null) {
                query.setParameter("career_id", careerId);
            }

            List<Object[]> rows = query.list();
            if (rows != null && rows.size() > 0) {
                List<Question> questionList = new ArrayList<>();
                Answer tmpAnswer = null;
                Question tmpQuestion = null;
                List<Answer> ansList = new ArrayList<>();

                Long qId = null;
                String question = null;
                int questionType = -1;
                Long careerId_ = null;
                Long levelId = null;
                Long aId = null;
                String answer = null;
                Integer isTrue = null;
                for (Object[] row : rows) {
                    qId = Long.parseLong(row[0].toString());
                    question = row[1].toString();
                    questionType = Integer.parseInt(row[2].toString());
                    careerId_ = Long.parseLong(row[3].toString());
                    levelId = Long.parseLong(row[4].toString());
                    if (row[5] != null) {
                        aId = Long.parseLong(row[5].toString());
                    } else {
                        aId = null;
                    }
                    if (row[6] != null) {
                        answer = row[6].toString();
                    } else {
                        answer = null;
                    }
                    if (row[7] != null) {
                        isTrue = Integer.parseInt(row[7].toString());
                    } else {
                        isTrue = null;
                    }

                    if (tmpQuestion == null || tmpQuestion.getId().compareTo(qId) != 0) {
                        if (tmpQuestion != null) {
                            questionList.add(tmpQuestion);
                            ansList = new ArrayList<>();
                        }

                        tmpQuestion = new Question();
                        tmpQuestion.setId(qId);
                        tmpQuestion.setQuestion(question);
                        tmpQuestion.setQuestionType(questionType);
                        tmpQuestion.setAnswerList(ansList);
                        tmpQuestion.setCareerId(careerId_);
                        tmpQuestion.setLevelId(levelId);
                    }
                    tmpAnswer = new Answer();
                    tmpAnswer.setId(aId);
                    tmpAnswer.setAnswer(answer);
                    if (type != null && type == 100) {
                        tmpAnswer.setIsTrue(isTrue);
                    } else {
                        tmpAnswer.setIsTrue(null);
                    }
                    if (tmpAnswer != null && tmpAnswer.getId() != null && !tmpAnswer.getId().equals(0L)) {
                        ansList.add(tmpAnswer);
                    }
                }
                tmpQuestion.setAnswerList(ansList);
                questionList.add(tmpQuestion);

                result.setDataRows(questionList);
                if (questionList != null) {
                    result.setTotalRows(new BigInteger(questionList.size() + ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void upsert(Question item) {
        this.session.saveOrUpdate(item);
    }

    public Long insertReturnId(Question item) {
        return (Long) this.session.save(item);
    }

    public boolean insertMultiQuestion(QuestionInsertDTO item) {
        for (Question obj : item.getData()) {
            this.session.saveOrUpdate(obj);
        }
        return true;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    public QuestionDataPaging findQuestionForUser(Long companyId, Long careerId,
            Long procedureId, int page, int rowsPerPage) {
        QuestionDataPaging result = new QuestionDataPaging();
        String[] arr = null;
        try {
            String condition = "";
            if (careerId != null) {
                condition += " AND q.career_id = :career_id ";
            }
            String sql = " SELECT q.id, q.question, q.question_type as questionType, "
                    + " GROUP_CONCAT((a.answer) SEPARATOR '###') AS answerLst"
                    + " FROM question q LEFT JOIN answer a ON q.id = a.question_id "
                    + " WHERE q.id IN (:answerLst) "
                    + condition + " GROUP BY q.id ORDER BY q.question ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.setParameterList("answerLst", arr);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("question", StandardBasicTypes.STRING);
            query.addScalar("questionType", StandardBasicTypes.INTEGER);
            query.addScalar("answerLst", StandardBasicTypes.STRING);
            query
                    .setResultTransformer(Transformers.aliasToBean(Question.class
                    ));

            if (careerId != null) {
                query.setParameter("career_id", careerId);
            }
            Object lst = query.list();
            if (lst != null) {
                result.setDataRows((List<Question>) lst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateQuestion(Question item) {
        //Update only company_id, question_id, question, question_type
        String sql = " Update question set question = :question, question_type = :question_type"
                + " WHERE id = :id and company_id = :company_id ";
        NativeQuery query = this.session.createNativeQuery(sql);
        query.setParameter("question", item.getQuestion());
        query.setParameter("question_type", item.getQuestionType());
        query.setParameter("id", item.getId());
        query.setParameter("company_id", item.getCompanyId());
        return query.executeUpdate() > 0;
    }

    public boolean deleteQuestion(String idList, Long companyId) {
        String[] arr = null;
        if (idList.contains(",")) {
            arr = idList.split(",");
        } else {
            arr = new String[]{idList};
        }

        String sql = " DELETE FROM question WHERE id IN (:idList) and company_id = :companyId";
        NativeQuery query = this.session.createNativeQuery(sql);
        query.setParameterList("idList", arr);
        query.setParameter("companyId", companyId);
        boolean result = query.executeUpdate() > 0;
        if (result) {
            sql = " DELETE FROM answer WHERE question_id IN (:questionIdList)";
            query = this.session.createNativeQuery(sql);
            query.setParameterList("questionIdList", arr);
            query.executeUpdate();
        }
        return true;
    }

    public String listByJob(Long jobId) {
        String sql = " SELECT GROUP_CONCAT(question_id SEPARATOR ',') AS questionLst"
                + " FROM question_job WHERE job_id = :jobId ORDER BY question_id ";
        NativeQuery query = this.session.createNativeQuery(sql);
        query.setParameter("jobId", jobId);
        Object lst = query.uniqueResult();
        if (lst != null) {
            return lst.toString();
        }
        return null;
    }
}
