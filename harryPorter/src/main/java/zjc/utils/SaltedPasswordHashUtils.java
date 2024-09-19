package zjc.utils;

import zjc.handler.exception.ZjcBusinessLogicException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SaltedPasswordHashUtils {

    private SaltedPasswordHashUtils() {
        throw new IllegalStateException("SaltedPasswordHashUtils class");
    }

    private static final int SALT_LENGTH = 32; // 盐长度

    public static String hashPasswordWithSalt(String password) {
        byte[] salt = generateSalt();
        byte[] hash = hashPasswordWithSalt(password, salt);
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] hashPasswordWithSalt(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            return digest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new ZjcBusinessLogicException(e.getMessage());
        }
    }

    public static boolean verifyPassword(String storedPassword, String providedPassword) {
        String[] parts = storedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedHash = Base64.getDecoder().decode(parts[1]);
        byte[] providedHash = hashPasswordWithSalt(providedPassword, salt);

        return MessageDigest.isEqual(storedHash, providedHash);
    }
}
