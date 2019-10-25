package com.rsa;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignedObject;

public interface DigitalSignature {

    SignedObject signMessage(final String message) throws InvalidKeyException, SignatureException, IOException;

    boolean checkSignature(final SignedObject signedObject,PublicKey publicKey) throws SignatureException, InvalidKeyException;

    PublicKey getPublicKey();

}
