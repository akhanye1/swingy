/*
 * Author Katleho Khanye (akhanye)
 * */

package com.swingy.app.models;

public class ValidationErrorModel {
	private String	field;
	private String	errorMessage;

	public ValidationErrorModel(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String	getField() { return (this.field); }
	public String	getErrorMessage() { return (this.errorMessage); }
}
