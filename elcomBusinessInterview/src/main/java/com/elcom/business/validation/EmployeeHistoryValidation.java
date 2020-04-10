package com.elcom.business.validation;

import com.elcom.common.Messages;
import com.elcom.sharedbiz.validation.AbstractValidation;
import com.elcom.sharedbiz.validation.ValidationException;
import com.elcom.util.StringUtils;

public class EmployeeHistoryValidation extends AbstractValidation {
	
	public void validateGetListCheckTime(Long departmentId, String time) throws ValidationException {
		
		if ( departmentId == null || departmentId.equals(0L) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "departmentId"));
		
		if ( StringUtils.isNullOrEmpty(time) )
			getMessageDes().add(Messages.getString("validation.field.madatory", "time"));
		else if( time.indexOf("-") == -1 )
			getMessageDes().add(Messages.getString("validation.field.invalidFormat", "time"));
		
		/**/
		if ( !isValid() )
			throw new ValidationException(this.buildValidationMessage());
	}
}
