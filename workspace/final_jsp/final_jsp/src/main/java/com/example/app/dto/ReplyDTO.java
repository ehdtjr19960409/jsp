package com.example.app.dto;

public class ReplyDTO {
	
//	CREATE TABLE tbl_reply(
//			reply_number NUMBER,
//			reply_content varchar2(500),
//			reply_date DATE DEFAULT current_timestamp,
//			board_number NUMBER,
//			member_number NUMBER,
//			CONSTRAINT pk_reply PRIMARY KEY(reply_number),
//			CONSTRAINT fk_reply_board FOREIGN KEY(board_number) REFERENCES tbl_board(board_number) ON DELETE CASCADE,
//			CONSTRAINT fk_reply_member FOREIGN KEY(member_number) REFERENCES tbl_member(member_number) ON DELETE CASCADE
//		);

	
	private int replyNumber;
	private String replyContent;
	private String replyDate;
	private int boardNumber;
	private int memberNumber;
	
	
	public int getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	@Override
	public String toString() {
		return "ReplyDTO [replyNumber=" + replyNumber + ", replyContent=" + replyContent + ", replyDate=" + replyDate
				+ ", boardNumber=" + boardNumber + ", memberNumber=" + memberNumber + "]";
	}
	
	
}
