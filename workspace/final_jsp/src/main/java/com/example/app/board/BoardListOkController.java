package com.example.app.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.dto.BoardListDTO;

public class BoardListOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
//		BoardDAO boardDAO = new BoardDAO();
//		Result result = new Result();
////		++++++++
////		처음으로 게시판 페이지에 진입하면 페이지에 대한 정보가 없다.
////		그러므로 temp에는 null이 들어가게 된다.
//		String temp = request.getParameter("page"); // 페이지를 받는다고 가정한다.
//
////		null인 경우는 게시판에 처음 이동하는 것이므로 1페이지를 띄워주면 된다.
//		int page = temp == null ? 1 : Integer.valueOf(temp);
//
////		한 페이지에 몇 개의 게시물? 10개
//		int rowCount = 10;
////		페이지 버튼은 최대 몇 개? 5개
//		int pageCount = 5;
//
////		limit 0, 10 → 1페이지
////		limit 10, 10 → 2페이지
////		limit 20, 10 → 2페이지
//	    int startRow = (page - 1) * rowCount + 1;
//	    int endRow = startRow + rowCount - 1;
//
//		Map<String, Integer> pageMap = new HashMap<>();
//	    pageMap.put("startRow", startRow);
//	    pageMap.put("endRow", endRow);
//
////		++++++++
////	    Map<String, Integer> pageMap = new HashMap<>();
//	    pageMap.put("startRow", 1); // 테스트 값
//	    pageMap.put("endRow", 10);  // 테스트 값
////	    List<BoardListDTO> boardList = boardDAO.selectAll(pageMap);
////	    request.setAttribute("boardList", boardList);
//
//	    // 데이터 가져오기
//	    List<BoardListDTO> boardList = boardDAO.selectAll(pageMap);
//	    request.setAttribute("boardList", boardList);
//	    
//	    //페이징 처리 정보
//		int total = boardDAO.getTotal(); //+++++++++
////		Math.ceil()은 올림처리 이다. (1.1 -> 2)
//		int endPage = (int)(Math.ceil(page / (double)pageCount) * pageCount);
////		endPage는 페이지 세트 당 마지막 번호를 의미한다.
////		마지막 번호는 Math.ceil(현재 페이지/페이지 세트 당 페이지 수) * 페이지 세트 당 페이지 수 이다.
////		1페이지 기준으로 Math.ceil(1/5) -> 올림하면 1 * 5 -> 5
//		
//		int startPage = endPage - (pageCount -1);
////		startPage는 페이지 세트 당 첫 번째 번호를 의미한다.
////		
//		
//		int realEndPage = (int)Math.ceil(total / (double)rowCount);
////		realEndPage는 전체 페이지 중 가장 마지막 번호를 의미한다.
////		이 공식에는 총 게시물 수(total)가 필요하다. -> DB조회를 위한 쿼리 만들어야함
//		
//		boolean prev = startPage > 1;
////		prev는 이전 버튼 표시 여부를 나타낸다. 현재 페이지 세트의 시작 번호가 1보다 커야 표시가 된다.
//		
//		endPage = endPage > realEndPage ? realEndPage : endPage;
////		첫 번째 페이지 세트가 1~5
////		두 번째 페이지 세트가 6~10 이여도
////		realEndPage가 7이라면 두 번째 페이지 세트의 마지막 번호는 7이어야 한다. 
//		
//		boolean next = endPage != realEndPage;
////		next는 다음 버튼 표시 여부를 나타낸다. 
////		현재 페이지 세트의 마지막 페이지가 realEndPage와 같은 숫자라면 다음 페이지세는 존재하지 않는다.  
////		++++++++
////		request.setAttribute("boardList", boardDAO.selectAll(pageMap)); // ********
//		request.setAttribute("page", page);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("prev", prev);
//		request.setAttribute("next", next);
//		
//		System.out.println("pageMap: " + pageMap);
//		
//		System.out.println("boardList: " + boardList);
//		request.setAttribute("boardList", boardDAO.selectAll(pageMap));
//
////		request.getRequestDispatcher("/app/board/boardList.jsp").forward(request, response);
//		result.setPath("/app/board/boardList.jsp");
//		result.setRedirect(false);
//		return result;
//
//	}   
		BoardDAO boardDAO = new BoardDAO();
	    Result result = new Result();

	    String temp = request.getParameter("page"); // 페이지 번호 파라미터
	    int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호 기본값: 1

	    int rowCount = 10; // 한 페이지당 게시글 수
	    int pageCount = 5; // 페이지 버튼 수

	    int startRow = (page - 1) * rowCount + 1;
	    int endRow = startRow + rowCount - 1;

	    Map<String, Integer> pageMap = new HashMap<>();
	    pageMap.put("startRow", startRow);
	    pageMap.put("endRow", endRow);

	    // 게시글 목록 조회
	    List<BoardListDTO> boardList = boardDAO.selectAll(pageMap);
	    request.setAttribute("boardList", boardList);

	    // 페이징 정보 설정
	    int total = boardDAO.getTotal(); // 총 게시글 수
	    int realEndPage = (int) Math.ceil(total / (double) rowCount); // 실제 마지막 페이지
	    int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
	    int startPage = endPage - (pageCount - 1);

	    endPage = Math.min(endPage, realEndPage); // 마지막 페이지 조정

	    boolean prev = startPage > 1;
	    boolean next = endPage < realEndPage;

	    request.setAttribute("page", page);
	    request.setAttribute("startPage", startPage);
	    request.setAttribute("endPage", endPage);
	    request.setAttribute("prev", prev);
	    request.setAttribute("next", next);

	    // 디버깅 출력
	    System.out.println("pageMap: " + pageMap);
	    System.out.println("boardList: " + boardList);
	    System.out.println("startPage: " + startPage + ", endPage: " + endPage + ", prev: " + prev + ", next: " + next);

	    result.setPath("/app/board/boardList.jsp");
	    result.setRedirect(false);
	    
	    
	    System.out.println("Start Row: " + startRow);
	    System.out.println("End Row: " + endRow);

	    List<BoardListDTO> boardList1 = boardDAO.selectAll(pageMap);
	    System.out.println("boardList from DAO: " + boardList1);

	    request.setAttribute("boardList", boardList);
	    System.out.println("Request Attribute Set: " + request.getAttribute("boardList"));
	
	    return result;
	}

}
