package com.todolist.service;

import com.todolist.model.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface ToDoService {

    ItemResponse showItem();

    ItemResponse saveOrUpdateItem(Long id, String tittle, Double latitude, Double longitude, String date, MultipartFile file) throws IOException, ParseException;

    ItemResponse deleteItem(Long id);
}
