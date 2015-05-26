/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;


import static Cell_Culture.Game.nutrientCount;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Travis
 */
public class Player extends GameObject{
    
    Random rand = new Random();
    
    Handler handler; 
    
    Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );
    
    public float initialSpeed=7, moveSpeed=initialSpeed, delX, delY,
             orginX, orginY, boardSize, factor;
    
    boolean eat;
    
    public Player(float x, float y, ID id, Handler handler, int mass, int playerID,  Color color){
        super(x, y, id);
        this.handler = handler;
        this.playerID=playerID;
        this.mass = mass;
        if(color != null)this.color=color;
        this.alpha=false;
        this.orginX = x;
        this.orginY = y;
        factor=1f;
        tempX = rand.nextInt(4970);
        tempY = rand.nextInt(4970);
        
        //check to see if this player already exists on the board
        int foundPlayer = 0;
        
        for(int i=0; i< handler.object.size(); i++){    
            
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
                
                if(tempObject.playerID == playerID){
                    
                    foundPlayer++;
                    
                }
            }
        }
        //if this is the first player with this id then set them in on the board
        if(foundPlayer <= 1){
            
            for(int i=0; i< handler.object.size(); i++){

                GameObject tempObject = handler.object.get(i);
                if(tempObject.getId() != ID.Player){
                    tempObject.x-=tempX-x-diameter;
                    tempObject.y-=tempY-y-diameter;
                }
            }
        }
        
        boardSize = 5000;
        
        eat = false;
        
        diameter = mass*3;
        
    }

    public void tick(){
        
        //reset velocities to zero every tick
        velX=0;
        velY=0;
        
        //claculate speed relative to mass
        if(moveSpeed > (410*initialSpeed)/(400+mass)){
            moveSpeed -= 0.5;
        }
        
        //calculate change in  x and y 
        delX = (mouseX - (x+diameter/2));
        delY = (mouseY - (y+diameter/2));
        
        //claculate the angle between player and curser in radians
        direction = (float)Math.atan2(delY,delX);

       //if the curser is farther than 3 pixles away, move 
        if(delX>3 || delX<-3){
            velX = (float)(moveSpeed * Math.cos(direction));
        }
        if(delY>3 || delY<-3){
            velY = (float)(moveSpeed * Math.sin(direction));
        }

        //check to see which one is biggest and center on screen (alpha)
//        for(int i=0; i< handler.object.size(); i++){
//            
//            GameObject tempObject = handler.object.get(i);
//            
//            if(tempObject.getId() == ID.Player){
//                if(tempObject.playerID == playerID && tempObject.mass > mass){
//                    alpha = false;
//                }else alpha = true;
//            }
//        }
            
        
        //keeps the player in the center of the screen as it grows
//        if(alpha){
        x = orginX-(diameter/2);
        y = orginY-(diameter/2);
        
        //keeps track of where the player is on the game board
        tempX+=velX;
        tempY+=velY; 
        
        //clamps the player to the confines of the game board
        tempX = Game.clamp(tempX, 0, boardSize);
        tempY = Game.clamp(tempY, 0, boardSize);
        
        for(int i=0; i< handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
   //         if(tempObject.getId() != ID.Player){
                if(tempX > 0 && tempX < boardSize){
                    tempObject.setDeltaX(-velX);
                }else{
                    tempObject.setDeltaX(0);
                }
                if(tempY > 0 && tempY < boardSize){
                    tempObject.setDeltaY(-velY);
                }else{
                    tempObject.setDeltaY(0);
                }
         //   }
        }
//        }else{
//        //keeps track of where the player is on the game board
//        tempX+=velX;
//        tempY+=velY; 
//        
//        //clamps the player to the confines of the game board
//        tempX = Game.clamp(tempX, 0, boardSize);
//        tempY = Game.clamp(tempY, 0, boardSize);
//        
//        x=tempX;
//        y=tempY;
//        
//        }
        
        collision();    
        

        diameter = diameterClamp(mass+30);
        


        
        
    }
    
    public void collision(){
        
        float tempMinDistance, temX, temY;
        
        for(int i=0; i< handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
                       
            temX = (tempObject.getX()+(tempObject.getDiameter())/2)-(x+(diameter/2));
            temY = (tempObject.getY()+(tempObject.getDiameter())/2)-(y+(diameter/2));
            
            if(tempObject.getId() != ID.Player){

                if(tempObject.getId() == ID.Clone){
                    tempMinDistance = (diameter)/2 + (tempObject.getDiameter())/2;
                }else{
                    tempMinDistance = (diameter)/2;
                }
                if((temX*temX + temY*temY) < (tempMinDistance * tempMinDistance)){
//                    if(tempObject.getId() != ID.Clone){
                        mass+=tempObject.getMass();
//                        if(tempObject.getId() == ID.Nutrient){
//                        nutrientCount--;
//                        }
                            eat = true;
                            handler.object.remove(tempObject);   
                            
//                    }else if(tempObject.getId() == ID.Clone){
//                            tempObject.setVelX(0);
//                            tempObject.setVelY(0);
//                    }
                       
                }          
            }
            if(eat){
                if(diameter >= 300 && diameter%3 == 0){
                    factor *= 0.9995;
                    for(int j=0; j< handler.object.size(); j++){
                        GameObject tempObject2 = handler.object.get(j);
                        if(tempObject2.getId() != ID.Player){
                            tempObject2.x *= 0.9995f;
                            tempObject2.y *= 0.9995f;
                            tempObject2.diameter *= 0.9995f;
                        }
                    }
                }
                boardSize *= 0.9995f;
                float randWidth=rand.nextInt((int)(boardSize));
                float randHeight=rand.nextInt((int)(boardSize));
//                randWidth = Game.clamp(randWidth, 0,boardSize);
//                randHeight = Game.clamp(randWidth, 0,boardSize);
               
                handler.addObject(new Nutrient(randWidth-tempX-x-diameter,
                                      randHeight-tempX-y-diameter, ID.Nutrient, factor));
                nutrientCount++;
                eat = false;
            }
        }
    }
    
    @Override
    public void render(Graphics g){
        if(id == ID.Player) g.setColor(color);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, (int)diameter, (int)diameter);
        g.setFont(stringFont);
        g.drawString(String.valueOf((int)mass), (int)(x+diameter/2-10), (int)(y+diameter/2+7));
    }
    



}