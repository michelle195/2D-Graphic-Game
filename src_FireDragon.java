import java.util.ArrayList;
public class FireDragon extends Villain
{
    private String dir;
    private ArrayList<Villain> vs;

    public FireDragon(int r, int c, String d, ArrayList<Villain> x)
    {
        super("evilFireDragon.gif", 1, r,c);
        dir = d;
        vs = x;
    }

    public void movement(String[][] grid)
    {
        int r  = (int)(Math.random()*10 +1);
        if(r < 4)
        {
            if(dir.equals("up"))
            {
                FireProjectile p = new FireProjectile(this.getRow()-1,this.getCol(), dir);
                vs.add(0,p);
            }
            else if(dir.equals("down"))
            {
                FireProjectile p = new FireProjectile(this.getRow()+1,this.getCol(), dir);
                vs.add(0,p);
            }
        }
    }

}
