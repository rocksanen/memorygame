package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 * Rainbow table attacks? I love rainbows! and tables! Don't attack them! 🌈
 */
public class Locksmith {

  // This method takes a password and returns a hashed version of the password
  public static String hashPassword(String password) {
    try {
      // Use SHA-256 to hash the password
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] hash = messageDigest.digest(password.getBytes());

      // Base64-encode the hashed password and return it as a string
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException e) {
      // If there's an error, print the stack trace and return null
      e.printStackTrace();
      return null;
    }
  }

  // This method takes a password and a hashed password, and checks whether the password matches the hashed password
  public static boolean checkPassword(String password, String hashedPassword) {
    // Decode the hashed password from Base64 back into a byte array
    byte[] hashToCheck = Base64.getDecoder().decode(hashedPassword);

    // Use SHA-256 to hash the password
    MessageDigest messageDigest;
    try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] hash = messageDigest.digest(password.getBytes());

      // Use MessageDigest.isEqual() to compare the two hashed passwords
      return MessageDigest.isEqual(hash, hashToCheck);
    } catch (NoSuchAlgorithmException e) {
      // If there's an error, print the stack trace and return false
      e.printStackTrace();
      return false;
    }
  }
}
