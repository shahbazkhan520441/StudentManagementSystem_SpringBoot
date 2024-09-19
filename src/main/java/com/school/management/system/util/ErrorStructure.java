package com.school.management.system.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ErrorStructure<T> {
	private int status;
	private String message;
	private T rootCause;

	public ErrorStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}

	public ErrorStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
		return this;
	}

}