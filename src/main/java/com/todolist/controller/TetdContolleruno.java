package com.todolist.controller;


import com.todolist.model.ItemResponse;
import com.todolist.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@Validated
public class TetdContolleruno {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/show-itdem")
    ResponseEntity<?> sdwsds(WebRequest request) {
        ItemResponse response = toDoService.showItem();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
