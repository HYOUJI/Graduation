package kr.ac.zebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.zebra.dao.MemberDAO;
import kr.ac.zebra.dto.Member;

@Service("loginservice") //service�� bean�� ��Ͻ����ش�
public class LoginService {

	private MemberDAO memberDAO;
	
	@Autowired //DI ����
	public void setMemberDAO(MemberDAO memberDAO){	
		this.memberDAO = memberDAO;
	}
	
	

	public Member checkMember(String tid, String tpassword){	

		Member member=memberDAO.getMember(tid);
		
		if(member==null){
			
			return null;
		
		}else{
		
		String dbpassword = member.getPassword();
		
		if(dbpassword.equals(tpassword)){
			
			return member;			
		}
		else{
			
			return null;
		}
	}

	}

}
