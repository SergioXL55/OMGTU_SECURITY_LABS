package com.rsa.impl;

import com.rsa.DigitalSignature;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;

public class RSASignature implements DigitalSignature {

    private PrivateKey privateKey = null;
    private PublicKey publicKey = null;
    private Signature signature;
    private static final String RSA_SIGNATURE = "NONEwithRSA";
    private static final String RSA = "RSA";


    public RSASignature() {
        try {
            generateKeys();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SignedObject signMessage(final String message) throws InvalidKeyException, SignatureException, IOException {
        return new SignedObject(message, privateKey, signature);
    }

    @Override
    public boolean checkSignature(final SignedObject signedMessage,PublicKey publicKey) throws SignatureException, InvalidKeyException {
        return signedMessage.verify(publicKey, signature);
    }

    private void generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
        signature = Signature.getInstance(RSA_SIGNATURE);
    }

    @Override
    public PublicKey getPublicKey() {
        return publicKey;
    }
}
