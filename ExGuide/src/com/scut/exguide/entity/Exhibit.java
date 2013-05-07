package com.scut.exguide.entity;

public class Exhibit {

	private String name;
	private String[] attributeNames;
	private String[] attributeValues;
	private String[] pUrls;
	private String[] vNames;
	private String[] vUrls;
	private String[] attachmenturls;
	private String[] attachmentNames;

	public Exhibit() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(String[] attributeNames) {
		this.attributeNames = attributeNames;
	}

	public String[] getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(String[] attributeValues) {
		this.attributeValues = attributeValues;
	}

	public String[] getpUrls() {
		return pUrls;
	}

	public void setpUrls(String[] pUrls) {
		this.pUrls = pUrls;
	}

	public String[] getvNames() {
		return vNames;
	}

	public void setvNames(String[] vNames) {
		this.vNames = vNames;
	}

	public String[] getvUrls() {
		return vUrls;
	}

	public void setvUrls(String[] vUrls) {
		this.vUrls = vUrls;
	}

	public String[] getAttachmenturls() {
		return attachmenturls;
	}

	public void setAttachmenturls(String[] attachmenturls) {
		this.attachmenturls = attachmenturls;
	}

	public String[] getAttachmentNames() {
		return attachmentNames;
	}

	public void setAttachmentNames(String[] attachmentNames) {
		this.attachmentNames = attachmentNames;
	}

}
