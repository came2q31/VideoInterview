package com.elcom.business.factory;

import com.elcom.business.aggregate.MediaAggregate;
import com.elcom.business.aggregate.QuestionAggregate;
import com.elcom.business.validation.CommonValidation;
import com.elcom.business.validation.CompanyValidation;
import com.elcom.business.validation.EmployeeHistoryValidation;
import com.elcom.business.validation.EmployeeScheduleValidation;
import com.elcom.business.validation.UserValidation;
import com.elcom.data.UnitOfWork;
import com.elcom.data.interview.UnitOfWorkInterview;
import com.elcom.sharedbiz.aggregate.CommonAggregate;

public final class InterviewFactory {

	private InterviewFactory() {
	}
	
	public static UserValidation getUserValidation() {
		
		return new UserValidation();
	}
	
	public static CommonValidation getCommonValidation() {
		
		return new CommonValidation();
	}
	
	public static CompanyValidation getCompanyValidation() {
		
		return new CompanyValidation();
	}
	
	public static EmployeeScheduleValidation getEmployeeScheduleValidation() {
		
		return new EmployeeScheduleValidation();
	}
	
	public static EmployeeHistoryValidation getEmployeeHistoryValidation() {
		
		return new EmployeeHistoryValidation();
	}
	
	public static CommonAggregate getCommonAggregateInstance(UnitOfWork uok) {
		
		return new CommonAggregate(uok);
	}
	
	public static QuestionAggregate getQuestionAggregateInstance(UnitOfWorkInterview uok) {
		
		return new QuestionAggregate(uok);
	}
	
	public static MediaAggregate getMediaAggregate(UnitOfWorkInterview uok) {
		
		return new MediaAggregate(uok);
	}
}
