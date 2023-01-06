package com.training.store.commons.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class GenericException extends RuntimeException {

    private static final long serialVersionUID = 1691239893584646190L;

    private List<ErrorMessage> messages;

    public GenericException(List<ErrorMessage> messages) {
        super(messages.toString());
    }

}
