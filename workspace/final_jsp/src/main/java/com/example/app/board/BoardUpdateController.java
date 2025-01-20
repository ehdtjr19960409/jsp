package com.example.app.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;

public class BoardUpdateController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int boardNumber = Integer.valueOf(request.getParameter("boardNumber"));
		BoardDAO boardDAO = new BoardDAO();
		
		request.setAttribute("board", boardDAO.select(boardNumber));
		
		request.getRequestDispatcher("/app/board/boardUpdate.jsp").forward(request, response);
		return null;
	}

}
