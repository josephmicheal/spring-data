package com.example.demo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateConversionUtils {
    public static Date convertStringToDate(String dateString) throws Exception {
        System.out.println("Flow in util method");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(format.parse(dateString).getTime());
        return  date;
    }

    public static Integer convertStringToNumber(Connection conn, String input, int[] output) throws SQLException {
        try {
            output[0] = Integer.parseInt(input);
            return output[0];
        } catch (NumberFormatException e) {
            throw new SQLException("Invalid input format.");
        }
    }
}
