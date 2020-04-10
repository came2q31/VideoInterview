/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.repository;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.ResultTest;
import com.elcom.data.repository.IUpsertRepository;
import org.hibernate.Session;

/**
 *
 * @author Administrator
 */
public class ResultTestRepository extends BaseRepository implements IUpsertRepository<ResultTest> {

    public ResultTestRepository(Session session) {
        super(session);
    }

    @Override
    public void upsert(ResultTest item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long insertResultTestReturnId(ResultTest item) {
        return (Long) this.session.save(item);
    }
    
}
