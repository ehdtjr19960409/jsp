package com.example.app.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;

public class DownloadController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileSystemName = request.getParameter("fileSystemName");
		String fileOriginalName = request.getParameter("fileOriginalName");
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
		String uploadPath2 = "/Users/thirdk/work_data/new-class/jsp/workspace/finalProject/WebContent/upload/";
//		파일 다운로드를 구현하려면 파일 입출력 스트림을 활용해야한다.
//		우선 외부파일로 존재하는 데이터를 자바로 읽어 들이고(입력)
//		자바에서 사용자의 브라우저로 전송해야 한다.(출력)
		InputStream in = null;
		OutputStream out = null;

		try {
			// 매개변수 2개를 넘긴다.
			// 경로, 이름 -> 알아서 연결 해준다.
			File file = new File(uploadPath2, fileSystemName);
			// 경로에 실제 존재하는 파일을 파일객체로 자바에 불러온 것
			// 즉, file은 실제 파일의 정보를 가지고 있다.

			// file.length();
			// 여기서 파일의 길이는 파일을 전송하기 위해 byte배열로 변환했을 때
			// byte배열의 길이를 의미한다.
			// 파일의 길이와 동일한 바이트 배열을 만들어 버퍼로 사용한다.
			// byte[] buffer = new byte[(int) file.length()];
			// 또는 정해놓은 크기의 버퍼를 사용한다.
			byte[] buffer = new byte[1024];

			// 클라이언트에게 보내는 응답이 이전과 다르게 파일(이진, 바이트) 데이터이므로
			// 컨텐트 타입을 설정해야한다.
			response.setContentType("application/octet-stream");
			// 그리고 resp의 헤더영역에 파일에 대한 추가 정보를 설정한다.
			response.setHeader("Content-Length", file.length() + "");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileOriginalName);

			in = new FileInputStream(file);
			// outputStream은 브라우저에게 출력해야 하므로 resp객체가 가지고 있는
			// outputStream을 꺼내서 사용한다.
			out = response.getOutputStream();

			// input스트림의 read()를 이용해 buffer배열에 바이트 데이터를 저장한다.
			// 만약 버퍼에서 읽어올 데이터가 없다면 -1을 반환한다.
			int readCnt = 0;
			while ((readCnt = in.read(buffer)) != -1) {
				out.write(buffer, 0, readCnt);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.gc(); // 권장하지 않음
				System.runFinalization(); // 권장하지 않음
			}
		}
		
		return null;
	}
}