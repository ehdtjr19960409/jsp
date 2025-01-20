package com.example.app.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.dto.BoardDTO;
import com.example.app.dto.FileDTO;
import com.example.app.file.dao.FileDAO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class BoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		FileDAO fileDAO = new FileDAO();
		FileDTO fileDTO = new FileDTO();
		Result result = new Result();

		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; // 5MB
		final String UPLOAD_PATH2 = "/Users/thirdk/work_data/new-class/jsp/workspace/finalProject/WebContent/upload/";
		int boardNumber = 0;

		MultipartParser parser = new MultipartParser(request, FILE_SIZE);
		parser.setEncoding("utf-8");
		while (true) {

			Part part = parser.readNextPart();
			if (part == null) {
				break;
			}
			String fileSystemName = null;
			String fileOriginalName = null;

			if (part.isFile()) {
				FilePart filePart = (FilePart) part;
				filePart.setRenamePolicy(new DefaultFileRenamePolicy());
				fileOriginalName = filePart.getFileName();

				System.out.println(fileOriginalName);
				if (fileOriginalName != null) {

					File file = new File(UPLOAD_PATH, fileOriginalName);

					filePart.writeTo(file);
					fileSystemName = filePart.getFileName();

					fileDTO.setFileSystemName(fileSystemName);
					fileDTO.setFileOriginalName(fileOriginalName);
					fileDTO.setBoardNumber(boardNumber);
					fileDAO.insert(fileDTO);
				}

			} else {
				ParamPart paramPart = (ParamPart) part;
				String param = paramPart.getName(); // 파라미터명
				String value = paramPart.getStringValue(); // 값
				System.out.println(param + " : " + value);

				if (param.equals("boardTitle")) {
					boardDTO.setBoardTitle(value);
				} else if (param.equals("boardContent")) {
					boardDTO.setBoardContent(value);
				} else if (param.equals("boardNumber")) { // ++++++++++++
					// jsp에서 hidden으로 추가해서 boardNumber 가져와야함
					boardNumber = Integer.valueOf(value); // ++++
					boardDTO.setBoardNumber(boardNumber); // ++++
				}

				if (boardDTO.getBoardTitle() == null || boardDTO.getBoardContent() == null) {
					continue;
				}

				boardDTO.setMemberNumber((Integer) request.getSession().getAttribute("memberNumber"));
				boardDAO.update(boardDTO); // **************
//				게시글 번호 가져오기
//				boardNumber = boardDAO.getSequence(); ------

				// ============ BoardDeleteOkController에서 코드 가져오기==============
				List<FileDTO> files = fileDAO.select(boardNumber);
//				스트림과 람다식
				files.stream().map(file -> file.getFileSystemName()).map(name -> new File(UPLOAD_PATH, name))
						.filter(tmp -> tmp.exists()).forEach(tmp -> tmp.delete());

				fileDAO.delete(boardNumber);
				// ==================================
			}

//			response.sendRedirect("/board/boardListOk.bo");
			result.setPath("/board/boardListOk.bo");
			result.setRedirect(true);
			return result;
		}
		return result;

	}
}
