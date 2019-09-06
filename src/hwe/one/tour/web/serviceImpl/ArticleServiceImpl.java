package hwe.one.tour.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hwe.one.tour.dao.ArticleDao;
import hwe.one.tour.po.Article;
import hwe.one.tour.web.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleDao articleDao;
	
	@Override
	public List<Article> selectRandSix() {
		// TODO Auto-generated method stub
		
		List<Article> articleList = articleDao.selectRandSix();
		
		return articleList;
	}

	@Override
	public Article selectArticleById(int id) {
		// TODO Auto-generated method stub
		
		Article article = articleDao.selectArticleById(id);
		
		return article;
	}

	@Override
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
		
		articleDao.addArticle(article);
		
	}

}
