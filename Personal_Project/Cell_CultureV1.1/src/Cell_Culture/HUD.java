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
    
    public static int HEALTH;
    
    public void tick(){
        HEALTH =Game.nutrientCount/3;
    }
    
    public void render(Graphics g){
//        g.setColor(Color.gray);
//        g.fillRect(15,15,200,32);
//        g.setColor(Color.green);
//        g.fillRect(15,15,HEALTH*2,32);
//        g.setColor(Color.white);
//        g.drawRect(15, 15, 200, 32);
//        g.drawString(String.valueOf(HEALTH), 15, 15);
        
        
        
    }
}
