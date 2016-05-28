package kr.ac.zebra.dao;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.Member;

//@Component�뒗 �씠 �겢�옒�뒪?���? �옄�룞�쑝濡� ?��?��?��濡� �꽕�젙�빐 以��떎.
@Component("appMemberDAO")
public class AppMemberDAO {

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
			
			return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { id }, new AppMemberMapper());
		} catch (Exception e) {

			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� ");
			e.printStackTrace();
			return null;

		}

	}

	// Querying and returning multiple object
	public List<Member> getMembers() {

		String sqlStatement = "select * from membertb";

		return jdbcTemplateObject.query(sqlStatement, new AppMemberMapper()); // Anonymous
																			// Classes
	}

	public List<Member> getFirstGrade() {

		String sqlStatement = "select * from membertb where level = 1 order by totalReviewCount desc limit 0,3";

		return jdbcTemplateObject.query(sqlStatement, new AppMemberMapper());

	}

	public List<Member> getSecondGrade() {

		String sqlStatement = "select * from membertb where level = 2 order by totalReviewCount desc limit 0,3";

		return jdbcTemplateObject.query(sqlStatement, new AppMemberMapper());

	}

	public List<Member> getThirdGrade() {

		String sqlStatement = "select * from membertb where level = 3 order by totalReviewCount desc limit 0,3";

		return jdbcTemplateObject.query(sqlStatement, new AppMemberMapper());

	}
}

/*
 * public int getRowCount() { String sqlStatement =
 * "select count(*) from member"; return
 * jdbcTemplateObject.queryForObject(sqlStatement, Integer.class);// �븯�굹�쓽
 * // �삤?��?��?���듃
 * 
 * }
 * 
 * //
 * -----------------------------------------------------------------------------
 * ----------------------------------// // Querying and returning a single
 * object public Member getMember(String id) {
 * 
 * try { String sqlStatement = "select * from membertb where id=?";
 * 
 * return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { id },
 * new MemberMapper()); } catch (Exception e) {
 * 
 * System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� "); e.printStackTrace(); return null;
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * public boolean insert(Member member) {
 * 
 * String id = member.getId(); String password = member.getPassword(); String
 * name = member.getName(); String memberUrl = member.getMemberUrl(); String
 * lastReviewDate = member.getLastReviewDate(); String phoneNumber =
 * member.getPhoneNumber(); int point = member.getPoint(); int level =
 * member.getLevel(); int reviewCount = member.getReviewCount(); int
 * totalReivewCount = member.getTotalReivewCount();
 * 
 * String sqlStatement =
 * "insert into membertb (id, password, name, memberUrl, lastReviewDate, phoneNumber, point, level, reviewCount, totalReivewCount) values (?,?,?,?,?,?,?,?,?,?)"
 * ; return (jdbcTemplateObject.update(sqlStatement, new Object[] { id,
 * password, name, memberUrl, lastReviewDate, phoneNumber, point, level,
 * reviewCount, totalReivewCount }) == 1); }
 * 
 * public boolean update(Member member) {
 * 
 * String id = member.getId(); String password = member.getPassword(); String
 * name = member.getName(); String memberUrl = member.getMemberUrl(); String
 * lastReviewDate = member.getLastReviewDate(); String phoneNumber =
 * member.getPhoneNumber(); int point = member.getPoint(); int level =
 * member.getLevel(); int reviewCount = member.getReviewCount(); int
 * totalReivewCount = member.getTotalReivewCount();
 * 
 * String sqlStatement =
 * "update membertb set id=?, password=?, name=?, memberUrl=?, lastReviewDate=?, phoneNumber=?, point=?, level=?, reviewCount=?, totalReivewCount=? where id=?"
 * ; return (jdbcTemplateObject.update(sqlStatement, new Object[] { id,
 * password, name, memberUrl, lastReviewDate, phoneNumber, point, level,
 * reviewCount, totalReivewCount }) == 1); }
 * 
 * public boolean delete(int id) {
 * 
 * String sqlstatement = "delete from membertb where id=?"; return
 * (jdbcTemplateObject.update(sqlstatement, new Object[] { id }) == 1); }
 * 
 * }
 */