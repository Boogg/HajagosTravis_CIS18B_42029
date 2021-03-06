/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Travis
 */
public class BasicEnemy extends GameObject{
    Random rand = new Random();
    float mapX;
    float mapY;
    
    public BasicEnemy(float x, float y, ID id){
        super(x, y, id);
        
        this.velX = rand.nextInt(14)-7;
        this.velY = rand.nextInt(14)-7;
        this.mass = 2;
        this.diameter = 23;
        
        
        this.mapX = x;
        this.mapY = y;
        
    }
    
    
    @Override
    public void tick(){
        x+=velX+deltaX;
        y+=velY+deltaY;
        
        mapX+=velX;
        mapY+=velY;
        
        if(mapY<=0 || mapY>=5000) velY *= -1;
        if(mapX<=0 || mapX>=5000) velX *= -1;
 
    }
    
    @Override
    public void render(Graphics g){
        if(y>=0 && y<=Game.HEIGHT && x>=0 && x<=Game.WIDTH){
          g.setColor(Color.red);
          g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
          g.setColor(Color.black);
          g.drawString("+" + String.valueOf((int)mass), (int)(x+diameter/2-7), (int)(y+diameter/2+8));
          g.setColor(Color.white);
          g.drawString("+" + String.valueOf((int)mass), (int)(x+diameter/2-8), (int)(y+diameter/2+7));
        }
    }

}