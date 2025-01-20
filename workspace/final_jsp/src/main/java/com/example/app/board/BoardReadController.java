package com.example.app.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.dto.BoardListDTO;

public class BoardReadController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));

        BoardDAO boardDAO = new BoardDAO();
        BoardListDTO board = boardDAO.selectOne(boardNumber); // 특정 게시글 조회
        request.setAttribute("board", board);

        Result result = new Result();
        result.setPath("/app/board/boardRead.jsp");
        result.setRedirect(false);
        return result;
    }
}

