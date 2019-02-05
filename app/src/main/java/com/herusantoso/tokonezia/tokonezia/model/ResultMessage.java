package com.herusantoso.tokonezia.tokonezia.model;

import com.google.gson.annotations.SerializedName;

public class ResultMessage {

    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private Object result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
