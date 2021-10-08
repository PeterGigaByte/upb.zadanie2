import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Console console = new Console();
        try{
        console.run(args);}catch (Exception e){
            System.out.println("Nastala chyba, reštartujem sa.");
            console.run(args);
        }

    }

}
