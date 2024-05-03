import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGTH = 480;
    public static int SCALE = 3;
    public World world;
    public static Player player;
    public List<Enemy> enemies = new ArrayList<>();
    public Game(){
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH,HEIGTH));
        new Spritesheet();
        world = new World();
        player = new Player(32,32);
        enemies.add(new Enemy(32,32));
        enemies.add(new Enemy(32,64));
    }

    public void tick(){
        player.tick();
        for(int i = 0;i < enemies.size();i++) {
            enemies.get(i).tick();
        }
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0,135,13));
        g.fillRect(0, 0, WIDTH*SCALE, HEIGTH*SCALE);

        player.render(g);

        for(int i = 0;i < enemies.size();i++) {
            enemies.get(i).render(g);
        }

        world.render(g);

        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run(){
        while (true){
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_Z){
            player.shoot = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }/*
        if(e.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = false;
        }*/
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent arg0){

    }

}
