package kr.ac.zebra.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.zebra.dto.Enterprise;
import kr.ac.zebra.dto.Member;
import kr.ac.zebra.service.LoginService;

@Controller
public class LoginController {

	private LoginService loginService;

	@Autowired // di ����
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	public String showDoLoginPage(Model model, HttpSession session, HttpServletRequest request) {

		String temail = request.getParameter("email");
		String tpassword = request.getParameter("password");

		Enterprise enterprise = loginService.checkEnterprise(temail, tpassword);

		if (enterprise == null) {
			System.out.println("�α��� ����");

			return "failLogin";
		} else {
			System.out.println("�α��� ����");

			model.addAttribute("modelLoginMember", enterprise);
			session.setAttribute("sessionLoginMember", enterprise);
			session.setAttribute("logOk", enterprise.getCompanyName());

			return "home";
		}

	}

	@RequestMapping("/logout")
	public String showLogoutPage() {
		// �α׾ƿ��� Ȩ�������� �̵���Ų��.
		return "logout";
	}

}
