package Messenger;

/**
 * Главный класс Main представляет точку входа в приложение мессенджера.
 * В методе `main` создается экземпляр класса `Messanger`, который инициализирует и запускает приложение мессенджера.
 *
 * @author Mikhail
 */
public class Main {

    /**
     * Точка входа в приложение мессенджера.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        Messanger mes = new Messanger("localhost");
    }
}