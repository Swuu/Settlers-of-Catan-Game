import objectdraw.*;
import java.awt.*;

public class GameSettlement
{
    boolean isSettlement;
    SettlementShape aSettlement;
    
    public GameSettlement(Coord aCoord, Player aPlayer, DrawingCanvas canvas)
    {
        aSettlement = new SettlementShape(aCoord.location(), aPlayer.getNum(), canvas);
        aPlayer.addPoints();
        isSettlement = true;
    }
    
    public void makeCity()
    {
        isSettlement = false;
        aSettlement.makeCity();
    }
}