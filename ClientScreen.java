import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


import java.io.*;
import java.net.*;

import java.util.ArrayList;  

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 
import java.net.URL;
import java.io.*;

import java.awt.event.MouseListener; 
import java.awt.event.MouseEvent; 

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class ClientScreen extends JPanel implements ActionListener, MouseListener, KeyListener{


	private String chatMessage = "";
	private JTextField textInput;
	private JButton sendButton;
	private PrintWriter out;
	//new trade vars
	private Boolean tradeRequested; 
	private Boolean allWantTrade;
	private Boolean someWantTrade;


	private Boolean drawTradeInstructions;
	private Boolean showCannotTrade;

	private Boolean askedTrade;

	private Boolean clickedStart; 
	private Boolean notifySkip;

	private Boolean outlineCard1;
	private Boolean outlineCard2;
	private Boolean outlineCard3;
	private Boolean outlineCard4;
	private Boolean outlineCard5;

	private Boolean outlineTableCard1;
	private Boolean outlineTableCard2;
	private Boolean outlineTableCard3;
	private Boolean outlineTableCard4;

	private Boolean showCard1;
	private Boolean showCard2;
	private Boolean showCard3;
	private Boolean showCard4;
	private Boolean showCard5;

	private Boolean showTableCard1;
	private Boolean showTableCard2;
	private Boolean showTableCard3;
	private Boolean showTableCard4;

	private Boolean choseCards;
	private Boolean cannotClickCards;

	private Boolean canRejectTrade;
	private Boolean ableToTrade;
	private Boolean ableToClickTrade;

	private Boolean end;

	private Boolean drawStep1;
	private Boolean drawStep2;
	private Boolean drawStep3;
	private Boolean drawNotMatch;
	private Boolean drawSuccessfulMatch;
	private Boolean startNewRound;
	private Boolean startNewRound2;
	private Boolean startNewRound3;
	private Boolean clickTrade;

	private DLList<IntroCards> introCards; 
	private DLList<Thread> introCardsThreads; 


	private BufferedImage titleImg;
	private BufferedImage instructionsImg;
	private BufferedImage waitingImg; 
	private BufferedImage step1Img; 
	private BufferedImage step2Img; 
	private BufferedImage notMatchImg; 
	private BufferedImage successfulMatch;
	private BufferedImage skipImg;
	private BufferedImage tradeImg;
	private BufferedImage tradeInstructions;
	private BufferedImage rejectTradeImg;
	private BufferedImage notifyTradeImg;
	private BufferedImage requestTradeImg;
	private BufferedImage skipStepImg;
	private BufferedImage card1;
	private BufferedImage card2;
	private BufferedImage card3;
	private BufferedImage card4;
	private BufferedImage card5;
	private BufferedImage round1Img;
	private BufferedImage round2Img;
	private BufferedImage round3Img;
	private BufferedImage loseImg;
	private BufferedImage winImg;
	private BufferedImage skipToEndImg;

	

	private BufferedImage tableCard1;
	private BufferedImage tableCard2;
	private BufferedImage tableCard3;
	private BufferedImage tableCard4;
	

	private Thread animationThread;
    

	private String clientDeck; 
	private String deckOnTable; 

	private String playerCardPicked;
	private String tableCardPicked;

	private String tradeCardPicked;

	private String [] urlsArr;
	private String [] tableUrls;
	private String [] playerDeck;
	private String [] tableDeck;


	private int score;
	private int round;


	//urls for the images of the cards to display once the game starts
	private String urlHearts; 
	private String urlDiamonds; 
	private String urlSpades; 
	private String urlClubs;

	private Font font;

	public ClientScreen(){
		end = false;
		font = new Font("Monospaced", Font.BOLD, 20);
		introCards = new DLList<IntroCards>(); 
		introCardsThreads = new DLList<Thread>(); 
		


		//music 
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("backgroundMusic.wav").getAbsoluteFile()));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        } 



		tradeRequested = false;
		allWantTrade = false;
		someWantTrade = false;
		drawTradeInstructions = false;
		showCannotTrade = true;

		askedTrade = false;
		
		clickedStart = false; 
		notifySkip = false;
		
		outlineCard1 = false;
		outlineCard2 = false;
		outlineCard3 = false;
		outlineCard4 = false;
		outlineCard5 = false;

		outlineTableCard1 = false;
		outlineTableCard2 = false;
		outlineTableCard3 = false;
		outlineTableCard4 = false;

		showCard1 = true;
		showCard2 = true;
		showCard3 = true;
		showCard4 = true;
		showCard5 = true;

		showTableCard1 = true;
		showTableCard2 = true;
		showTableCard3 = true;
		showTableCard4 = true;

		choseCards = false;
		cannotClickCards = false;
		canRejectTrade = false;
		ableToTrade = true;
		ableToClickTrade = true;

		drawStep1 = false;
		drawStep2 = false;
		drawStep3 = false;
		drawNotMatch = false;
		drawSuccessfulMatch = false;
		startNewRound = false;
		startNewRound2 = false;
		startNewRound3 = false;

		clickTrade = false;

		this.setLayout(null);

		introCards.add(new IntroCards(-20, 20, 16, "right"));
		introCards.add(new IntroCards(-140, 20, "right"));
		int x = 100; 
		int y = 20;
		for(int i = 0; i < 14; i++){
			introCards.add(new IntroCards(x, y, "right"));
			x += 100; 
		}

		introCards.add(new IntroCards(-20, 740, 16, "left"));
		introCards.add(new IntroCards(-140, 740, "left"));
		x = 100; 
		y = 740;
		for(int i = 0; i < 14; i++){
			introCards.add(new IntroCards(x, y, "left"));
			x += 100; 
		}

		for(int i = 0; i < introCards.size(); i++){
			introCardsThreads.add(new Thread(introCards.get(i)));
			introCardsThreads.get(i).start();
		}

		
		try{
			titleImg = ImageIO.read(new File("title.png"));
		} catch (IOException e) {} 

		try{
			instructionsImg = ImageIO.read(new File("instructions.png"));
		} catch (IOException e) {} 

		try{
			waitingImg = ImageIO.read(new File("waiting.png"));
		} catch (IOException e) {} 

		//player's card images
		try {
            URL url1 = new URL("");
            card1 = ImageIO.read(url1);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url2 = new URL("");
            card2 = ImageIO.read(url2);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url3 = new URL("");
            card3 = ImageIO.read(url3);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url4 = new URL("");
            card4 = ImageIO.read(url4);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url5 = new URL("");
            card5 = ImageIO.read(url5);
        } catch (IOException e) {
            System.out.println(e);
        }

		//steps (images)
		try {
            URL url = new URL("https://i.postimg.cc/44jZpqzJ/myMatch.png");
            step1Img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url = new URL("https://i.postimg.cc/sD2SZB4F/table-Match.png");
            step2Img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url = new URL("https://i.postimg.cc/9fBFpbcL/notMatch.png");
            notMatchImg = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
			URL url = new URL("https://i.postimg.cc/HWw60QPM/Untitled-drawing-5.png");
			successfulMatch = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		try {
			URL url = new URL("https://i.postimg.cc/WpGPQwKj/skip.png");
			skipImg = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		//trade option 
		try {
			URL url = new URL("https://i.postimg.cc/brSff2SC/trade.png");
			tradeImg = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		//trade instructions
		try {
			URL url = new URL("https://i.postimg.cc/hjPpmWSV/trade-Instructions.png");
			tradeInstructions = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		//request trade image 
		try {
			URL url = new URL("https://i.postimg.cc/C1pVqryn/Untitled-drawing-4.png");
			requestTradeImg = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		//cannot trade notif
		try {
			URL url = new URL("https://i.postimg.cc/sDTTB3vG/Untitled-drawing-6.png");
			rejectTradeImg = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		//notify player of trade request
		try {
			URL url = new URL("https://i.postimg.cc/x1sFf1GL/notify-Trade.png");
			notifyTradeImg = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }
	

		try {
			URL url = new URL("https://i.postimg.cc/gcw1Kr6p/Untitled-drawing-8.png");
			skipStepImg = ImageIO.read(url);
		} catch (IOException e) {
            System.out.println(e);
        }

		//cards on table 
		try {
            URL url1 = new URL("");
            tableCard1 = ImageIO.read(url1);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url2 = new URL("");
            tableCard2 = ImageIO.read(url2);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url3 = new URL("");
            tableCard3 = ImageIO.read(url3);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url4 = new URL("");
            tableCard4 = ImageIO.read(url4);
        } catch (IOException e) {
            System.out.println(e);
        }


		//round images 
		try {
            URL url = new URL("https://i.postimg.cc/HWX0MT8g/round1.png");
            round1Img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url = new URL("https://i.postimg.cc/nLXBhxfN/round2.png");
            round2Img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url = new URL("https://i.postimg.cc/yN29JGVx/round3.png");
            round3Img = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }

		//lose/win screen images
		try {
            URL url = new URL("https://i.postimg.cc/0N17CntF/lose-Screen.png");
            loseImg = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		try {
            URL url = new URL("https://i.postimg.cc/RVjtMGzh/win-Screen.png");
            winImg = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }

		//skip to end 
		try {
            URL url = new URL("https://i.postimg.cc/Bnfd7NcB/Untitled-drawing-7.png");
            skipToEndImg = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e);
        }
		




		

		//animation thread 
        Animate animate = new Animate(this); 

        //start thread for animation 
        animationThread = new Thread( animate ); 
		animationThread.start(); 

		clientDeck = ""; 
		deckOnTable = ""; 

		playerCardPicked = "";
		tableCardPicked = "";

		urlsArr = new String[5];
		tableUrls = new String[5];
		playerDeck = new String[5];
		tableDeck = new String[4];

		score = 0;
		round = 1;
        
        


	   addMouseListener(this); 
	   addKeyListener(this);
	   this.setFocusable(true);

	}
	
	


	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if(end == true){
			g.setColor(new Color(128, 0, 32)); 
			g.fillRect(0, 0, 1920, 1080); 

			g.setColor(Color.BLACK);

			for(int i = 0; i < introCards.size(); i++){
				introCards.get(i).drawMe(g); 
			}

			g.drawImage(skipToEndImg, 460, 345, null);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("Your score: " + score, 650, 580);
		}
		
		if(end != true){

		
			//check if winner 
			if(round == 4){
				g.setColor(new Color(128, 0, 32)); 
				g.fillRect(0, 0, 1920, 1080); 

				g.setColor(Color.BLACK);

				for(int i = 0; i < introCards.size(); i++){
					introCards.get(i).drawMe(g); 
				}

				//send user's score to then find winner
				out.println("Score: " + score);
				out.println("");

				if(chatMessage.equals("You win")){
					g.drawImage(winImg, 420, 345, null);
				}
				else if(chatMessage.equals("You lose")){
					g.drawImage(loseImg, 420, 345, null);
				}

				g.setColor(Color.WHITE);
				g.setFont(font);
				g.drawString("Your score: " + score, 650, 580);
				
			}
			else if(!chatMessage.equals("Clients, all users have clicked start") && clickedStart == false && !chatMessage.equals("All have matched")){
				g.setColor(new Color(128, 0, 32)); 
				g.fillRect(0, 0, 1920, 1080); 

				g.drawImage(titleImg, 420, 170, null);
				g.drawImage(instructionsImg, 335, 360, null);

				for(int i = 0; i < introCards.size(); i++){
					introCards.get(i).drawMe(g); 
				}


			}
			else if(!chatMessage.equals("Clients, all users have clicked start") && clickedStart == true && !chatMessage.equals("All have matched") && !chatMessage.equals("All want to trade") && !chatMessage.equals("Some players want to trade")){
				g.setColor(new Color(128, 0, 32)); 
				g.fillRect(0, 0, 1920, 1080); 

				g.drawImage(titleImg, 420, 170, null);
				g.drawImage(waitingImg, 380, 450, null);
				for(int i = 0; i < introCards.size(); i++){
					introCards.get(i).drawMe(g); 
				}

			}

			
			else if(chatMessage.equals("Clients, all users have clicked start") || chatMessage.equals("All have matched") || chatMessage.equals("All want to trade") || chatMessage.equals("Some players want to trade")){
				//screen to show when everyone is ready
				g.setColor(new Color(128, 0, 32)); 
				g.fillRect(0, 0, 1920, 1080); 
				g.setColor(Color.WHITE); 

				//draws what the current round is 
				if(round == 3){
					g.drawImage(round3Img, 80, 0, null);
				}
				if(round == 2){
					g.drawImage(round2Img, 80, 0, null);
				}
				if(round == 1){
					g.drawImage(round1Img, 80, 0, null);
				}


				//draws skip button 
				g.setColor(Color.BLACK);
				g.fillRect(50, 450, 150, 50);
				//draws the skip button image
				g.drawImage(skipImg, 67, 430, null);


				if(ableToTrade == true){
					//draws the trade button image
					g.fillRect(50, 530, 150, 50);
					g.drawImage(tradeImg, 72, 510, null);

					//draws the reject trade button image
					g.fillRect(50, 610, 150, 70);
					g.drawImage(rejectTradeImg, 76, 600, null);
				}
				
				

				if(drawStep1 == true){
					g.drawImage(step1Img, 460, 0, null);
				}
				if(drawStep2 == true){
					g.drawImage(step2Img, 460, 0, null);
				}
				if(drawStep3 == true){
					g.drawImage(skipStepImg, 460, 0, null);
				}
				if(drawNotMatch == true){
					g.drawImage(notMatchImg, 460, 0, null);
				}
				if(drawSuccessfulMatch == true){
					g.drawImage(successfulMatch, 460, 0, null);
				}
				if(notifySkip == true){
					g.drawImage(skipStepImg, 460, 0, null);
				}


				if (ableToClickTrade == true){
					if(tradeRequested == true && allWantTrade == false){
						drawStep1 = false;
						drawStep2 = false;
						drawStep3 = false;
						drawNotMatch = false;
						drawSuccessfulMatch = false;
						g.drawImage(requestTradeImg, 460, 0, null);

					}
					if(tradeRequested == true && allWantTrade == true){
						drawStep1 = false;
						drawStep2 = false;
						drawStep3 = false;
						drawNotMatch = false;
						drawSuccessfulMatch = false;
						g.drawImage(tradeInstructions, 460, 0, null);
					}
					else if(tradeRequested == false && someWantTrade == true && drawSuccessfulMatch == false){
						drawStep1 = false;
						drawStep2 = false;
						drawStep3 = false;
						drawNotMatch = false;
						drawSuccessfulMatch = false;
						g.drawImage(notifyTradeImg, 400, 50, null);
					}
				}
				
				

				
				g.setColor(Color.WHITE);
				g.drawString("Your Cards: ", 660, 570);
				//draws cards to the player's screen 
				if(outlineCard1 == false && showCard1 == true){
					g.drawImage(card1, 325, 600, null);
				}
				if(outlineCard2 == false && showCard2 == true){
					g.drawImage(card2, 475, 600, null);
				}
				if(outlineCard3 == false && showCard3 == true){
					g.drawImage(card3, 625, 600, null);
				}
				if(outlineCard4 == false && showCard4 == true){
					g.drawImage(card4, 775, 600, null);
				}
				if(outlineCard5 == false && showCard5 == true){
					g.drawImage(card5, 925, 600, null);
				}

				//draw cards placed on table (visible to all players)
				if(outlineTableCard1 == false && showTableCard1 == true){
					g.drawImage(tableCard1, 400, 250, null);
				}
				if(outlineTableCard2 == false && showTableCard2 == true){
					g.drawImage(tableCard2, 550, 250, null);
				}
				if(outlineTableCard3 == false && showTableCard3 == true){
					g.drawImage(tableCard3, 700, 250, null);
				}
				if(outlineTableCard4 == false && showTableCard4 == true){
					g.drawImage(tableCard4, 850, 250, null);
				}

				//draws outlines to the card clicked (player's card)
				if(outlineCard1 == true && showCard1 == true){
					g.drawImage(card1, 325, 550, null);
				}
				if(outlineCard2 == true && showCard2 == true){
					g.drawImage(card2, 475, 550, null);
				}
				if(outlineCard3 == true && showCard3 == true){
					g.drawImage(card3, 625, 550, null);
				}
				if(outlineCard4 == true && showCard4 == true){
					g.drawImage(card4, 775, 550, null);
				}
				if(outlineCard5 == true && showCard5 == true){
					g.drawImage(card5, 925, 550, null);
				}

				//draws outline to the card clicked (table card)
				if(outlineTableCard1 == true && showTableCard1 == true){
					g.drawImage(tableCard1, 400, 200, null);
				}
				if(outlineTableCard2 == true && showTableCard2 == true){
					g.drawImage(tableCard2, 550, 200, null);
				}
				if(outlineTableCard3 == true && showTableCard3 == true){
					g.drawImage(tableCard3, 700, 200, null);
				}
				if(outlineTableCard4 == true && showTableCard4 == true){
					g.drawImage(tableCard4, 850, 200, null);
				}

				if(choseCards == true){
					if(compareCards() == true){

						
						if(outlineCard1 == true){
							showCard1 = false;
						}
						if(outlineCard2 == true){
							showCard2 = false;
						}
						if(outlineCard3 == true){
							showCard3 = false;
						}
						if(outlineCard4 == true){
							showCard4 = false;
						}
						if(outlineCard5 == true){
							showCard5 = false;
						}

						if(outlineTableCard1 == true){
							showTableCard1 = false;
						}
						if(outlineTableCard2 == true){
							showTableCard2 = false;
						}
						if(outlineTableCard3 == true){
							showTableCard3 = false;
						}
						if(outlineTableCard4 == true){
							showTableCard4 = false;
						}

						drawStep2 = false;
						drawSuccessfulMatch = true;
						
						out.println("matched");
						out.println("");
						
			
					}
					else{
						drawStep2 = false;
						drawNotMatch = true;
						
						//reset the positions of all of the cards 
						outlineCard1 = false;
						outlineCard2 = false;
						outlineCard3 = false;
						outlineCard4 = false;
						outlineCard5 = false;
						outlineTableCard1 = false;
						outlineTableCard2 = false;
						outlineTableCard3 = false;
						outlineTableCard4 = false;
						choseCards = false;
						playerCardPicked = "";
						tableCardPicked = "";

					}
					choseCards = false;
				}
			}
		}

	}

	public Boolean compareCards(){
		if(!playerCardPicked.equals("") && !tableCardPicked.equals("")){
			String[] playerCardInfo = playerCardPicked.split(" : ");
			String[] tableCardInfo = tableCardPicked.split(" : ");

			if(Integer.parseInt(playerCardInfo[1]) == Integer.parseInt(tableCardInfo[1])){
				score += Integer.parseInt(playerCardInfo[1]);
				return true;
			}
		}
		return false;
	}

	
	
	public void setImages(String [] urlsArr, String type){
		if(type.equals("player")){
			try {
				URL url1 = new URL(urlsArr[0]);
				card1 = ImageIO.read(url1);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url2 = new URL(urlsArr[1]);
				card2 = ImageIO.read(url2);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url3 = new URL(urlsArr[2]);
				card3 = ImageIO.read(url3);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url4 = new URL(urlsArr[3]);
				card4 = ImageIO.read(url4);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url5 = new URL(urlsArr[4]);
				card5 = ImageIO.read(url5);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		else if(type.equals("table")){
			try {
				URL url1 = new URL(tableUrls[1]);
				tableCard1 = ImageIO.read(url1);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url2 = new URL(tableUrls[2]);
				tableCard2 = ImageIO.read(url2);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url3 = new URL(tableUrls[3]);
				tableCard3 = ImageIO.read(url3);
			} catch (IOException e) {
				System.out.println(e);
			}
			try {
				URL url4 = new URL(tableUrls[4]);
				tableCard4 = ImageIO.read(url4);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		

		
	}

	public void reset(){
		outlineCard1 = false;
		outlineCard2 = false;
		outlineCard3 = false;
		outlineCard4 = false;
		outlineCard5 = false;

		outlineTableCard1 = false;
		outlineTableCard2 = false;
		outlineTableCard3 = false;
		outlineTableCard4 = false;

		showCard1 = true;
		showCard2 = true;
		showCard3 = true;
		showCard4 = true;
		showCard5 = true;

		showTableCard1 = true;
		showTableCard2 = true;
		showTableCard3 = true;
		showTableCard4 = true;

		choseCards = false;
		cannotClickCards = false;
		canRejectTrade = false;

		drawStep2 = false;
		drawStep3 = false;
		drawNotMatch = false;

		drawSuccessfulMatch = false;

		tradeRequested = false; 
		allWantTrade = false; 
		someWantTrade = false;
		notifySkip = false;
	}
	


	public Dimension getPreferredSize() {
		return new Dimension(1920, 1080);
	}


	public void actionPerformed(ActionEvent e) {


	}


	public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
		int mouseY = e.getY();
		if(!chatMessage.equals("Clients, all users have clicked start") && clickedStart == false){
			if(mouseX >= 0 && mouseX <= 1920 && mouseY >= 0 && mouseY <= 1080){
				out.println("started");
				clickedStart = true; 
				drawStep1 = true;
			}
		}

		//player picks which one of their cards they want to put down  
		if(outlineCard1 == false && outlineCard2 == false && outlineCard3 == false && outlineCard4 == false && outlineCard5 == false && cannotClickCards == false && allWantTrade == false){
			//click on card1
			if(mouseX >= 325 && mouseX <= 474 && mouseY >= 600 && mouseY <= 819){
				outlineCard1 = true;
				playerCardPicked = playerDeck[0].substring(20);
				drawStep1 = false;
				drawStep2 = true;
				drawNotMatch = false;
			}
			//click on card2 
			else if(mouseX >= 475 && mouseX <= 624 && mouseY >= 600 && mouseY <= 819){
				outlineCard2 = true;
				playerCardPicked = playerDeck[1];
				drawStep1 = false;
				drawStep2 = true;
				drawNotMatch = false;
			}
			//click on card3 
			else if(mouseX >= 625 && mouseX <= 774 && mouseY >= 600 && mouseY <= 819){
				outlineCard3 = true;
				playerCardPicked = playerDeck[2];
				drawStep1 = false;
				drawStep2 = true;
				drawNotMatch = false;
			}
			//click on card4 
			else if(mouseX >= 775 && mouseX <= 924 && mouseY >= 600 && mouseY <= 819){
				outlineCard4 = true;
				playerCardPicked = playerDeck[3];
				drawStep1 = false;
				drawStep2 = true;
				drawNotMatch = false;
			}
			//click on card5 
			else if(mouseX >= 925 && mouseX <= 1076 && mouseY >= 600 && mouseY <= 819){
				outlineCard5 = true;
				playerCardPicked = playerDeck[4];
				drawStep1 = false;
				drawStep2 = true;
				drawNotMatch = false;
			}
		}
		if(outlineCard1 == true || outlineCard2 == true || outlineCard3 == true || outlineCard4 == true || outlineCard5 == true && cannotClickCards == false && allWantTrade == false){
			if(mouseX >= 400 && mouseX <= 550 && mouseY >= 250 && mouseY <= 469){
				outlineTableCard1 = true;
				tableCardPicked = tableDeck[0].substring(15);
				choseCards = true;
			}
			else if(mouseX >= 551 && mouseX <= 699 && mouseY >= 250 && mouseY <= 469){
				outlineTableCard2 = true;
				tableCardPicked = tableDeck[1];
				choseCards = true;
			}
			else if(mouseX >= 700 && mouseX <= 850 && mouseY >= 250 && mouseY <= 469){
				outlineTableCard3 = true;
				tableCardPicked = tableDeck[2];
				choseCards = true;
			}
			else if(mouseX >= 851 && mouseX <= 1002 && mouseY >= 250 && mouseY <= 469){
				outlineTableCard4 = true;
				tableCardPicked = tableDeck[3];
				choseCards = true;
			}
		}
		//user clicks skip button 
		else if(mouseX >= 50 && mouseX <= 200 && mouseY >= 450 && mouseY <= 500 && allWantTrade == false){
			if(outlineCard1 == true){
				showCard1 = false;
			}
			if(outlineCard2 == true){
				showCard2 = false;
			}
			if(outlineCard3 == true){
				showCard3 = false;
			}
			if(outlineCard4 == true){
				showCard4 = false;
			}
			if(outlineCard5 == true){
				showCard5 = false;
			}

			if(outlineTableCard1 == true){
				showTableCard1 = false;
			}
			if(outlineTableCard2 == true){
				showTableCard2 = false;
			}
			if(outlineTableCard3 == true){
				showTableCard3 = false;
			}
			if(outlineTableCard4 == true){
				showTableCard4 = false;
			}

			drawStep2 = false;
			out.println("skipped");
			out.println("");
			drawStep1 = false;
			drawStep2 = false;
			drawStep3 = false;
			drawNotMatch = false;
			notifySkip = true;
		}

		if(ableToClickTrade == true){

			//user clicks trade button 
			if(mouseX >= 50 && mouseX <= 200 && mouseY >= 530 && mouseY <= 580 && allWantTrade == false){
				if(outlineCard1 == true){
					showCard1 = false;
				}
				if(outlineCard2 == true){
					showCard2 = false;
				}
				if(outlineCard3 == true){
					showCard3 = false;
				}
				if(outlineCard4 == true){
					showCard4 = false;
				}
				if(outlineCard5 == true){
					showCard5 = false;
				}

				if(outlineTableCard1 == true){
					showTableCard1 = false;
				}
				if(outlineTableCard2 == true){
					showTableCard2 = false;
				}
				if(outlineTableCard3 == true){
					showTableCard3 = false;
				}
				if(outlineTableCard4 == true){
					showTableCard4 = false;
				}

				drawStep2 = false;
				out.println("trade requested");
				tradeRequested = true;

			}
			else if(allWantTrade == true){

				if(outlineCard1 == true){
					showCard1 = false;
				}
				if(outlineCard2 == true){
					showCard2 = false;
				}
				if(outlineCard3 == true){
					showCard3 = false;
				}
				if(outlineCard4 == true){
					showCard4 = false;
				}
				if(outlineCard5 == true){
					showCard5 = false;
				}

				if(outlineTableCard1 == true){
					showTableCard1 = false;
				}
				if(outlineTableCard2 == true){
					showTableCard2 = false;
				}
				if(outlineTableCard3 == true){
					showTableCard3 = false;
				}
				if(outlineTableCard4 == true){
					showTableCard4 = false;
				}

				//player picks which one of their cards they want to put down  
				if(outlineCard1 == false && outlineCard2 == false && outlineCard3 == false && outlineCard4 == false && outlineCard5 == false && cannotClickCards == false){
					//click on card1
					if(mouseX >= 325 && mouseX <= 474 && mouseY >= 600 && mouseY <= 819){
						outlineCard1 = true;
						tradeCardPicked = playerDeck[0].substring(20);
					}
					//click on card2 
					else if(mouseX >= 475 && mouseX <= 624 && mouseY >= 600 && mouseY <= 819){
						outlineCard2 = true;
						tradeCardPicked = playerDeck[1];
					}
					//click on card3 
					else if(mouseX >= 625 && mouseX <= 774 && mouseY >= 600 && mouseY <= 819){
						outlineCard3 = true;
						tradeCardPicked = playerDeck[2];
					}
					//click on card4 
					else if(mouseX >= 775 && mouseX <= 924 && mouseY >= 600 && mouseY <= 819){
						outlineCard4 = true;
						tradeCardPicked = playerDeck[3];
					}
					//click on card5 
					else if(mouseX >= 925 && mouseX <= 1076 && mouseY >= 600 && mouseY <= 819){
						outlineCard5 = true;
						tradeCardPicked = playerDeck[4];
					}
					
				}

				out.println("Trading card: " + tradeCardPicked);
			}
			else if(canRejectTrade == true){
				if(mouseX >= 50 && mouseX <= 200 && mouseY >= 610 && mouseY <= 680){
					out.println("reset");
				}
			}
		}
		

		
		
		
	

        repaint(); 

    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

	@Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'y' || e.getKeyCode() == KeyEvent.VK_Y) {
            end = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    

	




	public void connect() throws IOException{


		String hostName = "10.232.151.69"; 
		int portNumber = 1024;
		Socket socket = new Socket(hostName, portNumber);

		out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));



		//listens for inputs
		try {


			while (true) {


				chatMessage = in.readLine();
				if(chatMessage.length() > 18){
					if(chatMessage.substring(0, 18).equals("Your deck of cards")){
						clientDeck = chatMessage; 
						playerDeck = chatMessage.split(", ");
					}
					else if(chatMessage.substring(0, 4).equals("Deck")){
						deckOnTable = chatMessage; 
						tableDeck = chatMessage.split(", ");
					}
					else if(chatMessage.substring(0, 5).equals("https")){
						urlsArr = chatMessage.split(", ");
						setImages(urlsArr, "player");
					}
					else if(chatMessage.substring(0, 5).equals("table")){
						tableUrls = chatMessage.split(", ");
						setImages(tableUrls, "table");
					}
					if(chatMessage.equals("Some players want to trade") && choseCards == false && ableToTrade == true){
						someWantTrade = true;
						canRejectTrade = true;
					}
					if(chatMessage.substring(0, 14).equals("Trading card: ") && !tradeCardPicked.equals("")){
						if(!chatMessage.substring(15).equals("Trading card: " + tradeCardPicked)){
							if(tradeCardPicked.equals(playerDeck[0].substring(20))){
								playerDeck[0] = "Your deck of cards: " + chatMessage.substring(14);
								String[] cardInfo = playerDeck[0].substring(20).split(" : ");
								urlsArr[0] = (new Card(cardInfo[0], Integer.parseInt(cardInfo[1]))).getImgUrl();
								setImages(urlsArr, "player");
							}
							else if(tradeCardPicked.equals(playerDeck[1])){
								playerDeck[1] = chatMessage.substring(14);
								String[] cardInfo = playerDeck[1].split(" : ");
								urlsArr[1] = new Card(cardInfo[0], Integer.parseInt(cardInfo[1])).getImgUrl();
								setImages(urlsArr, "player");
							}
							else if(tradeCardPicked.equals(playerDeck[2])){
								playerDeck[2] = chatMessage.substring(14);
								String[] cardInfo = playerDeck[2].split(" : ");
								urlsArr[2] = new Card(cardInfo[0], Integer.parseInt(cardInfo[1])).getImgUrl();
								setImages(urlsArr, "player");
							}
							else if(tradeCardPicked.equals(playerDeck[3])){
								playerDeck[3] = chatMessage.substring(14);
								String[] cardInfo = playerDeck[3].split(" : ");
								urlsArr[3] = new Card(cardInfo[0], Integer.parseInt(cardInfo[1])).getImgUrl();
								setImages(urlsArr, "player");
							}
							else if(tradeCardPicked.equals(playerDeck[4])){
								playerDeck[4] = chatMessage.substring(14);
								String[] cardInfo = playerDeck[4].split(" : ");
								urlsArr[4] = new Card(cardInfo[0], Integer.parseInt(cardInfo[1])).getImgUrl();
								setImages(urlsArr, "player");
							}
							if(outlineCard1 == true){
								showCard1 = false;
							}
							if(outlineCard2 == true){
								showCard2 = false;
							}
							if(outlineCard3 == true){
								showCard3 = false;
							}
							if(outlineCard4 == true){
								showCard4 = false;
							}
							if(outlineCard5 == true){
								showCard5 = false;
							}
							drawStep1 = true; 
							reset();
						}
					}
				
				}
				else if(chatMessage.length() > 14){
					if(chatMessage.equals("All have matched")){
						round++;
						drawStep1 = true;
						reset();
					}
					if(chatMessage.equals("All want to trade")){
						allWantTrade = true;
						ableToTrade = false;
					}
					
				}
				else if(chatMessage.equals("reset")){
					drawStep1 = true;
					if(outlineCard1 == true){
						showCard1 = false;
					}
					if(outlineCard2 == true){
						showCard2 = false;
					}
					if(outlineCard3 == true){
						showCard3 = false;
					}
					if(outlineCard4 == true){
						showCard4 = false;
					}
					if(outlineCard5 == true){
						showCard5 = false;
					}

					if(outlineTableCard1 == true){
						showTableCard1 = false;
					}
					if(outlineTableCard2 == true){
						showTableCard2 = false;
					}
					if(outlineTableCard3 == true){
						showTableCard3 = false;
					}
					if(outlineTableCard4 == true){
						showTableCard4 = false;
					}
					ableToTrade = false;
					ableToClickTrade = false;

					reset();
				}
				
				
				repaint();


			}
		} catch (UnknownHostException e) {
			System.err.println("Host unkown: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}
