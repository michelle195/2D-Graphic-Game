public class RatHoriz extends Villain
{
    private boolean moveRight;
    public RatHoriz(int r, int c)
    {
        //should be rat.gif
        super("evilRat.gif", 1, r, c);
        moveRight = true;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        //int key =
        if(moveRight)
        {
            if(curCol != grid[0].length-1)//if the curCol is not the last col and the next location is not a wall
            {
                if(grid[curRow][curCol+1] == null)//if there is nothing to the right
                {
                        grid[curRow][curCol] = null;
                        moveRight();
                        curCol = getCol();
                        grid[curRow][curCol] = getVillainName();

                }
                else//there is something to the right
                {
                    moveRight = false;
                    if (grid[curRow][curCol + 1].indexOf("Fit.gif") != -1 && !(grid[curRow][curCol + 1].equals("ratCityKillFit.gif")))
                    {
                        //if(key == 40)
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
                if(grid[curRow][curCol-1] == null)
                {
                    grid[curRow][curCol] = null;
                    moveLeft();
                    curCol = getCol();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    moveRight = true;
                    if(grid[curRow][curCol-1].indexOf("Fit.gif") != -1 && !(grid[curRow][curCol - 1].equals("ratCityKillFit.gif")))
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
