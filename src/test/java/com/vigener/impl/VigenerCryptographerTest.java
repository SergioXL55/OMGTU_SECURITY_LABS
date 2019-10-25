package com.vigener.impl;

import com.vigener.Cryptographer;
import com.vigener.service.exception.InvalidInputDataException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VigenerCryptographerTest {

    private Cryptographer cryptographer = new VigenerCryptographer();
    private String keyOne = "КЕЙ";
    private String message = "Привет";

    @Before
    public void prepare() {
        cryptographer.setKey(keyOne);
    }

    @Test
    public void encode() throws InvalidInputDataException {
        Assert.assertNotNull(cryptographer.encode(message));
    }

    @Test
    public void decode() throws InvalidInputDataException {
        String encodedMessage = cryptographer.encode("Привет");
        Assert.assertEquals("Привет", cryptographer.decode(encodedMessage));
        encodedMessage = cryptographer.encode("Дом");
        Assert.assertEquals("Дом", cryptographer.decode(encodedMessage));
    }
}