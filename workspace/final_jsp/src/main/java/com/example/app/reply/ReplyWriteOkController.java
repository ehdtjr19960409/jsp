package com.example.app.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.dto.ReplyDTO;
import com.example.app.reply.dao.ReplyDAO;
import com.google.gson.Gson;

public class ReplyWriteOkController implements Execute{

//	@Override
//	public Result execute(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//	    ReplyDTO replyDTO = new ReplyDTO();
//	    request.setCharacterEncoding("utf-8");
//
//	    try {
//	        replyDTO.setBoardNumber(Integer.valueOf(request.getParameter("boardNumber")));
//	        replyDTO.setMemberNumber(Integer.valueOf(request.getParameter("memberNumber")));
//	        replyDTO.setReplyContent(request.getParameter("replyContent"));
//	        System.out.println("boardNumber: " + request.getParameter("boardNumber"));
//	        System.out.println("memberNumber: " + request.getParameter("memberNumber"));
//	        System.out.println("replyContent: " + request.getParameter("replyContent"));
//
//	        new ReplyDAO().insert(replyDTO);
//
//	        // 성공 응답
//	        response.setStatus(HttpServletResponse.SC_OK);
//	        response.getWriter().write("success");
//	    } catch (Exception e) {
//	        // 에러 처리
//	        e.printStackTrace();
//	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	        response.getWriter().write("fail");
//	    }
//
//	    return null;
//
//	}
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	HttpSession session = request.getSession();

        ReplyDTO replyDTO = new ReplyDTO();
        session.setAttribute("memberNumber", replyDTO.getMemberNumber()); // 로그인한 사용자 ID 저장
    	System.out.println("Session memberNumber: " + session.getAttribute("memberNumber"));

        request.setCharacterEncoding("utf-8");

        // JSON 응답을 위한 준비
        Gson gson = new Gson();
        Map<String, Object> responseMap = new HashMap<>();
        try {
            // 파라미터 유효성 검사
            String boardNumberParam = request.getParameter("boardNumber");
            String memberNumberParam = request.getParameter("memberNumber");
            String replyContent = request.getParameter("replyContent");

            if (boardNumberParam == null || boardNumberParam.isEmpty() ||
                memberNumberParam == null || memberNumberParam.isEmpty() ||
                replyContent == null || replyContent.isEmpty()) {
                throw new IllegalArgumentException("파라미터가 누락되었거나 잘못되었습니다.");
            }

            replyDTO.setBoardNumber(Integer.valueOf(boardNumberParam));
            replyDTO.setMemberNumber(Integer.valueOf(memberNumberParam));
            replyDTO.setReplyContent(replyContent);

            // 댓글 DB 삽입
            new ReplyDAO().insert(replyDTO);

            // 성공 응답
            response.setContentType("application/json; charset=utf-8");
            responseMap.put("status", "success");
            responseMap.put("message", "댓글이 성공적으로 작성되었습니다.");
            response.getWriter().write(gson.toJson(responseMap));
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            response.setContentType("application/json; charset=utf-8");
            responseMap.put("status", "fail");
            responseMap.put("error", e.getMessage());
            response.getWriter().write(gson.toJson(responseMap));
        }

        return null; // JSON 응답만 반환
    }
}
