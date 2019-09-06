package hwe.one.tour.web.service;

import java.util.List;

import hwe.one.tour.po.Scenery;

public interface SceneryService {

	public List<Scenery> selectSceneryAll();
	
	//根据条件来查景点
	public List<Scenery> selectSceneryByLike(String like);
	
	//根据id查询景点简介
	public Scenery selectSceneryById(String sId);
	
	//根据名字模糊查询
	public List<Scenery> selectFuzzyNameScenery(String name);

}
