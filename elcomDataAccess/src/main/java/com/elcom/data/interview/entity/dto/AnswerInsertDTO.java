/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity.dto;

import com.elcom.data.interview.entity.Answer;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AnswerInsertDTO {
    private List<Answer> data;

    public List<Answer> getData() {
        return data;
    }

    public void setData(List<Answer> data) {
        this.data = data;
    }
    
}
