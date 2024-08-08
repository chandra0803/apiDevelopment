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

    private String status;
    private Object details;
    private List<ErrorDetails> errorDetailsList;
}
