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
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author Travis
 */
public class Player extends GameObject{
    
    Random rand = new Random();
    
    Handler handler; 
    
    Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );
    Font looseFont = new Font( "SansSerif", Font.PLAIN, 72 );
    public float initialSpeed=7, moveSpeed=initialSpeed, delX, delY,
             orginX, orginY, boardSize, factor;
    
    boolean eat, lost;
    
    public Graphics g;
    float maxMass = 0;
    
    public Player(float x, float y, ID id, Handler handler, float mass, int playerID,  Color color){
        super(x, y, id);
        this.handler = handler;
        this.playerID=playerID;
        this.mass = mass;
        if(color != null)this.color=color;
        this.alpha=true;
        this.orginX = x;
        this.orginY = y;
        factor=1f;
        tempX = rand.nextInt(4970);
        tempY = rand.nextInt(4970);
        
        this.lost = false;
        
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
        if(mass > maxMass){
            maxMass = mass;
        }
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

       //if the curser is farther than 1/4 the diameter away, move 
        if(delX>diameter/4 || delX<-diameter/4){
            velX = (float)(moveSpeed * Math.cos(direction));
        }
        if(delY>diameter/4 || delY<-diameter/4){
            velY = (float)(moveSpeed * Math.sin(direction));
        }

        //check to see which one is biggest and center on screen (alpha)
//        for(int i=0; i< handler.object.size(); i++){
//            
//            GameObject tempObject = handler.object.get(i);
//           if(tempObject.getId() == ID.Player){
//                if(tempObject.playerID == playerID && tempObject.mass > mass){
//                    if(tempObject.alpha){
//                    alpha = false;
//                    }
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
            if(tempObject.getId() != ID.Player){
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
            }
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
        if(mass>30){
            int temp = (int)mass;
            mass -= mass/50000;
            if(mass > 300 && (temp - (int)mass) == 1){
                boardSize += 0.02f;
                factor = boardSize/5000;                
                for(int j=0; j< handler.object.size(); j++){
                    GameObject tempObject2 = handler.object.get(j);
                    if(tempObject2.getId() != ID.Player){
                        tempObject2.x += 0.01f;
                        tempObject2.y += 0.01f;
                        tempObject2.diameter = 23 * factor;
                    }
                }

            }

            
        }
       
         if(mass <= 0){
            lost = true;
//            long time1 = System.currentTimeMillis();
//            long time2 = time1;
//            while((time2 - time1)< 3000){
//                time2 = System.currentTimeMillis();
//            }
//            Menu menu = new Menu();
//            menu.setResizable(false);
//            menu.setLocationRelativeTo(null);        
//            menu.setVisible(true);
//            Game.window.close();
//            Game.stop();
         }


        
        
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
                    if(tempObject.getId() != ID.StalkerEnemy){
                        mass+=tempObject.getMass();
//                        if(tempObject.getId() == ID.Nutrient){
//                        nutrientCount--;
                    }else{
                        mass-=tempObject.getMass();
                        float randWidth=rand.nextInt((int)(boardSize));
                        float randHeight=rand.nextInt((int)(boardSize));
                        handler.addObject(new StalkerEnemy(randWidth-tempX+x,
                                      randHeight-tempY+y, ID.StalkerEnemy, (float)factor));
                    }
                            eat = true;
                            handler.object.remove(tempObject);

                    }else if(tempObject.getId() == ID.Clone && tempObject.playerID == playerID){
                            tempObject.setVelX(0);
                            tempObject.setVelY(0);
//                    }

                }
            }
            if(eat){
                if(diameter >= 300 && mass%3 == 0){
                    boardSize -= 0.02f;   
                    factor = boardSize/5000;                    
                    for(int j=0; j< handler.object.size(); j++){
                        GameObject tempObject2 = handler.object.get(j);
                        if(tempObject2.getId() != ID.Player){
                            tempObject2.x -= 0.01f;
                            tempObject2.y -= 0.01f;
                            tempObject2.diameter = 23 * factor;
                        }
                    }

                }
                
                float randWidth=rand.nextInt((int)(boardSize));
                float randHeight=rand.nextInt((int)(boardSize));
//                randWidth = Game.clamp(randWidth, 0,boardSize);
//                randHeight = Game.clamp(randWidth, 0,boardSize);
               
                handler.addObject(new Nutrient(randWidth-tempX+x,
                                      randHeight-tempY+y, ID.Nutrient, factor));
       //         nutrientCount++;
                eat = false;
            }
        }
    }
    
    @Override
    public void render(Graphics g){
        this.g = g;
        if(id == ID.Player) g.setColor(color);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, (int)diameter, (int)diameter);
        g.setColor(Color.black);
        g.setFont(stringFont);
        if(User.getUserID() == -1){
            g.drawString(String.valueOf((int)mass), (int)(x+diameter/2-9), (int)(y+diameter/2+8));
            g.setColor(Color.white);
            g.drawString(String.valueOf((int)mass), (int)(x+diameter/2-10), (int)(y+diameter/2+7));
        }else{
            g.drawString(String.valueOf((int)mass), (int)(x+diameter/2-9), (int)(y+diameter/2+14));
            g.drawString(User.getUserName(), (int)(x+diameter/2-29), (int)(y+diameter/2-6));
            g.setColor(Color.white);
            g.drawString(String.valueOf((int)mass), (int)(x+diameter/2-10), (int)(y+diameter/2+15));
            g.drawString(User.getUserName(), (int)(x+diameter/2-30), (int)(y+diameter/2-7));
        }
        if(lost){   
            g.setColor(Color.white);
            g.setFont(looseFont);
            g.drawString("You Loose", Window.HEIGHT/2-10, Window.WIDTH/2-100);
            long time1 = System.currentTimeMillis();
            long time2 = time1;
            while((time2 - time1)< 3000){
                time2 = System.currentTimeMillis();
            }
            Menu menu = new Menu();
            menu.setResizable(false);
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            Game.window.close();
            Game.stop();
            
        }
        
        g.setColor(Color.white);
        //test displays
//        g.drawString("X: "+String.valueOf(tempX), 10, 15);
//        g.drawString("Y: "+String.valueOf(tempY), 10, 30);
//        g.drawString("Direction: "+String.valueOf(direction), 10, 45);
        g.drawString("Score: "+String.valueOf((int)maxMass), 10, 60);
        
    }
    
}