package kr.ac.zebra.dao;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.Enterprise;
import kr.ac.zebra.dto.Member;


	//@Component�뒗 �씠 �겢�옒�뒪?���? �옄�룞�쑝濡� ?��?��?��濡� �꽕�젙�빐 以��떎.
	@Component("enterpriseDAO")
	public class EnterpriseDAO {

		private JdbcTemplate jdbcTemplateObject;

		// @Autowired 寃쎌?�� type�씠 媛숈�? 寃쎌?��?���? 泥섎?���븳�떎.
		@Autowired
		public void setDataSource(DataSource dataSource) {
			this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		}

		public int getRowCount() {
			String sqlStatement = "select count(*) from member";
			return jdbcTemplateObject.queryForObject(sqlStatement, Integer.class);// �븯�굹�쓽
																					// �삤?��?��?���듃

		}
	//--------------------------------------���� ����-------------------------------------------------------------------------//
		// Querying and returning a single object
		public Enterprise getEnterprise(String email) {
			
		
			try {
				String sqlStatement = "select * from enterprisetb where email=?";

				return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { email }, new EnterpriseMapper());
			}catch (Exception e) {
			    
				System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� ");
				e.printStackTrace();
				return null;
				
			}
		

		}

		// Querying and returning multiple object
		public List<Enterprise> getEnterprises() {

			String sqlStatement = "select * from enterprisetb";

			return jdbcTemplateObject.query(sqlStatement, new EnterpriseMapper()); // Anonymous
																				// Classes

		}

		public boolean insert(Enterprise enterprise){
			
			 String companyName = enterprise.getCompanyName();
			 String email = enterprise.getEmail();
			 String password = enterprise.getPassword();
			 
			 
				
					
			String sqlStatement="insert into enterprisetb (companyName, email, password) values (?,?,?)";
			return (jdbcTemplateObject.update(sqlStatement, new Object[]{companyName, email, password})==1);
		}

		
		public boolean update(Enterprise enterprise){
			
			
			 String companyName = enterprise.getCompanyName();
			 String email = enterprise.getEmail();
			 String password = enterprise.getPassword();
			 
			 
			String sqlStatement="update enterprisetb set companyName=?, email=?, password=? where email=?";
			return (jdbcTemplateObject.update(sqlStatement, new Object[]{companyName, email, password, email})==1);
		}
		
		public boolean delete (int email) {
			
			String sqlstatement="delete from enterprisetb where email=?";
			return(jdbcTemplateObject.update(sqlstatement,new Object[]{email})==1);
		}
		
		

		
	}

	
	
	
	
	
