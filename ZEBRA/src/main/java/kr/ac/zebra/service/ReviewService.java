package kr.ac.zebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.zebra.dao.ReviewDAO;
import kr.ac.zebra.dto.Review;

@Service("ReviewService") // service로 bean에 등록시켜준다
public class ReviewService {

	private ReviewDAO reviewDAO;

	@Autowired // DI 주입
	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}

	public List<Review> getReviews(String barcode) {

		return reviewDAO.getReviews(barcode);

	}

	public Review getReview(String barcode) {

		return reviewDAO.getReview(barcode);

	}

	public List<Review> getReviewsById(String id) {

		return reviewDAO.getReviewsById(id);

	}

	public void setReview(String id, String barcode, String reviewText, double starPoint, String memberUrl,
			String productUrl) {

		if (reviewDAO.isexit(id, barcode) == null) {
			reviewDAO.setReview(id, barcode, reviewText, starPoint, memberUrl, productUrl);
		} else {

			System.out.println(id);
			System.out.println(barcode);
			System.out.println(reviewText);
			System.out.println(starPoint);
			System.out.println(memberUrl);
			System.out.println(productUrl);
			reviewDAO.updateReview(id, barcode, reviewText, starPoint, memberUrl, productUrl);
		}

	}
}
