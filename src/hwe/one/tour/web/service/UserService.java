package hwe.one.tour.web.service;

import java.util.List;

import hwe.one.tour.po.User;

public interface UserService {
	
	//添加（注册）用户
	public void addUser(User user);
	
	//通过用户名和密码查询一个用户
	public User selectUserByNameAndPassword(String username, String password);
	
	//查询所有用户
	public List<User> selectAllUser();
	
	//删除一个用户,通过id删除
	public void deleteUserById(User user);
	
	//修改用户资料
	public void updateUser(User user);
	
	public User selectUserById(int userId);
	
}
