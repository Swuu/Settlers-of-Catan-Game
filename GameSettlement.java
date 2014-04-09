import objectdraw.*;
import java.awt.*;

public class GameSettlement
{
    boolean isSettlement;
    private Player aPlayer;
    SettlementShape aSettlement;
    
    public GameSettlement(Coord aCoord, Player player, DrawingCanvas canvas)
    {
        aPlayer = player;
        aSettlement = new SettlementShape(aCoord.location(), aPlayer.getNum(), canvas);
        aPlayer.addPoints();
        isSettlement = true;
    }
    
    public void makeCity()
    {
        isSettlement = false;
        aSettlement.makeCity();
        aPlayer.addPoints();
    }
    
    public Player getPlayer()
    {
        return aPlayer;
    }
}