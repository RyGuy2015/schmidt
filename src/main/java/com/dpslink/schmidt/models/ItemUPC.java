package com.dpslink.schmidt.models;

public class ItemUPC {
	private String cono;
	private String item;
	private String upc;
	private String resultCode;
	
	
	public ItemUPC() {
		
	}

	public ItemUPC(String cono, String item, String upc, String resultCode) {
		super();
		this.cono = cono;
		this.item = item;
		this.upc = upc;
		this.resultCode = resultCode;
	}

	public String getCono() {
		return cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	
	
	
	
	
}
