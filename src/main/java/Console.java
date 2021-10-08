import java.io.*;
import java.security.Key;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Console {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int mode;
    public void run(String[] args) throws Exception {
        File theDir = new File("encripted");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        theDir = new File("decripted");
        if (!theDir.exists()){
            theDir.mkdir();
        }
        theDir = new File("keys");
        if (!theDir.exists()){
            theDir.mkdir();
        }
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
                FileWriter saveKey = new FileWriter("keys/key_"+inputFile.getName().replaceFirst("[.][^.]+$", "")+".txt");
                saveKey.write(generateKey.keyToString());
                saveKey.close();
            }else{
                keyFile = loadKey();
                Scanner reader = new Scanner(keyFile);
                String dataKey = null;
                while (reader.hasNextLine()) {
                    dataKey = reader.nextLine();
                }
                key = GenerateKey.stringToKey(dataKey);
            }
        }else{
            keyFile = loadKey();
            Scanner reader = new Scanner(keyFile);
            String dataKey = null;
            while (reader.hasNextLine()) {
                dataKey = reader.nextLine();
            }
            key = GenerateKey.stringToKey(dataKey);
        }
        System.out.println();
        File outputFile;
        if(mode==1){
            System.out.println();
            System.out.println("Zahajuje sa šifrovanie.");
             outputFile = new File( "encripted/encripted_file_"+inputFile.getName());
        }else{
            System.out.println();
            System.out.println("Zahajuje sa dešifrovanie.");
             outputFile = new File( "decripted/decribted_file_"+inputFile.getName());
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
