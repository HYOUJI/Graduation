package kr.ac.zebra.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.Product;

//@Component�뒗 �씠 �겢�옒�뒪?���? �옄�룞�쑝濡� ?��?��?��濡� �꽕�젙�빐 以��떎.
@Component("ourProductDAO")
public class OurProductDAO {

	private JdbcTemplate jdbcTemplateObject;

	// @Autowired 寃쎌?�� type�씠 媛숈�? 寃쎌?��?���? 泥섎?���븳�떎.
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from producttb";
		return jdbcTemplateObject.queryForObject(sqlStatement, Integer.class);// �븯�굹�쓽
																				// �삤?��?��?���듃

	}

	// --------------------------------------����
	// ����-------------------------------------------------------------------------//

	// ----------------------------------------�ڻ�
	// ��ǰ-----------------------------------------

	// Querying and returning multiple object
	public List<Product> getOurAllProducts(String companyName) {
		try {

			String sqlStatement = "select * from producttb  where companyName = ? order by totalReviewCount DESC LIMIT 0, 10";

			return jdbcTemplateObject.query(sqlStatement, new Object[] { companyName }, new ProductMapper()); // Anonymous

		} catch (Exception e) {

			System.out.println("getAllProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
			e.printStackTrace();
			return null;

		}

	}

	// Querying and returning multiple object
	public List<Product> getOurPopularityProducts(String companyName) {
		try {

			System.out.println("ourProductDAO getOurPopularityProducts ���� ����");
			String sqlStatement = "select * from producttb where ( companyName = ? ) and ( totalReviewCount >= 2 ) order by starPoint DESC";
			return jdbcTemplateObject.query(sqlStatement, new Object[] { companyName }, new ProductMapper()); // Anonymous

		} catch (Exception e) {

			System.out.println("ourProductDAO getOurPopularityProducts ���� ó�� �߻�  ");
			e.printStackTrace();
			return null;

		}
		// Classes

	}

	// Querying and returning multiple object
	public List<Product> getOurMostReviewProducts(String companyName) {
		try {

			System.out.println("ourProductDAO getOurMostReviewProducts ���� ����");
			String sqlStatement = "select * from producttb where companyName = ? and totalReviewCount > 0 order by totalReviewCount DESC";
			return jdbcTemplateObject.query(sqlStatement, new Object[] { companyName }, new ProductMapper()); // Anonymous

		} catch (Exception e) {

			System.out.println("ourProductDAO getOurMostReviewProducts ���� ó�� �߻�");
			e.printStackTrace();
			return null;

		}

	}

	// Querying and returning multiple object
	public List<Product> getOurMostScanProducts(String companyName) {
		try {

			System.out.println("ourProductDAO getOurMostScanProducts ���� ����");
			String sqlStatement = "select * from producttb where companyName = ? order by scanCount DESC";
			return jdbcTemplateObject.query(sqlStatement, new Object[] { companyName }, new ProductMapper()); // Anonymous

		} catch (Exception e) {

			System.out.println("ourProductDAO getOurMostScanProducts ���� ó�� �߻�");
			e.printStackTrace();
			return null;

		}

	}
	
	public List<Integer> getCompanyCount(String companyName) {

	      List<Integer> count = new ArrayList<Integer>();
	      int percentage = 0;

	      String sqlStatMent = "select sum(totalReviewCount) from producttb where companyName = ?";

	      count.add(jdbcTemplateObject.queryForInt(sqlStatMent, new Object[] { companyName }));

	      sqlStatMent = "select sum(scanCount) from producttb where companyName = ?";

	      count.add(jdbcTemplateObject.queryForInt(sqlStatMent, new Object[] { companyName }));

	      percentage = count.get(0) * 100 / count.get(1);
	      count.add(percentage);

	      System.out.println("company=" + count);

	      return count;
	   }

	  

}
