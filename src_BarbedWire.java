public class BarbedWire extends Villain
{
    String dir;

    public BarbedWire(int r, int c)
    {
        super("evilBarbedWireHoriz.gif", 1, r, c);
        dir = "right";
    }
    public BarbedWire(int r, int c, String d)
    {
        super("evilBarbedWireVert.gif",1, r, c);
        dir = d;
    }
    public void movement(String[][] grid)
    {

    }
}
