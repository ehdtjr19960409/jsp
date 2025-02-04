package com.example.app.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Result;

public class MemberFrontController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(target);
		Result result = new Result();

		switch (target) {
		case "/member/join.me":
			System.out.println("회원가입 페이지 요청");
			request.getRequestDispatcher("/app/member/join.jsp").forward(request, response);
			break;
		case "/member/joinOk.me":
			System.out.println("회원가입 처리 요청");
			result = new JoinOkController().execute(request, response);
			break;
		case "/member/checkIdOk.me":
			System.out.println("아이디 중복 확인 요청");
			result = new CheckIdOkController().execute(request, response);
			break;
		case "/member/login.me":
			System.out.println("로그인 페이지 요청");
			result = new LoginController().execute(request, response);
//			request.getRequestDispatcher("/app/member/login.jsp").forward(request, response);
			break;
		case "/member/loginOk.me":
			System.out.println("로그인 처리 요청");
			result = new LoginOkController().execute(request, response);
			break;
		case "/member/logoutOk.me":
			System.out.println("로그아웃 처리 요청");
			result = new LogoutOkController().execute(request, response);
			break;
		case "/member/sendSMS.me":
			System.out.println("핸드폰 인증 요청");
			result = new JoinSMSController().execute(request, response);
			break;
		case "/member/verifyCode.me":
			System.out.println("인증번호 확인 요청");
			result = new VerifyCodeController().execute(request, response);
			break;
		}

		if (result != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}