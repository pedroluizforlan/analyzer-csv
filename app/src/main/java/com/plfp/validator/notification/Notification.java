package com.plfp.validator.notification;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> errors = new ArrayList<>();

    public void addError(final String error){
        errors.add(error);
    }

    public boolean hasError(){
        return !errors.isEmpty();
    }

    public String errorMessage(){
        return errors.toString();
    }

    public List<String> getErrors(){
        return errors;
    }
}
