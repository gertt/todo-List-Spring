package com.todolist.controller;

import com.todolist.model.ItemResponse;
import com.todolist.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.io.IOException;
import java.text.ParseException;

import static com.todolist.util.AppConstants.START_SERVICE;
import static com.todolist.util.AppConstants.STOP_SERVICE;

@RestController
@Validated
public class ToDoListController {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/show-item")
    ResponseEntity<?> showItem(WebRequest request) {
        log.info(START_SERVICE.concat(": {} "), ((ServletWebRequest) request).getRequest().getRequestURI());
        ItemResponse response = toDoService.showItem();
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": [{}]"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-item")
    ResponseEntity<?> addItem(@RequestParam(required = false) @Positive Long id,
                              @RequestParam @NotEmpty String tittle,
                              @RequestParam @NotNull @Positive Double latitude,
                              @RequestParam @NotNull @Positive Double longitude,
                              @RequestParam @NotEmpty @NotEmpty String date,
                              @RequestParam("file") @NotNull MultipartFile file,
                              WebRequest request) throws IOException, ParseException {
        log.info(START_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI())
                .concat(" id: {}, tittle: {}, latitude: {}, longitude: {}, date: {}, file: {}"), id, tittle, latitude, longitude, date, file);
        ItemResponse response = toDoService.saveOrUpdateItem(id, tittle, latitude, longitude, date, file);
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": [{}]"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete-item")
    ResponseEntity<?> deleteItem(@RequestParam Long id, WebRequest request) {
        log.info(START_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": "), id);
        ItemResponse response = toDoService.deleteItem(id);
        log.info(STOP_SERVICE.concat(((ServletWebRequest) request).getRequest().getRequestURI()).concat(": {}"), response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
