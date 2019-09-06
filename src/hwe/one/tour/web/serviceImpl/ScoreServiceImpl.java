package hwe.one.tour.web.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hwe.one.tour.dao.ScoreDao;
import hwe.one.tour.po.Score;
import hwe.one.tour.web.service.ScoreService;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{
	
	@Autowired
	private ScoreDao scoreDao;

	@Override
	public void addScore(Score score) {
		// TODO Auto-generated method stub
		scoreDao.addScore(score);
	}

	@Override
	public Score selectScoreByUserAndScenery(int userId, int sceneryId) {
		// TODO Auto-generated method stub
		
		Score score = null;
		
		score = scoreDao.selectScoreByUserAndScenery(userId, sceneryId);
		
		return score;
	}

}
