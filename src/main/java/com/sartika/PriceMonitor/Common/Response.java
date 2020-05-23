package com.sartika.PriceMonitor.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status", "error", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    @JsonProperty("status")
    private Integer code;
    @JsonProperty("error")
    private String errorMessage;
    @JsonProperty("data")
    private T data;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
