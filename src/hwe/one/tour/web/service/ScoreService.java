package hwe.one.tour.web.service;

import hwe.one.tour.po.Score;

public interface ScoreService {
	
	public void addScore(Score score);
	
	public Score selectScoreByUserAndScenery(int userId, int sceneryId);

}
