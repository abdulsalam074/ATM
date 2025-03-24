/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atmguiproject;

/**
 *
 * @author Fame
 */
import java.awt.*;
import javax.swing.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;

public class AtmGuiProject {
    
    static String storedAccountNumber;
    static  String storedCardNumber="";
    static  String storedName="";
    static  String storedPin="";
    static  double storedBalance;
    
      static void cardCheck()
    {
        JFrame frame = new JFrame("UBL");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Label
        JLabel cardLabel = new JLabel("Enter Your Card Number:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(cardLabel, gbc);

        // Text Field
        JTextField cardField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cardField, gbc);

        // Button
        JButton submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submit, gbc);

        frame.add(panel);
        frame.setVisible(true);
        submit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
                   
            {
                 boolean loginSuccess=false;
                 String enteredCardNumber = cardField.getText().trim(); 
                 if(enteredCardNumber.isEmpty())
                 {
                     JOptionPane.showMessageDialog(frame,"Please enter card number");
                 }
                 else{
                try(BufferedReader reader=new BufferedReader(new FileReader("file.txt")))
                {
                    String line;
                    while((line=reader.readLine())!=null)
                            {
                                if(line.startsWith("CardNumb"))
                                {
                                    String[] data=line.split(",");
                                    String storedCardNum=data[0].substring(10).trim();
                                    String name=data[1].split(":")[1].trim();
                                    String pin=data[2].split(":")[1].trim();                                 
                                    double balanc=Double.parseDouble(data[3].split(":")[1].trim());
                                    
                                    if(enteredCardNumber.equals(storedCardNum))
                                    {
                                        storedCardNumber=storedCardNum;
                                        storedName=name;
                                        storedPin=pin;
                                        storedBalance=balanc;
                                        loginSuccess=true;
                                        break;
                                    }                                    
                                }
                            }
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    return;
                }
                
                if(loginSuccess)
                {
                    pin();
                    frame.dispose();
                }
                else
                      {
                        JOptionPane.showMessageDialog(frame, "Invalid card number");
                      }
                 }
            }
        });
    }
    static void pin()
    {
        JFrame frame=new JFrame("Welcome");
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        JPanel panel =new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        

         JLabel name=new JLabel(storedName);
         name.setBounds(100,50,100,25);
         panel.add(name);

         JLabel pinLabel=new JLabel("Enter Your Pin");
         pinLabel.setBounds(100, 80, 150, 25);
         panel.add(pinLabel);
        
         JTextField pinField=new JTextField();
         pinField.setBounds(100, 120, 150, 25);
         panel.add(pinField);
        
         JButton submit=new JButton("Submit");
         submit.setBounds(120, 160, 100, 25);
         panel.add(submit);
        
        submit.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String enteredPin=pinField.getText().trim();
                if(enteredPin.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame,"Please enter a 4 digit pin");
                }
                else{
                if(enteredPin.equals(storedPin))
                {
                    JOptionPane.showMessageDialog(frame, "Welcome  "+storedName);
                    mainScreen();
                    frame.dispose();
                }
                 else
                      {
                        JOptionPane.showMessageDialog(frame, "Invalid pin");
                      }
                }
            }
        });
    }
    static void mainScreen()
    {
        JFrame frame =new JFrame("Welcome to UBL");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(4,2,100,10));
        frame.add(panel);
        
        JButton withdraw=new JButton("Cash Withdraw");
        JButton fastCash=new JButton("Fast Cash");
        JButton balanceIquiry=new JButton("Balance Inquiry");
        JButton moneyTransfer=new JButton("Money Transfer");
        JButton miniStatement=new JButton("MiniStatement");
        JButton billPayment=new JButton("Bill Payment");
        JButton cashDeposit=new JButton("Cash Deposit");
        
        panel.add(withdraw);
        panel.add(fastCash);      
        panel.add(balanceIquiry);       
        panel.add(moneyTransfer);       
        panel.add(miniStatement);        
        panel.add(billPayment);
        panel.add(cashDeposit);
        
        JButton exit=new JButton("Exit");
        panel.add(exit);
        
        //add actionlistener of cash withdraw
        withdraw.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                withdraw();
                frame.dispose();
            }
        });                
        //add actionlistener of fast cash
        fastCash.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fastCash();
                frame.dispose();
            }
        });
        
        //add actionlistener of balance inquiry
        balanceIquiry.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
             balanceInquiry();
             frame.dispose();
          }
        });
        
        //add actionlistener of cash Transfer
        moneyTransfer.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
              moneyTransfer();
              frame.dispose();
          }
        }); 
        //add actionlistener of ministatement
        miniStatement.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
              miniStatement();
              frame.dispose();
          }
        });
        //add actionlistener ofbill payment
        billPayment.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
               billPayment();
               frame.dispose();
          }
        });
        //add actionlistener of cash deposit
        cashDeposit.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
              cashDeposit();
              frame.dispose();
          }
        });
        //add actionlistener of exit
        exit.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent e)
          {
              exit();
              frame.dispose();
          }
        });       
    }
//end all action Listener of mainScreen
private static void withdraw() {
    
    JFrame frame = new JFrame("Cash Withdraw");
    frame.setSize(500,400);
    frame.setLocationRelativeTo(null); // Center window
    frame.setVisible(true);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    frame.add(panel);

    JLabel withdrawLabel = new JLabel("Enter Amount");
    withdrawLabel.setBounds(50, 30, 200, 25);
    panel.add(withdrawLabel);

    JTextField withdrawField = new JTextField();
    withdrawField.setBounds(50, 60, 200, 25);
    panel.add(withdrawField);

    JButton submit = new JButton("Submit");
    submit.setBounds(100, 100, 100, 25);
    panel.add(submit);

    submit.addActionListener(e -> {
        
            String input = withdrawField.getText();
            if (input.isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
            return;
        }

            double cashWithdraw = Double.parseDouble(input);
            File file = new File("file.txt");
            StringBuilder updatedContent = new StringBuilder();
            boolean found = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CardNumb: " + storedCardNumber + ",")) {
                    String[] data = line.split(", ");
                    double balance = Double.parseDouble(data[3].split(":")[1].trim());
                    if (cashWithdraw <= balance) {
                        balance -= cashWithdraw;
                        data[3] = "Balanc: " + balance;
                        line = String.join(", ", data);
                        found = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                    }
                }
                updatedContent.append(line).append("\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading the file.");
            return;
        }
        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(updatedContent.toString());
                JOptionPane.showMessageDialog(frame, "Transaction successful!");
                receipt();
                frame.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error writing to the file.");
            }
        } 
    });
}
 
    static void receipt()
    {
        JFrame frame=new JFrame("Receipt");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setVisible(true);
        frame.add(panel);
        
        JLabel receipt=new JLabel("Can you take receipt");
        receipt.setBounds(200,80,150,25);
        panel.add(receipt);

        JButton yes=new JButton("Yes");
        yes.setBounds(200,110,30,25);
        panel.add(yes);
        
        JButton no=new JButton("No");
        no.setBounds(250,110,30,25);
        panel.add(no);
        
        yes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(frame, "Please take your receipt");
                JOptionPane.showMessageDialog(frame, "Please take your card");
                JOptionPane.showMessageDialog(frame, "Please take your cash");
                JOptionPane.showMessageDialog(frame, "Thanks for using UBL");
                frame.dispose();
            }
        });
        
        no.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(frame, "Please take your card");
                JOptionPane.showMessageDialog(frame, "Please take your cash");
                JOptionPane.showMessageDialog(frame, "Thanks for using UBL");
                
                frame.dispose();
            }
        });
    }
         static void fastCash()
    {
        
        JFrame frame=new JFrame("Fast Cash");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(4,2,100,10));
        frame.add(panel);
        
        JButton oneK=new JButton("1000");
        JButton twoK=new JButton("2000"); 
        JButton threeK=new JButton("3000");
        JButton fiveK=new JButton("5000");
        JButton tenK=new JButton("10000");
        JButton fifteenK=new JButton("15000");
        JButton twentyK=new JButton("20000");
        
        panel.add(oneK);
        panel.add(twoK);
        panel.add(threeK);
        panel.add(fiveK);
        panel.add(tenK);  
        panel.add(fifteenK);
        panel.add(twentyK);
        
        oneK.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
        double withdraw=Double.parseDouble(oneK.getText());
        File file = new File("file.txt");
        StringBuilder updatedContent = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CardNumb: " + storedCardNumber+",")) {
                    String[] data = line.split(",");
                    double balance = Double.parseDouble(data[3].split(":")[1].trim());
                if (withdraw <= balance) {
                        balance -= withdraw;
                        data[3] = "Balanc: " + balance;
                        line = String.join(",", data);
                        found = true;
                    } 
                else {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                    }
                }
                updatedContent.append(line).append("\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading the file.");
            return;
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(updatedContent.toString());
                JOptionPane.showMessageDialog(frame, "Transaction successful!");
                receipt();
                frame.dispose();
                } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error writing to the file.");
                }
            }
        }
        });
       
        twoK.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                double withdrawTwoK=Double.parseDouble(twoK.getText());
               
                File file=new File("file.txt");
                StringBuilder updatedContent=new StringBuilder();
                boolean found=false;
                try(BufferedReader reader=new BufferedReader(new FileReader(file))){
                    String line;
                    while((line=reader.readLine())!=null){
                        if(line.contains("CardNumb: "+storedCardNumber+",")){
                            String[] data=line.split(",");
                            double balance=Double.parseDouble(data[3].split(":")[1].trim());
                            if(withdrawTwoK<=balance){
                                balance-=withdrawTwoK;
                                data[3]="Balanc: "+balance;
                                line=String.join(",", data);
                                found=true;
                            }
                            else{
                            JOptionPane.showMessageDialog(frame, "Insufficeient Balance");
                        }}
                        updatedContent.append(line).append("\n");
                    }
                }
                catch(IOException ex){
                    JOptionPane.showMessageDialog(frame, "Error Reading the file");
                }
                if(found){
                    try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
                        writer.write(updatedContent.toString());
                        JOptionPane.showMessageDialog(frame,"TranscationSuccessful");
                        receipt();
                        frame.dispose();
                    }
                    catch(IOException ex){
                        JOptionPane.showMessageDialog(frame, "Error Wrtitng the file");
                        
                    }
                }                   
            }
        });
        
        threeK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               double withdrawThreeK=Double.parseDouble(threeK.getText());
               
               StringBuilder updatedContent=new StringBuilder();
               File file=new File("file.txt");
               boolean found=false;
               try(BufferedReader reader=new BufferedReader(new FileReader(file))){
                   String line;
                   while((line=reader.readLine())!=null){
                       if(line.contains("CardNumb: "+storedCardNumber+",")){
                           String[] data=line.split(",");
                           double balance=Double.parseDouble(data[3].split(":")[1].trim());
                           if(withdrawThreeK<=balance){
                               balance-=withdrawThreeK;
                                 data[3]="Balanc: "+balance;
                               line=String.join(",", data);
                               found=true;
                           }
                           else{
                               JOptionPane.showMessageDialog(frame, "Insufficient Balance");
                           }
                       }
                       updatedContent.append(line).append("\n");
                   }
               }
               catch(IOException ex){
                   JOptionPane.showMessageDialog(frame,"Error Reading the file");
                   
               }
               if(found){
                   try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
                       writer.write(updatedContent.toString());
                       JOptionPane.showMessageDialog(frame,"Transcational Successfull");
                       receipt();
                       frame.dispose();
                   }
                   catch(IOException ex){
                       JOptionPane.showMessageDialog(frame, "Error wrtitng the file");
                   }
               }       
            }
        });
        fiveK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                double withdrawFiveK=Double.parseDouble(fiveK.getText());
                File file=new File("file.txt");
                StringBuilder updatedContent=new StringBuilder();
                boolean found=false;
                try(BufferedReader reader=new BufferedReader(new FileReader(file))){
                    String line;
                    while((line=reader.readLine())!=null){
                        if(line.contains("CardNumb: "+storedCardNumber+",")){
                            String[] data=line.split(",");
                            double balance=Double.parseDouble(data[3].split(":")[1].trim());
                            if(withdrawFiveK<=balance){
                                balance-=withdrawFiveK;
                                  data[3]="Balanc: "+balance;
                                line=String.join(",", data);
                                found=true;
                            }
                            else{
                                JOptionPane.showMessageDialog(frame, "Insufficient Balance");
                            }
                        }
                        updatedContent.append(line).append("\n");
                    }
                }
                catch(IOException ex){
                    JOptionPane.showMessageDialog(frame, "Error! Something went Wrong");
                }
                if(found){
                    try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
                        writer.write(updatedContent.toString());
                        JOptionPane.showMessageDialog(frame,"Transcation Successful");
                        receipt();
                        frame.dispose();
                    }
                    catch(IOException ex){
                        JOptionPane.showMessageDialog(frame, "Error! Updated Balance");
                    }
                }
            }
        });
       tenK.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        double withdrawTenK;
        try {
            withdrawTenK = Double.parseDouble(tenK.getText()); // Convert text input to double
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input! Enter a valid amount.");
            return; // Stop execution if input is invalid
        }

        File originalFile = new File("file.txt");
        File tempFile = new File("temp_file.txt");
        StringBuilder updatedContent = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CardNumb: " + storedCardNumber + ",")) {
                    String[] data = line.split(",");
                    double balance = Double.parseDouble(data[3].split(":")[1].trim());

                    if (withdrawTenK <= balance) {
                        balance -= withdrawTenK;
                        data[3] = "Balanc: " + balance;
                        line = String.join(",", data);
                        found = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient Balance");
                    }
                }
                updatedContent.append(line).append("\n"); // FIXED: Correct newline
            }

            writer.write(updatedContent.toString()); // Write updated content to temp file
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error! Reading/Writing the file");
            return; // Stop execution if an error occurs
        }

        // If transaction was successful, replace the original file with the updated file
        if (found) {
            if (originalFile.delete() && tempFile.renameTo(originalFile)) {
                JOptionPane.showMessageDialog(frame, "Transaction Successful");
                receipt();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating account balance.");
            }
        } else {
            tempFile.delete(); // Clean up temporary file if transaction was not found
        }
    }
});

      
        fifteenK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                double withdrawFifteenK;
                try {
            withdrawFifteenK = Double.parseDouble(fifteenK.getText()); // Convert text input to double
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input! Enter a valid amount.");
            return; // Stop execution if input is invalid
        }
                File file=new File("file.txt");
                File tempFile=new File("tempFile.txt");
                StringBuilder updatedContent=new StringBuilder();
                boolean found=false;
                try(BufferedReader reader=new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    String line;
                    while((line=reader.readLine())!=null){
                        if(line.contains("CardNumb: "+storedCardNumber+",")){
                            String[] data=line.split(",");
                            double balance=Double.parseDouble(data[3].split(":")[1].trim());
                            if(withdrawFifteenK<=balance){
                                balance-=withdrawFifteenK;
                                data[3]="Balanc: "+balance;
                                line=String.join(",", data);
                                found=true;
                            }
                            else{
                                JOptionPane.showMessageDialog(frame, "Insufficient Balance");
                            }
                        }
                        updatedContent.append(line).append("\n");
                    }
                    writer.write(updatedContent.toString());
                }
                
                catch(IOException ex){
                    JOptionPane.showMessageDialog(frame, "Error! Reading the File");
                    return;
                }
                if(found){
                   {
                      if (file.delete() && tempFile.renameTo(file)) {
                JOptionPane.showMessageDialog(frame, "Transaction Successful");
                receipt();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating account balance.");
            }
                   }
                }
                else {
            tempFile.delete(); // Clean up temporary file if transaction was not found
        }
            }
        });
        
        twentyK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            double withdrawTwentyK=Double.parseDouble(twentyK.getText());
            
            File file=new File("file.txt");
            StringBuilder updatedContent=new StringBuilder();
            boolean found=false;
            
            try(BufferedReader reader=new BufferedReader(new FileReader(file))){
                String line;
                while((line=reader.readLine())!=null){
                if(line.contains("CardNumb: "+storedCardNumber+",")){
                    String[] data=line.split(",");
                    double balance=Double.parseDouble(data[3].split(":")[1].trim());
                    if(withdrawTwentyK<=balance){
                        balance-=withdrawTwentyK;
                        data[3]="Balanc: "+balance;
                        line=String.join(",", data);
                        found=true;
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Insufficient Balacne");
                    }
                }
                updatedContent.append(line).append("\n");
            }                     
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(frame, "Error! Reading the file");
            }
            if(found){
                try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
                    writer.write(updatedContent.toString());
                    JOptionPane.showMessageDialog(frame, "Transcation Successful");
                    receipt();
                    frame.dispose();
                }
                catch(IOException ex){
                    JOptionPane.showMessageDialog(frame, "Error! Updated Balance");
                }
            }
        }
    });
        JButton other=new JButton("Other Amount");
        panel.add(other);
        other.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                withdraw();
                 frame.dispose();
            }
        });
    }    
       static void balanceInquiry()
    {
       
        JFrame frame=new JFrame("Balance Inquiry");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
        JLabel showBalance=new JLabel("Your Balance is:  "+storedBalance);
        showBalance.setBounds(200,80,150,25);
        panel.add(showBalance);
        
        JLabel withdraw=new JLabel("Can you withdraw cash");
        withdraw.setBounds(200,140,150,25);
        panel.add(withdraw);
        
        JButton yes =new JButton("Yes");
        yes.setBounds(200,170,60,25);
        panel.add(yes);
        
        yes.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                withdraw();
                frame.dispose();
            }
        });
        JButton no=new JButton("No");
        no.setBounds(280,170,50,25);
        panel.add(no);
        
        no.addActionListener(new ActionListener ()
        {
            public void actionPerformed(ActionEvent e)
            {
                exit();
                frame.dispose();
            }
        });
    }
static void moneyTransfer() {
    JFrame frame = new JFrame("Money Transfer");
    frame.setSize(500,400);
    frame.setLocationRelativeTo(null); // Center window
    frame.setVisible(true);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    frame.add(panel);

    String[] selectBank = { "Select Any Bank", "Allied Bank", "Alfalah Bank", "Bank Al-Habib" };
    JComboBox<String> bankSelectBox = new JComboBox<>(selectBank);
    bankSelectBox.setBounds(0, 10, 200, 25);
    panel.add(bankSelectBox);

    JLabel accountNumber = new JLabel("Enter Account Number");
    accountNumber.setBounds(0, 60, 200, 25);
    panel.add(accountNumber);

    JTextField accountNumberField = new JTextField();
    accountNumberField.setBounds(140, 60, 160, 25);
    panel.add(accountNumberField);

    JButton submit = new JButton("Submit");
    submit.setBounds(100, 160, 100, 25);
    panel.add(submit);
 
    submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             // Local variable for sender's card number
             
            String senderCardNumber = storedCardNumber;
            String receiverAccountNumber = accountNumberField.getText().trim();
            boolean accountFound = false; // Track if the account exists

            try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("CardNumb")) {
                        String[] data = line.split(",");
                        String storedCardNum = data[0].substring(10).trim();
                        String name = data[1].split(":")[1].trim();
                        String pin = data[2].split(":")[1].trim();
                        double balance = Double.parseDouble(data[3].split(":")[1].trim());

                        if (receiverAccountNumber.equals(storedCardNum)) { // Corrected condition
                            storedCardNumber = storedCardNum;
                            storedName = name;
                            storedPin = pin;
                            storedBalance = balance;
                            accountFound = true;

                            // Sender ka card number storedCardNumber me hona chahiye
                            withdrawOfMoneyTransfer(senderCardNumber, receiverAccountNumber);
                            frame.dispose();
                            return;
                        }
                    }
                }
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
                return;
            }
            if (!accountFound) {
                JOptionPane.showMessageDialog(frame, "Invalid Account Number");
            }
        }
    });
}
static void withdrawOfMoneyTransfer(String senderCardNumber, String receiverCardNumber) {
    JFrame frame = new JFrame("Money Transfer");
    frame.setSize(500,400);
    frame.setLocationRelativeTo(null); // Center window
    
    frame.setVisible(true);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    frame.add(panel);

    JLabel name = new JLabel("Confirm Account Holder Name:  "+storedName);
    name.setBounds(120, 40, 2000, 25);
    panel.add(name);

    JLabel withdrawLabel = new JLabel("Enter Amount");
    withdrawLabel.setBounds(150, 80, 100, 25);
    panel.add(withdrawLabel);

    JTextField withdrawField = new JTextField();
    withdrawField.setBounds(130, 110, 120, 25);
    withdrawField.setEditable(true);
    panel.add(withdrawField);

    JButton submit = new JButton("Submit");
    submit.setBounds(100, 150, 100, 25);
    panel.add(submit);

    submit.addActionListener(e -> {
        String input = withdrawField.getText();
        if (input.isEmpty() || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
            return;
        }

        double transferAmount = Double.parseDouble(input);
        File file = new File("file.txt");
        StringBuilder updatedContent = new StringBuilder();
        boolean senderFound = false, receiverFound = false;
        double senderBalance = 0, receiverBalance = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CardNumb: " + senderCardNumber + ",")) {
                    senderFound = true;
                    String[] data = line.split(", ");
                    senderBalance = Double.parseDouble(data[3].split(":")[1].trim());

                    if (transferAmount <= senderBalance) {
                        senderBalance -= transferAmount;
                        data[3] = "Balanc: " + senderBalance;
                        line = String.join(", ", data);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                        return;
                    }
                }
                if (line.contains("CardNumb: " + receiverCardNumber + ",")) {
                    receiverFound = true;
                    String[] data = line.split(", ");
                    receiverBalance = Double.parseDouble(data[3].split(":")[1].trim());

                    receiverBalance += transferAmount;
                    data[3] = "Balanc: " + receiverBalance;
                    line = String.join(", ", data);
                }
                updatedContent.append(line).append("\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading the file.");
            return;
        }

        if (!senderFound) {
            JOptionPane.showMessageDialog(frame, "Sender Account Not Found.");
            return;
        }
        if (!receiverFound) {
            JOptionPane.showMessageDialog(frame, "Receiver Account Not Found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(updatedContent.toString());
            JOptionPane.showMessageDialog(frame, "Transaction successful!");
            frame.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error writing to the file.");
        }
    });
}

    static void miniStatement()
    {
            
        JFrame frame=new JFrame("Money Transfer");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);    
    }
        static void billPayment()
    {
        JFrame frame=new JFrame("Money Transfer");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
    }
         static void cashDeposit()
    {
        JFrame frame=new JFrame("Money Transfer");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
        JLabel depositCash=new JLabel("Enter Amount ");
        depositCash.setBounds(200,100,100,25);
        panel.add(depositCash);
        
        
        JTextField enteredDepositCash=new JTextField();
        enteredDepositCash.setBounds(180,140,100,25);
        panel.add(enteredDepositCash);
        
        JButton submit=new JButton("Submit");
        submit.setBounds(180,180,80,25);
        panel.add(submit);
        
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
        double depositCash=Double.parseDouble(enteredDepositCash.getText());
        depositCash+=storedBalance;
                
        JOptionPane.showMessageDialog(frame, "Your new balance is  "+depositCash);
        frame.dispose();
                
        JFrame frame=new JFrame("Cash Deposit");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
        JLabel transcation=new JLabel("can you withdraw cash");
        transcation.setBounds(200,170,200,30);
        panel.add(transcation);
                
                
        JButton yes=new JButton("Yes");
        yes.setBounds(200,200,50,25);
        panel.add(yes);
        
        JButton no=new JButton("No");
        no.setBounds(280,200,50,25);
        panel.add(no);
        
        yes.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                withdraw();
                frame.dispose();
            }
        });
        
        no.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               exit();
               frame.dispose();
            }
        });
            }
        });
    }
    
           static void exit()
    {
        JFrame frame=new JFrame("Exit");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
        JOptionPane.showMessageDialog(frame, "Thanks for using UBL!");
        frame.dispose();
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        cardCheck();
    }
}
