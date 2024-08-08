package com.newprocess.util;

import com.newprocess.api.ErrorDetails;
import com.newprocess.api.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

@Slf4j
public class AppUtility {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date parseDate(String date) {
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Timestamp parseTimestamp(String timestamp) {
        try {
            if (null == timestamp) {
                Instant now = Instant.now();
                Timestamp currentTime = Timestamp.from(now);
                return new Timestamp(DATE_TIME_FORMAT.parse(currentTime.toString()).getTime());
            }
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static ResponseMessage contructErrorMsg(String inputObject, String inputId) {

        ErrorDetails errorDetails = ErrorDetails.builder().message(inputObject + " not found for this id: " + inputId + "; Please enter valid id.")
                .timestamp(new java.util.Date())
                .build();
        log.error("error details: {}", errorDetails);
        return ResponseMessage.builder().details(errorDetails.getMessage())
                .status(String.valueOf(Response.SC_NOT_FOUND))
                .build();
    }
}
