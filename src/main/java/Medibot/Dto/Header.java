package Medibot.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Header {
    @JsonProperty("resultCode")
    private String resultCode;

    @JsonProperty("resultMsg")
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    @Override
    public String toString() {
        return "Header{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}
