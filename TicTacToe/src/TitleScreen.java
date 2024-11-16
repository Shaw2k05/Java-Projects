import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TitleScreen extends Component implements ActionListener, WindowListener {

    ImageIcon icon = new ImageIcon("resources/icon-no-bg(60px).png");
    JFrame frame = new JFrame();
    JButton twoPlayerButton = new JButton("2 Player Mode");
    JButton singlePlayerButton = new JButton("Single Player Mode");

    TitleScreen() {

        twoPlayerButton.setBounds(100, 120, 200, 40);
        twoPlayerButton.setFocusable(true);
        twoPlayerButton.addActionListener(this);

        singlePlayerButton.setBounds(100, 200, 200, 40);
        singlePlayerButton.setFocusable(true);
        singlePlayerButton.addActionListener(this);

        frame.add(singlePlayerButton);
        frame.add(twoPlayerButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.addWindowListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == twoPlayerButton) {

            frame.dispose();
            TwoPlayerMode twoPlayerMode = new TwoPlayerMode();
        }

        if(e.getSource() == singlePlayerButton) {

            frame.dispose();
            SinglePlayerMode singlePlayerMode = new SinglePlayerMode();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String[] responses = {"Yes, I'm done for now", "No, I wanna play more"};

        int choice = JOptionPane.showOptionDialog(
                this,
                "Do you want to close the game?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                responses,
                0
        );

        if(choice == 0) {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else {

            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE   );
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
