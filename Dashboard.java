// creating gui for the email sender

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

import java.util.*;


public class Dashboard extends JFrame{
    private JButton send;
    private JButton attach;
    private JTextField to;
    private JTextField username;
    private JTextField pass;
    private JTextField subject;
    private JTextArea text;
    private JScrollPane scrollPane;
    private FileOpener fp;
    private FileOpener fpCsvContact;
    private JLabel fileDisplay;
    private JLabel csvfileDisplay;
    private JButton uploadCSV;
    private JTextField dLimit;


	Dashboard(){
		super("Email_Blaster");
		setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(2,3));
        JPanel textP = new JPanel(new GridLayout(1,1));
        JPanel buttonP = new JPanel();
        JPanel subjectP = new JPanel();

        // initialize the JLabel

        fileDisplay = new JLabel("No File");
        csvfileDisplay = new JLabel("No contact loaded");

        //p.setLayout(new FlowLayout());
        add(p , BorderLayout.NORTH);
        add(buttonP , BorderLayout.SOUTH);
        // add below 
        add(textP , BorderLayout.CENTER);

        // adding jtextfield
        to = new JTextField("To");
        to.setBounds(50,150,150,20); 
        username = new JTextField("User");
        username.setBounds(50,150,150,20);  
        pass = new JTextField("Pass");
        pass.setBounds(50,150,150,20); 
        subject = new JTextField("Subject"); 
        subject.setBounds(50,150,200,20); 
        dLimit = new JTextField("Limit, Max 100");
        dLimit.setBounds(50,150,200,20);

        text = new JTextArea("Write your message here");
         

        // adding the JButton
        send = new JButton("Send");
        attach = new JButton("Add Attachment");
        uploadCSV = new JButton("Upload CSV");


         /*
        JScrollPane scrollbar = new JScrollPane(text);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollbar.setBounds(50, 30, 300, 50);
        */

        // adding textfiekd
        p.add(uploadCSV);
        p.add(csvfileDisplay);
        p.add(username);
        p.add(pass);
        p.add(subject);
        p.add(dLimit); 
        
       // button 
        //p.add(send);

        // adding text area 
      
        
        
        textP.add(text);
        //textP.add(scrollbar);

        // adding send and attach button
        buttonP.add(fileDisplay);
        buttonP.add(attach);
        buttonP.add(send);
      
        // fcor subject
        // adding Listeners

        send.addMouseListener(new ClickSend());
        attach.addActionListener(new AttachListener());
        uploadCSV.addActionListener(new CSVListener());

		setSize(600, 600);
		//setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class ClickSend implements MouseListener{
        private int check;
        private String areaMsg;
        private String modIterMsg;
        private String user;
        private ArrayList<CharSequence> toWhom;
        private ArrayList<String> contactEmail;
        private String password;
        private String sub;
        private CSVRead csv;
        private int limit;
        private int counter = 0;
        private Text t;
        private boolean markPresent;

        ClickSend(){
            t = new Text();
        }
        public void mouseClicked(MouseEvent e){
            /*  // for sending individually.
                areaMsg =text.getText();
                user = username.getText();
                toWhom = to.getText();
                password = pass.getText();
                sub = subject.getText();
                try{
                    String attach = fp.getFileName();
                    check = SendEmailTLS.sendMailAttachment(user, password , toWhom , areaMsg , sub , attach);
                }catch(Exception ex){
                    attach = null;
                    if(attach  == null){
                        check = SendEmailTLS.sendMail(user, password , toWhom , areaMsg , sub);
                    }
                }
    			// should make it static for obj effeciency */



                try{
                    csv = new CSVRead(fpCsvContact.getFileName());
                }catch(Exception csvE){
                    System.out.println("No csv");
                    csvE.printStackTrace();
                }

                areaMsg =text.getText();
                user = username.getText();
                password = pass.getText();
                contactEmail = new ArrayList<String>(csv.getContacts());// Array of Contacts
                toWhom = new ArrayList<CharSequence>(csv.getName());
                sub = subject.getText();
                limit = Integer.parseInt(dLimit.getText());

                
                if(limit > 100){
                    System.out.println("More than limit. Google will label your emails as spam.");
                    System.out.println("Please try again later with a limit below 100.");
                    // throw dialog alert
                    JOptionPane.showMessageDialog(null, "More than 100! Google classify to spam." , "Number limit", JOptionPane.INFORMATION_MESSAGE);
                    

                }
                
                    
                

                for(String dest : contactEmail){

                    if(dest == null){
                        continue;
                    }

                    try{
                        String attach = fp.getFileName();
                        markPresent = t.searchPattern(areaMsg);
                        
                        modIterMsg = t.replacePattern(toWhom.get(counter) , areaMsg , markPresent);
                    
                        check = SendEmailTLS.sendMailAttachment(user, password , dest , modIterMsg , sub , attach);
                    }catch(Exception ex){
                        attach = null;
                        if(attach  == null){
                            markPresent = t.searchPattern(areaMsg);

                            modIterMsg = t.replacePattern(toWhom.get(counter) , areaMsg , markPresent);
                            
                            check = SendEmailTLS.sendMail(user, password , dest , modIterMsg , sub);
                        }
                    }

                    counter = counter+1;

                    if(counter == limit ){
                        System.out.println("Resume from line "+ limit);
                        break;

                    }

                }
                
    		
    	}

    	public void mousePressed(MouseEvent e){

    	}
    	public void mouseReleased(MouseEvent e){
            System.out.println("Processing Email. Please wait.");
    	}
    	public void mouseExited(MouseEvent e){

    	}
    	public void mouseEntered(MouseEvent e){
    		System.out.println("Click to send!");
    	}
    }

    private class AttachListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
           fp = new FileOpener();
           fileDisplay.setText(fp.getFileName());
        }
    }

    private class CSVListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
           fpCsvContact = new FileOpener();
           csvfileDisplay.setText(fpCsvContact.getFileName());
        }
    }
    
    public static void main(String[] args){
    	Dashboard d = new Dashboard();
        
    }
    

}
