import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 


public class IntroCards implements Runnable{

    private int x, y; 
    private BufferedImage card1; 
    private BufferedImage card2; 
    private BufferedImage card3; 
    private BufferedImage card4; 
    private BufferedImage card5; 
    private BufferedImage card6; 
    private BufferedImage card7; 
    private BufferedImage card8; 
    private BufferedImage card9; 
    private BufferedImage card10; 
    private BufferedImage card11; 
    private BufferedImage card12; 
    private BufferedImage card13; 
    private BufferedImage card14; 
    private BufferedImage card15;

    private BufferedImage card; 


    private int cardNum; 

    private String direction; 

    public IntroCards(int x, int y, String direction){
        this.x = x; 
        this.y = y; 

        this.direction = direction; 
        try{
			card1 = ImageIO.read(new File("card1.png"));
		} catch (IOException e) {} 
        try{
			card2 = ImageIO.read(new File("card2.png"));
		} catch (IOException e) {} 
        try{
			card3 = ImageIO.read(new File("card3.png"));
		} catch (IOException e) {}
        try{
			card4 = ImageIO.read(new File("card4.png"));
		} catch (IOException e) {} 
        try{
			card5 = ImageIO.read(new File("card5.png"));
		} catch (IOException e) {} 
        try{
			card6 = ImageIO.read(new File("card6.png"));
		} catch (IOException e) {} 
        try{
			card7 = ImageIO.read(new File("card7.png"));
		} catch (IOException e) {} 
        try{
			card8 = ImageIO.read(new File("card8.png"));
		} catch (IOException e) {} 
        try{
			card9 = ImageIO.read(new File("card9.png"));
		} catch (IOException e) {} 
        try{
			card10 = ImageIO.read(new File("card10.png"));
		} catch (IOException e) {} 
        try{
			card11 = ImageIO.read(new File("card11.png"));
		} catch (IOException e) {} 
        try{
			card12 = ImageIO.read(new File("card12.png"));
		} catch (IOException e) {} 
        try{
			card13 = ImageIO.read(new File("card13.png"));
		} catch (IOException e) {} 
        try{
			card14 = ImageIO.read(new File("card14.png"));
		} catch (IOException e) {}

        int randNum = (int)(Math.random()*14 + 1); 
        if(randNum == 1){
            card = card1;
        }
        if(randNum == 2){
            card = card2;
        }
        if(randNum == 3){
            card = card3;
        }
        if(randNum == 4){
            card = card4;
        }
        if(randNum == 5){
            card = card5;
        }
        if(randNum == 6){
            card = card6;
        }
        if(randNum == 7){
            card = card7;
        }
        if(randNum == 8){
            card = card8;
        }
        if(randNum == 9){
            card = card9;
        }
        if(randNum == 10){
            card = card10;
        }
        if(randNum == 11){
            card = card11;
        }
        if(randNum == 12){
            card = card12;
        }
        if(randNum == 13){
            card = card13;
        }
        if(randNum == 14){
            card = card14;
        }


    }

    public IntroCards(int x, int y, int num, String direction){
        this.x = x; 
        this.y = y; 

        this.direction = direction;
        
        try{
			card15 = ImageIO.read(new File("readyYet.png"));
		} catch (IOException e) {}
        card = card15;
    }

    public void drawMe(Graphics g){
        g.drawImage(card, x, y, null); 
    }

    public void run(){

        if(direction.equals("right")){
            while (true){
                x++; 
                if(x > 1440){
                    x = -200; 
                }
                try{
                    Thread.sleep(10); 
                }catch(InterruptedException ex){
                    Thread.currentThread().interrupt(); 
                }
            }
        }
        else if(direction.equals("left")){
            while (true){
                x--; 
                if(x < -200){
                    x = 1440; 
                }
                try{
                    Thread.sleep(10); 
                }catch(InterruptedException ex){
                    Thread.currentThread().interrupt(); 
                }
            }
        }
        
    }

}
