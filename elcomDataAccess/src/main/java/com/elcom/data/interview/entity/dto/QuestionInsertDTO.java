/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity.dto;

import com.elcom.data.interview.entity.Question;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QuestionInsertDTO {

    private List<Question> data;

    public List<Question> getData() {
        return data;
    }

    public void setData(List<Question> data) {
        this.data = data;
    }
    
}
