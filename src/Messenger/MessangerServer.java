package Messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс MessangerServer представляет серверную часть мессенджера.
 * Он слушает и принимает входящие подключения от клиентов на порту 4444 и создает отдельные потоки для каждого клиента.
 *
 * @author Mikhail
 */
public class MessangerServer {

    private ServerSocket serverSocket;

    /**
     * Конструктор класса MessangerServer.
     * Инициализирует серверный сокет и начинает слушать входящие подключения от клиентов.
     */
    public MessangerServer() {
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Connection accepted on port: 4444");
                Thread t = new Thread(new ClientThread(clientSocket));
                t.start();
            } catch (IOException e) {
                System.out.println("Accept failed on port: 4444");
            }
        }
    }
}