import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 
import java.net.URL;

public class Card{

    private int value; 
    private String suit; 

    private String urlHearts; 
    private String urlDiamonds; 
    private String urlSpades; 
    private String urlClubs; 

    private String url; 

    public Card(String suit, int value){
        this.suit = suit; 
        this.value = value; 
        this.url = ""; 

        if(suit.equals("hearts")){
            if(value == 1){
                url = "https://i.postimg.cc/ZYfBYw38/ace-of-hearts.png";
            }
            else if(value == 2){
                url = "https://i.postimg.cc/QCSMFdHN/2-of-hearts.png"; 
            }
            else if(value == 3){
                url = "https://i.postimg.cc/N01Byjnh/3-of-hearts.png";
            }
            else if(value == 4){
                url = "https://i.postimg.cc/VvJhC7NS/4-of-hearts.png"; 
            }
            else if(value == 5){
                url = "https://i.postimg.cc/mrXf11S5/5-of-hearts.png";
            }
            else if(value == 6){
                url = "https://i.postimg.cc/VNHpkQfy/6-of-hearts.png"; 
            }
            else if(value == 7){
                url = "https://i.postimg.cc/rsGfWvkw/7-of-hearts.png";
            }
            else if(value == 8){
                url = "https://i.postimg.cc/FHzxsf7R/8-of-hearts.png"; 
            }
            else if(value == 9){
                url = "https://i.postimg.cc/xC3mPYch/9-of-hearts.png";
            }
            else if(value == 10){
                url = "https://i.postimg.cc/g096Frh1/10-of-hearts.png";
            }
            
        }
        
        else if(suit.equals("diamonds")){
            if(value == 1){
                url = "https://i.postimg.cc/T1PKqb85/ace-of-diamonds.png";
            }
            else if(value == 2){
                url = "https://i.postimg.cc/TwM1Sszx/2-of-diamonds.png"; 
            }
            else if(value == 3){
                url = "https://i.postimg.cc/Wb3N9jdr/3-of-diamonds.png";
            }
            else if(value == 4){
                url = "https://i.postimg.cc/ZqLXtBgq/4-of-diamonds.png"; 
            }
            else if(value == 5){
                url = "https://i.postimg.cc/SNCh1syn/5-of-diamonds.png";
            }
            else if(value == 6){
                url = "https://i.postimg.cc/jStG84g1/6-of-diamonds.png"; 
            }
            else if(value == 7){
                url = "https://i.postimg.cc/KzMqc1pL/7-of-diamonds.png";
            }
            else if(value == 8){
                url = "https://i.postimg.cc/ZKVcSPY2/8-of-diamonds.png"; 
            }
            else if(value == 9){
                url = "https://i.postimg.cc/P501X2g3/9-of-diamonds.png";
            }
            else if(value == 10){
                url = "https://i.postimg.cc/L6b1Dx89/10-of-diamonds.png";
            }
            
        }
        else if(suit.equals("spades")){
            if(value == 1){
                url = "https://i.postimg.cc/Nf5yKR7W/ace-of-spades.png";
            }
            else if(value == 2){
                url = "https://i.postimg.cc/D0fv3359/2-of-spades.png"; 
            }
            else if(value == 3){
                url = "https://i.postimg.cc/zvfrPSG5/3-of-spades.png";
            }
            else if(value == 4){
                url = "https://i.postimg.cc/8zQY7mg5/4-of-spades.png"; 
            }
            else if(value == 5){
                url = "https://i.postimg.cc/vBkJ3Rk7/5-of-spades.png";
            }
            else if(value == 6){
                url = "https://i.postimg.cc/qMdYRGT7/6-of-spades.png"; 
            }
            else if(value == 7){
                url = "https://i.postimg.cc/Twg0MZhJ/7-of-spades.png";
            }
            else if(value == 8){
                url = "https://i.postimg.cc/3wDCmPjQ/8-of-spades.png"; 
            }
            else if(value == 9){
                url = "https://i.postimg.cc/nrVDR5mz/9-of-spades.png";
            }
            else if(value == 10){
                url = "https://i.postimg.cc/zfnHJ5Sv/10-of-spades.png";
            }
        }
        else if(suit.equals("clubs")){
            if(value == 1){
                url = "https://i.postimg.cc/vBh1VzKd/ace-of-clubs.png";
            }
            else if(value == 2){
                url = "https://i.postimg.cc/Cxcb3w7g/2-of-clubs.png"; 
            }
            else if(value == 3){
                url = "https://i.postimg.cc/G3z3J9md/3-of-clubs.png";
            }
            else if(value == 4){
                url = "https://i.postimg.cc/25YgKF0J/4-of-clubs.png"; 
            }
            else if(value == 5){
                url = "https://i.postimg.cc/WtR5JTKK/5-of-clubs.png";
            }
            else if(value == 6){
                url = "https://i.postimg.cc/8cvqYKg2/6-of-clubs.png"; 
            }
            else if(value == 7){
                url = "https://i.postimg.cc/CKtrVG7S/7-of-clubs.png";
            }
            else if(value == 8){
                url = "https://i.postimg.cc/66VcHJk9/8-of-clubs.png"; 
            }
            else if(value == 9){
                url = "https://i.postimg.cc/VsF9q9tv/9-of-clubs.png";
            }
            else if(value == 10){
                url = "https://i.postimg.cc/NfMXR3YC/10-of-clubs.png";
            }
        }
        
    }

    public int getValue(){
        return value;
    }

    public String getSuit(){
        return suit; 
    }

    
    public String getImgUrl(){
        return url;
    }
    

    public String toString(){
        return suit + " : " + value; 
    }
}
