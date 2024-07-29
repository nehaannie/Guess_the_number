import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame {
    private int secretNumber;
    private int attempts;
    private int maxAttempts;
    private JLabel guessLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;

    public GuessTheNumberGUI() {
        super("Guess the Number Game");

        secretNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        maxAttempts = 5;

        guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(guessLabel);
        panel.add(guessField);
        panel.add(guessButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if (guess == secretNumber) {
                    resultArea.append("Congratulations! You guessed the number in " + attempts + " attempts.\n");
                    guessButton.setEnabled(false);
                } else if (guess < secretNumber) {
                    resultArea.append("Try again! Your guess is too low.\n");
                } else {
                    resultArea.append("Try again! Your guess is too high.\n");
                }

                if (attempts == maxAttempts && guess != secretNumber) {
                    resultArea.append("Sorry, you've run out of attempts. The number was " + secretNumber + ".\n");
                    guessButton.setEnabled(false);
                }

                guessField.setText("");
                guessField.requestFocus();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessTheNumberGUI();
            }
        });
    }
}
