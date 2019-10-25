package com.vigener;

import com.vigener.service.exception.InvalidInputDataException;
import com.vigener.validate.InputValidator;

public interface Cryptographer extends InputValidator {

    void setKey(String key);

    String encode(String text) throws InvalidInputDataException;

    String decode(String text) throws InvalidInputDataException;

}
