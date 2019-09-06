package hwe.one.tour.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hwe.one.tour.dao.SceneryDao;
import hwe.one.tour.po.Scenery;
import hwe.one.tour.web.service.SceneryService;

@Service("sceneryService")
@Transactional
public class SceneryServiceImpl implements SceneryService{
	
	@Autowired
	private SceneryDao sceneryDao;

	@Override
	public List<Scenery> selectSceneryAll() {
		// 获取随机6个景点信息
		List<Scenery> sceneryList = sceneryDao.selectScenerySix();
		
		return sceneryList;
	}

	@Override
	public List<Scenery> selectSceneryByLike(String like) {
		// 根据景点类型查询
		List<Scenery> sceneryByLikeList = sceneryDao.selectSceneryByLike(like);
		
		return sceneryByLikeList;
	}

	@Override
	public Scenery selectSceneryById(String sId) {
		// TODO Auto-generated method stub
		
		Scenery scenery = sceneryDao.selectSceneryById(sId);
		
		return scenery;
	}

	@Override
	public List<Scenery> selectFuzzyNameScenery(String name) {
		// TODO Auto-generated method stub
		
		List<Scenery> sceneryByNameList = sceneryDao.selectFuzzyNameScenery(name);
		
		return sceneryByNameList;
	}

}
