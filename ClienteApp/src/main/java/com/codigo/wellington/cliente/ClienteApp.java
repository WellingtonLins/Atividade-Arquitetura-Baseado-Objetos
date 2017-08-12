package com.codigo.wellington.cliente;

import com.codigo.wellington.shared.Order;
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
//                savePersonSalesman();
                savePersonSalesmanViaNode3();
//                saveProduct();
//        saveOrder();
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

    private static void savePersonSalesmanViaNode3() throws NumberFormatException, IOException {
        boolean continuar = true;
        char opcao;
        Scanner scanner = new Scanner(System.in);
        saveNode3();
        while (continuar) {
            System.out.println("Again y/n ?");
            opcao = scanner.next().charAt(0);

            if (opcao == 'n') {
                System.out.println("Good bye!");
                continuar = false;

            } else {
                saveNode3();
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

    private static void saveNode3() throws IOException, NumberFormatException {
        Socket node3 = new Socket("localhost", 1236);
        List<String> date = cadastro();
        Person person = new Person(Integer.parseInt(date.get(0)), date.get(1));
        Salesman salesman = new Salesman(person, readPhone());

        try (ObjectOutputStream saida = new ObjectOutputStream(node3.getOutputStream())) {
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

    private static void saveOrder() throws IOException {
        Socket node3 = new Socket("localhost", 1236);
        List<String> date = cadastro();
        Person person = new Person(Integer.parseInt(date.get(0)), date.get(1));
        Salesman salesman = new Salesman(person, readPhone());

        List<String> dateProduct = cadastroProduct();
        Product product = new Product(Integer.parseInt(dateProduct.get(0)),
                dateProduct.get(1));
        List<Integer> dateOrder = cadastroOrder();
        Order order = new Order(dateOrder.get(0), salesman, product, dateOrder.get(1));

        try (ObjectOutputStream saida = new ObjectOutputStream(node3.getOutputStream())) {
            saida.writeObject(order);
        }
    }

    private static List<Integer> cadastroOrder() {
        List<Integer> data = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id from Order");
        int id = scanner.nextInt();
        data.add(id);
                
        System.out.println("Enter the quantity");
        int qnt = scanner.nextInt();
        data.add(qnt);

        return data;
    }
}
