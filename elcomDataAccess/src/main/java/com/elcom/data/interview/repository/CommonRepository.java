package com.elcom.data.interview.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Answer;
import com.elcom.data.interview.entity.Career;
import com.elcom.data.interview.entity.Letter;
import com.elcom.data.interview.entity.Level;
import com.elcom.data.interview.entity.Procedures;
import com.elcom.data.interview.entity.dto.AnswerInsertDTO;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.data.user.entity.User;
import com.elcom.model.dto.RatingInsertDTO;

public class CommonRepository extends BaseRepository implements IUpsertRepository<User> {

    public CommonRepository(Session session) {
        super(session);
    }

    public boolean insertRating(RatingInsertDTO item) {

        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("insertRating")
                .setParameter("job_id", item.getJobId())
                .setParameter("user_id", item.getUserId())
                .setParameter("note", item.getNote())
                .setParameter("rating", item.getRating());

        return query.executeUpdate() >= 1;
    }

    public List<Career> findAllCareer() {
        return this.session.createQuery("from Career order by name", Career.class).list();
    }

    public List<Level> findAllLevel(Long careerId) {
        return this.session.createQuery("from Level where careerId = :careerId order by name", Level.class)
                .setParameter("careerId", careerId)
                .list();
    }

    public List<Procedures> findProceduresByJob(Long jobId) {
        try {
            @SuppressWarnings("rawtypes")
            NativeQuery query = this.session.getNamedNativeQuery("findProceduresByJob");
            query.setParameter("jobId", jobId != null ? jobId : 0);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("userInterview", StandardBasicTypes.LONG);
            query.addScalar("jobId", StandardBasicTypes.LONG);
            query.addScalar("name", StandardBasicTypes.STRING);
            query.addScalar("type", StandardBasicTypes.INTEGER);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.addScalar("email", StandardBasicTypes.STRING);
            query.addScalar("fullName", StandardBasicTypes.STRING);
            query.addScalar("stt", StandardBasicTypes.INTEGER);
            query.addScalar("total", StandardBasicTypes.INTEGER);
            query.setResultTransformer(Transformers.aliasToBean(Procedures.class));

            return query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean checkSendLetter(Long jobId, String email) {
        List<Letter> list = this.session.createQuery("from Letter where jobId = :jobId and email = :email", Letter.class)
                .setParameter("jobId", jobId).setParameter("email", email)
                .list();
        return list != null && list.size() > 0;
    }

    public String getMediaFilePath(Long mediaId) {
        return (String) this.session.createNativeQuery(" SELECT file_path FROM media WHERE id = :mediaId ")
                .setParameter("mediaId", mediaId)
                .uniqueResult();
    }

    @Override
    public void upsert(User item) {
        // TODO Auto-generated method stub
    }

    public boolean insertAnswer(AnswerInsertDTO item) {
        for (Answer obj : item.getData()) {
            this.session.saveOrUpdate(obj);
        }
        return true;
    }

    public boolean updateAnswer(Answer item) {
        //this.session.update(item);
        //return true;
        //Update only id, question_id, is_true
        String sql = " Update answer set is_true = :is_true WHERE id = :id and question_id = :question_id ";
        NativeQuery query = this.session.createNativeQuery(sql);
        query.setParameter("is_true", item.getIsTrue());
        query.setParameter("id", item.getId());
        query.setParameter("question_id", item.getQuestionId());
        return query.executeUpdate() > 0;
    }

    public boolean deleteAnswer(String idList) {
        String[] arr = null;
        if (idList.contains(",")) {
            arr = idList.split(",");
        } else {
            arr = new String[]{idList};
        }
        String sql = " DELETE FROM answer WHERE id IN (:idList)";
        NativeQuery query = this.session.createNativeQuery(sql);
        query.setParameterList("idList", arr);
        return query.executeUpdate() > 0;
    }

    public boolean insertCareer(Career item) {
        this.session.saveOrUpdate(item);
        return true;
    }

    public boolean updateCareer(Career item) {
        this.session.update(item);
        return true;
    }

    public boolean deleteCareer(Long id) {
        String sql = " DELETE FROM career WHERE id = :id";
        NativeQuery query = this.session.createNativeQuery(sql);
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }
}
