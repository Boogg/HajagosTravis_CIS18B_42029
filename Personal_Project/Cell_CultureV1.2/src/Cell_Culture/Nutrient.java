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
public class Nutrient extends GameObject{
    
    
    
    
    public Nutrient(float x, float y, ID id, float factor){
        super(x, y, id);
        
        diameter = 23 * factor;
        mass = 1;
        
    }
    
    @Override
    public void tick(){
  
        x+=deltaX;
        y+=deltaY;
        
    }
    
    @Override
    public void render(Graphics g){
        if(y>= -23 && y<=Game.HEIGHT && x>= -23 && x<=Game.WIDTH){
          g.setColor(color);
          g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
          g.setColor(Color.black);
          g.drawString("+" + String.valueOf((int)mass), (int)(x+diameter/2-7), (int)(y+diameter/2+8));
          g.setColor(Color.white);
          g.drawString("+" + String.valueOf((int)mass), (int)(x+diameter/2-8), (int)(y+diameter/2+7));
        }
    }
    
}
