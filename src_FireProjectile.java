public class FireProjectile extends Villain
{
    private String dir;

    public FireProjectile(int r, int c, String d)
    {
        super("evilFireball.gif", 1, r, c);
        dir = d;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        if(dir.equals("up"))
        {
            //setCol(getCol()+1);
            if(curRow != 1)
            {
                if(grid[curRow-1][curCol] == null)
                {
                    grid[curRow][curCol] = null;
                    moveUp();
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    this.die();
                    if (grid[curRow-1][curCol].indexOf("Fit") != -1 && !(grid[curRow-1][curCol].equals("hellWaterGunFit.gif")))
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
        else if(dir.equals("down"))
        {
            //setCol(getCol()-1);
            if(curRow != grid.length-1)
            {
                if(grid[curRow+1][curCol] == null)
                {
                    grid[curRow][curCol] = null;
                    moveDown();
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    this.die();
                    if (grid[curRow+1][curCol].indexOf("Fit") != -1 && !(grid[curRow+1][curCol].equals("hellWaterGunFit.gif")))
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
