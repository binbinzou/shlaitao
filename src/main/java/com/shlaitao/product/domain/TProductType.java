package com.shlaitao.product.domain;

public class TProductType {

	private int id;
	private String typename;
	private String typedesc;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypedesc() {
		return typedesc;
	}
	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
