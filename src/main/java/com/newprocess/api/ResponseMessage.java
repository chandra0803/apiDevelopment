package com.newprocess.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Builder
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage<Object> {

    private Integer responseFlag;
    private String responseCode;
    private Object responseMessage;
    private ResponseValue id;
    private List<ErrorDetails> errorDetailsList;
}
