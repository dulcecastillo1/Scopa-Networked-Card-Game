import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;


import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class ServerScreen extends JPanel {
  private BufferedReader in; 
  private PrintWriter out;
	private int y; 
	private String displayString; 
	private int numUsers; 
	private JTextArea display;
	private Manager manager; 
  private Boolean allStarted; 



	public ServerScreen(){
		this.y = 20; 
		displayString = ""; 
		numUsers = 0; 
        allStarted = false; 

		display = new JTextArea(); 
        display.setBounds(10, 20, 300, 330); 
        display.setEditable(false); 


		this.setLayout(null);

		add(display); 

		this.manager = new Manager(); 
		
	}




	public Dimension getPreferredSize() {


		return new Dimension(1400,800);
		
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);

		display.setText(displayString); 

		g.drawString("Number of users: " + numUsers, 550, y); 

        if(allStarted == true){
            g.drawString("All users have clicked start", 550, y+20); 
        }




	} 



	public void startServer() throws IOException {
		int portNumber = 1024;


		ServerSocket server = new ServerSocket(portNumber);
		

        //This loop will run and wait for one connection at a time.
		
        while(true){
            System.out.println("Waiting for a connection");
			if(numUsers == 2){
				while(allStarted == false){
					allStarted = manager.allStarted(); 
				}
				repaint();
			}
			

            //Wait for a connection.
            Socket socket = server.accept();

            //Once a connection is made, run the socket in a ServerThread.

			ServerThread st = new ServerThread(socket, manager); 
			manager.add(st);
            Thread thread = new Thread(st);
            thread.start();
			

			try {
				displayString += (" IP: " + InetAddress.getLocalHost().getHostAddress() + "\n");
				numUsers += 1; 



				repaint();
				
                
			} catch (UnknownHostException ex) {
				System.out.println("Could not find IP address for this host");
			}

			

			repaint();
        }

    
	}






}
