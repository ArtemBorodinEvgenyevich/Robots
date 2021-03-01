package robots.src.gui;

import robots.src.log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    JFrame parent;
    JMenu lookAndFeelMenu = new JMenu("Режим отображения");
    JMenu testMenu = new JMenu("Тесты");

    public MenuBar(JFrame parent) {
        this.parent = parent;

        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");
        setSystemLookAndFeelItem();
        setCrossPlatformLookAndFeel();

        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");
        setLogMessageItem();

        this.add(lookAndFeelMenu);
        this.add(testMenu);

    }

    private void setSystemLookAndFeelItem() {
        JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            parent.invalidate();
        });
        lookAndFeelMenu.add(systemLookAndFeel);
    }

    private void setCrossPlatformLookAndFeel() {
        JMenuItem crossPlatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
        crossPlatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            parent.invalidate();
        });
        lookAndFeelMenu.add(crossPlatformLookAndFeel);
    }

    private void setLogMessageItem() {
        JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug("Новая строка");
        });
        testMenu.add(addLogMessageItem);
    }

    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(parent);
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }
}
