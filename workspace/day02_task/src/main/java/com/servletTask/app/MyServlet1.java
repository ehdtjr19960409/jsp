package com.servletTask.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet1
 */
//@WebServlet("/MyServlet1")
public class MyServlet1 extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
//      response.getWriter().append("Served at: ").append(request.getContextPath());
	   String name = request.getParameter("name");
	   int age = Integer.parseInt(request.getParameter("age"));
	   
	   String gender = request.getParameter("gender");
	   String[] hobbies = request.getParameterValues("hobby"); //여러개일 때는 파라미터 벨류스 사용(배열일경우)
	   
	   
	   System.out.println(name);
	   System.out.println(age);
	   System.out.println(hobbies);
	   System.out.println(gender);
	   
	   request.setAttribute("name", name);
	   request.setAttribute("age", age);
	   request.setAttribute("gender", gender);
	   request.setAttribute("hobbies", hobbies);
	   
	   
	   
	   request.getRequestDispatcher("result.jsp").forward(request, response);
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
