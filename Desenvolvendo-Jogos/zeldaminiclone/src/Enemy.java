import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle{

    public int spd=2;
    public int right = 1,up = 0,down = 0,left = 0;
    public int curAnimation = 0;
    public int curFrames = 0, targetFrames = 15;
    public static List<Bullet> bullets = new ArrayList<Bullet>();
    public boolean shoot = false;
    public int dir = 1;

    public Enemy(int x, int y){
        super(x,y,32,32);
    }

    public void perseguirPlayer(){
        Player p = Game.player;
        if(x <p.x && World.isFree(x+spd,y)){
            if(new Random().nextInt(100) < 70)
                x+=spd;
        }else if(x > p.x && World.isFree(x-spd,y)) {
            if(new Random().nextInt(100) < 70)
                x-=spd;
        }

        if(y <p.y && World.isFree(x,y+spd)){
            if(new Random().nextInt(100) < 70)
                y+=spd;
        }else if(y > p.y && World.isFree(x,y-spd)) {
            if(new Random().nextInt(100) < 70)
                y-=spd;
        }
    }
    //176,205
    //193,205
    public void tick(){
        boolean moved = true;
        perseguirPlayer();

        if(right == 1 && World.isFree(x + 1,y)){
            x++;
        }

        if(moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation == Spritesheet.enemy_front.length) {
                    curAnimation = 0;
                }
            }
        }
        if(shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,dir));
        }
        for (int i=0;i < bullets.size(); i++){
            bullets.get(i).tick();
        }
    }
    public void render(Graphics g){
        //g.setColor(Color.blue);
        //g.fillRect(x,y,width,height);
        g.drawImage(Spritesheet.enemy_front[curAnimation],x,y,32,32,null);

        for (int i=0;i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}