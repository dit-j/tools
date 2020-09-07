package de.jawb.tools.security;

import de.jawb.tools.security.base64.Base64;
import de.jawb.tools.string.StringUtil;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author dit
 */
public class SignatureVerifier {

    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM   = "SHA1withRSA";

    /**
     * Verifies that the data was signed with the given signature, and returns
     * the verified purchase. The data is in JSON format and signed
     * with a private key. The data also contains the PurchaseState
     * and product ID of the purchase.
     *
     * @param base64PublicKey
     *            the base64-encoded public key to use for verifying.
     * @param signedData
     *            the signed JSON string (signed, not encrypted)
     * @param signature
     *            the signature for the data, signed with the private key
     */
    public static boolean verifySignedData(String base64PublicKey, String signedData, String signature) {
        if (StringUtil.findEmpty(base64PublicKey, signedData, signature) >= 0) {
            return false;
        }
        PublicKey key = SignatureVerifier.generatePublicKey(base64PublicKey);
        return SignatureVerifier.verify(key, signedData, signature);
    }

    /**
     * Generates a PublicKey instance from a string containing the
     * Base64-encoded public key.
     *
     * @param encodedPublicKey
     *            Base64-encoded public key
     * @throws IllegalArgumentException
     *             if encodedPublicKey is invalid
     */
    public static PublicKey generatePublicKey(String encodedPublicKey) {
        try {
            byte[] decodedKey = Base64.decodeToBytes(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Verifies that the signature from the server matches the computed
     * signature on the data. Returns true if the data is correctly signed.
     *
     * @param publicKey
     *            public key associated with the developer account
     * @param signedData
     *            signed data from server
     * @param signature
     *            server signature
     * @return true if the data and signature match
     */
    public static boolean verify(PublicKey publicKey, String signedData, String signature) {
        byte[] signatureBytes;
        try {
            signatureBytes = Base64.decodeToBytes(signature);
        } catch (IllegalArgumentException e) {
            return false;
        }
        try {
            Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initVerify(publicKey);
            sig.update(signedData.getBytes());
            if (!sig.verify(signatureBytes)) {
                return false;
            }
            return true;
        } catch (NoSuchAlgorithmException e) {} catch (InvalidKeyException e) {} catch (SignatureException e) {} catch (Exception e) {}
        return false;
    }
}
