package com.example.app.board;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.dto.BoardDTO;
import com.example.app.dto.FileDTO;
import com.example.app.file.dao.FileDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		FileDAO fileDAO = new FileDAO(); // +++++++++
		FileDTO fileDTO = new FileDTO(); // +++++++++
		Result result = new Result();

//		현재 어플리케이션의 실제 루트 경로를 문자열로 반환한다.
//		매개변수로 /upload를 넘겨주면 우리의 루트경로에서 /upload를 추가한다.
//		우리 컴퓨터에서 upload의 실제 경로를 멀티파트 객체에게 알려줘야한다.
//		D:를 루트로 삼아서 알려줘야하는데 굉장히 길고, 실제 배포시 대부분의 서버는 리눅스 기반이고
//		리눅스에 루트경로는 windows와 다르므로 직접 명시하는 것은 좋지 않다.
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; // 5MB

		// 맥북 경로 이상하면 실제 upload폴더 찾아가서 커맨드+i눌러서 확인 붙여넣기
		final String UPLOAD_PATH2 = "E:/kdt_jsp/final_jsp/src/main/webapp/upload";
		System.out.println(UPLOAD_PATH);

//		req를 MultipartRequest객체로 만들어서 사용하면 데이터를 정상적으로 가져와 사용이 가능하다
//		MultipartRequest객체를 만들 때 여러 옵션을 설정해야한다.
//		생성자 매개변수 : req객체, 업로드 경로, 최대 크기, 인코딩 방식, 이름 정책(중복되는 파일명이 있을경우 이름을 바꾸는 규칙)
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH2, FILE_SIZE, "utf-8",
				new DefaultFileRenamePolicy());

////		req가 저장한 데이터의 인코딩타입이 multipart라면 일반적인 방법으로 데이터를 가져올 수 없다.
//		System.out.println("ok컨트롤러 들어왔다!!!");
//		System.out.println(request.getParameter("boardTitle"));
//		System.out.println(request.getParameter("boardContent"));
//		System.out.println(request.getSession().getAttribute("memberNumber"));
//		System.out.println(request.getParameter("boardFile"));

//		+++++++++++++++++++++++
		boardDTO.setBoardTitle(multipartRequest.getParameter("boardTitle"));
		boardDTO.setBoardContent(multipartRequest.getParameter("boardContent"));
	     // 세션에서 사용자 번호 가져오기
        Object memberNumber = request.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            throw new IllegalStateException("로그인 상태가 아닙니다.");
        }
        boardDTO.setMemberNumber((Integer) memberNumber);

        // 게시글 추가
        int boardNumber = boardDAO.insertBoard(boardDTO); // 생성된 게시글 번호 반환
        System.out.println("생성된 게시글 번호: " + boardNumber);
        
        
		boardDAO.insertBoard(boardDTO);

//		getFileNames는 input태그의 name속성을 의미한다.
//		반환타입이	Enumeration객체이다.
//		Enumeration은 이터레이터와 비슷하다고 생각하면 된다.
//		이터레이터가 나오기 이전에 사용되던 인터페이스이다.
		Enumeration<String> fileNames = multipartRequest.getFileNames();

//		이터레이터의 hasNext()
		while (fileNames.hasMoreElements()) {
//			이터레이터의 next()
			String name = fileNames.nextElement();
//			여기서 name은 태그의 이름을 의미함

//			파일이 저장될 때의 이름을 알려줌
			String fileSystemName = multipartRequest.getFilesystemName(name);
//			파일의 원본 이름을 알려줌
			String fileOriginalName = multipartRequest.getOriginalFileName(name);

			if (fileSystemName == null) {
				continue;  // 파일이 업로드되지 않은 경우 처리하지 않음
			}
			fileDTO.setFileSystemName(fileSystemName);
			fileDTO.setFileOriginalName(fileOriginalName);
			fileDTO.setBoardNumber(boardNumber);

			System.out.println(fileDTO);
			fileDAO.insert(fileDTO);
		}
//		+++++++++++++++++++++++
		result.setPath("/board/boardListOk.bo");
		result.setRedirect(false);

		return result;
	}

}
