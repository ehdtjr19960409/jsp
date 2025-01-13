package com.servlet.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetPostHandler
 */
@WebServlet("/GetPostHandler")
public class GetPostHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("userName"); //name 속성값 불러오기 위해서 index.jsp에서
		System.out.println(name);
		response.setContentType("text/html; charset=UTF-8"); //한글 깨지지 않기 위해서
		response.getWriter().println("<h1>GET 요청 처리 </h1>");
		response.getWriter().println("<p>이름: " + name + "</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String email = request.getParameter("userEmail");
		System.out.println(email);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("<h1>POST 요청 처리 </h1>");
		response.getWriter().println("<p>이메일 : " + email + "</p>");
		
		
		
	}

}
