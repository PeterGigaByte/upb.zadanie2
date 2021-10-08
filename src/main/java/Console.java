import java.io.*;

public class Console {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int mode;
    public void run(String[] args) throws Exception {
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
        File outputFile;
        if(mode==1){
            System.out.println();
            System.out.println("Zahajuje sa šifrovanie.");
             outputFile = new File( "encripted_file");
        }else{
            System.out.println();
            System.out.println("Zahajuje sa dešifrovanie.");
             outputFile = new File( "decribted_file");
        }
        try{
        Crypt.crypto( "Mary has one cat",inputFile,outputFile,mode);
        if(mode==1){
            System.out.println();
            System.out.println("Akcia bola dokončená.");

        }else{
            System.out.println();
            System.out.println("Akcia bola dokončená.");
        }}catch (Exception e){
            System.out.println();
            System.out.println("Niečo sa pokazilo.");
        }
        end(args);
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
