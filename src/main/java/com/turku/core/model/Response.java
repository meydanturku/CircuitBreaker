package com.turku.core.model;

import lombok.*;
import org.json.simple.JSONObject;

import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    public T data;
    public String message;
    private boolean isErrorExist;
    public Map<String, JSONObject> result;
    private String errorCode;
}
