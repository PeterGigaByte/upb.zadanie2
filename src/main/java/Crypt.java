import javax.crypto.*;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
public class Crypt {
    public static void crypto(Key kluc, File vstupnyFile, File vystupnyFile, int mod) throws Exception {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        if (mod==1){
            cipher.init(Cipher.ENCRYPT_MODE, kluc);
        }
        else if (mod==2){
            cipher.init(Cipher.DECRYPT_MODE, kluc);
        }
        else throw new Exception("Zly mod");


        FileInputStream vstupnyStream = null;

        try {
            vstupnyStream = new FileInputStream(vstupnyFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        byte[] vystupneBytes = new byte[1024*1024];
        int bytes;
        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(vystupnyFile), cipher);

        while((bytes = vstupnyStream.read(vystupneBytes)) > 0)
        {
            cos.write(vystupneBytes, 0, bytes);
        }
        cos.flush();
        cos.close();

    }
}
