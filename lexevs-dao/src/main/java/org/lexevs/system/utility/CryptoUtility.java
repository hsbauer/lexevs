/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.lexevs.system.utility;

/*import static org.bouncycastle.util.encoders.Base64.decode;
import static org.bouncycastle.util.encoders.Base64.encode;*/

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.lexevs.logging.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * The Class CryptoUtility.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public final class CryptoUtility {
    
    /**
     * Instantiates a new crypto utility.
     */
    CryptoUtility() {}
 
    /** Internal Key. */
    protected static char[] key = "MayoClinic".toCharArray();
    
    /** The salt. */
    private static byte[] salt = "LEX-GRID".getBytes();
    
    /** The log. */
    private static Logger log = new Logger(); 
    
    /**
     * Encrypts data.
     * 
     * @param cleartext the cleartext
     * 
     * @return the string
     * 
     * @throws GeneralSecurityException on an encryption error
     */
    public static String encrypt(final String cleartext) {
        int count = 30;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, count);
 
        byte[] cipherBytes = null;
        try {
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(), parameterSpec);
            cipherBytes = cipher.doFinal(cleartext.getBytes());
        } catch (GeneralSecurityException e) {
            log.fatal("Error on passowrd encryption.", e);
        }
        
//        return new String(encode(cipherBytes));
        return new String(new BASE64Encoder().encode(cipherBytes));
    }
 
    /**
     * Decrypts cipher text using the shared passphrase.
     * 
     * @param ciphertext the ciphertext
     * 
     * @return The decrypted text.
     */
    public static String decrypt(final String ciphertext) {
        int count = 30;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, count);
 
        String result = null;
//        byte[] decodedBytes = decode(ciphertext);
        
        try {
            byte[] decodedBytes = new BASE64Decoder().decodeBuffer(ciphertext);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, getKey(), parameterSpec);
            result = new String(cipher.doFinal(decodedBytes));
            return result;
        } catch (GeneralSecurityException e) {
            log.fatal("Error on passowrd decryption.", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * Gets the key.
     * 
     * @return the key
     * 
     * @throws GeneralSecurityException the general security exception
     */
    protected static SecretKey getKey() throws GeneralSecurityException {
        KeySpec keySpec = new PBEKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        return keyFactory.generateSecret(keySpec);
    }
}