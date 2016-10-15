package com.ccm.excel.utils;

public class InvalidExcelException extends Exception {

	private static final long serialVersionUID = 6565449665698717120L;

    private String message = null;
    
    public InvalidExcelException() {
        super();
    }
 
    public InvalidExcelException(String message) {
        super(message);
        this.message = message;
    }
 
    public InvalidExcelException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}
