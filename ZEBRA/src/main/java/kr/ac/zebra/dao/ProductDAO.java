package kr.ac.zebra.dao;

import java.util.ArrayList;
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

		try{
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
	
		}catch (Exception e) {
		
		System.out.println("insert ProductDAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return false;
		
	}
	
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
	
	
	
	
	// �α� ��ǰ�� �����´�
	public List<Product> getPopularProducts(String category) {
		try {
			if(category.equals("0")){
				System.out.println("getPopularProducts ��� ��ǰ ������ DAO");
				String sqlStatement = "select * from producttb where totalReviewCount >= 2 order by starPoint DESC LIMIT 0, 9";
				return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
					
			}else{
				System.out.println("getPopularProducts ����ǰ �̿� ������ DAO");
				String sqlStatement = "select * from producttb where totalReviewCount >= 2 and category =" + category +" order by starPoint DESC LIMIT 0, 9";
				return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
			}
						
			
		}catch (Exception e) {
		
		
		System.out.println("getPopularProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	// �ִ� ���並 �����ش�
	public List<Product> getMostReviewProducts(String category) {
		try {
			if(category.equals("0")){
				System.out.println("getMostReviewProducts ��� ��ǰ ������ DAO");
			String sqlStatement = "select * from producttb where totalReviewCount > 0 order by totalReviewCount DESC LIMIT 0, 10";
			return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
		
			}else{
				System.out.println("getMostReviewProducts ����ǰ �̿� ������ DAO");
				String sqlStatement = "select * from producttb where totalReviewCount > 0 and category =" + category +" order by totalReviewCount DESC LIMIT 0, 10";
				return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
				
			}			
		}catch (Exception e) {
		
		
		System.out.println("getMostReviewProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	
	// �ִ� ��ĵ�� �����´�.
	public List<Product> getMostScanProducts(String category) {
		try {
		
			if(category.equals("0")){
			
			System.out.println("getMostScanProducts ��� ��ǰ ������ DAO");
			String sqlStatement = "select * from producttb  order by scanCount DESC LIMIT 0, 10;";

			return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous
			}
			else{
				
			System.out.println("getMostScanProducts ��� ��ǰ �̿� ������ DAO");
			String sqlStatement = "select * from producttb  where  category =" + category +" order by scanCount DESC LIMIT 0, 10;";
			return jdbcTemplateObject.query(sqlStatement, new ProductMapper()); // Anonymous	
				
			}
			
		}catch (Exception e) {
		
		
		System.out.println("getMostScanProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
		e.printStackTrace();
		return null;

	}
																	// Classes

	}
	
	public List<Product> getSearchProduct(String keyword) {
	      try {
	         String sqlStatement = "select * from producttb where productName like '%" + keyword + "%'";

	         return jdbcTemplateObject.query(sqlStatement, new ProductMapper());
	      } catch (Exception e) {
	         System.out.println("getMostScanProducts DAO ���� ó�� �߻� ȹ�� �޼��� ");
	         e.printStackTrace();
	         return null;

	      }
	   }
	
	
	
	 public List<Integer> getProductCount(String barcode) {

	      List<Integer> count = new ArrayList<Integer>();
	      int percentage = 0;

	      String sqlStatMent = "select sum(totalReviewCount) from producttb where barcode = ?";

	      count.add(jdbcTemplateObject.queryForInt(sqlStatMent, new Object[] { barcode }));

	      sqlStatMent = "select sum(scanCount) from producttb where barcode = ?";

	      count.add(jdbcTemplateObject.queryForInt(sqlStatMent, new Object[] { barcode }));

	      percentage = count.get(0) * 100 / count.get(1);
	      count.add(percentage);

	      System.out.println("product=" + count);

	      return count;
	   }
	
	
	

}
