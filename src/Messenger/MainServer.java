package Messenger;

/**
 * Главный класс MainServer представляет точку входа в приложение мессенджера для сервера.
 * В методе `main` создается экземпляр класса `MessangerServer`, который инициализирует и запускает сервер мессенджера.
 *
 * @author Mikhail
 */
public class MainServer {

    /**
     * Точка входа в приложение мессенджера для сервера.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        MessangerServer m = new MessangerServer();
    }
}