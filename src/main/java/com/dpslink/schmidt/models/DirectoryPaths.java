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
	
}
