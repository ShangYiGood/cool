package hwe.one.tour.po;

/*
 * 景点
 */
public class Scenery {
	
	private int id;
	private String name;
	private String location;
	private String synopsis;  //简介
	private String images; //图片
	private String type;  //类型
	
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Scenery [id=" + id + ", name=" + name + ", location=" + location + ", synopsis=" + synopsis
				+ ", images=" + images + "]";
	}
	
}
