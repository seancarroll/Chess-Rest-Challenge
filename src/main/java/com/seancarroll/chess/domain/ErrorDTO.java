package com.seancarroll.chess.domain;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = -7719935520668321533L;
    private String code;
    private String message;
    private String field;
    private String rejectedValue;
    
    public ErrorDTO() {
        
    }
    
    public ErrorDTO(String code, String message) {
        this(code, message, null, null);
    }
    
    public ErrorDTO(String code, String message, String field, String rejectedValue) {
        this.code = code;
        this.message = message;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", getCode())
                .add("message", getMessage())
                .add("field", getField())
                .add("rejectedValue", getRejectedValue())
                .toString();
    }
    
    
}
