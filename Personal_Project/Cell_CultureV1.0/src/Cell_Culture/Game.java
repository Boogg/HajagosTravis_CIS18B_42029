/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cell_Culture;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author Travis
 */
public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 2322472837614L;
    
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static int WIDTH = (int)screenSize.getWidth(), HEIGHT = (int)screenSize.getHeight();
    
    private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    private HUD hud;
    
    public Game(){              
        handler = new Handler();
        hud = new HUD();
        
        Random rand = new Random();
        
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH, HEIGHT, "Dodge Ball", this);
        requestFocus();
        
        for(int i=0;i<300;i++){           
            handler.addObject(new Nutrient(rand.nextInt(WIDTH-32), 
                              rand.nextInt(HEIGHT-32), ID.Nutrient));       
        }
        
        handler.addObject(new BasicEnemy(WIDTH/2 +32, HEIGHT/2+32, ID.BasicEnemy));        
        handler.addObject(new Player(WIDTH/2 -32, HEIGHT/2-32, ID.Player, handler));
 
       
    }
     
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
     public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     
    public void run(){
    
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }     
        }
        stop();
    }
    
    private void tick(){
        handler.tick();
        hud.tick();
        
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        hud.render(g);
        
        g.dispose();
        bs.show();
        
    }
    
    public static float clamp(float var, float min, float max){
        if(var >= max)return max;
        else if(var <= min) return min;
        else return var;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new Game();
        
    }
    
}