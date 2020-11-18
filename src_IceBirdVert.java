import java.util.ArrayList;

public class IceBirdVert extends Villain
{
    private boolean moveUp;
    private int currentStep, maxStep;
    private String dir;
    private ArrayList<Villain> vs;
    public IceBirdVert(int r, int c, String d, ArrayList<Villain> x)
    {
        super("evilIceBird.gif", 1, r, c);
        dir = d;
        vs = x;
        moveUp = true;
        currentStep=0;
        maxStep = 2;
    }
    public IceBirdVert(int r, int c, int maxS)
    {
        super("evilIceBird.gif", 1, r, c);
        moveUp = true;
        currentStep=0;
        maxStep = maxS;
    }
    public void movement(String[][] grid)
    {
        int curRow = getRow();
        int curCol = getCol();

        if(moveUp && currentStep < maxStep)
        {
            if(getRow() != 1)//and the next location is not a wall
            {
                if(grid[curRow-1][curCol] == null)
                {
                    grid[curRow][curCol] = null;
                    moveUp();
                    currentStep++;
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    currentStep = 0;
                    moveUp = false;
                    if(grid[curRow-1][curCol].indexOf("Fit.gif") != -1)
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
            {
                currentStep = 0;
                moveUp = false;
            }

        }
        else if(!moveUp && currentStep < maxStep)
        {
            if(getRow() != grid.length-1)
            {
                if(grid[curRow+1][curCol] == null)
                {
                    grid[curRow][curCol] = null;
                    moveDown();
                    currentStep++;
                    curRow = getRow();
                    grid[curRow][curCol] = getVillainName();
                }
                else
                {
                    currentStep = 0;
                    moveUp = true;
                    if(grid[curRow+1][curCol].indexOf("Fit.gif") != -1)
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
            {
                currentStep = 0;
                moveUp = true;
            }
        }
        else
        {
            //i have finished doing all my steps and I never walked into a wall
            currentStep=0;
            moveUp = !moveUp;
            //i would put a println here  and see that it turns aroundd when it should
        }

        int r  = (int)(Math.random()*10 +1);
        System.out.println(r);
        if(r < 3)
        {
            IceProjectile p = new IceProjectile(this.getRow(),this.getCol()-1, dir);
            vs.add(0,p);
        }
    }

}
