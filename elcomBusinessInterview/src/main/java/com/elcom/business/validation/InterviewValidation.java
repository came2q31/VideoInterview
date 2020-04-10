/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.business.validation;

import com.elcom.common.Messages;
import com.elcom.data.interview.entity.Rating;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;

/**
 *
 * @author Admin
 */
public class InterviewValidation extends AbstractValidation {

    public void validateInsertRating(Rating item) throws ValidationException {
        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }
}
