package com.todolist.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.todolist.util.AppConstants.DATE_FORMAT;

public class DateUtil {

    @SneakyThrows
    public static Date stringToDate(String dateString) {
        return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
    }
}
