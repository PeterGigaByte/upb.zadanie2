import java.io.File;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws Exception {
        Console console = new Console();
        try{
        console.run(args);}catch (Exception e){
            System.out.println("Nastala chyba, reštartujem sa.");
            console.run(args);
        }

    }
    public static void test(String[] args) throws Exception{
        PublicSecretKey generator = new PublicSecretKey(1024);
        AsymmetricCrypt ac = new AsymmetricCrypt();
        File file = new File("C:\\upb\\target\\aes keys\\key_dv.txt");
        byte[] data = Files.readAllBytes(file.toPath());
        String key = ac.decryptFile(data,ac.getPrivate("C:\\upb\\target\\private rsa keys\\priKEY_dv.txt"));
        System.out.println(key);
    }

}
