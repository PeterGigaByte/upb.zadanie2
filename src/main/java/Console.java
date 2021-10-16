import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.file.Files;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Console {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    AsymmetricCrypt ac = new AsymmetricCrypt();
    PublicSecretKey generator = new PublicSecretKey(1024);
    int mode;

    public Console() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
    }

    public void run(String[] args) throws Exception {
        File theDir = new File("encrypted");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        theDir = new File("decrypted");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        theDir = new File("aes keys");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        theDir = new File("public rsa keys");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        theDir = new File("private rsa keys");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        //krok 1 vyber co chceme robit
        System.out.println();
        System.out.println("Vítam vás v tejto konzolovej aplikácii pre šifrovanie alebo dešifrovanie dokumentov.");
        System.out.println("Pozorne čítajte konzolu pre správne fungovanie aplikácie.");
        System.out.println();
        System.out.println("Krok 1.");
        System.out.println("Zadajte či chcete šifrovať/dešifrovať.");
        System.out.println();
        System.out.println("Pre šifrovanie napíšte 'c'");
        System.out.println("Pre dešifrovanie napíšte 'd'");
        String name = reader.readLine();
        while (!name.equals("c") && !name.equals("d")){
            for (int i = 0; i<50; i++){
                System.out.println();
            }
            System.out.println();
            System.out.println("Nesprávne.");
            System.out.println();
            System.out.println("Krok 1.");
            System.out.println();
            System.out.println("Pre šifrovanie napíšte 'c'");
            System.out.println("Pre dešifrovanie napíšte 'd'");
            name = reader.readLine();
        }
        for (int i = 0; i<50; i++){
            System.out.println();
        }
        System.out.println();
        if(name.equals("d") ){
            mode=2;
            System.out.println("Vybrali ste si dešifrovanie.");
        }else{
            mode=1;
            System.out.println("Vybrali ste si šifrovanie.");
        }
        //krok 2 vyber suboru co chceme sifrovat/desifrovat
        System.out.println();
        System.out.println("Výborne, v nasledujúcom kroku je potrebné mi dať vedieť, ktorý súbor chceme dešifrovať/zašifrovať.");
        System.out.println();
        System.out.println("Krok 2.");
        System.out.println();
        System.out.println("Napíšte mi celkovú cestu k súboru.");
        System.out.println("Príklad formátu: 'C:\\AiOLog.txt' ");
        System.out.println("Príklad formátu 2(ak sa súbor nachádza v rovnakom priečinku): 'AiOLog.txt' ");
        File inputFile = new File(reader.readLine());
        while (!inputFile.exists()){
            for (int i = 0; i<50; i++){
                System.out.println();
            }
            System.out.println();
            System.out.println("Tento súbor neexistuje alebo je zle uvedená cesta.");
            System.out.println();
            System.out.println("Krok 2.");
            System.out.println();
            System.out.println("Napíšte mi celkovú cestu k súboru.");
            System.out.println("Príklad formátu: 'C:\\AiOLog.txt' ");
            System.out.println("Príklad formátu 2(ak sa súbor nachádza v rovnakom priečinku): 'AiOLog.txt' ");
            System.out.println();
            inputFile = new File( reader.readLine());
        }
        for (int i = 0; i<50; i++){
            System.out.println();
        }
        //krok 3 vyber kluca na zasifrovanie/desifrovanie
        System.out.println();
        System.out.println("Výborne, súbor bol nájdený.");
        System.out.println();
        System.out.println("File name: " + inputFile.getName());
        System.out.println("Absolute path: " + inputFile.getAbsolutePath());
        System.out.println("Writeable: " + inputFile.canWrite());
        System.out.println("Readable " + inputFile.canRead());
        System.out.println("File size in bytes " + inputFile.length());
        System.out.println();
        System.out.println("Krok 3.");
        System.out.println("Vyberte kľúč.");
        File keyFile;
        Key key;
        //krok 3 sifrovanie
        if (mode==1){
            System.out.println("Pre náhodnú generáciu kľúča napíšte 'randomKey'.");
            System.out.println("Pre načítanie kľúča z textového dokumentu .txt zadajte 'loadKey'.");
            name=reader.readLine();
            while (!name.equals("randomKey") && !name.equals("loadKey")){
                for (int i = 0; i<50; i++){
                    System.out.println();
                }
                System.out.println();
                System.out.println("Nesprávne.");
                System.out.println();
                System.out.println("Krok 3.");
                System.out.println("Vyberte kľúč.");
                System.out.println();
                System.out.println("Pre náhodnú generáciu kľúča napíšte 'randomKey'.");
                System.out.println("Pre načítanie kľúča z textového dokumentu .txt zadajte 'loadKey'.");
                name = reader.readLine();
            }
            if(name.equals("randomKey")){
                GenerateKey generateKey = new GenerateKey();
                key=generateKey.getSecretKey();
                FileWriter saveKey = new FileWriter("aes keys/key_"+inputFile.getName().replaceFirst("[.][^.]+$", "")+".txt");
                saveKey.write(generateKey.keyToString());
                saveKey.close();
            }
            //krok 3 sifrovanie s konkretnym klucom
            else{
                keyFile = loadKey();
                Scanner reader = new Scanner(keyFile);
                String dataKey = null;
                while (reader.hasNextLine()) {
                    dataKey = reader.nextLine();
                }
                key = GenerateKey.stringToKey(dataKey);
            }
            generator.createKeys();
            generator.writeToFile("public rsa keys/pubKEY_"+inputFile.getName().replaceFirst("[.][^.]+$", "")+".txt", generator.getPublicKey().getEncoded());
            generator.writeToFile("private rsa keys/priKEY_"+inputFile.getName().replaceFirst("[.][^.]+$", "")+".txt", generator.getPrivateKey().getEncoded());
            File aesEncrypted = new File("aes keys/key_"+inputFile.getName().replaceFirst("[.][^.]+$", "")+".txt");
            byte[] data = Files.readAllBytes(aesEncrypted.toPath());
            ac.encryptFile(data,aesEncrypted,generator.getPublicKey());
        }
        //krok 3 desifrovanie s konkretnym klucom
        else{
            keyFile = loadKey();
            String dataKey = null;
            //Scanner reader = new Scanner(keyFile);
            //while (reader.hasNextLine()) {
            //    dataKey = reader.nextLine();
            //}
            byte[] data = Files.readAllBytes(keyFile.toPath());
            dataKey = ac.decryptFile(data,ac.getPrivate("C:\\upb\\target\\private rsa keys\\priKEY_dv.txt"));
            key = GenerateKey.stringToKey(dataKey);
            System.out.println(key);
        }
        System.out.println();
        File outputFile;
        //finalna akcia na zaklade predchadzajucich krokov
        if(mode==1){
            System.out.println();
            System.out.println("Zahajuje sa šifrovanie.");
             outputFile = new File( "encrypted/encrypted_file_"+inputFile.getName());
        }else{
            System.out.println();
            System.out.println("Zahajuje sa dešifrovanie.");
             outputFile = new File( "decrypted/decrypted_file_"+inputFile.getName());
        }
        try{
            TimeWatch watch = TimeWatch.start();
            Crypt.cryptFunction( key,inputFile,outputFile,mode);
            long passedTimeInMs = watch.time();
            long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);
        if(mode==1){
            System.out.println();
            System.out.println("Akcia bola dokončená.");
        }else{
            System.out.println();
            System.out.println("Akcia bola dokončená.");
        }
            System.out.println("Operácia trvala "+passedTimeInMs+" milisekúnd.");
            if(passedTimeInSeconds==2){
                System.out.println("Operácia trvala "+passedTimeInSeconds+" sekundy.");
            }else if(passedTimeInSeconds==1){
                System.out.println("Operácia trvala "+passedTimeInSeconds+" sekundu.");
            }else {
                System.out.println("Operácia trvala "+passedTimeInSeconds+" sekúnd.");
            }
        }catch (Exception e){
            System.out.println();
            System.out.println("Niečo sa pokazilo.");
        }
        end(args);
    }

    private File loadKey() throws IOException {
        File keyFile;
        System.out.println("Pre načítanie kľúča z textového dokumentu .txt zadajte cestu tak ako v predchádzajúcom kroku.");
        keyFile = new File(reader.readLine());
        while (!keyFile.exists()){
            System.out.println();
            System.out.println("Krok 3.");
            System.out.println("Vyberte kľúč.");
            System.out.println();
            System.out.println("Pre načítanie kľúča z textového dokumentu .txt zadajte cestu tak ako v predchádzajúcom kroku.");
            keyFile = new File(reader.readLine());
        }
        return keyFile;
    }

    public void end(String[] args) throws Exception {
        System.out.println();
        System.out.println("Write 'again' to try again.");
        System.out.println("or");
        System.out.println("Press enter to exit.");
        String input = reader.readLine();

        if(input.equals("again")){
            for (int i = 0; i<50; i++){
                System.out.println();
            }
            run(args);
        }
    }

}
