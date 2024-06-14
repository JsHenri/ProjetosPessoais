import java.awt.*;
import java.util.Random;

public class Ball {

    public double x,y;
    public int width,height;
    public double dx,dy;
    public double speed = 1.2;

    public Ball(int x,int y){
        this.x = x;
        this.y = y;
        this.width = 3;
        this.height = 3;

        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
    }

    public void tick(){
        x+=dx*speed;
        y+=dy*speed;

        if(x+(dx*speed) + width >= Game.WIDTH){
            dx*=-1;
        } else if (x+(dx*speed) < 0) {
            dx*=-1;
        }

        if(y >= Game.HEIGHT){
            System.out.println("Ponto do inimigo!");
        } else if (y < 0) {
            System.out.println("Ponto nosso! YAYY!");
        }

        Rectangle bounds = new Rectangle((int)(x+(dy*speed)),(int)(y+(dy*speed)),width,height);
        Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
        Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
        if(bounds.intersects(boundsPlayer)){
            dy*=-1;
        }else if (bounds.intersects(boundsEnemy)){
            dy*=-1;
        }


    }
    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect((int)x,(int)y,width,height);
    }
}
