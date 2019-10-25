package com.vigener.validate;

import com.vigener.service.exception.InvalidInputDataException;

public interface InputValidator {

    void validateInputData(String inputText, String key) throws InvalidInputDataException;

}
