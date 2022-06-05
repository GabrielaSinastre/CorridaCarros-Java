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
    
    
    //the finish method synchronized so the threads must wait their turn to acess it
    public synchronized void finish(int i) {
        msg.setVisible(true);
        msg.setText("Car #" + winningCar+ " wins the race! Reset race again!");
        if (i == 100){
            winner = true;
            System.out.println("Car #" + winningCar + " wins the race! Reset race again!");
        }
        frame.getContentPane().add(msg);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try{
                    CorridaCarros window = new CorridaCarros();
                    window.frame.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CorridaCarros(){
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100,100,598,430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        car1.setStringPainted(true);
        car1.setForeground(Color.RED);
        car1.setBounds(150,129,259,14);
        frame.getContentPane().add(car1);
    
        msg.setBounds(85,100,410,14);
        msg.setVisible(false);
        frame.getContentPane().add(msg);
    
        //buttons
        JButton btnStart = new JButton("Run Race");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnStart.addActionListener(new RunRace());
        btnStart.setBounds(50,287,155,40);
        frame.getContentPane().add(btnStart);
    
        JButton btnReset = new JButton("Reset Race");
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnReset.addActionListener(new ResetRace());
        btnReset.setBounds(205,287,155,40);
        frame.getContentPane().add(btnReset);
        
        JButton btnQuit = new JButton("Quit Race");
        btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnQuit.addActionListener(new RunQuit());
        btnQuit.setBounds(360,287,155,40);
        frame.getContentPane().add(btnQuit);
    }
    
    //run rac is an inner class that starts 1 car threads and runs a race
    class RunRace implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            if (!runRaceButtonIsPressed){
                msg.setVisible(false);
                resetRaceButtonIsPressed = false;
                runRaceButtonIsPressed = true;
           
                Car1 car1 = new Car1();
                car1.start();
            }
        }
    }
    
    class ResetRace implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            if (!resetRaceButtonIsPressed){
                msg.setVisible(false);
                resetRaceButtonIsPressed = true;
                runRaceButtonIsPressed = false;
                winner = false;
           
                Car1 car1 = new Car1();
                car1.reset();
            }
        }
    }
    
    class RunQuit implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            System.exit(0);
        }
    }
    
    class Car1 extends Thread{
        public void reset(){
            car1.setValue(0);
            car1.repaint();
        }
        public void run(){
            for (int i = 0; i < 101; i++){
                if(winner){
                    break;
                }
                car1.setValue(i);
                car1.repaint();
                if(i == 100){
                    winningCar = 1;
                    finish(i);
                }
                try{
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits())%60);
                }catch(InterruptedException err){
                    err.printStackTrace();
                }
            }
        }
    }
}
