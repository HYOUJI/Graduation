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

	public Product getProduct(String barcode) {

		System.out.println("Product Service");

		Product product = productDAO.getProduct(barcode);

		if (product == null) {

			return null;
		} else {

			return product;
		}
	}

	public List<Product> getPopularProducts() {

		return productDAO.getPopularProducts();

	}

	public List<Product> getMostReviewProducts() {

		return productDAO.getMostReviewProducts();

	}

	public List<Product> getMostScanProducts() {

		return productDAO.getMostScanProducts();

	}

}
