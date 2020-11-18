import java.util.ArrayList;

public class IceBirdHoriz extends Villain
{
    private boolean moveRight;
    private String dir;
    private ArrayList<Villain> vs;
    public IceBirdHoriz(int r, int c, String d, ArrayList<Villain> x)
    {
        super("evilIceBird.gif", 1, r, c);
        dir = d;
        vs = x;
        moveRight = true;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();

        if(moveRight)
        {
            int r  = (int)(Math.random()*10 +1);
            //System.out.println(r);
            if(r < 3)
            {
                IceProjectile p = new IceProjectile(this.getRow(),this.getCol()+1, dir);
                vs.add(0,p);
            }
            if(getCol() != grid[0].length-1)//and the next location is not a wall
            {
                if(grid[curRow][curCol+1] == null)
                {
                    grid[curRow][curCol] = null;
                    moveRight();
                    curCol = getCol();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    moveRight = false;
                    if(grid[curRow-1][curCol].indexOf("RegFit.gif") != -1)
                    {
                        Game.numLives--;
                        for(int i = grid[0].length-1; i > 0; i--)
                        {
                            if(grid[0][i].equals("heart.gif"))
                            {
                                grid[0][i] = "healthBar.gif";
                                i = 0;
                            }
                        }
                    }
                }
            }
            else
            {
                moveRight = false;
            }

        }
        else
        {
            int r  = (int)(Math.random()*10 +1);
            //System.out.println(r);
            if(r < 3)
            {
                IceProjectile p = new IceProjectile(this.getRow(),this.getCol()-1, dir);
                vs.add(0,p);
            }
            if(getCol() != 0)
            {
                if(grid[curRow+1][curCol] == null)
                {
                    grid[curRow][curCol] = null;
                    moveLeft();
                    curCol = getCol();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    if(grid[curRow+1][curCol].indexOf("RegFit.gif") != -1)
                    {
                        Game.numLives--;
                        for(int i = grid[0].length-1; i > 0; i--)
                        {
                            if(grid[0][i].equals("heart.gif"))
                            {
                                grid[0][i] = "healthBar.gif";
                                i = 0;
                            }
                        }
                    }
                }

            }
            else
            {
                moveRight = true;
            }
        }
    }
}
