/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.model.dto.interview;

import com.elcom.model.dto.InterviewUserDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class InterviewUserDataPaging implements Serializable {

    private List<InterviewUserDTO> dataRows;
    private BigInteger totalRows;

    public InterviewUserDataPaging() {
        totalRows = new BigInteger("0");
        dataRows = new ArrayList<InterviewUserDTO>();
    }

    public List<InterviewUserDTO> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<InterviewUserDTO> dataRows) {
        this.dataRows = dataRows;
    }

    public BigInteger getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(BigInteger totalRows) {
        this.totalRows = totalRows;
    }
    
}
