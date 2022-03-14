package ModuleTransaction.Orgcom.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides a set of static methods to compute hash values.
 */
public class HashUtils {

    private static final int SMALL_HASH_LENGTH = 8;

    /**
     * Returns the hash of the given string.
     * For the same string, the same hash is returned.
     * A hash must return a hexadecimal string.
     *
     * @param message to be hashed.
     * @return a hash of the message.
     * @throws UnHashableException if the hash algorithm is not supported.
     */
    public static String getHash(String message) {
        try {
            return HashUtils.bytesToHex(MessageDigest.getInstance("SHA-256").digest(message.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new UnHashableException(e.getMessage());
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Returns the hash of the given string in a small format.
     *
     * @param text to be hashed.
     * @return a hash of the text with a small length.
     */
    public static String getSmallHash(String text) {
        return text.length() > HashUtils.SMALL_HASH_LENGTH ? text.substring(0, HashUtils.SMALL_HASH_LENGTH) : text;
    }

}
