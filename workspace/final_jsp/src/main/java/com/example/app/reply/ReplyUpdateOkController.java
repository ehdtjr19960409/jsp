package com.example.app.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.dto.ReplyDTO;
import com.example.app.reply.dao.ReplyDAO;

public class ReplyUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setReplyNumber(Integer.valueOf(request.getParameter("replyNumber")));
		replyDTO.setReplyContent(request.getParameter("replyContent"));
		
		new ReplyDAO().update(replyDTO);
		return null;
	}
}
