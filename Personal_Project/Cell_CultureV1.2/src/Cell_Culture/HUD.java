/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Travis
 */
public class HUD {
    
    Handler handler;
    
    public static int HEALTH;
    
    float deltaX, deltaY;
    
    public HUD(Handler handler){
        this.handler = handler;
        this.deltaX = 0;
        this.deltaY = 0;
    }
    
    public void tick(){
        
        for(int i=0; i< handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                deltaX = tempObject.tempX;
                deltaY = tempObject.tempY;
            }
        }
        deltaX %= 50;
        deltaY %= 50;
//        HEALTH =Game.nutrientCount/3;
    }
    
    public void render(Graphics g){
        g.setColor(Color.darkGray);
        for(int i=0; i<Game.WIDTH; i+=50){
            g.drawLine(i-(int)deltaX, 0, i-(int)deltaX, Game.HEIGHT);
        }
        for(int j=0; j<Game.HEIGHT; j+=50){
            g.drawLine(0, j-(int)deltaY, Game.WIDTH, j-(int)deltaY);
        }
        
        
//        g.fillRect(15,15,200,32);
//        g.setColor(Color.green);
//        g.fillRect(15,15,HEALTH*2,32);
//        g.setColor(Color.white);
//        g.drawRect(15, 15, 200, 32);
//        g.drawString(String.valueOf(HEALTH), 15, 15);
//        
        
        
    }
}
