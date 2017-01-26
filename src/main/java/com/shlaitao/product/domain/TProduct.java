
package com.shlaitao.product.domain;

public class TProduct {

	private int id;
	private String number;//编号
	private String name;//名称
	private String dose;//剂量
	private String content;//内容
	private String consistence;//浓度
	private String status;//状态
	private String type;//所属类别
	private String typename;//类别名称
	
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConsistence() {
		return consistence;
	}
	public void setConsistence(String consistence) {
		this.consistence = consistence;
	}
	
}
