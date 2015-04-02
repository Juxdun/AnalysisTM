package com.juxdun.analysisTM.analysis.entities;

public class BucketBaseURI {
	private String bucketName;
	private String uri;
	public String getBucketName() {
		return bucketName;
	}
	public String getUri() {
		return uri;
	}
	@Override
	public String toString() {
		return "BucketBaseURI [bucketName=" + bucketName + ", uri=" + uri + "]";
	}
	
}
