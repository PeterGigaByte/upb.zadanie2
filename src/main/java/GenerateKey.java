import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class GenerateKey {
    private SecretKey secretKey;

    public GenerateKey( ) {
        this.secretKey = generateKey();
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
    public static SecretKey generateKey(){
        KeyGenerator generatedKey = null;
        try {
            generatedKey = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecureRandom secureRandom = new SecureRandom();
        assert generatedKey != null;
        generatedKey.init(128, secureRandom);
        return generatedKey.generateKey();
    }
    public String keyToString(){
        byte[] encoded = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }
    public static SecretKey stringToKey(String key) {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decodedKey, 0,
                decodedKey.length, "AES");
    }
}
