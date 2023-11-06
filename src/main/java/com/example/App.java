package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket s = new Socket("localhost", 3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String risposta = new String();
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println("Scrivi una nota o inserisci @ per vedere la lista delle note, inserire Q per uscire");
                out.writeBytes(scanner.nextLine()+ "\n");
                risposta = in.readLine();
                switch (risposta) {
                    case "Q":
                        System.out.println("Disconnesso...");
                        break;
                    case "@":
                        System.err.println("Lista Blocco Note \n");                       
                            System.out.println(in.readLine());
                            break;
                    default:
                        System.out.println("Nota inserita");
                        break;
                } 
            }while (!risposta.equals("Q"));
            scanner.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Qualcosa è andato storto, chiusura del client...");
            System.exit(1);
        }
    }
}
