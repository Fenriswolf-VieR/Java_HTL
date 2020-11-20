import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class GuiClockApplication extends JFrame {
    private JLabel lbl_clock;
    private JButton update;

    public GuiClockApplication(String title) {

        setTitle(title);

        // Wenn kommentiert läuft das Programm im Hintergrund weiter wenn es über X
        // beendet wird
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        setSize(250, 100);
        setResizable(false);

        initComponents();

        add(lbl_clock);
        add(update);

        // Zentriert das Fenster
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void initComponents() {
        lbl_clock = new JLabel(String.format("%tT", new Date()));

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl_clock.setText(String.format("%tT", new Date()));
            }

        });
    }
}
