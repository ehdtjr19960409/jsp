package com.example.app.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.dto.BoardListDTO;
import com.example.app.dto.FileDTO;
import com.example.app.file.dao.FileDAO;

public class BoardReadOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int boardNumber = Integer.valueOf(request.getParameter("boardNumber"));
		BoardDAO boardDAO = new BoardDAO();
		BoardListDTO boardListDTO = boardDAO.select(boardNumber);
		Result result = new Result();
		FileDAO fileDAO = new FileDAO();
		
		List<FileDTO> files = fileDAO.select(boardNumber);
		boardListDTO.setFiles(files);
		
		boardDAO.updateReadCount(boardNumber); // 조회수 증가
		request.setAttribute("board", boardListDTO); 
		
//		request.getRequestDispatcher("/app/board/boardRead.jsp").forward(request, response);		return null;
	
		result.setPath("/app/board/boardRead.jsp");
		result.setRedirect(false);
		
		return result;
	}

}
