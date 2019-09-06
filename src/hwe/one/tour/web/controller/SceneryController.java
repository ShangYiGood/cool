package hwe.one.tour.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hwe.one.tour.po.Article;
import hwe.one.tour.po.Comment;
import hwe.one.tour.po.Scenery;
import hwe.one.tour.po.User;
import hwe.one.tour.web.service.ArticleService;
import hwe.one.tour.web.service.CommentService;
import hwe.one.tour.web.service.SceneryService;
import hwe.one.tour.web.service.UserService;
import net.sf.json.JSONArray;

@Controller
public class SceneryController {
	
	@Autowired
	private SceneryService sceneryService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userServcie;
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 查询首页6个景点信息
	 */
	@RequestMapping(value="/scenery/autoScenery.action")
	public String autoScenery(Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("USER_SESSION");
		List<Scenery> sceneryList;
		List<Article> articleList;
		
		/*
		 * 先判断用户是否登录,如果登录
		 */
		sceneryList = sceneryService.selectSceneryAll();
		articleList = articleService.selectRandSix();
//		if(user != null) {
//			sceneryList = sceneryService.selectSceneryByLike(user.getLike());
//		}else {
//			sceneryList = sceneryService.selectSceneryAll();
//		}
		
		if(sceneryList != null) {
			model.addAttribute("sceneryList", sceneryList);
		}
		
		if(articleList != null) {
			model.addAttribute("articleList", articleList);
		}
	
		
		return "index";
	}
	
	@RequestMapping(value="/scenery/toScenerySynopsis.action")
	public String toScenerySynopsis(String sId, Model model) {
		
		Scenery scenery = sceneryService.selectSceneryById(sId);
		
		model.addAttribute("scenery", scenery);
		
		
		return "details";
	}
	
	@RequestMapping(value="/scenery/fuzzyQuerryResult.action", method=RequestMethod.POST)
	public String fuzzyQuerryResult(String likeOrname, Model model) {
		
		/**
		 * 简单模糊查询
		 */
	
		
		try {
			byte likeOrnameByte[] =  likeOrname.getBytes("ISO8859-1");
			
			likeOrname = new String(likeOrnameByte, "utf-8");
			
			System.out.println("进入模糊查询。。。输出参数..." + likeOrname);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String types = "山岳、湖泊、河川、瀑布、海岛海滨、森林、岩溶、火山、人文风景";
		String like = likeOrname;
		List<Scenery> fuzzySceneryList = null;
		
		if(types.indexOf(likeOrname) >= 0) {
			
			int b = types.indexOf(likeOrname);
			
			like = types.substring(b, (b + 2));
			
			System.out.println("搜索词条为：" + like);
			
			fuzzySceneryList = sceneryService.selectSceneryByLike(like);
		}else {
			
			fuzzySceneryList = sceneryService.selectFuzzyNameScenery(like);
			
		}
		
		if(fuzzySceneryList.size() > 0) {
			System.out.println("数据已经取出。。。第一个元素输出。。。");
			
		}else {
			System.out.println("没有取到数据。。。");
		}
		
		model.addAttribute("fuzzySceneryList", fuzzySceneryList);
		
		return "seachResult";
	}
	
	//查询景点评论 返回一个map容器
	/** 功能：通过景点id查询属于该景点的所有评论，并且查询每个评论属于哪个用户。
	 * 但 这个方法非常不合理，大大增加了耦合性，应该是数据库的设计不合理，但此处只能。。。 
	 * 
	 * 还要考虑jsp页面要怎么遍历map容器？？？？？？？？
	 * 
	 * map在jsp中的遍历
	 * 	我这里采用的是利用jstl标签库中的c标记进行遍历...en..和遍历list容器差不多。
	 * */
	public Map<User, Comment> sceneryComment(int scId) {
		
		List<Comment> comments = commentService.selectCommentBySceneryId(scId);
		Map<User, Comment> userAndCommentMap = new Hashtable<>();
		User user = null;
		Comment comment = null;
		
		for(int i = 0; i < comments.size(); i++) {
			
			comment = comments.get(i);
			
			user = userServcie.selectUserById(comment.getUserId());
			
			userAndCommentMap.put(user, comment);
			
		}
		
		return userAndCommentMap;
	}
	
	//进入游记编写页面
	@RequestMapping(value="/scenery/toAddArticle.action")
	public String toAddArticle() {
		
		//System.out.println("进入编辑页面。。。准备编写游记。。。");
		
		return "editArticle";
	}
	
	//游记编写
	@RequestMapping(value="/scenery/addArticle.action", method=RequestMethod.POST)
	public String addArticle(String articleContent, Model model, HttpServletRequest request) {
		
		String articleTitle = null;
		String articleContentHtml = null;
		String articleContentTxt = null;
		String para = request.getParameter("articleContent");

		
		String paras[] = para.split("___");
		articleTitle = paras[0];
		articleContentHtml = paras[1];
		articleContentTxt = paras[2];
		
		Article article = new Article();
		article.setTitle(articleTitle);
		article.setContentHtml(articleContentHtml);
		article.setContentTxt(articleContentTxt);
		
		articleService.addArticle(article);
		
		return "redirect:/scenery/autoScenery.action";  //转到首页
	}
	
	@RequestMapping(value="/scenery/toArticleDetails.action")
	public String toArticleDetails(String arId, Model model) {
		
		Article article = articleService.selectArticleById(Integer.parseInt(arId));
		
		model.addAttribute("article", article);
		
		return "details_hot";
	}
	
}
