package com.example.app.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.example.app.dto.BoardDTO;
import com.example.app.dto.BoardListDTO;
import com.mybatis.config.MyBatisConfig;

public class BoardDAO {
	public SqlSession sqlSession;

	public BoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 모든 게시글 가져오기
	public List<BoardListDTO> selectAll(Map<String, Integer> pageMap) {
//	    System.out.println("DAO selectAll 실행");
//	    System.out.println("pageMap: " + pageMap);
		List<BoardListDTO> list = sqlSession.selectList("board.selectAll", pageMap);
//	    System.out.println("조회 결과: " + list);
		return list;
	}

	// 게시글 총 개수 가져오기
	public int getTotal() {
		return sqlSession.selectOne("board.getTotal");
	}

	/*
	 * public void insert(BoardDTO boardDTO) { sqlSession.insert("board.insert",
	 * boardDTO); }
	 */

	// 게시글 추가 후 자동 생성된 boardNumber 반환
	public int insertBoard(BoardDTO boardDTO) {
		// INSERT 실행
		sqlSession.insert("board.insert", boardDTO);

		// CURRVAL을 사용하여 마지막 생성된 시퀀스 값 가져오기
		int boardNumber = sqlSession.selectOne("board.getCurrentBoardNumber");
		boardDTO.setBoardNumber(boardNumber);
		System.out.println("마지막 생성된 값 : " + boardNumber);
		return boardNumber;
	}

	public BoardListDTO select(int boardNumber) {
		return sqlSession.selectOne("board.select", boardNumber);
	}

	public void updateReadCount(int boardNumber) {
		sqlSession.update("board.updateReadCount", boardNumber);
	}
	
	public void delete(int boardNumber) {
		sqlSession.delete("board.delete", boardNumber);
	}
	
	public void update(BoardDTO boardDTO) {
		sqlSession.update("board.update", boardDTO);
	}
	
	public BoardListDTO selectOne(int boardNumber) {
	    return sqlSession.selectOne("board.selectOne", boardNumber);
	}


}