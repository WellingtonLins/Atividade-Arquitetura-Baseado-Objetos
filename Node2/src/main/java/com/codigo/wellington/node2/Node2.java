package com.codigo.wellington.node2;

import com.codigo.wellington.shared.Salesman;
import java.io.IOException;
import java.io.ObjectInputStream;
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

        try {
            while (true) {
                Socket socket = server.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

                Salesman salesman = (Salesman) entrada.readObject();
                DaoNode2.persistir(salesman);

            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Node2 erro " + e.getMessage());
        }
    }
}
