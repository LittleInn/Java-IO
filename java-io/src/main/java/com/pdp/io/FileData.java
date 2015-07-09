package com.pdp.io;

public class FileData {
	private String path;
	private String hash;

	public FileData(String path, String hash) {
		super();
		this.path = path;
		this.hash = hash;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
