public class PikaCatHoriz extends Villain
{
    private boolean moveRight;
    public PikaCatHoriz(int r, int c)
    {
        //should be rat.gif
        super("evilPikaCat.gif", 1, r, c);
        moveRight = true;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        if(moveRight)
        {
            if(curCol != grid[0].length-1)
            {
                if(grid[curRow][curCol+1] == null || grid[curRow][curCol+1].equals("brownTile.gif"))
                {
                    grid[curRow][curCol] = null;
                    moveRight();
                    curCol = getCol();
                    grid[curRow][curCol] = getVillainName();

                }
                else
                {
                    moveRight = false;
                    if (grid[curRow][curCol + 1].indexOf("Fit") != -1)
                    {
                        moveRight = false;
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
                moveRight = false;
        }
        else
        {
            if(getCol() > 0)
            {
                if(grid[curRow][curCol-1] == null || grid[curRow][curCol-1].equals("brownTile.gif"))
                {
                    grid[curRow][curCol] = null;
                    moveLeft();
                    curCol = getCol();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    moveRight = true;
                    if(grid[curRow][curCol-1].indexOf("Fit") != -1)
                    {
                        //this.die();//you will do this with the enemy projectile
                        moveRight = true;
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
                moveRight = true;
        }
    }
}
