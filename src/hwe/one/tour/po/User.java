package hwe.one.tour.po;

/*
 * 用户表
 */
public class User {
	private int id;
	private String name;
	private String password;
	private String phone;
	private int age;
	private String sex;
	private String like;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", age=" + age
				+ ", sex=" + sex + "]";
	}
	
	
}
