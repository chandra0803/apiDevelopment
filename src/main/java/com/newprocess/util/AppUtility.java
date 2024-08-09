package com.newprocess.util;

import com.newprocess.api.BranchDetails;
import com.newprocess.api.ErrorDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.ResponseValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Optional;

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

    public  static ResponseMessage saveOrUpdateDomain(Long inputDomainId, Object inputDomain, JpaRepository jpaRepository){
        if( null == inputDomainId){
            jpaRepository.save(inputDomain);
            return constructSuccess(inputDomain, null);
        }else{
            Optional<Class> fromDBBranchDetails = jpaRepository.findById(inputDomainId);
            if (fromDBBranchDetails.isEmpty()) {
                return constructFailure("Branch Details not found for this id: " + inputDomainId,inputDomainId);
            } else {
                jpaRepository.save(inputDomain);
                return constructSuccess(inputDomain, inputDomainId);
            }
        }

    }


    public static ResponseMessage contructErrorMsg(String inputObject, String inputId) {
        ErrorDetails errorDetails = ErrorDetails.builder().message(inputObject + " not found for this id: " + inputId + "; Please enter valid id.")
                .timestamp(new java.util.Date())
                .build();
        log.error("error details: {}", errorDetails);
        return constructFailure(errorDetails, Long.valueOf(inputId));
    }

    public static ResponseMessage constructSuccess(Object object, Long id) {
        log.info("Response:: Success for this id: {} ; Details: {}", id, object);
        return ResponseMessage.builder().responseMessage(object)
                .responseCode(String.valueOf(Response.SC_OK))
                .responseFlag(0)
                .responseCode("Success")
                .id(ResponseValue.builder().id(id).build())
                .build();
    }

    public static ResponseMessage constructFailure(Object object, Long id) {
        log.info("Response:: !! Failure for this id:{}!! ; Details: {}", id, object, "; Please reenter valid id.");
        return ResponseMessage.builder().responseMessage(object)
                .responseCode(String.valueOf(Response.SC_BAD_GATEWAY))
                .responseFlag(1)
                .responseCode("Failure")
                .id(ResponseValue.builder().id(id).build())
                .build();
    }
}
