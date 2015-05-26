/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.awt.Graphics;


/**
 *
 * @author Travis
 */
public class Nutrient extends GameObject{
    
    
    
    
    public Nutrient(float x, float y, ID id){
        super(x, y, id);
        
        diameter = 16;
        mass=1;
        
 //
    }
    
    public void tick(){

        

    }
    
    public void render(Graphics g){
        g.setColor(color);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
    }
    
}
