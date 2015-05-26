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
    
    protected int mouseX, mouseY, mass, playerID;
    protected float x, y, velX, velY, diameter, deltaX, deltaY,
            tempX, tempY, boardSize, direction;
    
    protected ID id;
    
    protected boolean alpha;
    
    
    //Chooses a random color
    Random random = new Random();
    final float hue = random.nextFloat();
    final float saturation = 1.0f;
    final float luminance = 1.0f;
    Color color = Color.getHSBColor(hue, saturation, luminance);
    

    
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
        this.velY = velY;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
//    public float getDeltaX(){
//        return deltaX;
//    }
//    public float getDeltaY(){
//        return deltaY;
//    }
//    public void setDeltaSize(float diameter){
//        this.deltaSize = diameter;
//    }
//    public float getDeltaSize(){
//        return deltaSize;
//    }
    public void setDeltaX(float x){
        this.deltaX = x;
    }
    public void setDeltaY(float y){
        this.deltaY = y;
    }
    
    public void setMass(int mass){
        this.mass = mass;
    }
    public int getMass(){
        return mass;
    }
    
    public void setMouseX(int x){
        this.mouseX=x;
    }
    
    public void setMouseY(int y){
        this.mouseY=y;
    }
    
    //clamp the diameter of the player
    public float diameterClamp(float diameter){
       if (diameter <=300)return diameter;
       else return 300;
    }
    
}