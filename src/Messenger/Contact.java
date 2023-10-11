package Messenger;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Класс Contact представляет контакт в мессенджере, включая имя и статус онлайн/оффлайн.
 * Контакты могут быть кликнуты для активации чата.
 *
 * @author Blazej
 */
public class Contact extends JPanel implements Serializable {
    private String name;

    /**
     * Получает имя контакта.
     *
     * @return Имя контакта.
     */
    public String getContactName() {
        return name;
    }

    transient private JLabel nameLabel;
    transient private JLabel statusLabel;

    public Contact(String name) {
        super();
        this.name = name;
        status = false;

        nameLabel = new JLabel(name);
        add(nameLabel);
        statusLabel = new JLabel(getStatus());
        add(statusLabel);

        this.setMinimumSize(new Dimension(200, 16));
        this.setMaximumSize(new Dimension(400, 24));

        addMouseListener(new ContactMouseAdapter(this));
    }

    transient private boolean status;

    /**
     * Получает статус контакта (онлайн/оффлайн).
     *
     * @return Статус контакта в виде строки: "(+)" для онлайн, "(-)" для оффлайн.
     */
    private String getStatus() {
        updateStatus();
        return status ? "(+)" : "(-)";
    }

    private void updateStatus() {
        // Реализуйте этот метод, чтобы обновить статус контакта на основе актуальных данных.
    }

    private class ContactMouseAdapter extends MouseAdapter {
        private Contact source;

        public ContactMouseAdapter(Contact contact) {
            source = contact;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                Messanger.activateConversation(source);
            }
        }
    }
}