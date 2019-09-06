package hwe.one.tour.dao;

import java.util.List;

import hwe.one.tour.po.Scenery;

public interface SceneryDao {
	
	public List<Scenery> selectScenerySix();
	
	//根据 景点类型查询
	public List<Scenery> selectSceneryByLike(String like);
	
	//根据id查询景点简介
	public Scenery selectSceneryById(String sId);
	
	//根据景点名字查
	public List<Scenery> selectFuzzyNameScenery(String name);
	
}
