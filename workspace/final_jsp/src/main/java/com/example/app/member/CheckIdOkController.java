package com.example.app.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.member.dao.MemberDAO;

public class CheckIdOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException {

		MemberDAO memberDAO = new MemberDAO();
		Result result = new Result();


        // 클라이언트에서 전달받은 memberId
        String memberId = request.getParameter("memberId");
        boolean isAvailable = memberDAO.checkId(memberId);

        // JSON 형식 응답 설정
        response.setContentType("application/json");
        //json 형식으로 변환
        response.setCharacterEncoding("UTF-8");

        
        // JSON 응답 작성
        //try 사용 이유 : 응답이 없을 수 있으므로 try사용
        try (PrintWriter out = response.getWriter()) {
        	//printwriter 파일 입출력
            out.print("{\"available\": " + isAvailable + "}");
            out.flush();
            //flush 모든 출력을 한번에 보내고 버퍼 비우는 형식으로 생각하면됌
            //스캐너에서 .close를 사용하면 flush도 같이 사용하게 됌
        }

        // Result 객체 반환 (JSON 응답 처리 후 페이지 이동 없음)
        //18번째 줄에 RESULT로 반환타입이 고정되어있으므로 사용
        
        result.setPath(null); // 페이지 이동이 없음을 명시
        result.setRedirect(false); // 리다이렉트 설정 (false로 유지)
        return result;
	}

}