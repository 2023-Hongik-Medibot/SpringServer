package Medibot.Dto;

import java.util.List;

public class ApisResponse {

    private Header header;
    private Body body;

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ApisResponse{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
