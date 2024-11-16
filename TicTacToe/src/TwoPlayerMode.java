import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;
import java.util.Random;

public class TwoPlayerMode extends Component implements ActionListener, WindowListener {

    Random random = new Random();

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = 	new JLabel();
    JButton[] buttons = new JButton[9];

    boolean player1_turn;
    int squaresFilled = 0;

    ImageIcon icon = new ImageIcon("resources/icon-no-bg(60px).png");

    TwoPlayerMode() {

        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(this);

        textfield.setBackground(new Color(9, 0, 57));
        textfield.setForeground(new Color(255, 189, 57));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(9, 0, 136));

        for(int i = 0; i < 9; i++) {

                buttons[i] = new JButton();
                button_panel.add(buttons[i]);
                buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[i].setEnabled(false);
                buttons[i].setFocusable(false);
                buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(squaresFilled < 9) {

            String[] responses = {"Yes, I want to exit", "No, I wanna continue"};

            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Your game is still ongoing\nDo you want to abandon the current game?",
                    "Abandon Ongoing Game?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    responses,
                    -1
            );

            if (choice == 0) {

                frame.dispose();
                TitleScreen screen = new TitleScreen();
            }
            else {

                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }

        else {

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public void firstTurn() {

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        if(random.nextInt(2) == 0) {

            player1_turn = true;
            textfield.setText("X Turn");
        }

        else {

            player1_turn = false;
            textfield.setText("O Turn");
        }

        for(int i = 0; i < 9; i++) {

            buttons[i].setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 9; i++) {

            if(e.getSource() == buttons[i]) {

                if(player1_turn) {

                    if(Objects.equals(buttons[i].getText(), "")) {
                        buttons[i].setForeground(new Color(147, 0, 119));
                        buttons[i].setText("X");
                        squaresFilled++;
                        player1_turn = false;
                        textfield.setText("O Turn");
                        checkWin();
                        checkDraw();
                    }
                }

                else {

                    if(Objects.equals(buttons[i].getText(), "")) {

                        buttons[i].setForeground(new Color(228, 0, 124));
                        buttons[i].setText("O");
                        squaresFilled++;
                        player1_turn = true;
                        textfield.setText("X Turn");
                        checkWin();
                        checkDraw();
                    }
                }
            }
        }
    }

    public void checkWin() {

        for(int i = 0; i < 7; i += 3) {
            if(
                    Objects.equals(buttons[i].getText(), "X") &&
                            Objects.equals(buttons[1 + i].getText(), "X") &&
                            Objects.equals(buttons[2 + i].getText(), "X")
            ) {
                xWin(i, 1 + i, 2 + i);
            }
        }

        for(int i = 0; i < 3; i++) {
            if(
                    Objects.equals(buttons[i].getText(), "X") &&
                            Objects.equals(buttons[3 + i].getText(), "X") &&
                            Objects.equals(buttons[6 + i].getText(), "X")
            ) {
                xWin(i, 3 + i, 6 + i);
            }
        }

        if(
                Objects.equals(buttons[0].getText(), "X") &&
                        Objects.equals(buttons[4].getText(), "X") &&
                        Objects.equals(buttons[8].getText(), "X")
        ) {
            xWin(0, 4, 8);
        }

        if(
                Objects.equals(buttons[2].getText(), "X") &&
                        Objects.equals(buttons[4].getText(), "X") &&
                        Objects.equals(buttons[6].getText(), "X")
        ) {
            xWin(2, 4, 6);
        }

        //for O to win
        for(int i = 0; i < 7; i += 3) {
            if(
                    Objects.equals(buttons[i].getText(), "O") &&
                            Objects.equals(buttons[1 + i].getText(), "O") &&
                            Objects.equals(buttons[2 + i].getText(), "O")
            ) {
                oWin(i, 1 + i, 2 + i);
            }
        }

        for(int i = 0; i < 3; i++) {
            if(
                    Objects.equals(buttons[i].getText(), "O") &&
                            Objects.equals(buttons[3 + i].getText(), "O") &&
                            Objects.equals(buttons[6 + i].getText(), "O")
            ) {
                oWin(i, 3 + i, 6 + i);
            }
        }

        if(
                Objects.equals(buttons[0].getText(), "O") &&
                        Objects.equals(buttons[4].getText(), "O") &&
                        Objects.equals(buttons[8].getText(), "O")
        ) {
            oWin(0, 4, 8);
        }

        if(
                Objects.equals(buttons[2].getText(), "O") &&
                        Objects.equals(buttons[4].getText(), "O") &&
                        Objects.equals(buttons[6].getText(), "O")
        ) {
            oWin(2, 4, 6);
        }
    }

    public void xWin (int a, int b, int c) {
        buttons[a].setBackground(new Color(255, 189, 57));
        buttons[b].setBackground(new Color(255, 189, 57));
        buttons[c].setBackground(new Color(255, 189, 57));

        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins");
        replay();
    }

    public void oWin (int a, int b, int c) {
        buttons[a].setBackground(new Color(255, 189, 57));
        buttons[b].setBackground(new Color(255, 189, 57));
        buttons[c].setBackground(new Color(255, 189, 57));

        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins");
        replay();
    }

    public void checkDraw () {
        if(squaresFilled == 9) {
            for(int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textfield.setText("Draw");
            replay();
        }
    }

    public void replay() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        String[] responses = {"Play again", "Go back to title screen"};

        int choice = JOptionPane.showOptionDialog(
                this,
                "Do you want to replay?",
                "Play again?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                icon,
                responses,
                -1
        );

        if(choice == 0) {

            frame.dispose();
            TwoPlayerMode twoPlayerMode = new TwoPlayerMode();
        }
        else {

            frame.dispose();
            TitleScreen screen = new TitleScreen();
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
