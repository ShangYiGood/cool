package hwe.one.tour.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hwe.one.tour.po.User;
import hwe.one.tour.web.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String username, String password, Model model, HttpSession session) {
		
		System.out.println("进入登录业务处理函数。。。" + username + "," + password);
		
		User user = userService.selectUserByNameAndPassword(username, password);
		
		if(user != null) {
			session.setAttribute("USER_SESSION", user);
			
			//重定向到首页
			return "redirect:/scenery/autoScenery.action";
		}
		
		return "loginAndregister";  //跳转到登录页面
		
	}
	
	/**
	 * 用户注册
	 */
	@RequestMapping(value="/register.action", method=RequestMethod.POST)
	public String register(User user) {
		
		if(user != null) {
			userService.addUser(user);
		}else {
			System.out.println("前台获取数据失败！");
		}
		
		return "redirect:/login.action";
		
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping(value="/logout.action")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/scenery/autoScenery.action";
		
	}
	
	/**
	 * 跳转到登录页面
	 */
	@RequestMapping(value="/login.action", method=RequestMethod.GET)
	public String toLogin() {
		System.out.println("准备跳转到登录界面。。。");
		return "loginAndregister";
	}
}
