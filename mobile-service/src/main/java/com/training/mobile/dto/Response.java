package com.training.mobile.dto;

import java.util.List;

import com.training.mobile.exception.ErrorDetails;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Response<T> {
	private T response;
	private List<ErrorDetails> errors;

}
