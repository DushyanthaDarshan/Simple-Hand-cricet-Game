package com.ddr.game.beWithFe;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SimpleCricketGame {

    private JFrame frame;
    private JTextField txtEnterAValue;
    private int turnNumber = 5;
    private int ballingTurnNumber = 5;
    private int computerTotal = 0;
    private int total = 0;
    private boolean isStart = false;
    private boolean isTimeOver = false;
    private boolean isAlreadyStart = false;
    private boolean isValidInput = false;
    private boolean isReset = false;
    private boolean isOut = false;
    private boolean isTurnsOver = false;
    private boolean isBallingStart = false;
    private boolean isAlreadyBallingStart = false;
    private boolean isBallingTurnsOver = false;
    private boolean isBallingOut = false;
    JLabel lblTimer;
    JLabel lblTurns;
    JLabel labelBallMark;
    JLabel labelBatMark;
    JLabel lblTotal;
    JLabel lblNowYouAre;
    JPanel panel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimpleCricketGame window = new SimpleCricketGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SimpleCricketGame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 0, 128));
        frame.setBounds(100, 100, 687, 457);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblGameName = new JLabel("Hand Cricket Simulation");
        lblGameName.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
        lblGameName.setForeground(Color.WHITE);
        lblGameName.setBounds(252, 0, 285, 24);
        frame.getContentPane().add(lblGameName);

        panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "TIMER", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        panel.setBounds(32, 41, 184, 54);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        lblTimer = new JLabel("20 S");
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setBounds(5, 17, 174, 32);
        panel.add(lblTimer);
        lblTimer.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTimer.setForeground(Color.BLACK);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "TURNS", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        panel_1.setBounds(469, 41, 184, 54);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        lblTurns = new JLabel("5");
        lblTurns.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurns.setBounds(5, 17, 174, 32);
        panel_1.add(lblTurns);
        lblTurns.setForeground(Color.BLACK);
        lblTurns.setFont(new Font("Dialog", Font.BOLD, 16));

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "TOTAL", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(51, 51, 51)));
        panel_2.setBounds(258, 107, 184, 54);
        frame.getContentPane().add(panel_2);

        lblTotal = new JLabel("0");
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setForeground(Color.BLACK);
        lblTotal.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTotal.setBounds(5, 17, 174, 32);
        panel_2.add(lblTotal);

        txtEnterAValue = new JTextField();
        txtEnterAValue.setBounds(196, 279, 93, 32);
        frame.getContentPane().add(txtEnterAValue);
        txtEnterAValue.setColumns(10);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(0, 0, 128));
        panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), null,
                null, null), "BAT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
        panel_3.setBounds(131, 176, 104, 54);
        frame.getContentPane().add(panel_3);
        panel_3.setLayout(null);

        labelBatMark = new JLabel("0");
        labelBatMark.setHorizontalAlignment(SwingConstants.CENTER);
        labelBatMark.setBounds(5, 17, 94, 32);
        panel_3.add(labelBatMark);
        labelBatMark.setFont(new Font("Dialog", Font.BOLD, 18));
        labelBatMark.setForeground(Color.ORANGE);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(0, 0, 128));
        panel_4.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), null,
                null, null), "BALL", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
        panel_4.setBounds(467, 176, 104, 54);
        frame.getContentPane().add(panel_4);
        panel_4.setLayout(null);

        labelBallMark = new JLabel("0");
        labelBallMark.setHorizontalAlignment(SwingConstants.CENTER);
        labelBallMark.setBounds(5, 17, 94, 32);
        panel_4.add(labelBallMark);
        labelBallMark.setForeground(Color.ORANGE);
        labelBallMark.setFont(new Font("Dialog", Font.BOLD, 18));

        lblNowYouAre = new JLabel("Please start the game");
        lblNowYouAre.setForeground(Color.WHITE);
        lblNowYouAre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNowYouAre.setBounds(12, 301, 165, 32);
        frame.getContentPane().add(lblNowYouAre);

        JLabel lblNewLabel_1 = new JLabel("Enter a value and submit it");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(80, 252, 190, 15);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnStart = new JButton("Start");
        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                lblNowYouAre.setText("Now you are Batting");
                JOptionPane.showMessageDialog(btnStart, "Game started!");
                populateStart();
            }
        });
        btnStart.setForeground(new Color(255, 255, 255));
        btnStart.setFont(new Font("Dialog", Font.BOLD, 16));
        btnStart.setBackground(new Color(75, 0, 130));
        btnStart.setBounds(345, 342, 117, 32);
        frame.getContentPane().add(btnStart);

        //submit block
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (!isAlreadyBallingStart && isBallingStart) {
                    lblTimer.setText("-");
                    lblTurns.setText("5");
                    lblTurns.setForeground(new Color(0, 0, 0));
                    lblTimer.setForeground(new Color(0, 0, 0));
                }
                if (!isBallingStart) {
                    if (isStart && !isTimeOver && !isOut) {
                        if (turnNumber > 0) {
                            int randomNumber = generateRandomNumber();
                            labelBallMark.setText(String.valueOf(randomNumber));
                            generateTurnNumber();
                            lblTurns.setText(String.valueOf(turnNumber));
                            int inputNumber = validateInput();
                            if (isValidInput) {
                                labelBatMark.setText(String.valueOf(inputNumber));
                                if (randomNumber == inputNumber) {
                                    timer();
                                    isOut = true;
                                    isBallingStart = true;
                                    JOptionPane.showMessageDialog(btnSubmit, "OUT", "WARNING", JOptionPane.WARNING_MESSAGE);
                                    populateBallingStartInterface();
                                    lblNowYouAre.setText("Now you are Balling");
                                } else {
                                    addTotal(inputNumber);
                                }
                            }
                            if (turnNumber < 3) {
                                lblTurns.setForeground(new Color(255, 0, 0));
                            }
                        } else {
                            isTurnsOver = true;
                            isBallingStart = true;
                            JOptionPane.showMessageDialog(btnSubmit, "Your turns are over. Now you can ball.", "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                            populateBallingStartInterface();
                            lblNowYouAre.setText("Now you are Balling");
                        }
                    } else if (!isStart) {
                        JOptionPane.showMessageDialog(btnSubmit, "Please start the game.", "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else if (isTimeOver) {
                        lblNowYouAre.setText("Now you are Balling");
                        JOptionPane.showMessageDialog(btnSubmit, "Your time is over.", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    lblTimer.setText("-");
                    isAlreadyBallingStart = true;
                    if (isStart && !isBallingOut && !isBallingTurnsOver) {
                        if (ballingTurnNumber > 0) {
                            int randomNumber = generateRandomNumber();
                            labelBatMark.setText(String.valueOf(randomNumber));
                            int inputNumber = validateInput();
                            if (isValidInput) {
                                if (randomNumber == inputNumber) {
                                    JOptionPane.showMessageDialog(btnSubmit, "OUT", "WARNING", JOptionPane.WARNING_MESSAGE);
                                    populateWinner(btnSubmit);
                                    isBallingOut = true;
                                } else {
                                    ballingTurnNumber = ballingTurnNumber - 1;
                                    lblTurns.setText(String.valueOf(ballingTurnNumber));

                                    computerTotal = computerTotal + randomNumber;
                                    labelBallMark.setText(String.valueOf(inputNumber));
                                    lblTotal.setText(String.valueOf(computerTotal));
                                }
                                if (computerTotal > total) {
                                    populateWinner(btnSubmit);
                                }
                            }
                            if (ballingTurnNumber < 3) {
                                lblTurns.setForeground(new Color(255, 0, 0));
                            }
                        } else {
                            isBallingTurnsOver = true;
                            JOptionPane.showMessageDialog(btnSubmit, "Computer turns are over", "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                            populateWinner(btnSubmit);
                        }
                    } else if (!isStart) {
                        JOptionPane.showMessageDialog(btnSubmit, "Please start the game.", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        btnSubmit.setBackground(new Color(153, 255, 102));
        btnSubmit.setFont(new Font("Dialog", Font.BOLD, 16));
        btnSubmit.setBounds(324, 279, 117, 32);
        frame.getContentPane().add(btnSubmit);

        JButton btnRestart = new JButton("Reset");
        btnRestart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (isTurnsOver) {
                    lblTimer.setText("20 S");
                }
                if (isTimeOver) {
                    lblTimer.setText("20 S");
                }
                if (isOut) {
                    lblTimer.setText("20 S");
                }
                isAlreadyStart = false;
                isStart = false;
                isTimeOver = false;
                isTurnsOver = false;
                isBallingStart = false;
                isOut = false;
                isBallingOut = false;
                isReset = true;
                isAlreadyBallingStart = false;
                lblTimer.setForeground(new Color(0, 0, 0));
                lblTurns.setForeground(new Color(0, 0, 0));
                lblTurns.setText("5");
                lblTotal.setText("0");
                labelBatMark.setText("0");
                labelBallMark.setText("0");
                txtEnterAValue.setText("");
                lblNowYouAre.setText("Please start the game");

                total = 0;
                turnNumber = 5;
                computerTotal = 0;
            }
        });
        btnRestart.setBackground(new Color(102, 255, 255));
        btnRestart.setFont(new Font("Dialog", Font.BOLD, 16));
        btnRestart.setBounds(187, 342, 117, 32);
        frame.getContentPane().add(btnRestart);

        Button btnExit = new Button("Exit");
        btnExit.setFont(new Font("Dialog", Font.BOLD, 16));
        btnExit.setForeground(Color.WHITE);
        btnExit.setBackground(Color.RED);
        btnExit.setBounds(283, 386, 93, 32);
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int input = JOptionPane.showConfirmDialog(btnExit, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    System.exit(0);
                }
            }
        });
        frame.getContentPane().add(btnExit);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("src/main/java/com/ddr/game/images/bat.png"));
        lblNewLabel.setBounds(15, 0, 649, 416);
        frame.getContentPane().add(lblNewLabel);
    }

    private void populateWinner(JButton btnSubmit) {
        if (total > computerTotal) {
            JOptionPane.showMessageDialog(btnSubmit, "You won", "Great", JOptionPane.INFORMATION_MESSAGE);
        } else if (total == computerTotal) {
            JOptionPane.showMessageDialog(btnSubmit, "Match Draw", "Great", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(btnSubmit, "You lost", "Oops", JOptionPane.INFORMATION_MESSAGE);
        }
        lblNowYouAre.setText("Please reset and replay the game");
    }

    private void populateStart() {
        if (!isAlreadyStart) {
            isReset = false;
            isStart = true;
            isAlreadyStart = true;
            timer();
        }
    }

    private void timer() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 20;

            public void run() {
                lblTimer.setText(String.valueOf(countdownStarter).concat(" S"));
                lblTimer.setForeground(new Color(0, 0, 0));
                if (countdownStarter <= 10) {
                    lblTimer.setForeground(new Color(255, 0, 0));
                }
                countdownStarter--;

                if (countdownStarter < 0) {
                    lblTimer.setText("Time over");
                    isTimeOver = true;
                    isBallingStart = true;
                    scheduler.shutdown();
                    populateBallingStartInterface();
                }
                if (isReset) {
                    scheduler.shutdown();
                    lblTimer.setText("20 S");
                }
                if (isTurnsOver) {
                    scheduler.shutdown();
                }
                if (isOut) {
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    private void populateBallingStartInterface() {
        labelBatMark.setText("0");
        labelBallMark.setText("0");
        lblTotal.setText("0");
        lblTimer.setText("-");
        lblTurns.setText("5");
    }

    private int generateRandomNumber() {
        int max = 6;
        int min = 1;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private void generateTurnNumber() {
        turnNumber = turnNumber - 1;
    }

    private int validateInput() {
        int inputNumber = 0;
        if (StringUtils.isNotBlank(txtEnterAValue.getText())) {
            String userInput = txtEnterAValue.getText();
            try {
                inputNumber = Integer.parseInt(userInput);
                if (inputNumber > 6 || inputNumber < 1) {
                    JOptionPane.showMessageDialog(txtEnterAValue, "Number should be between 1 and 6.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    isValidInput = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(txtEnterAValue, "Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            txtEnterAValue.setText("");
        }
        return inputNumber;
    }

    private void addTotal(int inputNumber) {
        total = total + inputNumber;
        lblTotal.setText(String.valueOf(total));
    }
}
