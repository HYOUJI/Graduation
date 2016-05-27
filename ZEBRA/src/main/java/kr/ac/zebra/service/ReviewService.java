package kr.ac.zebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.ac.zebra.dao.ProductDAO;
import kr.ac.zebra.dao.ReviewDAO;
import kr.ac.zebra.dto.Product;
import kr.ac.zebra.dto.Review;

@Service("reviewService") // service�� bean�� ��Ͻ����ش�
public class ReviewService {
	

	private ProductDAO productDAO;
	private ReviewDAO reviewDAO;
	@Autowired // DI ����
	public void setProductDAO(ProductDAO productDAO,ReviewDAO reviewDAO) {
		this.productDAO = productDAO;
		this.reviewDAO = reviewDAO;

	}

	public List<Review> getReviews(String barcode) {
	
		return reviewDAO.getReviews(barcode);
		
	}
	


	

}
