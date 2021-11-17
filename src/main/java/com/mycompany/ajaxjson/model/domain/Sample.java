package com.mycompany.ajaxjson.model.domain;

public class Sample {
	private static final long serialVersionUID = 1111L;
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Sample [name=" + name + ", age=" + age + "]";
	}
	
	
}
