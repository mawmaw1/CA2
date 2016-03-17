/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Magnus
 */
public class ErrorMessage {

    private String message;
    private int httpStatusCode;
    private String stackTrace;

    public ErrorMessage(Throwable ex, String message, int httpStatusCode) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

}
