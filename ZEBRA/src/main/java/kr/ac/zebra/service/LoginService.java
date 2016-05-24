package kr.ac.zebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.zebra.dao.EnterpriseDAO;
import kr.ac.zebra.dao.MemberDAO;
import kr.ac.zebra.dto.Enterprise;
import kr.ac.zebra.dto.Member;

@Service("loginservice") // service�� bean�� ��Ͻ����ش�
public class LoginService {

	private EnterpriseDAO enterpriseDAO;
	private MemberDAO memberDAO;

	@Autowired // DI ����
	public void setEnterpriseDAO(EnterpriseDAO enterpriseDAO) {
		this.enterpriseDAO = enterpriseDAO;
	}

	@Autowired // DI ����
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public Enterprise checkEnterprise(String temail, String tpassword) {

		Enterprise enterprise = enterpriseDAO.getEnterprise(temail);

		if (enterprise == null) {

			return null;

		} else {

			String dbpassword = enterprise.getPassword();

			if (dbpassword.equals(tpassword)) {

				return enterprise;
			} else {

				return null;
			}
		}

	}

	public Member checkMember(String id, String password) {

		Member member = memberDAO.getMember(id);

		if (member == null) {

			return null;

		} else {

			String dbpassword = member.getPassword();

			if (dbpassword.equals(password)) {
				System.out.println("2");
				return member;
			} else {

				return null;
			}
		}

	}

}
