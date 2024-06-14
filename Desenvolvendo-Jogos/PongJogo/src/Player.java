import java.awt.*;

public class Player {

    public int x,y;
    public boolean left,right;
    public int width,height;
    public Player(int x,int y){
        this.x = x;
        this.y = y;
        this.width=40;
        this.height=10;
    }
    public void tick(){
        if(right){
            x++;
        } else if (left) {
            x--;
        }
        if(x+width > Game.WIDTH){
            x= Game.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    }
    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height);
    }
}
