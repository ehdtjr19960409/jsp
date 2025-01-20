package com.example.app.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.app.dto.ReplyDTO;
import com.example.app.dto.ReplyListDTO;
import com.mybatis.config.MyBatisConfig;

public class ReplyDAO {
	public SqlSession sqlSession;
	
	public ReplyDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public List<ReplyListDTO> selectAll(int boardNumber){
		return sqlSession.selectList("reply.selectAll", boardNumber);
	}
	
	public void insert(ReplyDTO replyDTO) {
		sqlSession.insert("reply.insert", replyDTO);
	}
	
	public void delete(int replyNumber) {
		sqlSession.delete("reply.delete", replyNumber);
	}
	
	public void update(ReplyDTO replyDTO) {
		sqlSession.update("reply.update", replyDTO);
	}
}
