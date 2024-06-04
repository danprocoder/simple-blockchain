package com.test;

import java.util.Scanner;

import com.test.blockchain.Miner;
import com.test.blockchain.Transaction;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Action: ");
            String command = scanner.nextLine();

            if (command.equals("transfer")) {
                System.out.print("From Addr: ");
                String from = scanner.nextLine();

                System.out.print("To Addr: ");
                String to = scanner.nextLine();

                System.out.print("Amount: ");
                String amount = scanner.nextLine();

                Miner.getInstance().onTransaction(new Transaction(from, to, Double.parseDouble(amount)));
            } else if (command.equals("print-blockchain")) {
                System.out.println("In progress");
            } else if (command.equals("exit")) {
                break;
            }
        }

        scanner.close();
    }
}
