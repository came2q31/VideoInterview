package com.elcom.data.user.repository;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.elcom.data.factory.HibernateMySQLFactory;
import com.elcom.model.constant.InterviewConstant;
import com.elcom.model.dto.interview.EmployeeDTO;

public class InitCalendarEmployee {
	
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public static void main(String[] args) throws ParseException {
		
		SessionFactory sessionFactoryMysql = HibernateMySQLFactory.getInstance();
		Session sessionMysql = sessionFactoryMysql.openSession();
		boolean isCloseMySqlConnection = false;
		
		String sql = " select id from employee where status = 1 and is_deleted = 0 and card_number is not null ";
		NativeQuery query = sessionMysql.createNativeQuery(sql);
		query.addScalar("id", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(EmployeeDTO.class));
		
		Object result = query.list();
		
		if( result != null ) {
			
			List<EmployeeDTO> employeeLst = (List<EmployeeDTO>) result;
			List<Integer> timeIdLst = null;
			
			if( employeeLst!=null && !employeeLst.isEmpty() ) {
				
				sql = " select id from time_dimension where (year = 2020 and (month >=1 and month <=3)) or year = 2019 and month = 12 order by id ";
				query = sessionMysql.createNativeQuery(sql);
				
				timeIdLst = query.list();
				
				if( timeIdLst == null || timeIdLst.isEmpty() ) {
					System.out.println("[InitCalendarEmployee] Next time list is null!");
					sessionMysql.clear();
					sessionMysql.close();
					return;
				}
				
				int i=0;
				String sqlInsert = "";
				NativeQuery queryInsert = null;
				
				try {
					
					if ( !sessionMysql.getTransaction().isActive() )
						sessionMysql.getTransaction().begin();
					
					for( EmployeeDTO employee : employeeLst ) {
						for( Integer day : timeIdLst ) {
							i++;
							sqlInsert = " insert into employee_time_dimension (time_dimension_id, employee_id) " +
										" values (:timeDimensionId, :employeeId) ";
							queryInsert = sessionMysql.createNativeQuery(sqlInsert);
							queryInsert.setParameter("timeDimensionId", day);
							queryInsert.setParameter("employeeId", employee.getId());
							queryInsert.executeUpdate();
							
							if( i % InterviewConstant.JDBC_BATCH_SIZE == 0 ) {
								sessionMysql.flush();
								sessionMysql.clear();
							}
						}
					}
				}catch(Exception ex) {
					System.out.println("[InitCalendarEmployee].ex: " + ex.toString());
					sessionMysql.getTransaction().rollback();
				} finally {
					if ( sessionMysql.getTransaction().isActive() )
						sessionMysql.getTransaction().commit();
					
					sessionMysql.clear();
					sessionMysql.close();
					//sessionFactoryMysql.close();
					isCloseMySqlConnection = true;
				}
			}
		}
		
		if( !isCloseMySqlConnection ) {
			sessionMysql.clear();
			sessionMysql.close();
			//sessionFactoryMysql.close();
		}
		
		System.exit(0);
	}
}