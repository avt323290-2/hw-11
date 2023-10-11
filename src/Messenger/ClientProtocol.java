package Messenger;

/**
 * Класс ClientProtocol является частью мессенджера и реализует протокол обмена данными между клиентом и сервером.
 * Он наследует функциональность LinkProtocol.
 *
 * @author Mikhail
 */
public class ClientProtocol extends LinkProtocol {

    /**
     * Обрабатывает входящее сообщение и устанавливает его состояние в мессенджере.
     *
     * @param m Входящее сообщение, которое необходимо обработать.
     */
    public static void processInput(ConnectionMessage m) {
        Messanger.setState(m);
    }

    /**
     * Получает и готовит исходящее сообщение для отправки на сервер. Сообщение составляется из
     * накопленных в очереди сообщений, после чего очередь очищается.
     *
     * @return Подготовленное исходящее сообщение для отправки на сервер.
     */
    public static ConnectionMessage getOutput() {
        ConnectionMessage m = new ConnectionMessage(messages, null, Messanger.getName());
        messages.clear();
        return m;
    }
}