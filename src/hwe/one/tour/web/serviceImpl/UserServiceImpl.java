package hwe.one.tour.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hwe.one.tour.dao.UserDao;
import hwe.one.tour.po.User;
import hwe.one.tour.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
		System.out.println("用户添加成功！");
	}

	@Override
	public User selectUserByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		User user = userDao.selectUserByNameAndPassword(name, password);
		
		return user;
	}

	@Override
	public List<User> selectAllUser() {
		// TODO Auto-generated method stub
		
		List<User> userList = userDao.selectAllUser();
		
		return userList;
	}

	@Override
	public void deleteUserById(User user) {
		// TODO Auto-generated method stub
		
		 userDao.deleteUserById(user);
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		userDao.updateUser(user);
		
	}

	@Override
	public User selectUserById(int userId) {
		// TODO Auto-generated method stub
		
		User user = userDao.selectUserById(userId);
		
		return user;
	}

}
