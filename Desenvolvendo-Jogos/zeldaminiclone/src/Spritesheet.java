import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Spritesheet {

    public static BufferedImage spritesheet;

    public static BufferedImage [] player_front;
    public static BufferedImage tileWall;
    public static BufferedImage [] enemy_front;

    public Spritesheet(){
        try {
            spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResource("/aula05-spritesheet.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player_front = new BufferedImage[2];
        enemy_front = new BufferedImage[2];
        player_front[0] = Spritesheet.getSprite(0,11,16,16);
        player_front[1] = Spritesheet.getSprite(16,11,16,16);
        enemy_front[0] = Spritesheet.getSprite(176,205,16,16);
        enemy_front[1] = Spritesheet.getSprite(193,205,16,16);
        tileWall = Spritesheet.getSprite(280,221,16,16);
    }

    public static BufferedImage getSprite(int x,int y, int width, int height){
        return spritesheet.getSubimage(x,y,width,height);
    }
}
