package com.todolist.controller;

import com.todolist.model.ItemResponse;
import com.todolist.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static com.todolist.util.AppConstants.*;

@RestController
@Validated
public class ToDoListController {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ToDoService toDoService;

    public ToDoListController(ToDoService toDoService) {
        super();
        this.toDoService = toDoService;
    }

    @GetMapping(SHOW_ITEM)
    ResponseEntity<?> showItem(WebRequest request) {
        log.info(START_SERVICE.concat(": {} "), ((ServletWebRequest) request).getRequest().getRequestURI());
        ItemResponse response = toDoService.showItem();
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": [{}]"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(SHOW_ITEM_ID)
    ResponseEntity<?> showItemById(@Positive @NotNull @RequestParam Long id, WebRequest request) {
        log.info(START_SERVICE.concat(": {} "), ((ServletWebRequest) request).getRequest().getRequestURI());
        ItemResponse response = toDoService.showItemById(id);
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": [{}]"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(ADD_ITEM)
    ResponseEntity<?> addItem(@RequestParam(required = false) @Positive Long id,
                              @RequestParam @NotEmpty String tittle,
                              @RequestParam @NotNull @Positive Double latitude,
                              @RequestParam @NotNull @Positive Double longitude,
                              @RequestParam @NotEmpty @NotEmpty String date,
                              @RequestParam("file") @NotNull MultipartFile file,
                              WebRequest request) {
        log.info(START_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI())
                .concat(" id: {}, tittle: {}, latitude: {}, longitude: {}, date: {}, file: {}"), id, tittle, latitude, longitude, date, file);
        ItemResponse response = toDoService.saveOrUpdateItem(id, tittle, latitude, longitude, date, file);
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": [{}]"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(DELETE_ITEM)
    ResponseEntity<?> deleteItem(@Positive @NotNull @RequestParam Long id, WebRequest request) {
        log.info(START_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": "), id);
        ItemResponse response = toDoService.deleteItem(id);
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": {}"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
