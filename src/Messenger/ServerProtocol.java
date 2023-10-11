package Messenger;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс ServerProtocol представляет протокол серверной части мессенджера для обработки входящих и исходящих сообщений.
 * Он хранит сообщения, полученные от клиентов, и позволяет отправлять сообщения клиентам.
 *
 * @author Mikhail
 */
public class ServerProtocol {

    static {
        messages = new CopyOnWriteArrayList<Message>();
    }
    private static CopyOnWriteArrayList<Message> messages;

    /**
     * Обрабатывает входящие сообщения от клиентов, добавляя их во внутренний список сообщений сервера.
     *
     * @param m Объект ConnectionMessage, содержащий входящие сообщения от клиентов.
     */
    public static void processInput(ConnectionMessage m) {
        Message mess;
        while ((mess = m.getMessage()) != null) {
            System.out.println("Cообщение, полученное от " + mess.getSender() + " для " + mess.getRecipient() + ": " + mess.getMessage());
            sendMessage(mess);
        }
    }

    /**
     * Отправляет сообщение, добавляя его во внутренний список сообщений сервера.
     *
     * @param m Сообщение, которое нужно отправить.
     */
    public static void sendMessage(Message m) {
        messages.add(m);
    }

    /**
     * Получает и удаляет сообщения, предназначенные для конкретного клиента.
     *
     * @param clientName Имя клиента, для которого нужно получить сообщения.
     * @return Объект ConnectionMessage, содержащий сообщения для клиента.
     */
    public static ConnectionMessage getOutput(String clientName) {
        Stack<Message> clientMessages = new Stack<Message>();
        Iterator i = messages.iterator();
        while (i.hasNext()) {
            Message m = (Message)i.next();
            if (m.getRecipient().equals(clientName)) {
                clientMessages.push(m);
                messages.remove(m);
            }
        }
        return new ConnectionMessage(clientMessages, null, "server");
    }
}