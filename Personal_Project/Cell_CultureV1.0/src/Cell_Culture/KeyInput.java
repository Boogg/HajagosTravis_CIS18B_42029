/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Travis
 */
public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    
    
    public KeyInput(Handler handler){
        this.handler = handler;
        
    }
    
    public void tick(){
        
    }
    
    
   
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i=0;i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
            //    key Events
                
                if(key == KeyEvent.VK_W)tempObject.setUpPressed(true);
                if(key == KeyEvent.VK_S)tempObject.setDownPressed(true);
                if(key == KeyEvent.VK_D)tempObject.setRightPressed(true);
                if(key == KeyEvent.VK_A)tempObject.setLeftPressed(true);
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i=0;i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){
            //    key Events
                
                if(key == KeyEvent.VK_W)tempObject.setUpPressed(false);
                if(key == KeyEvent.VK_S)tempObject.setDownPressed(false);
                if(key == KeyEvent.VK_D)tempObject.setRightPressed(false);
                if(key == KeyEvent.VK_A)tempObject.setLeftPressed(false);
            }
        }
    }
    
    
    
}
