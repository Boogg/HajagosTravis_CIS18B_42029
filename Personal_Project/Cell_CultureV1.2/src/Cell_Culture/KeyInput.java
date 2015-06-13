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
    
    private boolean spacePressed;
    
    public KeyInput(Handler handler){
        
        this.handler = handler;
        
    }
    
    public void tick(){
        
    }
    
    
   
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        //press space bar to split
        if(key == KeyEvent.VK_SPACE){
            for(int i=0;i<handler.object.size();i++){
                GameObject tempObject = handler.object.get(i);

                if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Clone){
                //    key Events

                    if(tempObject.getMass() > 40 && spacePressed == false){
                        tempObject.setMass(tempObject.mass/2);


                        float xDelt= 2.0f + (float)(Math.cos(tempObject.direction)*tempObject.getDiameter() ),
                              yDelt= 2.0f + (float)(Math.sin(tempObject.direction)*tempObject.getDiameter() );
                        System.out.println(xDelt);
                        System.out.println((int)(tempObject.getX()+xDelt));
                        handler.addObject(new Clone(
                                (int)(tempObject.getX()+xDelt), 
                                (int)(tempObject.getY()+yDelt), 
                                ID.Clone, handler, tempObject.getMass(), 
                                tempObject.playerID, tempObject.color, 15f));
                    }
                }
            }
            spacePressed = true;
        } 
        //press escape to exit game
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
//        for(int i=0;i<handler.object.size();i++){
//            GameObject tempObject = handler.object.get(i);
//            
//            if(tempObject.getId() == ID.Player){
            //    key Events
                
                if(key == KeyEvent.VK_SPACE){
                    spacePressed = false;
                }
//            }
//        }
    }
    
    public void spacePressed(){
        
    }
    
}
