package Messenger;

import java.util.Stack;

/**
 * Класс LinkProtocol представляет базовый протокол для обмена сообщениями между клиентом и сервером.
 * Он содержит стек сообщений для отправки между клиентом и сервером.
 * Дочерние классы, такие как ClientProtocol и ServerProtocol, используют этот протокол для отправки и приема сообщений.
 *
 * @author Mikhail
 */
public class LinkProtocol {

    static {
        messages = new Stack<Message>();
    }

    protected static Stack<Message> messages;

    /**
     * Отправляет сообщение, добавляя его в стек сообщений для отправки.
     *
     * @param m Сообщение, которое нужно отправить.
     */
    public static void sendMessage(Message m) {
        messages.push(m);
    }
}