package br.upe;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        LogData log = new LogData();
        log.read("./projeto-log/src/files/access.log");
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
                    log.write("recursosGrandes");
                    break;
                case 2:
                    log.write("naoRespondidos");
                    break;
                case 3:
                    log.write("OS");
                    break;
                case 4:
                    System.out.println(log.makeAverage());
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