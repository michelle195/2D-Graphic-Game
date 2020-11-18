public class BubbleProjectile extends Villain
{
    private String dir;

    public BubbleProjectile(int r, int c, String d)
    {
        super("bubbles.gif", 1, r, c);
        dir = d;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();
        if(dir.equals("up"))
        {
            if(curRow != 2)
            {
                if(grid[curRow-1][curCol] == null)
                {
                    grid[curRow][curCol] = null;
                    moveUp();
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else if(grid[curRow-1][curCol].equals("scubaDiveForceFieldFit.gif"))
                {
                    grid[curRow][curCol] = null;
                    setRow(8);
                    setCol(4);
                }
                else
                {
                    if (grid[curRow-1][curCol].equals("scubaDiveRegFit.gif"))
                    {
                        grid[Game.userRow][Game.userCol] = null;
                        Game.userRow--;
                        grid[Game.userRow][Game.userCol] = "scubaDiveRegFit.gif";

                        grid[curRow][curCol] = null;
                        moveUp();
                        curRow = getRow();
                        grid[curRow][curCol] = getVillainName();

                    }
                }
            }
            else
            {
                grid[curRow][curCol] = null;
                setRow(8);
                setCol(4);
            }
        }
    }
}
