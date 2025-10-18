public class Manager{
    private MyArrayList<ServerThread> list; 
    private Boolean allStarted; 
    private Boolean allMatched;
    private Boolean allWantTrade;
    private Boolean onlySomeWantTrade;
    private Boolean allNewRound1;
    private Boolean allNewRound2;
    private Boolean ableToMakeNewDeck;
    private MyHashSet<Card> deck; 

    private MyHashSet<Card> onTableDeck; 

    public Manager(){
        list = new MyArrayList<ServerThread>(); 
        allStarted = false; 
        allMatched = false;
        allWantTrade = false;
        allNewRound1 = false;
        allNewRound2 = false;
        ableToMakeNewDeck = false;
        onlySomeWantTrade = false;

        deck = new MyHashSet<Card> (); 

        onTableDeck = new MyHashSet<Card>(); 

        //add hearts to the deck 
        deck.add(new Card("hearts", 1)); //ace
        deck.add(new Card("hearts", 2));
        deck.add(new Card("hearts", 3));
        deck.add(new Card("hearts", 4));
        deck.add(new Card("hearts", 5));
        deck.add(new Card("hearts", 6));
        deck.add(new Card("hearts", 7));
        deck.add(new Card("hearts", 8));
        deck.add(new Card("hearts", 9));
        deck.add(new Card("hearts", 10));


        //add diamonds to the deck 
        deck.add(new Card("diamonds", 1)); //ace
        deck.add(new Card("diamonds", 2)); 
        deck.add(new Card("diamonds", 3)); 
        deck.add(new Card("diamonds", 4)); 
        deck.add(new Card("diamonds", 5)); 
        deck.add(new Card("diamonds", 6)); 
        deck.add(new Card("diamonds", 7)); 
        deck.add(new Card("diamonds", 8)); 
        deck.add(new Card("diamonds", 9)); 
        deck.add(new Card("diamonds", 10)); 

        //add spades to the deck
        deck.add(new Card("spades", 1)); //ace
        deck.add(new Card("spades", 2)); 
        deck.add(new Card("spades", 3)); 
        deck.add(new Card("spades", 4)); 
        deck.add(new Card("spades", 5)); 
        deck.add(new Card("spades", 6)); 
        deck.add(new Card("spades", 7)); 
        deck.add(new Card("spades", 8)); 
        deck.add(new Card("spades", 9)); 
        deck.add(new Card("spades", 10)); 

        //add clubs to the deck
        deck.add(new Card("clubs", 1)); //ace
        deck.add(new Card("clubs", 2)); 
        deck.add(new Card("clubs", 3)); 
        deck.add(new Card("clubs", 4)); 
        deck.add(new Card("clubs", 5)); 
        deck.add(new Card("clubs", 6)); 
        deck.add(new Card("clubs", 7)); 
        deck.add(new Card("clubs", 8)); 
        deck.add(new Card("clubs", 9)); 
        deck.add(new Card("clubs", 10)); 


        //add cards which will be shown on the table (displayed to both clients)
        for(int i = 0; i < 4; i++){
            int randNum = (int)(Math.random()*(deck.size()-1)); 
            if(onTableDeck.add(deck.DLList().get(randNum)) == false){
                if(randNum != deck.size()-1){
                    randNum += 1;
                }
                else{
                    randNum -= 1;
                }
            }
            onTableDeck.add(deck.DLList().get(randNum)); 
        }


    }

    public MyHashSet<Card> getDeck(){
        return deck; 
    }

    public MyHashSet<Card> getOnTableDeck(){
        return onTableDeck; 
    }

    public void createNewDeck(){
        onTableDeck.clear();
        //add cards which will be shown on the table (displayed to both clients)
        for(int i = 0; i < 4; i++){
            int randNum = (int)(Math.random()*(deck.size()-1)); 
            if(onTableDeck.add(deck.DLList().get(randNum)) == false){
                if(randNum != deck.size()-1){
                    randNum += 1;
                }
                else{
                    randNum -= 1;
                }
            }
            onTableDeck.add(deck.DLList().get(randNum)); 
        }
    }



    public void checkStatus(){
        int counter = 0; 
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getStartStatus() == true){
                counter += 1; 
            }
        }
        if(counter == list.size()){
            allStarted = true; 
        }
    }

    public void checkMatchStatus(){
        int counter = 0; 
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getMatchStatus() == true){
                counter += 1; 
            }
        }
        if(counter == list.size()){
            allMatched = true; 
            if(ableToMakeNewDeck == true){
                createNewDeck();
                ableToMakeNewDeck = false;
            }
            
        }
    }


    public void checkTradeStatus(){
        int counter = 0; 
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getTradeStatus() == true){
                counter += 1; 
            }
        }
        if(counter == list.size()){
            allWantTrade = true; 
        }
        else if(counter > 0 && counter != list.size()){
            onlySomeWantTrade = true;
        }
    }


    public DLList getTradingCards(){
        DLList<String> tradingCardList = new DLList<String>();
        for(int i = 0; i < list.size(); i++){
            if(!list.get(i).getTradingCard().equals("")){
                tradingCardList.add(list.get(i).getTradingCard());
            }
        }
        return tradingCardList;
    }




    public int compareScores(){
        int highestScore = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getScore() > highestScore){
                highestScore = list.get(i).getScore();
            }
        }
        //returns the highest score 
        return highestScore;
    }

    //used to send a message to all clients 
    public void broadcast(String message){
        for(int i = 0; i < list.size(); i++){
            list.get(i).sendMessage(message); 
        }
    }
    
    public boolean allStarted(){
        return allStarted; 
    }


    public void setAllStarted(Boolean newStarted){
        this.allStarted = newStarted;
    }

    public void setSomeWantTrade(Boolean status){
        this.onlySomeWantTrade = status;
    }

    public void setAllWantTrade(Boolean status){
        this.allWantTrade = status;
    }

    public boolean allMatched(){
        return allMatched;
    }

    public boolean allWantTrade(){
        return allWantTrade;
    }

    public boolean someWantTrade(){
        return onlySomeWantTrade;
    }

    public void setAllMatched(Boolean status){
        this.allMatched = status;
    }


    public void add(ServerThread st){
        list.add(st);
    }

    public void setNewDeckBoolean(Boolean status){
        this.ableToMakeNewDeck = status;
    }
}
