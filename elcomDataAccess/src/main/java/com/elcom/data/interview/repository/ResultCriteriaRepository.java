/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.repository;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.ResultCriteria;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Administrator
 */
public class ResultCriteriaRepository extends BaseRepository implements IUpsertRepository<ResultCriteria> {

    public ResultCriteriaRepository(Session session) {
        super(session);
    }

    @Override
    public void upsert(ResultCriteria item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long insertResultCriteriaReturnId(ResultCriteria item) {
        return (Long) this.session.save(item);
    }

    @SuppressWarnings({"rawtypes", "deprecation", "unchecked"})
    public List<ResultCriteria> findAllResultCriteria(Long companyId, Long jobId, String userId) {
        List<ResultCriteria> result = new ArrayList<>();
        try {
            String hql = "From ResultCriteria rc WHERE 1 = 1 ";
            if (companyId != null && !companyId.equals(0L)) {
                hql += " AND rc.companyId = :companyId ";
            }
            if (jobId != null && !jobId.equals(0L)) {
                hql += " AND rc.jobId = :jobId ";
            }
            if (!StringUtils.isNullOrEmpty(userId)) {
                hql += " AND rc.userId IN (:userIdLst) ";
            }
            Query query = this.session.createQuery(hql);
            if (companyId != null && !companyId.equals(0L)) {
                query.setParameter("companyId", companyId);
            }
            if (jobId != null && !jobId.equals(0L)) {
                query.setParameter("jobId", jobId);
            }
            if (!StringUtils.isNullOrEmpty(userId)) {
                Long [] arr = null;
                if (userId.contains(",")) {
                    String [] strArr = userId.split(",");
                    arr = new Long[strArr.length];
                    int index = 0;
                    for(String tmp : strArr){
                        arr[index++] = new Long(tmp);
                    }
                } else {
                    arr = new Long[]{new Long(userId)};
                }
                query.setParameterList("userIdLst", arr);
            }

            Object lst = query.list();
            if (lst != null) {
                result = (List<ResultCriteria>) lst;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
