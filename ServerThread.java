import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.*;



public class ServerThread implements Runnable {
    private Socket socket;
    private String message; 
    private String tradingCardPicked;
    private Manager manager; 
    private String messageToSend; 
    private Boolean send; 
    private PrintWriter out;
    private String startStatus; 
    private String tradeStatus; 
    private String matchStatus;
    private String roundStatus;
    private Boolean startStatusBoolean;
    private Boolean tradeStatusBoolean;
    private Boolean matchStatusBoolean;
    private Boolean sendDeckOnTable; 
    private Boolean sendNewUserDeck;

    private MyHashSet<Card> deck; 
    private MyHashSet<Card> myCards; 

    private MyHashSet<Card> onTableDeck; 
    private String cardUrls;
    private String tableCardUrls;
    private int playerScore;


    public ServerThread(Socket socket, Manager manager) {
        this.socket = socket;
        this.message = ""; 
        this.tradingCardPicked = "";
        this.manager = manager;
        this.messageToSend = ""; 
        this.startStatus = ""; 
        this.tradeStatus = "";
        this.matchStatus = "";
        this.roundStatus = "";
        this.send = false; 
        this.startStatusBoolean = false; 
        this.tradeStatusBoolean = false;
        this.matchStatusBoolean = false;
        cardUrls = "";
        tableCardUrls = "";

        sendDeckOnTable = true; 
        sendNewUserDeck = false;
        
        playerScore = 0;

        deck = manager.getDeck();
        myCards = new MyHashSet<Card>(); 

        for(int i = 0; i < 5; i++){
            int randNum = (int)(Math.random()*(deck.size()-1)); 
            if(myCards.add(deck.DLList().get(randNum)) == false){
                if(randNum != deck.size()-1){
                    randNum += 1;
                }
                else{
                    randNum -= 1;
                }
            }
            myCards.add(deck.DLList().get(randNum)); 
        }


        onTableDeck = manager.getOnTableDeck(); 


    }

    public void newUserDeck(){
        myCards.clear();
        for(int i = 0; i < 5; i++){
            int randNum = (int)(Math.random()*(deck.size()-1)); 
            if(myCards.add(deck.DLList().get(randNum)) == false){
                if(randNum != deck.size()-1){
                    randNum += 1;
                }
                else{
                    randNum -= 1;
                }
            }
            myCards.add(deck.DLList().get(randNum)); 
        }
    }

    public void sendMyCardNewUrls(){
        cardUrls = "";
        for(int i = 0; i < myCards.size(); i++){
            cardUrls += myCards.DLList().get(i).getImgUrl() + ", ";
        }
        out.println(cardUrls);
    }

    public void sendTableDeckNewUrls(){
        tableCardUrls = "";
        tableCardUrls = "table cards urls, ";
        for(int i = 0; i < onTableDeck.size(); i++){
            tableCardUrls += onTableDeck.DLList().get(i).getImgUrl();
            if(i != onTableDeck.size()-1){
                tableCardUrls += ", ";
            }
        }
        out.println(tableCardUrls);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ": connection opened.");
        
        
        try {

            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            
            try{

                out.println("Your deck of cards: " + myCards.toString()); 
                for(int i = 0; i < myCards.size(); i++){
                    cardUrls += myCards.DLList().get(i).getImgUrl() + ", ";
                }
                tableCardUrls = "table cards urls, ";
                for(int i = 0; i < onTableDeck.size(); i++){
                    tableCardUrls += onTableDeck.DLList().get(i).getImgUrl();
                    if(i != onTableDeck.size()-1){
                        tableCardUrls += ", ";
                    }
                }
                out.println(cardUrls);
                out.println(tableCardUrls);

                out.println("Deck on table: " + onTableDeck.toString()); 

                
                while(true){
                    message = in.readLine();

                    //checks score of the players if end of game is reached
                    if(!message.equals("")){
                        if(message.substring(0, 5).equals("Score")){
                            manager.setAllMatched(false);
                            matchStatusBoolean = false;
                            score(message);
                            if(manager.compareScores() == playerScore){
                                out.println("You win");
                                startStatusBoolean = false;
                            }
                            else{
                                out.println("You lose");
                                startStatusBoolean = false;
                            }
                        }
                    }
                    startStatus(message); 
                    manager.checkStatus(); 
                    if(manager.allStarted() == true){
                        manager.broadcast("Clients, all users have clicked start");
                        manager.setAllStarted(false);
                    }

                    tradeStatus(message);
                    manager.checkTradeStatus();
                    if(manager.allWantTrade() == true){
                        manager.broadcast("All want to trade");
                        manager.broadcast("Clients, all users have clicked start");
                        manager.setAllWantTrade(false);
                        tradeStatus = "";
                        tradeStatusBoolean  = false;
                        tradingCardPicked = "";
                    }
                    else if(manager.someWantTrade() == true && manager.allWantTrade() == false){
                        manager.broadcast("Some players want to trade");
                        manager.broadcast("Clients, all users have clicked start");
                    }

                    matchStatus(message);
                    manager.checkMatchStatus();
                    if(manager.allMatched() == true){
                        manager.broadcast("All have matched");
                        manager.broadcast("Clients, all users have clicked start");
                        manager.setAllMatched(false);
                        matchStatusBoolean = false;
                        sendTableDeck();
                        sendUserDeck();
                        sendMyCardNewUrls();
                        sendTableDeckNewUrls();
                        
                    }
                    
                    if(message.length() > 14){
                        if(message.substring(0, 14).equals("Trading card: ")){
                            tradingCard(message);
                            DLList<String> tradingList = manager.getTradingCards();
                            if(tradingList.size() == 2){
                                manager.broadcast(tradingList.get(0));
                                manager.broadcast(tradingList.get(1));
                                manager.broadcast("Clients, all users have clicked start");

                            }
                            
                        }
                    }

                    if(message.equals("reset")){
                        manager.setSomeWantTrade(false);
                        tradeStatus = "";
                        tradeStatusBoolean  = false;
                        tradingCardPicked = "";
                        manager.broadcast("reset");
                        manager.broadcast("Clients, all users have clicked start");
                    }
                    
                    
                    

                }
                

                
            }
            catch(IOException e1) {
                System.err.println("ST Couldn't get I/O for the connection to ");
                System.exit(1);
            }
            

        

        } catch (IOException ex) {
            System.out.println("Error listening for a connection");
            System.out.println(ex.getMessage());
        }
    }

    public void sendTableDeck(){
        onTableDeck = manager.getOnTableDeck(); 
        manager.broadcast("Deck on table: " + onTableDeck.toString());
    }

    public void tradingCard(String message){
        this.tradingCardPicked = message;
    }

    public String getTradingCard(){
        return tradingCardPicked;
    }

    public void sendUserDeck(){
        newUserDeck();
        out.println("Your deck of cards: " + myCards.toString()); 
    }


    public void sendMessage(String message){
        out.println(message);
    }

    public void startStatus(String message){
        this.startStatus = message; 
        if(startStatus.equals("started")){
            startStatusBoolean = true; 
        }
        
    }

    public void tradeStatus(String message){
        this.tradeStatus = message; 
        if(tradeStatus.equals("trade requested")){
            tradeStatusBoolean = true;
        }
    }

    

    public void matchStatus(String message){
        this.matchStatus = message; 
        if(matchStatus.equals("matched") || matchStatus.equals("skipped")){
            matchStatusBoolean = true;
        }
    }

    public void score(String message){
        this.playerScore = Integer.parseInt(message.substring(7));
    }

    public int getScore(){
        return playerScore;
    }

    public Boolean getStartStatus(){
        return startStatusBoolean; 
    }

    public Boolean getTradeStatus(){
        return tradeStatusBoolean;
    }

    public Boolean getMatchStatus(){
        return matchStatusBoolean; 
    }
    
    
}
