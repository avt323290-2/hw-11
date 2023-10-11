package Messenger;

import java.io.Serializable;

/**
 * Класс Message представляет сообщение, которое может быть отправлено между пользователями в мессенджере.
 * Он содержит информацию о отправителе, получателе и текстовом сообщении.
 *
 * @author Mikhail
 */
public class Message implements Serializable {

    private String from;

    /**
     * Получает имя отправителя сообщения.
     *
     * @return Имя отправителя.
     */
    public String getSender() {
        return from;
    }

    private String to;

    /**
     * Получает имя получателя сообщения.
     *
     * @return Имя получателя.
     */
    public String getRecipient() {
        return to;
    }

    private String message;

    /**
     * Получает текстовое сообщение.
     *
     * @return Текстовое сообщение.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Конструктор класса Message.
     *
     * @param from Имя отправителя сообщения.
     * @param to Имя получателя сообщения.
     * @param message Текстовое сообщение.
     */
    public Message(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }
}