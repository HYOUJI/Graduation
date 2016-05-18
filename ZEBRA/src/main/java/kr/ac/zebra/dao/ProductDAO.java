package kr.ac.zebra.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.Product;


//@Component�뒗 �씠 �겢�옒�뒪?���? �옄�룞�쑝濡� ?��?��?��濡� �꽕�젙�빐 以��떎.
@Component("productDAO")
public class ProductDAO {

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

	// --------------------------------------���� ����-------------------------------------------------------------------------//
	// Querying and returning a single object
	public Product getProduct(String barcode) {

		try {
			String sqlStatement = "select * from producttb where barcode=?";

			return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { barcode }, new ProductMapper());
		} catch (Exception e) {

			System.out.println("getProduct DAO ���� ó�� �߻� ȹ�� �޼��� ");
			e.printStackTrace();
			return null;

		}

	}

	// Querying and returning multiple object
	public List<Product> getProducts() {

		String sqlStatement = "select * from producttb";

		return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
																				// Classes

	}

	public boolean insert(Product product) {

		
		 String barcode = product.getBarcode();
		 String productName = product.getCompanyName();
		 String description = product.getDescription();
		 String category = product.getCategory();
		 String productUrl = product.getProductUrl();
		 String companyName = product.getCompanyName();
		 int scanCount = product.getScanCount();
		 int totalReviewCount = product.getTotalReviewCount();
		 double starPoint = product.getStarPoint();
		
	
		String sqlStatement = "insert into producttb (barcode, productName, description, category, productUrl, companyName, scanCount, totalReviewCount, starPoint) values (?,?,?,?,?,?,?,?,?)";
		return (jdbcTemplateObject.update(sqlStatement, new Object[] { barcode, productName, description, category, productUrl, companyName, scanCount, totalReviewCount, starPoint }) == 1);
	
	
	}

	public boolean update(Product product) {

		 String barcode = product.getBarcode();
		 String productName = product.getCompanyName();
		 String description = product.getDescription();
		 String category = product.getCategory();
		 String productUrl = product.getProductUrl();
		 String companyName = product.getCompanyName();
		 int scanCount = product.getScanCount();
		 int totalReviewCount = product.getTotalReviewCount();
		 double starPoint = product.getStarPoint();

		String sqlStatement = "update producttb set barcode=?, productName=?, description=?, category=?, productUrl=?, companyName=?, scanCount=?, totalReviewCount=?, starPoint=? where barcode=?";
		return (jdbcTemplateObject.update(sqlStatement, new Object[] { barcode, productName, description, category, productUrl, companyName, scanCount, totalReviewCount, starPoint }) == 1);
	}

	public boolean delete(int barcode) {

		String sqlstatement = "delete from producttb where barcode=?";
		return (jdbcTemplateObject.update(sqlstatement, new Object[] { barcode }) == 1);
	}
	
	
	
	
	// Querying and returning multiple object
	public List<Product> getPopularProducts() {
		try {
		
			String sqlStatement = "select * from producttb where totalReviewCount >= 2 order by starPoint DESC LIMIT 0, 9";

			return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
			
		}catch (Exception e) {
		
		
		System.out.println("getPopularProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	// Querying and returning multiple object
	public List<Product> getMostReviewProducts() {
		try {
		
			String sqlStatement = "select * from producttb where totalReviewCount > 0 order by totalReviewCount DESC LIMIT 0, 10";

			return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
			
		}catch (Exception e) {
		
		
		System.out.println("getMostReviewProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	
	// Querying and returning multiple object
	public List<Product> getMostScanProducts() {
		try {
		
			String sqlStatement = "select * from producttb  order by scanCount DESC LIMIT 0, 10;";

			return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
			
		}catch (Exception e) {
		
		
		System.out.println("getMostScanProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	
	
	//----------------------------------------�ڻ� ��ǰ-----------------------------------------
	

	// Querying and returning multiple object
	public List<Product> getHousePopularProducts(String companyName) {
		try {
		
			String sqlStatement = "select * from producttb  where (companyName = ?) and (totalReviewCount > 2) order by starPoint DESC LIMIT 0, 10";

			return jdbcTemplateObject.query(sqlStatement,new Object[] { companyName }, new ProductMapper()); // Anonymous
			
		}catch (Exception e) {
		
		
		System.out.println("getHousePopularProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	
	
	

}
