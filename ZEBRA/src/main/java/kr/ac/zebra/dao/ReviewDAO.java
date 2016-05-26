package kr.ac.zebra.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.Review;

@Component("ReviewDAO")
public class ReviewDAO {

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<Review> getReviewsById(String id) {
		try {
			String sqlStatement = "select * from reviewtb where id=?";

			return jdbcTemplateObject.query(sqlStatement, new Object[] { id }, new AppReviewMapper());
		} catch (Exception e) {

			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� getReviewsById ");
			e.printStackTrace();

			return null;
		}
	}

	public Review getReview(String barcode) {

		try {
			String sqlStatement = "select * from reviewtb where barcode=?";

			return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { barcode }, new AppReviewMapper());
		} catch (Exception e) {

			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� getReview ");
			e.printStackTrace();

			return null;
		}
	}

	public List<Review> getReviews(String barcode) {

		try {
			String sqlStatement = "select * from reviewtb where barcode= ?";

			return jdbcTemplateObject.query(sqlStatement, new Object[] { barcode }, new AppReviewMapper());
		} catch (Exception e) {

			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� getReviews ");
			e.printStackTrace();

			return null;
		}

	}

	public void setReview(String id, String barcode, String reviewText, double starPoint, String memberUrl,
			String productUrl) {

		try {
			String sqlStatement = "insert into reviewtb (id, barcode, reviewText, starPoint, memberUrl, productUrl) values (?, ?, ?, ?, ?, ?)";

			jdbcTemplateObject.update(sqlStatement,
					new Object[] { id, barcode, reviewText, starPoint, memberUrl, productUrl });
		} catch (Exception e) {
			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� setReview ");
			e.printStackTrace();
		}
	}

	public void updateReview(String id, String barcode, String reviewText, double starPoint, String memberUrl,
			String productUrl) {
		try {
			String sqlStatement = "update reviewtb set reviewText=?, starPoint=? where id=? and barcode=?";

			jdbcTemplateObject.update(sqlStatement, new Object[] { reviewText, starPoint, id, barcode });
		} catch (Exception e) {
			System.out.println("DAO ���� ó�� �߻� ȹ�� �޼��� updateReview ");
			e.printStackTrace();
		}

	}

	public String isexit(String id, String barcode) {

		try {
			String sqlStatement = "select reviewText from reviewtb where id=? and barcode=?";

			Review review = jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { id, barcode },
					new AppReviewMapper());

			return review.getReviewText();
		} catch (Exception e) {
			return null;
		}
	}

}
