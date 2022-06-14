/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package corridacarros;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author Gabriela Sinastre, Maykon Borges and Karen Almeida
 */
public class CorridaCarros {

    JFrame frame;
    JProgressBar car1 = new JProgressBar(0, 100);
    JLabel msg = new JLabel("");

    static boolean runRaceButtonIsPressed = false;
    static boolean resetRaceButtonIsPressed = false;
    static int winningCar = 0;
    static boolean winner = false;
    static int carNumber;
    static int lapNumber;
    static int probNumber;
    static int fuelNumber;

    //the finish method synchronized so the threads must wait their turn to acess it
    public synchronized void finish(int i) {
        msg.setVisible(true);
        msg.setText("Car #" + winningCar + " wins the race! Reset race again!");
        if (i == 100) {
            winner = true;
            JLabel labelWinner = new JLabel("Car #" + winningCar + " wins the race! Reset race again!");
            labelWinner.setAlignmentX(lapNumber);
            frame.getContentPane().add(labelWinner);
            System.out.println("Car #" + winningCar + " wins the race! Reset race again!");
        }
        frame.getContentPane().add(msg);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CorridaCarros window = new CorridaCarros();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CorridaCarros() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 598, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        car1.setStringPainted(true);
        car1.setForeground(Color.RED);
        car1.setBounds(150, 129, 259, 14);
        frame.getContentPane().add(car1);

        msg.setBounds(85, 100, 410, 14);
        msg.setVisible(false);
        frame.getContentPane().add(msg);

        //buttons
        JButton btnReset = new JButton("Reeniciar");
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnReset.addActionListener(new ResetRace());
        btnReset.setBounds(205, 287, 155, 40);
        frame.getContentPane().add(btnReset);

        JButton btnQuit = new JButton("Finalizar");
        btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnQuit.addActionListener(new RunQuit());
        btnQuit.setBounds(360, 287, 155, 40);
        frame.getContentPane().add(btnQuit);

        var numCarLabel = new JLabel("Número de carros:");
        numCarLabel.setBounds(10, 10, 120, 20);

        JTextField carNum = new JTextField();
        carNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
        carNum.setBounds(121, 10, 30, 20);

        var numLapsLabel = new JLabel("Número de voltas:");
        numLapsLabel.setBounds(10, 30, 120, 20);

        JTextField lapNum = new JTextField();
        lapNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lapNum.setBounds(121, 30, 30, 20);

        var numProbBreakLabel = new JLabel("Probabilidade de quebra:");
        numProbBreakLabel.setBounds(10, 50, 155, 20);

        JTextField probBreakNum = new JTextField();
        probBreakNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
        probBreakNum.setBounds(160, 50, 30, 20);

        var numProbFuelLabel = new JLabel("Probabilidade de abastecimento:");
        numProbFuelLabel.setBounds(10, 70, 190, 20);

        JTextField probFuelNum = new JTextField();
        probFuelNum.setFont(new Font("Tahoma", Font.PLAIN, 18));
        probFuelNum.setBounds(200, 70, 30, 20);

        frame.getContentPane().add(numCarLabel);
        frame.getContentPane().add(carNum);
        frame.getContentPane().add(numLapsLabel);
        frame.getContentPane().add(lapNum);
        frame.getContentPane().add(numProbBreakLabel);
        frame.getContentPane().add(probBreakNum);
        frame.getContentPane().add(numProbFuelLabel);
        frame.getContentPane().add(probFuelNum);

        carNum.setText("0");
        lapNum.setText("0");
        probBreakNum.setText("0");
        probFuelNum.setText("0");

        JButton btnStart = new JButton("Iniciar");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnStart.setBounds(50, 287, 155, 40);
        frame.getContentPane().add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!runRaceButtonIsPressed) {
                    msg.setVisible(false);
                    resetRaceButtonIsPressed = false;
                    runRaceButtonIsPressed = true;

                    carNumber = Integer.parseInt(carNum.getText());
                    lapNumber = Integer.parseInt(lapNum.getText());
                    probNumber = Integer.parseInt(probBreakNum.getText());
                    fuelNumber = Integer.parseInt(probFuelNum.getText());

                    System.out.println(carNumber);
                    
                    /* if (carNumber < 2) {
                        System.out.print("OI");
                        JLabel labelAlert = new JLabel("Insira uma quantidade carros maior que 1!");
                        frame.getContentPane().add(labelAlert);
                    } else { */
                      Car1 car1 = new Car1();
                      car1.start();
                    // }
                }
            }
        });

    }

    //run rac is an inner class that starts 1 car threads and runs a race
    /* class RunRace implements ActionListener{
        public void actionPerformed(ActionEvent arg0, JTextField carNum, JTextField lapNum, JTextField probBreakNum, JTextField probFuelNum){
            if (!runRaceButtonIsPressed){
                msg.setVisible(false);
                resetRaceButtonIsPressed = false;
                runRaceButtonIsPressed = true;
                
               carNumber = Integer.parseInt(carNum.getText());
               lapNumber = Integer.parseInt(lapNum.getText());
               probNumber = Integer.parseInt(probBreakNum.getText());
               fuelNumber = Integer.parseInt(probFuelNum.getText());
               
               System.out.println(carNumber);
                    
                Car1 car1 = new Car1();
                car1.start();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    } */
    class ResetRace implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            // aqui é quando click para executar, eu deveria conseguir recolher os valores dos inputs aqui
            if (!resetRaceButtonIsPressed) {
                msg.setVisible(false);
                resetRaceButtonIsPressed = true;
                runRaceButtonIsPressed = false;
                winner = false;

                Car1 car1 = new Car1();
                car1.reset();
            }
        }
    }

    class RunQuit implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    class Car1 extends Thread {

        public void reset() {
            car1.setValue(0);
            car1.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                car1.setValue(i);
                car1.repaint();
                if (i == 100) {
                    winningCar = 1;
                    finish(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
}
