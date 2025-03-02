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

	//모든 게시글 가져오기
	public List<BoardListDTO> selectAll(Map<String, Integer> pageMap){
		System.out.println("모든 게시글 조회하기 - selectAll 메소드 실행" + pageMap);
		List<BoardListDTO> list = sqlSession.selectList("board.selectAll", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
	
	//게시글 총 개수 가져오기
	public int getTotal() {
		return sqlSession.selectOne("board.getTotal");
	}
	
	//게시글 추가 후 자동으로 생성된 boardNumber 반환 - 파일테이블에서도 써야되기때문에 사용
	public int insertBoard(BoardDTO boardDTO) {
		int insert = sqlSession.insert("board.insert", boardDTO);
		System.out.println(boardDTO + "출력====");
		System.out.println(boardDTO.getBoardContent() + "출력====");
		System.out.println(boardDTO.getBoardTitle() + "출력====");
		System.out.println("=== 게시글 작성 DAO ===");
		System.out.println("insert : " + insert);
		System.out.println("==========");
		System.out.println("생성된 boardNumber : " + boardDTO.getBoardNumber());
		return boardDTO.getBoardNumber();
	}
	
	//게시글 상세 페이지 조회 메소드
	public BoardListDTO select(int boardNumber) {
		System.out.println("+++++ 디테일 페이지 쿼리 +++++");
		return sqlSession.selectOne("board.select", boardNumber);
	
	}
	
	//조회수 증가 메소드
	public void updateReadCount(int boardNumber) {
		System.out.println("조회수 업데이트 실행" + boardNumber);
		int result = sqlSession.update("board.updateReadCount", boardNumber);
		System.out.println("조회수 업데이트 결과 : " + result);
		
	}
	
	//게시글 삭제 메소드
	public void delete(int boardNumber) {
		System.out.println("게시글 삭제 실행" + boardNumber);
		sqlSession.delete("board.delete", boardNumber);
		System.out.println("게시글 삭제 쿼리 실행 완료");
	}
	
	//게시글 수정 메소드
	public void update(BoardDTO boardDTO) {
		System.out.println("게시글 수정 실행"+boardDTO);
		sqlSession.update("board.update", boardDTO);
		System.out.println("게시글 수정 쿼리 실행 완료");
	}
	
}