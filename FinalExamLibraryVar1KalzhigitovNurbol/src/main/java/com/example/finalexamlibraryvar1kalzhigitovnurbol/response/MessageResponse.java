package com.example.finalexamlibraryvar1kalzhigitovnurbol.response;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init(){
        System.out.println("Bean is going through init.");
    }
    public void destroy() {
        System.out.println("Bean will destroy now.");
    }
}
