package hwe.one.tour.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hwe.one.tour.po.User;

public interface UserDao {

	//添加（注册）用户
	public void addUser(User user);
	
	//通过用户名和密码查询一个用户
	public User selectUserByNameAndPassword(@Param("username")String username, @Param("password")String password);
	
	//查询所有用户
	public List<User> selectAllUser();
	
	//删除一个用户,通过id删除
	public void deleteUserById(User user);
	
	//修改用户资料
	public void updateUser(User user);
	
	//通过id查询用户
	public User selectUserById(int userId);
}
