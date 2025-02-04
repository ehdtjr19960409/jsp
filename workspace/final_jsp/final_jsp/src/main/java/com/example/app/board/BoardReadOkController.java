package com.example.app.board;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

		Result result = new Result();
		
		
		//boardNumber가 빈문자열이나 null인 경우
		String boardNumberStr = request.getParameter("boardNumber");
		if(boardNumberStr == null || boardNumberStr.trim().isEmpty()) {
			System.out.println("boardNumber 값이 없습니다.");
			
			result.setPath("/app/board/boardList.jsp"); //게시글 목록 페이지 리다이렉트로 진행
			result.setRedirect(true);
			return result;
			
		}
		
		int boardNumber = Integer.parseInt(boardNumberStr);
		
		BoardDAO boardDAO = new BoardDAO();
		BoardListDTO boardListDTO = boardDAO.select(boardNumber);
		
		FileDAO fileDAO = new FileDAO();
		
		//게시글이 존재하지 않을 경우 처리
		if(boardListDTO == null) {
			System.out.println("존재하지 않는 게시글 입니다. " + boardNumber);
			result.setPath("/app/board/boardList.jsp");
			result.setRedirect(true);
			return result;
		}
		
		List<FileDTO> files = fileDAO.select(boardNumber);
		System.out.println("===파일 확인====");
		System.out.println(files);
		System.out.println("=============");
		boardListDTO.setFiles(files);
		
		//로그인한 사용자 번호 가져오기
		Integer loginMemberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		System.out.println("로그인한 멤버 번호 : " + loginMemberNumber);
		
		//현재 게시글의 작성자 번호 가져오기
		int boardWriterNumber = boardListDTO.getMemberNumber();
		System.out.println("현재 게시글 작성자 번호 : " + boardWriterNumber);
		
		
		//로그인한 사용자가 작성자가 아닐 때만 조회수 증가
		if(!Objects.equals(loginMemberNumber, boardWriterNumber)) {
			
			boardDAO.updateReadCount(boardNumber);
		}
		request.setAttribute("board", boardListDTO);
		result.setPath("/app/board/boardRead.jsp");
		result.setRedirect(false);
		return result;
		
	}

}
