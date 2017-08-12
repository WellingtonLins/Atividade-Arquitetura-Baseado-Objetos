package com.codigo.wellington.node3;

import com.codigo.wellington.shared.Order;
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
public class Node3 {

    public static void main(String[] args) throws IOException, Exception {
        ServerSocket server = new ServerSocket(12347);

        while (true) {

            Socket socket = server.accept();
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            try {
                node3SaveOrder(server);
            } catch (ClassCastException e) {
                node3SaveSalesman(server);
            }
        }

    }

    private static void node3SaveOrder(ServerSocket server) throws IOException, ClassNotFoundException, Exception {

        Socket socket = server.accept();
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        Order order = (Order) entrada.readObject();
        DaoNode3.persistOrder(order);
        node3SaveSalesman(server);
    }

    private static void node3SaveSalesman(ServerSocket server) throws IOException, ClassNotFoundException, Exception {
  Socket socket = server.accept();
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        Socket node2 = new Socket("localhost", 12346);

        Salesman salesman = (Salesman) entrada.readObject();

        try (ObjectOutputStream saida
                = new ObjectOutputStream(node2.getOutputStream())) {
            saida.writeObject(salesman);
        }
    }
}
