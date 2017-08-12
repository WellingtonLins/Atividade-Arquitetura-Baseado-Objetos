package com.codigo.wellington.node2;

import com.codigo.wellington.shared.Order;
import com.codigo.wellington.shared.Person;
import com.codigo.wellington.shared.Product;
import com.codigo.wellington.shared.Salesman;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Date 11/08/2017 @Time 08:37:23
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Node2 {

    public static void main(String[] args) throws IOException, Exception {
        ServerSocket server = new ServerSocket(1235);

        while (true) {
            try {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Object object = entrada.readObject();

                String operador = object.getClass().getSimpleName();

                System.out.println(operador);

                switch (operador) {
                    case "Salesman":

                        Salesman salesman = (Salesman) object;
                        DaoNode2.persistir(salesman);
                        Person person = salesman.getPerson();
                       if(person.getId() ==  DaoNode2.consultaPerson(person).getId()){
                           System.out.println("ID já cadastrado!");
                           break;
                           
                       }
                        Socket node1 = new Socket("localhost", 1234);

                        try (ObjectOutputStream saida
                                = new ObjectOutputStream(node1.getOutputStream())) {
                            saida.writeObject(person);
                        }
                        break;
                    case "Order":
                        Order order = (Order) object;
                        Salesman salesman1 = order.getSalesman();
                        DaoNode2.persistir(salesman1);
                        
                        Socket fornode1 = new Socket("localhost", 1234);
                        try (ObjectOutputStream saida
                                = new ObjectOutputStream(fornode1.getOutputStream())) {
                            saida.writeObject(order);
                        }
                        break;
                    default:
                        System.out.println("Node2  Erro durante processamento");
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {

                System.out.println("Node2 erro " + e.getMessage());
            }
        }

    }
}
