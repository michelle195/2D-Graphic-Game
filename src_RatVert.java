public class RatVert extends Villain
{
    private boolean moveUp;
    public RatVert(int r, int c)
    {
        //should be rat.gif
        super("evilRat.gif", 1, r, c);
        moveUp = true;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        if(moveUp)
        {
            if(getRow() != 1)//and the next location is not a wall
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
                        moveUp = false;
                        if(grid[curRow-1][curCol].indexOf("Fit.gif") != -1 && !(grid[curRow-1][curCol].equals("ratCityKillFit.gif")))
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
                if(grid[curRow+1][curCol] == null)
                {

                    grid[curRow][curCol] = null;
                    moveDown();
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                        moveUp = true;
                        if(grid[curRow+1][curCol].indexOf("Fit.gif") != -1 && !(grid[curRow+1][curCol].equals("ratCityKillFit.gif")))
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
