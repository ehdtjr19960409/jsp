package com.example.app.reply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Result;

/**
 * Servlet implementation class ReplyFrontController
 */
@WebServlet("/ReplyFrontController")
public class ReplyFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyFrontController() {
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
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(target);
		Result result = new Result();

		switch (target) {
		case "/reply/replyWriteOk.re" :
			System.out.println("댓글 작성 컨트롤러 진입");
			result = new ReplyWriteOkController().execute(request, response);
			break;
			
		case "/reply/replyListOk.re":
			System.out.println("댓글 목록 컨트롤러 진입");
			result = new ReplyListOkController().execute(request,response);
			break;
			
			
		}
	}

}
