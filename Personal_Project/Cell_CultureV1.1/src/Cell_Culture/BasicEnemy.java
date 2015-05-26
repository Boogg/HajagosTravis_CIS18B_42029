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
public class BasicEnemy extends GameObject{
    
    public BasicEnemy(float x, float y, ID id){
        super(x, y, id);
        
        velX = 5;
        velY = 5;
        mass = 2;
        diameter = 16;
    }
    
    public void tick(){
        x+=velX;
        y+=velY;
        
        if(y<=0 || y>=Game.HEIGHT-46) velY*= -1;
        if(x<=0 || x>=Game.WIDTH -25) velX*= -1;
    }
    
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
    }

}