package Messenger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Класс ClientThread представляет собой поток клиента, который обрабатывает входящие и исходящие
 * сообщения с сервером.
 *
 * @author Mikhail
 */
public class ClientThread implements Runnable {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    /**
     * Конструктор класса ClientThread.
     *
     * @param clientSocket Сокет клиента, через который будет установлено соединение.
     */
    public ClientThread(Socket clientSocket) {
        socket = clientSocket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("IO exception 1 on port: 4444");
            System.exit(-1);
        }
    }

    /**
     * Запускает поток клиента для обмена сообщениями с сервером.
     */
    public void run() {
        System.out.println("Client thread ran.");
        ConnectionMessage m;
        try {
            while ((m = (ConnectionMessage) in.readObject()) != null) {
                ServerProtocol.processInput(m);
                out.writeObject(ServerProtocol.getOutput(m.getSender()));
            }
        } catch (IOException e) {
            System.out.println("IO exception 2 on port: 4444");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception on port: 4444");
        }
    }
}