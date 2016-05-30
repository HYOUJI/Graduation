package kr.ac.zebra.dao;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.Member;

//@Component�뒗 �씠 �겢�옒�뒪?���? �옄�룞�쑝濡� ?��?��?��濡� �꽕�젙�빐 以��떎.
@Component("memberDAO")
public class MemberDAO {

	private JdbcTemplate jdbcTemplateObject;

	// @Autowired 寃쎌?�� type�씠 媛숈�? 寃쎌?��?���? 泥섎?���븳�떎.
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	// ---------------------------------------------------------------------------------------------------------------//
	// Querying and returning a single object
	public Member getMember(String id) {

		try {
			String sqlStatement = "select * from membertb where id=?";

			return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { id }, new MemberMapper());
		} catch (Exception e) {

			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� ");
			e.printStackTrace();
			return null;

		}

	}

	// Querying and returning multiple object
	public List<Member> getMembers() {

		String sqlStatement = "select * from membertb";

		return jdbcTemplateObject.query(sqlStatement, new MemberMapper()); // Anonymous
																			// Classes
	}

	public List<Member> getFirstGrade() {

		String sqlStatement = "select * from membertb where level = 1 order by totalReviewCount desc limit 0,3";

		return jdbcTemplateObject.query(sqlStatement, new MemberMapper());

	}

	public List<Member> getSecondGrade() {

		String sqlStatement = "select * from membertb where level = 2 order by totalReviewCount desc limit 0,3";

		return jdbcTemplateObject.query(sqlStatement, new MemberMapper());

	}

	public List<Member> getThirdGrade() {

		String sqlStatement = "select * from membertb where level = 3 order by totalReviewCount desc limit 0,3";

		return jdbcTemplateObject.query(sqlStatement, new MemberMapper());

	}
}
