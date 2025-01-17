package com.example.app.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.dto.MemberDTO;
import com.example.app.member.dao.MemberDAO;

public class LoginOkController implements Execute {


	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		Result result = new Result();
		
//		memberDTO.setMemberId(request.getParameter("memberId"));
//		memberDTO.setMemberPassword(request.getParameter("memberPassword"));
		
		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");
		memberDTO = memberDAO.login(memberId, memberPassword);
		
		
		//로그인 성공시와 실패 조건
		if(memberDTO != null) {
			//로그인 성공시 세션에 해당 사용자 정보저장
			HttpSession session = request.getSession();
			session.setAttribute("memberDTO", memberDTO);
			
			
			//쿠키에 사용자 Id 저장
			Cookie cookie = new Cookie("memberId", memberId);
			cookie.setMaxAge(60*60*24); // 유효기간 1일 -> 쿠키의 저장되는 기간
			response.addCookie(cookie); // 쿠키에 추가해주는 것
			
			result.setPath(request.getContextPath());
			result.setRedirect(true);
						
		}else {
			//로그인 실패시 로그인페이지로 이동
			result.setPath(request.getContextPath()+"/app/member/login.jsp");
			result.setRedirect(true);
			
		}
				
		return result;
		
	}
	
}