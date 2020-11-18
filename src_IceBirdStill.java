import java.util.ArrayList;
public class IceBirdStill extends Villain
{
    private String dir;
    private ArrayList<Villain> vs;

    public IceBirdStill(int r, int c, String d, ArrayList<Villain> x)
    {
        super("evilIceBird.gif", 1, r,c);
        dir = d;
        vs = x;
    }

    public void movement(String[][] grid)
    {
        int r  = (int)(Math.random()*10 +1);
        if(r < 4)
        {
            if(dir.equals("left"))
            {
                IceProjectile p = new IceProjectile(this.getRow(),this.getCol()-1, dir);
                vs.add(0,p);
            }
            else if(dir.equals("right"))
            {
                IceProjectile p = new IceProjectile(this.getRow(),this.getCol()+1, dir);
                vs.add(0,p);
            }
        }
    }

}
