package kr.ac.zebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.zebra.dao.ProductDAO;
import kr.ac.zebra.dto.Product;

@Service("productService") // service�� bean�� ��Ͻ����ش�
public class ProductService {

	private ProductDAO productDAO;

	@Autowired // DI ����
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public List<Product> getPopularProducts() {
	
		return productDAO.getPopularProducts();
		
	}

	

}
