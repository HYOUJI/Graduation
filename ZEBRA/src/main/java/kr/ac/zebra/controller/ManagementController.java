package kr.ac.zebra.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.zebra.dto.AppApply;
import kr.ac.zebra.dto.Product;
import kr.ac.zebra.service.ManagementService;

@Controller
public class ManagementController {

	private ManagementService managementService;

	
	@Autowired // di ����
	public void setProductService(ManagementService managementService) {
		this.managementService = managementService;
		
	}

	@RequestMapping(value = "/Management", method = RequestMethod.GET)
	public String showManagementPage(Model model, HttpSession session, HttpServletRequest request) {

		
		System.out.println("Management �޼��� ����");
		List<AppApply> applyList = managementService.getApplys();
		model.addAttribute("applyListModel", applyList);
		
		
		return "Management";

	}

	@RequestMapping(value = "/Confirm", method = RequestMethod.GET)
	public String showConfirmPage(Model model, HttpSession session, HttpServletRequest request) {

		
		System.out.println("Confirm �޼��� ����");
		
		
		
		return "Confirm";

	}
	
	
	@RequestMapping(value = "/doConfirm", method = RequestMethod.GET)
	public String showDoConfirmPage(Model model, HttpSession session, HttpServletRequest request) {

		
		if(request.getParameter("allow").equals("okay")){
			System.out.println("doConfirm ��ǰ��� if");
			//---------��ǰ ���----------//
			//1. ��ǰ ����ϱ� ���� dto ����
			//2. ��� ����Ʈ ����
			//3. ApplyTable Delete 
			
			Product product = new Product();
			
			product.setBarcode(request.getParameter("barcode"));
			product.setCategory(request.getParameter("category"));
			product.setCompanyName(request.getParameter("companyName"));
			product.setDescription(request.getParameter("description"));
			product.setProductName(request.getParameter("productName"));
			product.setProductUrl(request.getParameter("productUrl"));
			product.setScanCount(1);//�ʱ�ȭ..
			product.setStarPoint(0);
			product.setTotalReviewCount(1);
			
			if(managementService.insertProduct(product)){
				
				System.out.println("��ǰ �ݿ� �Ϸ�!!");
				//2. ��� ����Ʈ ����
				managementService.increasePoint(request.getParameter("id"));
				
				//3. ApplyTable Delete 
				managementService.DeleteApplyTb(request.getParameter("barcode"));
					
				System.out.println("�������Ʈ ���� && ���ö��� ��� ����");
				
			}else{
				
				System.out.println("��ǰ �ݿ� ����");
			}
			
			
				
		
			
			
		}else{
			
			//-------��ȿ���� �ʴ� ��ǰ��--------
			//1. ApplyTable Delete 
			//2. ��� ����Ʈ ����
			
			//1. ApplyTable Delete 
			managementService.DeleteApplyTb(request.getParameter("barcode"));
			
			//2. ��� ����Ʈ ����
			managementService.decreasePoint(request.getParameter("id"));
			
		}
		
	
	
		 
		System.out.println("doConfirm �޼��� ����");
		
		
		
		return "doConfirm";

	}
}
