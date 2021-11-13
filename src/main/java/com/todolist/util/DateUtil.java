package com.todolist.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.todolist.util.AppConstants.DATE_FORMAT;

public class DateUtil {

    public static Date stringToDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
}
