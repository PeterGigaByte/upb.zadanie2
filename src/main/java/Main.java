import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {
    public static void test(String[] args) throws Exception {
        Console console = new Console();
        try{
        console.run(args);}catch (Exception e){
            System.out.println("Nastala chyba, reštartujem sa.");
            console.run(args);
        }

    }
    public static void main(String[] args) throws Exception{
        //String path = "private rsa keys/priKey_encrypted_file_dv.txt";
        //path = path.replace("encrypted_file_","");
        File file = new File("C:\\Users\\Peter\\Desktop\\link.txt");
        try
        {
            ByteBuffer bb= StandardCharsets.UTF_8.encode("value1");
            Files.setAttribute(file.toPath(), "user:custom_attribute", bb);
        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            byte[] b=(byte[])Files.getAttribute(file.toPath(), "user:custom_attribute");
            String value=StandardCharsets.UTF_8.decode(ByteBuffer.wrap(b)).toString();
            System.out.println(value);
        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        /*PublicSecretKey generator = new PublicSecretKey(1024);
        AsymmetricCrypt ac = new AsymmetricCrypt();
        File file = new File("C:\\upb\\target\\aes keys\\key_dv.txt");
        byte[] data = Files.readAllBytes(file.toPath());
        String key = ac.decryptFile(data,ac.getPrivate("C:\\upb\\target\\private rsa keys\\priKEY_dv.txt"));
        System.out.println(key);*/
    }
    public static ByteBuffer str_to_bb(String msg, Charset charset){
        return ByteBuffer.wrap(msg.getBytes(charset));
    }

    public static String bb_to_str(ByteBuffer buffer, Charset charset){
        byte[] bytes;
        if(buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return new String(bytes, charset);
    }

}
