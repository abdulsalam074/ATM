/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atm24march;

/**
 *
 * @author Fame
 */
import com.mycompany.atm24march.User.ATM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class User{
    public String cardNumber;
    public String name;
    public String pin;
    public double balance;
    
    public User(String cardNumber, String name, String pin, double balance){
        this.cardNumber=cardNumber;
        this.name=name;
        this.pin=pin;
        this.balance=balance;
    }
    
    public String getCardNumber(){return cardNumber;}
    public String getName(){return name;}
    public String getPin(){return name;}
    public double getBalance(){return balance;}
    
    public void setBalance(double balance){this.balance=balance;}
    
    class ATM{
        public User user;
        
        public ATM(User user){
            this.user=user;
        }
        
        public boolean verifyPin(String enteredPin){
            return user.getPin().equals(enteredPin);
        }
        
        public boolean withdraw(double amount){
            if(amount > 0 && amount <=user.getBalance()){
                user.setBalance(user.getBalance()-amount);
                return true;
            }
            return false;
        }
        public void deposit(double amount){
            if(amount  >  0){
                user.setBalance(user.getBalance()+amount);
            }
        }
    }         
}
public class Atm24March {

    public static void main(String[] args) {
        User user=new User("1234","John Doe","1234",3000.0);
        ATM atm=new ATM(user);
        javax.swing.SwingUtilities.invokeLater(()->new LoginFrame(atm));
    }
}


class LoginFrame extends JFrame{
    private ATM atm;
    private JTextField pinField;
    
    public LoginFrame(ATM atm){
        this.atm=atm;
        setTitle("ATM Login");
        setSize(399,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        
        JLabel pinLabel=new JLabel("Enter PIN");
        pinField=new JPasswordField();
        JButton loginButton=new JButton("Login");
        
        loginButton.addActionListener(e ->{
            if(atm.verifyPin(pinField.getText())){
                new ATMMainScreen(atm);
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Invalid Pin");
            }
        });
        
        add(pinLabel);
        add(pinField);
        add(loginButton);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class ATMMainScreen extends JFrame{
    public ATMMainScreen(ATM amt){
        setTitle("ATM Main Menu");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        
        JButton withdrawButton=new JButton("Withdraw");
        JButton depositButton=new JButton("Deposit");
        JButton balanceButton=new JButton("Balance Inquiry");
        
        withdrawButton.addActionListener(e  ->{
            String amountStr=JOptionPane.showInputDialog("Enter Amount To Withdraw");
            if(atm.withdraw(amount)){
                JOptionPane.showMessageDialog(this, "Withdrawal Amount Successful");
            
            }
            else{
                JOptionPane.showMessageDialog(this, "Insufficeint Balance");
            }
        });
        
    }
}
