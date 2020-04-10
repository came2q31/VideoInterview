package com.elcom.business.validation;

import com.elcom.common.Messages;
import com.elcom.model.dto.InterviewUserDTO;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class UserValidation extends AbstractValidation {

    public void validateInsert(InterviewUserDTO item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (item.getUserType() == null || item.getUserType() == 0) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "userType"));
        }

        if (StringUtils.isNullOrEmpty(item.getEmail())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "email"));
        } else if (!StringUtils.validateEmail(item.getEmail())) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "email"));
        } else if (item.getEmail().length() > 128) {
            getMessageDes().add(Messages.getString("validation.field.length", "email", 128));
        }

        if (StringUtils.isNullOrEmpty(item.getPassword())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "password"));
        } else if (item.getPassword().length() > 128) {
            getMessageDes().add(Messages.getString("validation.field.length", "password", 128));
        }

//        if (StringUtils.isNullOrEmpty(item.getMobile())) {
//            getMessageDes().add(Messages.getString("validation.field.madatory", "mobile"));
//        } else if (item.getMobile().length() > 20) {
//            getMessageDes().add(Messages.getString("validation.field.length", "mobile", 20));
//        }

        if (StringUtils.isNullOrEmpty(item.getFullName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "fullName"));
        } else if (item.getFullName().length() > 100) {
            getMessageDes().add(Messages.getString("validation.field.length", "fullName", 100));
        }

//        if (StringUtils.isNullOrEmpty(item.getAddress())) {
//            getMessageDes().add(Messages.getString("validation.field.madatory", "address"));
//        } else if (item.getAddress().length() > 256) {
//            getMessageDes().add(Messages.getString("validation.field.length", "address", 256));
//        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateUpdate(InterviewUserDTO item) throws ValidationException {

        if (item == null) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
            throw new ValidationException(this.buildValidationMessage());
        }

        if (StringUtils.isNullOrEmpty(item.getUuid())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "uuid"));
        } else if (item.getUuid().length() != 36) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "uuid"));
        }

        if (StringUtils.isNullOrEmpty(item.getMobile())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "mobile"));
        } else if (item.getMobile().length() > 20) {
            getMessageDes().add(Messages.getString("validation.field.length", "mobile", 20));
        }

        if (StringUtils.isNullOrEmpty(item.getFullName())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "fullName"));
        } else if (item.getFullName().length() > 100) {
            getMessageDes().add(Messages.getString("validation.field.length", "fullName", 100));
        }

        if (StringUtils.isNullOrEmpty(item.getAddress())) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "address"));
        } else if (item.getAddress().length() > 256) {
            getMessageDes().add(Messages.getString("validation.field.length", "address", 256));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateIdentityUser(String uuid) throws ValidationException {

        if (StringUtils.isNullOrEmpty(uuid)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "uuid"));
        } else if (uuid.length() != 36) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "uuid"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateChangePassword(String uuid, String password) throws ValidationException {

        if (StringUtils.isNullOrEmpty(uuid)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "uuid"));
        } else if (uuid.length() != 36) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "uuid"));
        }

        if (StringUtils.isNullOrEmpty(password)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "password"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateEmail(String email) throws ValidationException {

        if (StringUtils.isNullOrEmpty(email)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "email"));
        } else if (!StringUtils.validateEmail(email)) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "email"));
        } else if (email.length() > 64) {
            getMessageDes().add(Messages.getString("validation.field.length", "email", 64));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }

    public void validateMobile(String mobile) throws ValidationException {

        if (StringUtils.isNullOrEmpty(mobile)) {
            getMessageDes().add(Messages.getString("validation.field.madatory", "mobile"));
        } else if (mobile.length() > 20) {
            getMessageDes().add(Messages.getString("validation.field.length", "mobile", 20));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }
    
     public void validateCompanyId(Long companyId) throws ValidationException {
        if (!StringUtils.isNumberic(companyId.toString())) {
            getMessageDes().add(Messages.getString("validation.field.invalidFormat", "id"));
        }

        /**/
        if (!isValid()) {
            throw new ValidationException(this.buildValidationMessage());
        }
    }
}
