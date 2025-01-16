package com.example.app.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.example.app.dto.MemberDTO;
import com.mybatis.config.MyBatisConfig;

/**
 * Servlet implementation class MemberFrontController
 */
//@WebServlet("/MemberFrontController")
public class MemberFrontController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public MemberFrontController() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
	   
      doProcess(request,response);
	   
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
	   
	   request.setCharacterEncoding("UTF-8"); //한글깨짐 방지
	   doProcess(request,response);
	   
   }

   //get과 post를 구분하지 않고 사용할 것이다
   protected void doProcess(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      System.out.println("member 서블릿 실행");

      // request.getContextPath() URL 루트경로를 의미
      System.out.println(request.getContextPath());

      // request.getRequestURI 현재 요청의 URI를 의미
      System.out.println(request.getRequestURI());

      
      //변수에 저장 후 분기처리로 사용
      //요청url이 뭔지 구분하기
      //우리가 필요한 것은 전체 URI에서 ContextPath를 제외시킨 부분이다
      String target = request.getRequestURI().substring(request.getContextPath().length());
      System.out.println(target);
      
      switch(target) {
      	
      	case "/member/join.me" :
      			System.out.println("join!!");
      			request.getRequestDispatcher("/app/member/join.jsp").forward(request, response);
      			//회원가입 버튼을 눌렀을 때 회원가입 페이지로 이동
      			break;
      
      	case "/member/joinOk.me":
      			System.out.println("joinOk!!");
      			
      			//sqlSession을 이용한다
      			MemberDTO memberDTO = new MemberDTO();
      			System.out.println(memberDTO);
      			memberDTO.setMemberId(request.getParameter("memberId"));
      			memberDTO.setMemberPassword(request.getParameter("memberPassword"));
      			memberDTO.setMemberName(request.getParameter("memberName"));
      			
      			//valueOf() 문자열을 Integer 타입으로 바꿔준다.
      			//parseInt() 와의 차이는 parseInt()는 문자열이 숫자가 아닐 경우 numberFormatException이 발생하지만 valueOf()는 null을 반환한다(즉, 예외발생안함)
      			memberDTO.setMemberAge(Integer.valueOf(request.getParameter("memberAge")));
      			
      			memberDTO.setMemberGender(request.getParameter("memberGender"));
      			
      			SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
      			sqlSession.insert("member.join", memberDTO);
      			response.sendRedirect(request.getContextPath());
      			break;
      			//Ok가 필요한 이유는 회원가입 페이지로 단순히 이동하는 것과 회원가입을 처리하는 URL을 나누기 위함이다
      			
      			
      			
      			
      	case "/member/login.me" :
      			System.out.println("login!!");
      			request.getRequestDispatcher("/app/member/login.jsp").forward(request, response);
      			break;
      	
      	case "/member/loginOk.me" : 
      			System.out.println("loginOk!!");
      			break;
      }
      
      
   }

}
