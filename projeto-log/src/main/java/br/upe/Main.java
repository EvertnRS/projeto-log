package br.upe;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        LogData l = new LogData();
        l.read("./access.log");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Menu:");
            System.out.println("1 - Recursos grandes respondidos");
            System.out.println("2 - Não respondidos");
            System.out.println("3 - % de requisições por SO");
            System.out.println("4 - Média das requisições POST");
            System.out.println("0 - Sair");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    l.write("recursosGrandes");
                    break;
                case 2:
                    l.write("naoRespondidos");
                    break;
                case 3:
                    l.write("OS");
                    break;
                case 4:
                    System.out.println(l.makeAverage());
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }

        } while (option != 0);
        sc.close();
    }
}
