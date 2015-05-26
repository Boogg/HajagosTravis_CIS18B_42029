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
public abstract class GameObject {
    
    protected int mass;
    protected float x, y, velX, velY, diameter;
    protected ID id;
    protected boolean leftPressed=false,
                      rightPressed=false,
                      upPressed=false, 
                      downPressed=false;
    
    
    //Chooses a random color
    Random random = new Random();
    final float hue = random.nextFloat();
    final float saturation = 1.0f;
    final float luminance = 1.0f;
    protected Color color = Color.getHSBColor(hue, saturation, luminance);
    

    
    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id= id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    //setter getters
    public void setX(float x){
        this.x = x;
    }   
    public void setY(float y){
        this.y = y;
    }    
    public float getX(){
        return x;
    }   
    public float getY(){
        return y;
    }   
    public void setDiameter(float diameter){
        this.diameter = diameter;
    }
    public float getDiameter(){
        return diameter;
    }
    public void setId(ID id){
        this.id = id;
    }    
    public ID getId(){
        return id;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY= velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    
    public void setMass(int mass){
        this.mass=mass;
    }
    public int getMass(){
        return mass;
    }
    
    public void setUpPressed(boolean pressed){
        this.upPressed = pressed;
    }
    
    public void setDownPressed(boolean pressed){
        this.downPressed = pressed;
    }
        
    public void setLeftPressed(boolean pressed){
        this.leftPressed = pressed;
    }
            
    public void setRightPressed(boolean pressed){
        this.rightPressed = pressed;
    }
    
}