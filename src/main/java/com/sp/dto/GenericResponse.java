package com.sp.dto;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class GenericResponse {
	private int status;
	private String message;
	private Object data;
	private Collection<? extends Object> objectList;
}
