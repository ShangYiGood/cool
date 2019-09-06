package hwe.one.tour.po;

import java.sql.Date;

public class Comment {
	private int userId;
	private int sceneryId;
	private String comment;
	private Date  date;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSceneryId() {
		return sceneryId;
	}
	public void setSceneryId(int sceneryId) {
		this.sceneryId = sceneryId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Comment [userId=" + userId + ", sceneryId=" + sceneryId + ", comment=" + comment + "]";
	}
	
}
