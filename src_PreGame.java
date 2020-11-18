
import java.util.ArrayList;

//michelle
public class PreGame
{
  private Grid grid;  //display
  private int userRow, userCol;   //row user is in
  private int msElapsed;   //milliseconds since start
  private int timesGet;   //# correct collisions
  private int timesAvoid;   //# incorrect collisions
  private String[][] gameGrid;
  private ArrayList<Villain> badGuys;
  
  public PreGame()
  {
    grid = new Grid(5,5);
    userRow = 2;
    userCol =1;
    msElapsed = 0;
    timesGet = 0;                         
    timesAvoid = 0;
    updateTitle();
    //make this a method setupLevl1
    grid.setImage(new Location(userRow, 0), "user.gif");
    String agif = "avoid.gif";
    String ugif = "user.gif";
    badGuys = new ArrayList<Villain>();
    badGuys.add(new RatHoriz(2,3));
    badGuys.add(new RatHoriz(3,4));


      /*String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hart,hart,hart,hart,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,ugif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,null,null,null,null,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,null,null,null,null,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,agif,agif,agif,agif,agif,agif,agif,agif},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,null},
              {agif,agif,agif,agif,agif,agif,agif,agif,null,null,null,null,null,null,null,null,null,null,null,nxtL}};*/
    
    for(Villain v: badGuys)
    {
        gameGrid[v.getRow()][v.getCol()]= v.getVillainName();
    }
    
    
    this.gameGrid = gameGrid;
    //---------
  }
  
  public void play()
  {
    while (!isGameOver())
    {
        drawStringGrid();
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0)
      {
          for(Villain v: badGuys)
          {
              v.movement(gameGrid);
          }
        //scrollLeft();
        //populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  public void drawStringGrid()
  {
      for(int r = 0; r < gameGrid.length; r++)   
      {
          for(int c = 0; c < gameGrid[r].length; c++)
          {
              grid.setImage(new Location(r,c), gameGrid[r][c]);
          }
      }
  }
  public void handleKeyPress()
  {
      int key = grid.checkLastKeyPressed();
      System.out.println(key);
      if(key == 38 && userRow > 0)
      {
          //grid.setImage(new Location(userRow, 0), null);
          gameGrid[userRow][userCol]=null;
          userRow-=1;
          //grid.setImage(new Location(userRow, 0), "user.gif");
          gameGrid[userRow][userCol]="user.gif";
      }
      if(key == 40 && userRow < grid.getNumRows()-1)
      {
          //grid.setImage(new Location(userRow, 0), null);
          gameGrid[userRow][userCol]=null;
          userRow+=1;
          gameGrid[userRow][userCol]="user.gif";
          //grid.setImage(new Location(userRow, 0), "user.gif");
      }
  }
  
    public void populateRightEdge()
    {  
        for(int i = 0; i < grid.getNumRows(); i++)
        {   
            double r = Math.random();
            if(r <= 0.05)
                grid.setImage(new Location(i, grid.getNumCols()-1), "avoid.gif");
            else if(r <= 0.12)
                grid.setImage(new Location(i, grid.getNumCols()-1), "get.gif");
            else
                grid.setImage(new Location(i, grid.getNumCols()-1), null);        
        }
            
      
    }
    //scrollLeftHelper
    private void scrollLeftHelper(int currCol)
    {
        String currImage;
        for(int i = 0; i < grid.getNumRows(); i++)
        {
            currImage = grid.getImage(new Location(i, currCol));
            if(currImage != null)
            {
                if(currImage.equals("avoid.gif") || currImage.equals("get.gif"))
                {
                    grid.setImage(new Location(i, currCol-1), currImage);
                    grid.setImage(new Location(i, currCol), null);
                }
                                                                                                                                                                                                                                                                         
            }
            
        }
    }
  
    public void scrollLeft()
    {
        for(int i = 0; i < grid.getNumCols(); i++)
        {
            if(i != 0)
            {
                scrollLeftHelper(i);
            }
            else
            {
                for(int j = 0; j < grid.getNumRows(); j++)
                {
                    String currImage = grid.getImage(new Location(j, i));
                    if(currImage != null)
                    {
                        if(currImage.equals("avoid.gif") || currImage.equals("get.gif"))
                        {
                        
                            grid.setImage(new Location(j, i), null);
                        }   
                                                                                                                                                                                                                                                                         
                    }
            
                }
            }
        }
    }    
  
    public void handleCollision(Location loc)
    {
        for(int i = 0; i < grid.getNumRows(); i++)
        {
            if(grid.getImage(new Location(i, 0)).equals("user.gif"))
            {
                if(grid.getImage(new Location(i, 0)).equals("get.gif"))
                {
                    timesGet++;
                    grid.setImage(new Location(i,0), null);
                    grid.setImage(new Location(i,0), "user.gif");
                }
                else if(grid.getImage(new Location(i, 0)).equals("avoid.gif"))
                {
                    timesAvoid++;
                    grid.setImage(new Location(i,0), null);
                    grid.setImage(new Location(i,0), "user.gif");
                }
            }
        }
    }
  
  public int getScore()
  {
    return timesGet;
  }
  
  public void updateTitle()
  {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver()
  {
    if(msElapsed == 3000000)
        return true;
    else
        return false;
  }
  
  public static void test()
  {
    PreGame game = new PreGame();
    game.play();
  }
  
  public static void main(String[] args)
  {
    test();
  }
}