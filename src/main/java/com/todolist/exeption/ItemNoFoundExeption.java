package com.todolist.exeption;

import java.io.Serial;

import static com.todolist.util.AppConstants.ITEM_NOT_FOUND;

public class ItemNoFoundExeption extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ItemNoFoundExeption(Long id) {
        super(ITEM_NOT_FOUND.concat(id.toString()));
    }
}


