/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.repository;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Rating;
import com.elcom.data.interview.entity.ResultCriteria;
import com.elcom.data.repository.IUpsertRepository;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Administrator
 */
public class RatingRepository extends BaseRepository implements IUpsertRepository<Rating> {

    public RatingRepository(Session session) {
        super(session);
    }

    @Override
    public void upsert(Rating item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long insertReturnId(Rating item) {
        return (Long) this.session.save(item);
    }

    @SuppressWarnings({"rawtypes", "deprecation", "unchecked"})
    public List<Rating> findAllForCampany(Long companyId) {
        List<Rating> result = new ArrayList<>();
        try {
            String sql = "SELECT rt.id, rt.job_id as jobId, rt.user_id as userId, "
                    + " rt.note, rt.rating, rt.created_at as createdAt FROM rating rt "
                    + " JOIN job j ON rt.job_id = j.id WHERE j.company_id = :companyId ";
            NativeQuery query = this.session.createNativeQuery(sql);
            query.addScalar("id", StandardBasicTypes.LONG);
            query.addScalar("jobId", StandardBasicTypes.LONG);
            query.addScalar("userId", StandardBasicTypes.LONG);
            query.addScalar("note", StandardBasicTypes.STRING);
            query.addScalar("rating", StandardBasicTypes.STRING);
            query.addScalar("createdAt", StandardBasicTypes.TIMESTAMP);
            query.setResultTransformer(Transformers.aliasToBean(Rating.class));
            query.setParameter("companyId", companyId);

            Object lst = query.list();
            if (lst != null) {
                result = (List<Rating>) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
}
