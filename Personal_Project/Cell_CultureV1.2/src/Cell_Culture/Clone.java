/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.Graphics;

/**
 *
 * @author Travis
 */
public class Clone extends Player{


    
    public Clone(float x, float y, ID id, Handler handler, int mass, int playerID, Color color) {
        super(x, y, id, handler, mass, playerID, color);
        
        
    }
    
    
@Override
public void collision(){

    float tempMinDistance, tempX, tempY;

    for(int i=0; i< handler.object.size(); i++){

        GameObject tempObject = handler.object.get(i);

        tempX = (tempObject.getX()+(tempObject.getDiameter())/2)-(x+(diameter/2));
        tempY = (tempObject.getY()+(tempObject.getDiameter())/2)-(y+(diameter/2));

        if(tempObject.getId() != ID.Clone){
            if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Clone){
                tempMinDistance = (diameter)/2 + (tempObject.getDiameter())/2;
            }else{
                tempMinDistance = (diameter)/2;
            }
            if((tempX*tempX + tempY*tempY) < (tempMinDistance * tempMinDistance)){
                if(tempObject.getId() != ID.Player || tempObject.getId() != ID.Clone){
                    mass+=tempObject.getMass();
                    if(tempObject.getId() == ID.Nutrient){
                    Game.nutrientCount--;
                    }
                    handler.object.remove(tempObject);
                }else if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Clone){
                    
                    if(tempObject.x+(tempObject.diameter/2)  > x+(diameter/2)){
                        setVelX(0);
                    }
                   // setVelX(0);
                    setVelY(0);
                }
            }
        }          
    }
}

    @Override
    public void tick(){
    //reset velocities to zero every tick
        velX=0;
        velY=0;
        
        diameter=mass+30; 
        
        //claculate speed relative to mass

        if(moveSpeed > (410*initialSpeed)/(400+mass)){
            moveSpeed-= 0.5;
        }
        
        //calculate change in  x and y 
        delX = (mouseX - (x+diameter/2));
        delY = (mouseY - (y+diameter/2));
        
        direction = (float)Math.atan2(delY,delX);

       // 
        if(delX>3 || delX<-3){
            velX = (float)(moveSpeed * Math.cos(direction));
        }
        if(delY>3 || delY<-3){
            velY = (float)(moveSpeed * Math.sin(direction));
        }
        
        x+=velX+deltaX;
        y+=velY+deltaY; 
        
        x = Game.clamp(x,0,Game.WIDTH);
        y = Game.clamp(y,0,Game.WIDTH);
        
        collision();


}

@Override
public void render(Graphics g){
    if(id == ID.Clone) g.setColor(color);
    g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
    g.setColor(Color.black);
    g.drawOval((int)x, (int)y, (int)diameter, (int)diameter);
    g.setFont(stringFont);
    g.drawString(String.valueOf((int)mass), (int)(x+diameter/2-10), (int)(y+diameter/2+7));
}
    
}
