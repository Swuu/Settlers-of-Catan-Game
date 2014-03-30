import objectdraw.*;
import java.awt.*;

public class GameSettlement
{
    public GameSettlement(Coord aCoord, Player aPlayer, DrawingCanvas canvas)
    {
        new SettlementShape(aCoord.location(), aPlayer.getNum(), canvas);
        aPlayer.addPoints();
    }
}