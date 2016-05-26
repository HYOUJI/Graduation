package kr.ac.zebra.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import kr.ac.zebra.dto.AppApply;
import kr.ac.zebra.dto.Product;

//@Component�뒗 �씠 �겢�옒�뒪?���? �옄�룞�쑝濡� ?��?��?��濡� �꽕�젙�빐 以��떎.
@Component("applyDAO")
public class AppApplyDAO {

	private JdbcTemplate jdbcTemplateObject;

	// @Autowired 寃쎌?�� type�씠 媛숈�? 寃쎌?��?���? 泥섎?���븳�떎.
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public AppApply getApply(String barcode) {

		try {
			String sqlStatement = "select * from producttb where barcode=" + barcode;

			AppApply apply = jdbcTemplateObject.queryForObject(sqlStatement, new AppApplyMapper());

			if (apply.getBarcode().isEmpty()) {

				return null;
			} else {

				return apply;
			}

		} catch (Exception e) {

			System.out.println("getApply DAO ���� ó�� �߻� ȹ�� �޼��� ");
			e.printStackTrace();
			return null;

		}

	}

	// Querying and returning multiple object
	public List<AppApply> getApplys() {

		String sqlStatement = "select * from producttb";

		return jdbcTemplateObject.query(sqlStatement, new AppApplyMapper());

	}

	public boolean insertProduct(String id, String barcode, String productName) {

		String sqlStatement = "insert into applytb (barcode, id, productName) values (?,?,?)";
		return (jdbcTemplateObject.update(sqlStatement, new Object[] { barcode, id, productName }) == 1);

	}

}
