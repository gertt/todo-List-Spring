package com.todolist.service;

import com.todolist.model.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ToDoService {

    ItemResponse showItem();

    ItemResponse saveOrUpdateItem(Long id, String tittle, Double latitude, Double longitude, String date, MultipartFile file);

    ItemResponse deleteItem(Long id);

    ItemResponse showItemById(Long id);
}
