package com.servletTask.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet2
 */
//@WebServlet("/MyServlet2")
public class MyServlet2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     // TODO Auto-generated method stub
     //  doGet(request, response);
	   
	  String userId = request.getParameter("userId");
	  String userPw = request.getParameter("userPw");
	  System.out.println(userId);
	  System.out.println(userPw);
	  
	  request.setAttribute("userId", userId);
	  request.setAttribute("userPw", userPw);
	  
	  
	  //forward 방식으로 loginResult.jsp
//	  request.getRequestDispatcher("loginResult.jsp").forward(request, response);
	  
	  //redirect 방식으로 loginResult.jsp
	  response.sendRedirect("result.jsp");
	  
	  
   }

}
