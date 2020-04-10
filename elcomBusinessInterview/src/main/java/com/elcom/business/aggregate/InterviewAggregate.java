/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.business.aggregate;

import com.elcom.data.UnitOfWork;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Admin
 */
public class InterviewAggregate {

    private UnitOfWork _uok = null;
    private static ModelMapper modelMapper = new ModelMapper();

    public InterviewAggregate(UnitOfWork uok) {
        this._uok = uok;
    }

    public UnitOfWork getUok() {
        return _uok;
    }

    public void setUok(UnitOfWork _uok) {
        this._uok = _uok;
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static void setModelMapper(ModelMapper modelMapper) {
        InterviewAggregate.modelMapper = modelMapper;
    }
    
}
