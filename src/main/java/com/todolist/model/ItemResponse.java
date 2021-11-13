package com.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.todolist.entity.ItemEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItemResponse(boolean success, String message, List<String> fieldErrors, List<ItemEntity> data) { }
