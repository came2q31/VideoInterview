package com.elcom.data.user.repository;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import com.elcom.data.factory.HibernateFactory;
import com.elcom.data.factory.HibernateSqlServerFactory;
import com.elcom.model.constant.InterviewConstant;
import com.elcom.model.dto.interview.EventDTO;

public class InitDataCardEventEP {
	
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public static void main(String[] args) throws ParseException {
		
		SessionFactory sessionFactorySqlServer = HibernateSqlServerFactory.getInstance();
		
		Session sessionSqlServer = sessionFactorySqlServer.openSession();
		
		String sql = "  select ep.Code as employeeCode, e.DateTimes as evenTimes " + 
					" from tblCardEvent e inner join tblController c on c.id = e.ControllerID " + 
					" inner join tblEmployee ep on ep.CardNumber = e.CardNumber " + 
					" where c.LineID = 2 and e.EventStatus = 'Access Granted'" +
					" and (e.DateTimes between '2019-12-01' and '2020-01-01') order by e.DateTimes ";
		NativeQuery query = sessionSqlServer.createNativeQuery(sql);
		query.addScalar("employeeCode", StandardBasicTypes.STRING);
		query.addScalar("evenTimes", StandardBasicTypes.TIMESTAMP);
		query.setResultTransformer(Transformers.aliasToBean(EventDTO.class));
		
		Object result = query.list();
		//System.exit(0);
		if( result != null ) {
			
			List<EventDTO> lst = (List<EventDTO>) result;
			
			if( lst!=null && !lst.isEmpty() ) {
				
				SessionFactory sessionFactoryMysql = HibernateFactory.getInstance();
				Session sessionMysql = sessionFactoryMysql.openSession();
				
				int i=0;
				String sqlInsert = "";
				NativeQuery queryInsert = null;
				
				try {
					
					if ( !sessionMysql.getTransaction().isActive() )
						sessionMysql.getTransaction().begin();
					
					for( EventDTO item : lst ) { 
						i++;
						sqlInsert = " insert into card_events(employee_code, event_times, time_dimension_id) " +
									" values (:employeeCode, :eventTimes, (select id from time_dimension where db_date = date(:eventTimes))) ";
						queryInsert = sessionMysql.createNativeQuery(sqlInsert);
						queryInsert.setParameter("employeeCode", item.getEmployeeCode());
						queryInsert.setParameter("eventTimes", item.getEvenTimes());
						queryInsert.executeUpdate();
						
						// Batch processing
						if( i % InterviewConstant.JDBC_BATCH_SIZE == 0 ) {
							
							sessionMysql.flush();
							sessionMysql.clear();
						}
					}
					
				}catch(Exception ex) {
					System.out.println("ex:" + ex.toString());
					sessionMysql.getTransaction().rollback();
					
				} finally {
					
					if ( sessionMysql.getTransaction().isActive() )
						sessionMysql.getTransaction().commit();
					
					sessionMysql.clear();
					sessionMysql.close();
					sessionFactoryMysql.close();
				}
			}
		}
		
		sessionSqlServer.clear();
		sessionSqlServer.close();
		sessionFactorySqlServer.close();
		
		System.exit(0);
	}
}