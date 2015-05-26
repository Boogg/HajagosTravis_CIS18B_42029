/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Travis
 */
public class Player extends GameObject{
    
    Handler handler; 
    
    Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );
    
    public float moveSpeed, initialSpeed=5;    
    
    public Player(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        
        diameter = 32;
        mass=10;
        
    }
    
 
    

    
    public void tick(){
        

        velX=0;
        velY=0;
        
        moveSpeed = (410*initialSpeed) / (400+mass);
        
        if (leftPressed && !rightPressed){
            if(upPressed && !downPressed && !(y <= 0)){
                velY = (float)(-moveSpeed*(0.707));
                velX = (float)(-moveSpeed*(0.707));
            }else if(downPressed && !upPressed && !(y >= (Game.HEIGHT-diameter))){
                velY = (float)(moveSpeed*(0.707));
                velX = (float)(-moveSpeed*(0.707));
            }else{
                velX =(-moveSpeed);
            }
        }else if (rightPressed && !leftPressed){
            if(upPressed && !downPressed && !(y <= 0)){
                velY =(float)(-moveSpeed*(0.707));
                velX = (float)(moveSpeed*(0.707));
            }else if(downPressed && !upPressed && !(y >=(Game.HEIGHT-diameter))){
                velY = (float)(moveSpeed*(0.707));
                velX = (float)(moveSpeed*(0.707));
            }else{
                velX = (moveSpeed);
            }
        }
        if ((upPressed)&&(!downPressed || !leftPressed || !rightPressed)){
            velY = (-moveSpeed);
        }else if((downPressed)&& (!upPressed || !leftPressed || !rightPressed)){
            velY = (moveSpeed);
        }
        
        x+=velX;
        y+=velY;
        
        x = Game.clamp(x, 0, Game.WIDTH -diameter);
        y = Game.clamp(y, 0, Game.HEIGHT-diameter);
        
        collision();
    }
    
    private void collision(){
        for(int i=0; i< handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() != ID.Player){
                float tempX= (tempObject.getX()+(tempObject.getDiameter())/2)-(x+(diameter/2));
                float tempY = (tempObject.getY()+(tempObject.getDiameter())/2)-(y+(diameter/2));
                float tempMinDistance = (diameter)/2;
                if((tempX*tempX + tempY*tempY) < (tempMinDistance*tempMinDistance)){
                    
                    mass+=tempObject.getMass();
                    if(mass%3 == 0){
                        diameter+=3;    
                    }
                    if(tempObject.getId() == ID.Nutrient){
                    Game.nutrientCount--;
                    }
                    handler.object.remove(tempObject);
                }
            }
        }
    }
    
    public void render(Graphics g){
        if(id == ID.Player) g.setColor(color);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
        g.setColor(Color.black);
        g.drawOval((int)x, (int)y, (int)diameter, (int)diameter);
        g.setFont(stringFont);
        g.drawString(String.valueOf(mass), (int)(x+diameter/2-10), (int)(y+diameter/2+7));
    }

}