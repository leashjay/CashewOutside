package seng202.team3.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;


/*Utility class to help with encrypting and checking passwords*/
public class PasswordUtils {

    //The number of times we will preform the hashing algorithm
    private static final int ITERATIONS = 65536;

    //The length of the resulting cryptographic key (in bits)
    private static final int KEY_LENGTH = 512;

    //The algorithm we are using to hash strings
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    private static final SecureRandom RAND = new SecureRandom();

    /**
     * Generates a random string (salt) for the given password to prevent dictionary attacks
     * @param length the length of the salt
     * @return the salt string.
     */
    public static Optional<String> generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    /**
     * Hashes a password to a string for secure storage
     * @param password the users password
     * @param salt the random string generated to prevent dictionary attacks
     * @return an encrypted string obtained from the users original password
     */
    public static Optional<String> hashPassword (String password, String salt) {

        //Need the password as an array of chars and the salt as bytes
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        //Specifying how to generate the hashed password
        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        //Fills the salt array with nulls as we are done with it
        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Determines whether a plaintext password generates the hashed passwod
     * @param password the plaintext password
     * @param key the previously generated hashed password
     * @param salt the random text generated to prevent dictionary attacks.
     * @return true if the password matches the salted password.
     */
    public static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        return optEncrypted.map(s -> s.equals(key)).orElse(false);
    }
}
