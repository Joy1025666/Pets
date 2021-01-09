package cn.edu.jsu.oyj.vo;

import java.io.Serializable;

public class Pets implements Serializable {
	private String num;
	private String category;
	private String sex;
	private double weight;
	private boolean state;
	private int heat;
	private int price;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public Pets() {
	}

	public Pets(String num, String category, String sex, double weight, boolean state, int heat, int price,String type) {
		super();
		this.num = num;
		this.category = category;
		this.sex = sex;
		this.weight = weight;
		this.state = state;
		this.heat = heat;
		this.price = price;
		this.type=type;
	}

	@Override
	public String toString() {
//		return "宠物编号:" + num +"\t"+ "宠物品种：" + category +"\t"+ "宠物性别：" + sex +"\t"+ "宠物重量：" + weight+"\t" + "宠物在售：" + state +"\t"+"宠物热度："+heat+"\t"+"宠物价格："+price;
		return num + "\t" + category + "\t" + sex + "\t" + weight + "\t" + state + "\t" + heat + "\t" + price + "\t"+type+"\t";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + heat;
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + (state ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pets other = (Pets) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (heat != other.heat)
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (state != other.state)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
}
