import java.io.*;

public class Console {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public void run(String[] args) throws IOException {
        System.out.println();
        System.out.println("Vítam vás v tejto konzolovej aplikácii pre šifrovanie alebo dešifrovanie dokumentov. Pozorne čítajte konzolu pre správne fungovanie aplikácie.");
        System.out.println();
        System.out.println("Krok 1.");
        System.out.println("Zadajte či chcete šifrovať/dešifrovať.");
        System.out.println();
        System.out.println("Pre šifrovanie napíšte 'c'");
        System.out.println("Pre dešifrovanie napíšte 'd'");
        String name = reader.readLine();
        while (!name.equals("c") && !name.equals("d")){
            System.out.println();
            System.out.println("Nesprávne.");
            System.out.println();
            System.out.println("Krok 1.");
            System.out.println();
            System.out.println("Pre šifrovanie napíšte 'c'");
            System.out.println("Pre dešifrovanie napíšte 'd'");
            name = reader.readLine();
        }
        System.out.println();
        if(name.equals("d") ){
            System.out.println("Vybrali ste si dešifrovanie.");
        }else{
            System.out.println("Vybrali ste si šifrovanie.");
        }
        System.out.println();
        System.out.println("Výborne, v nasledujúcom kroku je potrebné mi dať vedieť, ktorý súbor chceme dešifrovať/zašifrovať.");
        System.out.println();
        System.out.println("Krok 2.");
        System.out.println();
        System.out.println("Napíšte mi celkovú cestu k súboru.");
        System.out.println("Príklad formátu: 'C:\\AiOLog.txt' ");
        File inputFile = new File(name = reader.readLine());
        while (!inputFile.exists()){
            System.out.println();
            System.out.println("Tento súbor neexistuje alebo je zle uvedená cesta.");
            System.out.println();
            System.out.println("Krok 2.");
            System.out.println();
            System.out.println("Napíšte mi celkovú cestu k súboru.");
            System.out.println("Príklad formátu: 'C:\\AiOLog.txt' ");
            System.out.println();
            inputFile = new File(name = reader.readLine());
        }
        System.out.println("Výborne, súbor bol nájdený.");
        System.out.println("File name: " + inputFile.getName());
        System.out.println("Absolute path: " + inputFile.getAbsolutePath());
        System.out.println("Writeable: " + inputFile.canWrite());
        System.out.println("Readable " + inputFile.canRead());
        System.out.println("File size in bytes " + inputFile.length());
    }
    public boolean crypt(File inputFile){
        return true;

    }
    public boolean decrypt(File inputFile){
        return false;
    }
}
