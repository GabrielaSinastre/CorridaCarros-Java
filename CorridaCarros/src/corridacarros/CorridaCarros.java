/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package corridacarros;

import java.awt.Color;
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
    JProgressBar car2 = new JProgressBar(0,100);
    JProgressBar car3 = new JProgressBar(0,100);
    
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
    public /*synchronized*/ void finish(int i) {
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

    /*public static void main(String[] args) {
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
    }*/
    
    public static void main(String[] args) {
            CorridaCarros window = new CorridaCarros();
            window.frame.setVisible(true);
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
        
        
        car2.setStringPainted(true);
        car2.setForeground(Color.PINK);
        car2.setBounds(150, 159, 259, 14);
        frame.getContentPane().add(car2);
        
        car3.setStringPainted(true);
        car3.setForeground(Color.BLUE);
        car3.setBounds(150, 189, 259, 14);
        frame.getContentPane().add(car3);
   

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
                    
                    if (lapNumber < 10) {
                        System.out.println("Enter a number of turns greater than 10 !");
                        System.exit(0);
                    } 
                    if (carNumber < 2 && carNumber > 3){
                        System.out.println("Enter a valid amount of cars, only 2 or 3!");
                        System.exit(0);
                    } 
                    if (probNumber < 10 && fuelNumber < 10){
                        System.out.println("Enter a reasonable amount of probability, numbers greater than 10 are recommended !");
                        System.exit(0);
                    } 
                    if (carNumber == 2) {
                        Car car1 = new Car();
                        Car car2 = new Car();
                        car1.correr();
                        car2.correr();
                        car3.setVisible(false);
                    }
                    if (carNumber == 3){
                        car3.setVisible(true);
                        Car car1 = new Car();
                        Car car2 = new Car();
                        Car car3 = new Car();
                        car1.correr();
                        car2.correr();
                        car3.correr();
                    }
                }
            }
        });

    }

    class ResetRace implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            if (!resetRaceButtonIsPressed) {
                msg.setVisible(false);
                resetRaceButtonIsPressed = true;
                runRaceButtonIsPressed = false;
                winner = false;

                Car car1 = new Car();
                car1.reset();
                Car car2 = new Car();
                car2.reset();
                Car car3 = new Car();
                car3.reset();
            }
        }
    }

    class RunQuit implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    class Car /*extends Thread*/ {

        public void reset() {
            car1.setValue(0);
            car1.repaint();
            car2.setValue(0);
            car2.repaint();
            car3.setValue(0);
            car3.repaint();
            
        }

        public void correr() {
            int eachLap = lapNumber;
            while (eachLap != 0){
            System.out.println("Remaining laps for the cars : " + eachLap);
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                car1.setValue(i);
                car1.repaint();
                car2.setValue(i);
                car2.repaint();
                car3.setValue(i);
                car3.repaint();
                if (i == 100 && eachLap == 1) {
                    winningCar = 1;
                    finish(i);
                }
                int rand1 = (int) (Math.random() * probNumber);
                int rand2 = (int) (Math.random() * fuelNumber);
                if ( rand1 <= probNumber/4 && i > 3){
                    System.out.println("Car broke at lap number " + eachLap + " in " + i + " meters" );
                    i--;
                }else if ( rand2 <= fuelNumber/4 && i > 2){
                    System.out.println("Car stopped because needed to fuel his tank at lap " + eachLap + " in " + i + " meters " );
                    i--;
                }        
                
                /*try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }*/
            }
            eachLap--;
            }
            
        }
    }
    /*
    class Car2 extends Thread {

        public void reset() {
            car2.setValue(0);
            car2.repaint();
        }

        public void correr() {
            int eachLap = lapNumber;
            while (eachLap != 0){
            System.out.println("Voltas restante: " + eachLap);
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                
                car2.setValue(i);
                car2.repaint();
                if (i == 100 && eachLap == 1) {
                    winningCar = 2;
                    finish(i);
                }
                int rand1 = (int) (Math.random() * probNumber);
                int rand2 = (int) (Math.random() * fuelNumber);
                if ( rand1 <= probNumber/4 && i > 3){
                    System.out.println("This car broke at lap number " + eachLap + " in " + i + " meters" );
                    i--;
                }else if ( rand2 <= fuelNumber/4 && i > 2){
                    System.out.println("This car stopped because need to fuel his tank at lap " + eachLap + " in " + i + " meters " );
                    i--;
                }        
                
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
            eachLap--;
            }
            
        }
    }
    class Car3 extends Thread {

        public void reset() {
            car3.setValue(0);
            car3.repaint();
        }

        public void correr() {
            int eachLap = lapNumber;
            while (eachLap != 0){
            System.out.println("Voltas restante: " + eachLap);
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                
                car3.setValue(i);
                car3.repaint();
                if (i == 100 && eachLap == 1) {
                    winningCar = 3;
                    finish(i);
                }
                int rand1 = (int) (Math.random() * probNumber);
                int rand2 = (int) (Math.random() * fuelNumber);
                if ( rand1 <= probNumber/4 && i > 3){
                    System.out.println("This car broke at lap number " + eachLap + " in " + i + " meters" );
                    i--;
                }else if ( rand2 <= fuelNumber/4 && i > 2){
                    System.out.println("This car stopped because need to fuel his tank at lap " + eachLap + " in " + i + " meters " );
                    i--;
                }        
                
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 60);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
            eachLap--;
            }
            
        }
    }*/
}
