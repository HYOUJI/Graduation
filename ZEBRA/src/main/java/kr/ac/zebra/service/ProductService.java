package kr.ac.zebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.zebra.dao.MemberDAO;
import kr.ac.zebra.dao.ProductDAO;
import kr.ac.zebra.dto.Member;
import kr.ac.zebra.dto.Product;

@Service("productService") // service�� bean�� ��Ͻ����ش�
public class ProductService {

	private ProductDAO productDAO;
	private MemberDAO memberDAO;
	@Autowired // DI ����
	public void setProductDAO(ProductDAO productDAO,MemberDAO memberDAO) {
		this.productDAO = productDAO;
		this.memberDAO = memberDAO;
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


//-------------------------�ڻ� ��ǰ--------------------------
	
	public List<Product> getHousePopularProducts(String companyName) {
		
		return productDAO.getHousePopularProducts(companyName);
		
	}
	
	
	//---------------------�ȵ���̵�------------------
	public Product getProduct(String barcode) {
		
		return productDAO.getProduct(barcode);
		
	}
	
	//---------------------�ȵ���̵�------------------
	public Member getMember(String id) {
		
		return memberDAO.getMember(id);
		
	}
	
	

	

}
