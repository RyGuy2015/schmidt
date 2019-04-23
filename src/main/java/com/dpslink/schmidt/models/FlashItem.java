package com.dpslink.schmidt.models;

public class FlashItem {
	
	private String code;
	private String description;
	private String categoryCode;
	private boolean itemCopied;
	
	public FlashItem() {
		
	}
	
	public FlashItem(String code, String description) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	  @Override
	  public String toString() {
	    return String.format("FlashItem[id=%s, description='%s']", code, description);
	  }

	public boolean getItemCopied() {
		return itemCopied;
	}

	public void setItemCopied(boolean itemCopied) {
		this.itemCopied = itemCopied;
	}

}
