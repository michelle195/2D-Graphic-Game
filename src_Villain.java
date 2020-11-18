public abstract class Villain
{
    private int row, col;
    private String villainName;
    private int lives;
    private boolean isAlive;
    
    public Villain(String n, int l, int r, int c)
    {
        villainName = n;
        lives = l;
        row = r;
        col = c;
        isAlive = true;
    }
    public boolean getIsAlive()
    {
        return isAlive;
    }
    public void die()
    {
        isAlive = false;
    }
    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public void setRow(int r)
    {
        row = r;
    }
    public void setCol(int c)
    {
        col = c;
    }
    public void moveUp()
    {
        row--;
    }
    public void moveDown()
    {
        row++;
    }
    public String getVillainName()
    {
        return villainName;
    }
    public void setVillainName(String str){villainName = str;}
    public void moveLeft()
    {
        col--;
    }
    public void moveRight()
    {
        col++;
    }
    public abstract void movement(String[][] arr);
    

    
    
}