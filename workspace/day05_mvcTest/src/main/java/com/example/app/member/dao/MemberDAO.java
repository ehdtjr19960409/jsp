package com.example.app.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.example.app.dto.MemberDTO;
import com.mybatis.config.MyBatisConfig;

public class MemberDAO {
	public SqlSession sqlsession;

	public MemberDAO() {
		// TODO Auto-generated constructor stub
		sqlsession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public void join(MemberDTO memberDTO) {
		sqlsession.insert("member.join", memberDTO);
	}

	public MemberDTO login(String memberId, String memberPassword) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPassword(memberPassword);
		
		return sqlsession.selectOne("member.login", memberDTO);
		
	}
	
	
}
