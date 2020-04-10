package com.elcom.data.interview.repository;

import java.util.List;

import org.hibernate.Session;

import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Procedures;
import com.elcom.data.interview.entity.dto.ProceduresInsertDTO;
import com.elcom.data.repository.IUpsertRepository;

public class ProceduresRepository extends BaseRepository implements IUpsertRepository<Procedures> {

    public ProceduresRepository(Session session) {
        super(session);
    }

    public List<Procedures> findAll() {
        return this.session.createQuery("from Procedures", Procedures.class).list();
    }

    @Override
    public void upsert(Procedures item) {
    }

    public Long insertReturnId(Procedures item) {
        return (Long) this.session.save(item);
    }

    public boolean upsert(ProceduresInsertDTO item) {

        for (Procedures obj : item.getData()) {

            this.session.saveOrUpdate(obj);
        }

        return true;
    }
}
