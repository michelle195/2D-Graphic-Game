public class Spikes extends Villain
{
    String dir;

    public Spikes(int r, int c)
    {
        super("evilHorizSpikes.gif", 1, r, c);
        dir = "right";
    }
    public Spikes(int r, int c, String d)
    {
        super("evilVertSpikes.gif",1, r, c);
        dir = d;
    }
    public void movement(String[][] grid)
    {

    }
}
