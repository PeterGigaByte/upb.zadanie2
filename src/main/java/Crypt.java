import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
public class Crypt {
    public static void crypto(String kluc, File vstupnyFile, File vystupnyFile, int mod) throws Exception {

        Key tajnyKluc = new SecretKeySpec(kluc.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        if (mod==1){
            cipher.init(Cipher.ENCRYPT_MODE, tajnyKluc);
        }
        else if (mod==2){
            cipher.init(Cipher.DECRYPT_MODE, tajnyKluc);
        }
        else throw new Exception("Zly mod");


        FileInputStream vstupnyStream = null;
        byte[] vstpneBytes = new byte[0];
        try {
            vstupnyStream = new FileInputStream(vstupnyFile);
            vstpneBytes = new byte[(int) vstupnyFile.length()];
            vstupnyStream.read(vstpneBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        byte[] vystupneBytes = new byte[0];
        try {
            vystupneBytes = cipher.doFinal(vstpneBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        FileOutputStream vystupnyStream = null;
        try {
            vystupnyStream = new FileOutputStream(vystupnyFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            vystupnyStream.write(vystupneBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            vstupnyStream.close();
            vystupnyStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
