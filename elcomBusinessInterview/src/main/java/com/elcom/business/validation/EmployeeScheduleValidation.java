package com.elcom.business.validation;

import java.math.BigDecimal;

import com.elcom.common.Messages;
import com.elcom.data.interview.entity.EmployeeSchedule;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class EmployeeScheduleValidation extends AbstractValidation {
	
	public void validateUpsert(EmployeeSchedule item, String actionType) throws ValidationException {
		
		if ( item == null ) {
			getMessageDes().add(Messages.getString("validation.field.invalidFormat", "input"));
			throw new ValidationException(this.buildValidationMessage());
		}
		
		if( "UPDATE".equals(actionType) ) {
			if( item.getId() == null )
				getMessageDes().add(Messages.getString("validation.field.madatory", "id"));
			else if( item.getId().equals(0L) )
				getMessageDes().add(Messages.getString("validation.field.invalidFormat", "id"));
		}
		
		if ( item.getEmployeeId() == null || item.getEmployeeId().equals(0L) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "employeeId"));
		
		if ( item.getEmployeeStatusId() == null || item.getEmployeeStatusId().equals(0L) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "employeeStatusId"));
		
		if ( StringUtils.isNullOrEmpty(item.getDescription()) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "description"));
		else if ( item.getDescription().length() > 512 )
			getMessageDes().add(Messages.getString("validation.field.length", "description", 512));
		
		if ( item.getStartDay() == null )
			getMessageDes().add(Messages.getString("validation.field.madatory", "startDay"));
		
		if ( item.getEndDay() == null )
			getMessageDes().add(Messages.getString("validation.field.madatory", "endDay"));
		
		if ( StringUtils.isNullOrEmpty(item.getStartTime()) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "startTime"));
		
		if ( StringUtils.isNullOrEmpty(item.getEndTime()) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "endTime"));
		
		if ( StringUtils.isNullOrEmpty(item.getRecipients()) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "recipients"));
		else {
			if( item.getRecipients().contains(";") ) {
				for( String s : item.getRecipients().split(";") ) {
					if ( !StringUtils.validateEmail(s) )
						getMessageDes().add(Messages.getString("validation.field.invalidFormat", "recipients email address"));
				}
			} else if ( !StringUtils.validateEmail(item.getRecipients()) )
				getMessageDes().add(Messages.getString("validation.field.invalidFormat", "recipients email address"));
		}
		
		if ( item.getTotalLeave() == null || item.getTotalLeave().compareTo(BigDecimal.ZERO) == 0 )
			getMessageDes().add(Messages.getString("validation.field.madatory", "totalLeave"));
		
		if ( StringUtils.isNullOrEmpty(item.getStatusCode()) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "statusCode"));
		
		/**/
		if ( !isValid() )
			throw new ValidationException(this.buildValidationMessage());
	}
}
