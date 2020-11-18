public class IceProjectile extends Villain
{
    private String dir;

    public IceProjectile(int r, int c, String d)
    {
        super("evilIceShard.gif", 1, r, c);
        dir = d;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        if(dir.equals("right"))
        {
            //setCol(getCol()+1);
            if(curCol != grid[0].length-1)
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
                    this.die();
                    if (grid[curRow][curCol + 1].indexOf("Fit") != -1 && !(grid[curRow][curCol + 1].equals("aviatorFireGunFit.gif")))
                    {
                        this.die();
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
                this.die();
        }
        else if(dir.equals("left"))
        {
            //setCol(getCol()-1);
            if(curCol > 0)
            {
                if(grid[curRow][curCol-1] == null)
                {
                    grid[curRow][curCol] = null;
                    moveLeft();
                    curCol = getCol();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    this.die();
                    if (grid[curRow][curCol - 1].indexOf("Fit") != -1 && !(grid[curRow][curCol - 1].equals("aviatorFireGunFit.gif")))
                    {
                        this.die();
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
                this.die();
        }
    }

}
