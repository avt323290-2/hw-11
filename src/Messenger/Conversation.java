package Messenger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Класс Conversation представляет окно чата в мессенджере для общения с определенным контактом (собеседником).
 * Он позволяет отправлять и принимать сообщения, а также активировать окно чата.
 *
 * @author Mikhail
 */
public class Conversation {
    private Contact interlocutor;
    private Vector<JLabel> messages;
    private JFrame window;
    private JTextField message;
    private JPanel content;
    private JPanel history;

    /**
     * Конструктор класса Conversation.
     *
     * @param interlocutor Контакт собеседника для чата.
     */
    public Conversation(Contact interlocutor) {
        this.interlocutor = interlocutor;
        messages = new Vector<JLabel>();

        window = new JFrame(interlocutor.getContactName());
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        history = new JPanel();
        history.setLayout(new BoxLayout(history, BoxLayout.Y_AXIS));
        history.setMinimumSize(new Dimension(400, 100));
        history.setPreferredSize(new Dimension(400, 400));
        content.add(history);

        message = new JTextField();
        message.addKeyListener(new MessageKeyAdapter());
        content.add(message);

        window.setContentPane(content);
        window.setSize(400, 450);
        window.setVisible(false);
        window.setResizable(false);

        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * Активирует окно чата.
     */
    public void activate() {
        window.setVisible(true);
    }

    private void sendMessage(String text) {
        ClientProtocol.sendMessage(new Message(Messanger.getName(), interlocutor.getContactName(), text));
        showMessage(new JLabel(text));
    }

    /**
     * Принимает сообщение от собеседника и отображает его в окне чата.
     *
     * @param message Сообщение от собеседника.
     */
    public void receiveMessage(Message message) {
        JLabel m = new JLabel(message.getMessage());
        m.setBackground(Color.ORANGE);
        m.setOpaque(true);
        showMessage(m);
        activate();
    }

    /**
     * Отображает сообщение в окне чата.
     *
     * @param text JLabel, представляющий текст сообщения.
     */
    public void showMessage(JLabel text) {
        messages.add(text);
        history.add(messages.lastElement());
        history.revalidate();
        history.repaint();
    }

    private class MessageKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMessage(message.getText());
                message.setText(null);
            }
        }
    }
}