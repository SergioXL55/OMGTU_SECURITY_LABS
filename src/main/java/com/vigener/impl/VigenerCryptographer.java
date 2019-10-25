package com.vigener.impl;

import com.vigener.Cryptographer;
import com.vigener.service.CryptoAction;
import com.vigener.service.alphabet.Alphabets;
import com.vigener.service.exception.InvalidInputDataException;

import static com.vigener.service.CryptoAction.DECODE;
import static com.vigener.service.CryptoAction.ENCODE;

public class VigenerCryptographer implements Cryptographer {

    private String key;
    private char[] alphabet = Alphabets.RU_NUMERIC.getAlphabet();

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String encode(String text) throws InvalidInputDataException {
        validateInputData(text, key);
        return getEncryptionResult(ENCODE, text);
    }

    @Override
    public String decode(String text) throws InvalidInputDataException {
        validateInputData(text, key);
        return getEncryptionResult(DECODE, text);
    }

    @Override
    public void validateInputData(String inputText, String key) throws InvalidInputDataException {
        if (inputText.length() > 0 && key.length() > 0) {
            for (char symbol : inputText.toCharArray()) {
                int c = getCharIndexFromArray(alphabet, symbol);
                if (c < 0) throw new InvalidInputDataException();
            }
            for (char symbol : key.toCharArray()) {
                int c = getCharIndexFromArray(alphabet, symbol);
                if (c < 0) throw new InvalidInputDataException();
            }
        } else {
            throw new InvalidInputDataException();
        }
    }

    private int getKeywordIndex(int currentIndex, int keylength) {
        currentIndex++;
        if ((currentIndex) == keylength) {
            currentIndex = 0;
        }
        return currentIndex;
    }

    private String getEncryptionResult(CryptoAction action, String incomingText) throws InvalidInputDataException {
        String result = "";
        int power = alphabet.length;
        int keyword_index = 0;
        for (char symbol : incomingText.toCharArray()) {
            int c = getCharIndexFromArray(alphabet, symbol);
            int k = getCharIndexFromArray(alphabet, key.toCharArray()[keyword_index]);
            int p = 0;
            switch (action) {
                case DECODE:
                    p = (c + power - k) % power;
                    break;
                case ENCODE:
                    p = (c + k) % power;
                    break;
            }
            result += alphabet[p];
            keyword_index = getKeywordIndex(keyword_index, key.length());
        }
        return result;
    }

    private int getCharIndexFromArray(char[] array, char symbol) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == symbol) {
                return i;
            }
        }
        return -1;
    }
}
