package com.codigo.wellington.cliente;

import com.codigo.wellington.shared.Person;
import com.codigo.wellington.shared.Product;
import com.codigo.wellington.shared.Salesman;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 11/08/2017 @Time 08:22:12
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class ClienteApp {

    public static void main(String[] args) throws IOException {
//        savePersonSalesman();
        saveProduct();
    }

    private static void savePersonSalesman() throws NumberFormatException, IOException {
        boolean continuar = true;
        char opcao;
        Scanner scanner = new Scanner(System.in);
        save();
        while (continuar) {
            System.out.println("Again y/n ?");
            opcao = scanner.next().charAt(0);

            if (opcao == 'n') {
                System.out.println("Good bye!");
                continuar = false;

            } else {
                save();
            }

        }
    }

    private static void saveProduct() throws NumberFormatException, IOException {
        boolean continuar = true;
        char opcao;
        Scanner scanner = new Scanner(System.in);
        save2();
        while (continuar) {
            System.out.println("Again y/n ?");
            opcao = scanner.next().charAt(0);

            if (opcao == 'n') {
                System.out.println("Good bye!");
                continuar = false;

            } else {
                save2();
            }

        }
    }

    private static void save() throws IOException, NumberFormatException {
        Socket node1 = new Socket("localhost", 1234);
        List<String> date = cadastro();
        Person person = new Person(Integer.parseInt(date.get(0)), date.get(1));
        Salesman salesman = new Salesman(person, readPhone());

        try (ObjectOutputStream saida = new ObjectOutputStream(node1.getOutputStream())) {
            saida.writeObject(salesman);
        }
    }

    private static void save2() throws IOException, NumberFormatException {
        Socket node1 = new Socket("localhost", 1234);
        List<String> date = cadastroProduct();
        Product product = new Product(Integer.parseInt(date.get(0)), date.get(1));

        try (ObjectOutputStream saida = new ObjectOutputStream(node1.getOutputStream())) {
            saida.writeObject(product);
        }

    }

    private static List<String> cadastro() {

        List<String> data = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id from Person");
        String personId = scanner.nextLine();
        data.add(personId);
        System.out.println("Enter the name from Person");
        String personName = scanner.nextLine();
        data.add(personName);

        return data;
    }

    private static List<String> cadastroProduct() {

        List<String> data = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id from Product");
        String productId = scanner.nextLine();
        data.add(productId);
        System.out.println("Enter the name from Product");
        String productName = scanner.nextLine();
        data.add(productName);

        return data;
    }

    private static String readPhone() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the phone from Salesman");
        String phone = scanner.nextLine();
        return phone;
    }

    private static String youWantContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want continue: yes/no");
        return scanner.nextLine();
    }
}

//void saveOrder() throws IOException {
//
//        Person person = new Person(1, "Kiko");
//        Salesman salesman = new Salesman(person, "8888-8888");
//        Product product = new Product(3, "Pera Verde");
//
//        Order order = new Order(1, salesman, product, 40);
//
//        try (ObjectOutputStream saida = new ObjectOutputStream(node3.getOutputStream())) {
//            saida.writeObject(order);
//        }
//    }
//    void savePersonSalesmanByNode3() throws IOException {
//
////        Person person = new Person(12, "Wellington");
////        Salesman salesman = new Salesman(person, "7777-7777");
////
////        try (ObjectOutputStream saida = new ObjectOutputStream(node3.getOutputStream())) {
////            saida.writeObject(salesman);
////        }
//    }

