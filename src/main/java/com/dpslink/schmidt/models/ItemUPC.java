package com.dpslink.schmidt.models;

public class ItemUPC {
	private String ITCONO;
	private String ITITEM;
	
	
	public ItemUPC() {
		
	}
	
	public ItemUPC(String iTCONO, String iTITEM) {
		super();
		ITCONO = iTCONO;
		ITITEM = iTITEM;
	}

	public String getITCONO() {
		return ITCONO;
	}

	public void setITCONO(String iTCONO) {
		ITCONO = iTCONO;
	}

	public String getITITEM() {
		return ITITEM;
	}

	public void setITITEM(String iTITEM) {
		ITITEM = iTITEM;
	}
	
	
	
	
}
