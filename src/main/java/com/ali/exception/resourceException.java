package com.ali.exception;

public class resourceException extends RuntimeException {

String resourceName;
String fieldName;
int fieldVlaue;
String keywords;
public resourceException(String resourceName, String fieldName, int fieldVlaue) {
	super(String.format("%s not found with %s: %s",resourceName, fieldName,fieldVlaue));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldVlaue = fieldVlaue;
}
public resourceException(String resourceName, String fieldName, String keywords) {
	super(String.format("%s not found with %s: %s",resourceName, fieldName,keywords));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.keywords = keywords;
}



}
