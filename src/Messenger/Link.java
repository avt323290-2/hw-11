package Messenger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Класс Link представляет собой соединение между клиентом и сервером по сети.
 * Он устанавливает и поддерживает соединение, обеспечивая передачу данных между клиентом и сервером.
 *
 * @author Mikhail
 */
public class Link {

    Socket kkSocket;
    ObjectOutputStream out;
    ObjectInputStream in;

    private String host;

    /**
     * Конструктор класса Link. Устанавливает соединение с сервером.
     *
     * @param hostName Имя хоста (сервера), к которому нужно установить соединение.
     */
    public Link(String hostName) {
        host = hostName;
        try {
            kkSocket = new Socket(host, 4444);
            out = new ObjectOutputStream(kkSocket.getOutputStream());
            in = new ObjectInputStream(kkSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Не удалось определить хост: " + host);
        } catch (IOException e) {
            System.err.println("Не удалось установить соединение с: " + host);
        }

        Thread t = new Thread(new ConnectionLoop());
        t.start();
    }

    private class ConnectionLoop implements Runnable {

        public void run() {
            ConnectionMessage fromServer;
            try {
                out.writeObject(ClientProtocol.getOutput());
                while ((fromServer = (ConnectionMessage) in.readObject()) != null) {
                    ClientProtocol.processInput(fromServer);
                    out.writeObject(ClientProtocol.getOutput());
                    Thread.sleep(100);
                }
            } catch (IOException e) {
                System.err.println("Ошибка соединения");
            } catch (ClassNotFoundException e) {
                System.err.println("Ошибка соединения");
            } catch (InterruptedException e) {
                System.err.println("Прерывание цикла соединения");
            }
        }
    }
}