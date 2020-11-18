import java.util.ArrayList;
public class PufferFish extends Villain
{
    private boolean moveRight;
    private String dir;
    private ArrayList<Villain> vs;
    public PufferFish(int r, int c, String d, ArrayList<Villain> x)
    {
        super("evilPufferFish.gif", 1, r, c);
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
            if(getCol() != grid[0].length-1)
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
            if(getCol() != 1)
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
                    if(grid[curRow][curCol - 1].indexOf("Fit") != -1)
                    {
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