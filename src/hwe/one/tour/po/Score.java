package hwe.one.tour.po;

public class Score {
	private int userId;
	private int sceneryId;
	private int score;
	
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "Score [userId=" + userId + ", sceneryId=" + sceneryId + ", score=" + score + "]";
	}
	
	
}
