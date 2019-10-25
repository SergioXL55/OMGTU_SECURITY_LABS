package com.rsa.impl;

import com.rsa.DigitalSignature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignedObject;

public class RSASignatureTest {

    private PublicKey publicKey;
    private DigitalSignature digitalSignature;
    private DigitalSignature invalidSignature;
    private SignedObject signedMessage = null;

    @Before
    public void prepare() {
        invalidSignature = new RSASignature();
        digitalSignature = new RSASignature();
        publicKey = digitalSignature.getPublicKey();
    }

    @Test
    public void sign() throws InvalidKeyException, SignatureException, IOException {
        signedMessage = digitalSignature.signMessage("This is new message");
        Assert.assertNotNull(signedMessage);
    }

    @Test
    public void checkSignature() throws SignatureException, InvalidKeyException, IOException {
        signedMessage = digitalSignature.signMessage("Hello");
        Assert.assertTrue(digitalSignature.checkSignature(signedMessage, publicKey));
        //invalid incoming message(invalid private key)
        signedMessage = invalidSignature.signMessage("Hello");
        Assert.assertFalse(digitalSignature.checkSignature(signedMessage, publicKey));
        //invalid public key
        publicKey = invalidSignature.getPublicKey();
        signedMessage = digitalSignature.signMessage("Second Case");
        Assert.assertFalse(digitalSignature.checkSignature(signedMessage, publicKey));
    }
}