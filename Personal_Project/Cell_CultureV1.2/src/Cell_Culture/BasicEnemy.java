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
    
    public BasicEnemy(float x, float y, ID id){
        super(x, y, id);
        
        velX = rand.nextInt(14)-7;
        velY = rand.nextInt(14)-7;
        mass = 2;
        diameter = 23;
    }
    
    
    @Override
    public void tick(){
        x+=velX+deltaX;
        y+=velY+deltaY;
        
        if(y<=0 || y>=5000) velY*= -1;
        if(x<=0 || x>=5000) velX*= -1;
 
    }
    
    @Override
    public void render(Graphics g){
        if(y>=0 && y<=Game.HEIGHT && x>=0 && x<=Game.WIDTH){
          g.setColor(Color.red);
          g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
        }
    }

}