package com.example.app.dto;

public class FileDTO {
//	-- 파일 테이블 생성
//	CREATE TABLE tbl_file(
//		FILE_SYSTEM_NAME VARCHAR2(300), -- 서버에 저장되는 실제 파일명
//		FILE_ORIGINAL_NAME VARCHAR2(300), -- 원본 파일명
//		BOARD_NUMBER NUMBER, 
//		CONSTRAINT PK_FILE PRIMARY KEY(FILE_SYSTEM_NAME),
//		CONSTRAINT FK_FILE FOREIGN KEY(BOARD_NUMBER) REFERENCES TBL_BOARD(BOARD_NUMBER)
//	);
	
	private String fileSystemName;
	private String fileOriginalName;
	private int boardNumber;
	
	public String getFileSystemName() {
		return fileSystemName;
	}
	public void setFileSystemName(String fileSystemName) {
		this.fileSystemName = fileSystemName;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	
	
	
	
}
