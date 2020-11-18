public class PikaCatVert extends Villain
{
    private boolean moveUp;
    public PikaCatVert(int r, int c)
    {
        //should be rat.gif
        super("evilPikaCat.gif", 1, r, c);
        moveUp = true;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        if(moveUp)
        {
            if(getRow() != 1)
            {
                if(grid[curRow-1][curCol] == null || grid[curRow-1][curCol].equals("brownTile.gif"))
                {
                    grid[curRow][curCol] = null;
                    moveUp();
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    moveUp = false;
                    if(grid[curRow-1][curCol].indexOf("Fit") != -1)
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
                moveUp = false;

        }
        else
        {
            if(getRow() != grid.length-1)
            {
                if(grid[curRow+1][curCol] == null || grid[curRow+1][curCol].equals("brownTile.gif"))
                {

                    grid[curRow][curCol] = null;
                    moveDown();
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    moveUp = true;
                    if(grid[curRow+1][curCol].indexOf("Fit") != -1)
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
                moveUp = true;
        }

    }

}
