import javax.crypto.*;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
public class Crypt {
    public static void cryptFunction(Key key, File inputFile, File outputFile, int mode) throws Exception {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        if (mode==1){
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        else if (mode==2){
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        else throw new Exception("Wrong mode");


        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        byte[] outputBytes = new byte[1024*1024];
        int bytes;
        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(outputFile), cipher);

        while((bytes = fileInputStream.read(outputBytes)) > 0)
        {
            cos.write(outputBytes, 0, bytes);
        }
        cos.flush();
        cos.close();

    }
}
