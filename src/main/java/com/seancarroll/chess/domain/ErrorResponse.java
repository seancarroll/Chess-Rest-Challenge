package com.seancarroll.chess.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -7166857302307601372L;
    private Integer status;
    private String type;
    private List<ErrorDTO> errors;
    
    public ErrorResponse(HttpStatus status, String type) {
        this(status, type, new ArrayList<ErrorDTO>());
    }
    
    public ErrorResponse(HttpStatus status, String type, List<ErrorDTO> errors) {
        this.status = Preconditions.checkNotNull(status).value();
        this.type = Preconditions.checkNotNull(type);
        this.errors = errors;
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }
    
    public void addError(ErrorDTO error) {
        errors.add(error);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", getStatus())
                .add("type", getType())
                .add("errors", getErrors())
                .toString();
    }

}
