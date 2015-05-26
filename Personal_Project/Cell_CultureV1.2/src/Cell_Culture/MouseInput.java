/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cell_Culture;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author rcc
 */
public class MouseInput implements MouseMotionListener{
    
    private Handler handler;
    
 
    
    public MouseInput(Handler handler){
        this.handler = handler;
        
        
        
    }
    
    public void tick(){
        
    }   
    
    
    @Override
    public void mouseMoved(MouseEvent e){
    int x = e.getX();
    int y = e.getY();
        for(int i=0;i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Clone){
            //    key Events
            tempObject.setMouseX(x);
            tempObject.setMouseY(y);
            

            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    
    
    
}
