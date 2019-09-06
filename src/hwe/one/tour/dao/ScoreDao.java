package hwe.one.tour.dao;

import hwe.one.tour.po.Score;

public interface ScoreDao {
	
	public void addScore(Score score);
	
	public Score selectScoreByUserAndScenery(int userId, int sceneryId);
	
}
