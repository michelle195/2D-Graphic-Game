public class WindProjectile extends Villain
{
    private String dir;
    private String bandaid;

    public WindProjectile(int r, int c, String d)
    {
        super("windHoriz.gif", 1, r, c);
        dir = d;
        bandaid = "horiz";
    }
    public WindProjectile(int r, int c, String d, String b)
    {
        super("windVert.gif", 1, r, c);
        dir = d;
        bandaid = b;
    }
    public void movement(String[][] grid)
    {
        if(bandaid.equals("horiz"))
        {
            int curRow = getRow();
            int curCol = getCol();
            if(dir.equals("right"))
            {
                if(curCol != grid[0].length-3)
                {
                    if(grid[curRow][curCol+1] == null)
                    {
                        grid[curRow][curCol] = null;
                        moveRight();
                        curCol = getCol();
                        grid[curRow][curCol] = getVillainName();
                    }
                    else if(grid[curRow][curCol+1].indexOf("RegFit") != -1)
                    {
                        grid[Game.userRow][Game.userCol] = null;
                        Game.userCol++;
                        grid[Game.userRow][Game.userCol] = Game.ugif;

                        grid[curRow][curCol] = null;
                        moveRight();
                        curCol = getCol();
                        grid[curRow][curCol] = getVillainName();
                    }
                }
                else
                {
                    grid[curRow][curCol] = null;
                    setCol(curCol-(curCol-1));
                }
            }
            else if(dir.equals("left"))
            {
                if(curCol != 0)
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
                        if(grid[curRow][curCol-1].indexOf("RegFit") != -1)
                        {
                            grid[Game.userRow][Game.userCol] = null;
                            Game.userCol--;
                            grid[Game.userRow][Game.userCol] = Game.ugif;

                            grid[curRow][curCol] = null;
                            moveLeft();
                            curCol = getCol();
                            grid[curRow][curCol] = getVillainName();
                        }
                    }
                }
                else
                {
                    grid[curRow][curCol] = null;
                    setCol(curCol+(curCol-1));
                }
            }
        }
        else if(bandaid.equals("vert"))
        {
            int curRow = getRow();
            int curCol = getCol();
            if(dir.equals("down"))
            {
                if(curRow < 7)
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
                        if(grid[curRow+1][curCol].indexOf("RegFit") != -1)
                        {
                            grid[Game.userRow][Game.userCol] = null;
                            Game.userRow++;
                            grid[Game.userRow][Game.userCol] = Game.ugif;

                            grid[curRow][curCol] = null;
                            moveDown();
                            curRow = getRow();
                            grid[curRow][curCol] = getVillainName();
                        }
                    }
                }
                else
                {
                    grid[curRow][curCol] = null;
                    setRow(Game.startRow);
                    setCol(Game.startCol);
                }
            }
        }
    }
}
