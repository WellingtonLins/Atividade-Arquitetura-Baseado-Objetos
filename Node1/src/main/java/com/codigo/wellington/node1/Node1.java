package com.codigo.wellington.node1;

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
public class Node1 {

    public static void main(String[] args) throws IOException, Exception {
        ServerSocket server = new ServerSocket(1234);

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
                        DaoNode1.persistPerson(salesman.getPerson());
                        Socket node2 = new Socket("localhost", 1235);

                        try (ObjectOutputStream saida
                                = new ObjectOutputStream(node2.getOutputStream())) {
                            saida.writeObject(salesman);
                        }
                        break;
                    case "Product":

                        Product product = (Product) object;
                        DaoNode1.persistProduct(product);
                        break;
                    case "Person":

                        Person person = (Person) object;
                        DaoNode1.persistPerson(person);
                        break;
                    default:
                        System.out.println("Node1 Erro durante processamento");
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
