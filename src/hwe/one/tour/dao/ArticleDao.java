package hwe.one.tour.dao;

import java.util.List;

import hwe.one.tour.po.Article;

public interface ArticleDao {
	
	//随机查询6条数据
	public List<Article> selectRandSix();
	
	//根据id查询
	public Article selectArticleById(int id);
	
	//添加文章
	public void addArticle(Article article);

}
