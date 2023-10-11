package Messenger;

import java.io.Serializable;
import java.util.Stack;

/**
 * Класс ConnectionMessage представляет сообщение, которое используется для обмена данными между клиентом и сервером.
 * Содержит информацию о отправителе, текстовых сообщениях, количестве сообщений и онлайн-пользователях.
 *
 * @author Mikhail
 */
public class ConnectionMessage implements Serializable {

    private String from;

    /**
     * Получает имя отправителя сообщения.
     *
     * @return Имя отправителя.
     */
    public String getSender() {
        return from;
    }

    private int currentMessage;
    private int messageCount;
    private Message messages[];

    /**
     * Получает следующее текстовое сообщение из массива сообщений.
     *
     * @return Следующее текстовое сообщение или null, если больше нет сообщений.
     */
    public Message getMessage() {
        if (messageCount > 0 && currentMessage < messageCount)
            return messages[currentMessage++];
        else
            return null;
    }

    /**
     * Получает количество текстовых сообщений в объекте ConnectionMessage.
     *
     * @return Количество текстовых сообщений.
     */
    public int getMessagesCount() {
        return messageCount;
    }

    private String onlineUsers[];

    /**
     * Проверяет, находится ли пользователь в режиме онлайн.
     *
     * @param name Имя пользователя, которое нужно проверить.
     * @return true, если пользователь находится в режиме онлайн, в противном случае - false.
     */
    public boolean isOnline(String name) {
        for (String s : onlineUsers) {
            if (s.equals(name))
                return true;
        }
        return false;
    }

    /**
     * Конструктор класса ConnectionMessage.
     *
     * @param messages    Стек сообщений, которые будут отправлены.
     * @param online      Массив онлайн-пользователей.
     * @param from        Имя отправителя сообщения.
     */
    public ConnectionMessage(Stack<Message> messages, String[] online, String from) {
        this.messageCount = messages.size();
        this.currentMessage = 0;
        this.messages = new Message[this.messageCount];
        messages.toArray(this.messages);
        this.onlineUsers = online;
        this.from = from;
    }
}