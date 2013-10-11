package com.plainvanilla.organix.engine.model.exception;

public class OrganixModelException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public OrganixModelException(String reason) {
		super(reason);
	}
}
