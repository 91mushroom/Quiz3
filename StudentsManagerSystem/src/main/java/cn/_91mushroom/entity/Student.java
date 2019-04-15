package cn._91mushroom.entity;
/**
 * 学生实体类
 * @author H.H
 *
 */
public class Student {
	
	private Integer id;
	private String name;
	private Integer age;
	private String avatar_url;
	private Double credit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", avatar_url=" + avatar_url + ", credit="
				+ credit + "]";
	}
	
	
	
}
