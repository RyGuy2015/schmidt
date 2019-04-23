package com.dpslink.schmidt.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DirectoryPaths {
	
	@NotNull
	@Size(min=1)
	private String fromDirectory;
	private String flashDirectory;
	private String htmlDirectory;
	private String largeImageDirectory;
	private String smallImageDirectory;
	private String upcDirectory;
	private boolean updateFlag;
	private boolean useMfgItem;
	private String updateReturnCode;
	

	public String getHtmlDirectory() {
		return htmlDirectory;
	}

	public String getLargeImageDirectory() {
		return largeImageDirectory;
	}

	public String getSmallImageDirectory() {
		return smallImageDirectory;
	}

	public void setFromDirectory(String fromDirectory) {
		this.fromDirectory = fromDirectory;
	}

	public String getFromDirectory() {
		return fromDirectory;
	}

	public String getFlashDirectory() {
		return flashDirectory;
	}
	public void setFlashDirectory(String flashDirectory) {
		this.flashDirectory = flashDirectory;
		this.htmlDirectory = flashDirectory + "descriptions/product/large/";
		this.largeImageDirectory = flashDirectory + "images/product/large/";
		this.smallImageDirectory = flashDirectory + "images/product/small/";
	}

	public String getUpcDirectory() {
		return upcDirectory;
	}

	public void setUpcDirectory(String upcDirectory) {
		this.upcDirectory = upcDirectory;
	}

	public boolean getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
		if (updateFlag) {
			this.updateReturnCode = "Y";
		} else {
			this.updateReturnCode = "N";
		}
		System.out.println("update flag is :" + updateFlag);
	}
	
	public String getUpdateReturnCode() {
		return updateReturnCode;
	}

	public boolean isUseMfgItem() {
		return useMfgItem;
	}

	public void setUseMfgItem(boolean useMfgItem) {
		System.out.println("setUseMfgItem fired");
		this.useMfgItem = useMfgItem;
		System.out.println("setUseMfgItem is now " + this.isUseMfgItem());
	}
	
	
}
