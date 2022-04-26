package com.example.demo.helper;

public class Response {

    int error = 0;
    String message;
    Object data = null;

    public Response(int error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public Response() {

    }

    public int getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void error() {
        this.error = 1;
        this.data = null;
    }

    public void successRetrieving() {
        this.message = "success retrieving data!";
    }
}
