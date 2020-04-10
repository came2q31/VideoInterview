/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.model.dto.interview;

import com.elcom.model.dto.InterviewLetterDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class InterviewLetterDataPaging implements Serializable {

    private List<InterviewLetterDTO> dataRows;
    private BigInteger totalRows;

    public InterviewLetterDataPaging() {
        totalRows = new BigInteger("0");
        dataRows = new ArrayList<InterviewLetterDTO>();
    }

    public List<InterviewLetterDTO> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<InterviewLetterDTO> dataRows) {
        this.dataRows = dataRows;
    }

    public BigInteger getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(BigInteger totalRows) {
        this.totalRows = totalRows;
    }
    
}
