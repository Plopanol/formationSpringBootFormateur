package com.training.store.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private AppErrorCode code;
    private List<String> message;

    public String toString() {
        return this.code + ":" + message;
    }
}
