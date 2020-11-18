import java.applet.*;
import java.util.ArrayList;
public class Game
{
    private Grid grid;  //display
    static int userRow, userCol;   //row user is in
    private int msElapsed;   //milliseconds since start
    private int timesGet;   //# correct collisions
    private int timesAvoid;   //# incorrect collisions
    private String[][] gameGrid;
    private ArrayList<Villain> badGuys;
    static int numLives;
    private int level;
    static String ugif;
    private AudioClip introSong, gameSong, finalSong, endSong;
    static int startRow, startCol;

    private int moves;
    private boolean on;


    //instance variables to help me with images
    private String nxtL;
    private String hBar;
    private String hart;

    public Game()
    {
        moves = 0;
        on = false;
        startRow = 0;
        startCol = 0;
        grid = new Grid(11,10);
        msElapsed = 0;
        timesGet = 0;                         
        timesAvoid = 0;
        numLives = 6;
        level = -23;
        nxtL = "door.gif";
        hBar = "healthBar.gif";
        hart = "heart.gif";
        updateTitle();
        cover();

    }
    public void play()
    {
        while (!isGameOver())
        {
            //System.out.println(badGuys);
            displayhearts();
            System.out.println("num lives = " + numLives);
            if(level > 0 && level < 12)
                drawStringGrid();
            grid.pause(100);
            handleKeyPress();
            if (msElapsed % 300 == 0 && level > 0)
            {
                handleVillians();
            }
            if(numLives == 0)
            {
                gameOver();
            }
            if(on)
            {
                gameGrid[userRow][userCol] = null;
                setUserFF();
                if((level == 3 || level == 4) && moves > 4)
                {
                    setUserFit();
                    on = false;
                    moves = 0;
                }
                else if((level > 4 && level < 11) && moves > 0)
                {
                    setUserFit();
                    on = false;
                    moves = 0;
                }
            }
            updateTitle();
            msElapsed += 100;
        }
    }
    public void handleVillians()
    {
        if(level == 11 || numLives == 0)
        {
            for(int i = 0; i < badGuys.size(); i++)
            {
                Villain curr = badGuys.remove(i);
                gameGrid[curr.getRow()][curr.getCol()]= null;
                i--;
            }
        }
        for(int i = 0; i < badGuys.size(); i++)
        {
            if(badGuys.get(i).getIsAlive() == false)
            {

                Villain curr = badGuys.remove(i);
                gameGrid[curr.getRow()][curr.getCol()]= null;
                i--;
            }
        }
        for(int i = 0; i < badGuys.size(); i++)
        {
            badGuys.get(i).movement(gameGrid);
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
    public void setUpLevels()
    {
        if(level == -24) {instructions(); }
        if(level == -22) { choice(); }
        if(level == -21) { one(); }
        if(level == -20) { two(); }
        if(level == -19) { three(); }
        if(level == -18) { four(); }
        if(level == -17) { five(); }
        if(level == -16) { six(); }
        if(level == -15) { seven(); }
        if(level == -14) { eight(); }
        if(level == -13) { nine(); }
        if(level == -12) { ten(); }
        if(level == -11) { eleven(); }
        if(level == -10) { twelve(); }
        if(level == -9) { thirteen(); }
        if(level == -8) { fourteen(); }
        if(level == -7) { fifteen(); }
        if(level == -6) { sixteen(); }
        if(level == -5) { seventeen(); }
        if(level == -4) { eighteen(); }
        if(level == -3) { nineteen(); }
        if(level == -2) { twenty(); }
        if(level == -1) { twentyone(); }
        if(level == 0) { twentytwo(); }
        if(level == 1) { setUpRatCity(); }
        if(level == 2) { setUpRatCity2(); }
        if(level == 3) { setUpScubaDive(); }
        if(level == 4) { setUpScubaDive2(); }
        if(level == 5) { setUpAviator(); }
        if(level == 6) { setUpAviator2(); }
        if(level == 7) { setUpMudpit(); }
        if(level == 8) { setUpMudpit2(); }
        if(level == 9) { setUpHell(); }
        if(level == 10) { setUpHell2(); }
        if(level == 11) { twentythree(); };
        if(level == 12) { twentyfour(); };
        if(level == 13) { twentyfive(); };
        if(level == 14) { twentysix(); };
        if(level == 15) { twentyseven(); };
        if(level == 16) { twentyeight(); };
        if(level == 17) { twentynine(); };
        if(level == 18) { thirty(); };
        if(level == 19) { thirtyone(); };
        if(level == 20) { end1(); };
        if(level == 21) { end2(); }
        if(level == 22) { fin(); }

    }
    public void gameOver()
    {
        gameSong.stop();
        grid.setBackground("gameOver.gif");

        String[][] gameGrid = {{null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;

    }
    public void instructions() //--24
    {
        grid.setBackground("instructions.gif");

    }
    public void cover() //-23
    {
        grid.setBackground("cover.gif");

        introSong = Applet.newAudioClip(this.getClass().getResource("whistleSong.wav"));
        introSong.loop();
    }
    public void choice() //-22
    {
        grid.setBackground("choice.gif");

    }
    public void one()
    {
        grid.setBackground("one.gif");

    }
    public void two()
    {
        grid.setBackground("two.gif");

    }
    public void three()
    {
        grid.setBackground("three.gif");
    }
    public void four()
    {
        grid.setBackground("four.gif");

    }
    public void five()
    {
        grid.setBackground("five.gif");

    }
    public void six()
    {
        grid.setBackground("six.gif");
    }
    public void seven()
    {
        grid.setBackground("seven.gif");

    }
    public void eight()
    {
        grid.setBackground("eight.gif");

    }
    public void nine()
    {
        grid.setBackground("nine.gif");

    }
    public void ten()
    {
        grid.setBackground("ten.gif");

    }
    public void eleven()
    {
        grid.setBackground("eleven.gif");

    }
    public void twelve()
    {
        grid.setBackground("twelve.gif");

    }public void thirteen()
    {
        grid.setBackground("thirteen.gif");
    }
    public void fourteen()
    {
        grid.setBackground("fourteen.gif");

    }public void fifteen()
    {
        grid.setBackground("fifteen.gif");

    }public void sixteen()
    {
        grid.setBackground("sixteen.gif");

    }public void seventeen()
    {
        grid.setBackground("seventeen.gif");

    }public void eighteen()
    {
        grid.setBackground("eighteen.gif");

    }public void nineteen()
    {
        grid.setBackground("nineteen.gif");

    }public void twenty()
    {
        grid.setBackground("twenty.gif");

    }public void twentyone()
    {
        grid.setBackground("twentyone.gif");

    }public void twentytwo()
    {
        grid.setBackground("twentytwo.gif");

    }

    public void setUpRatCity()
    {
        introSong.stop();
        gameSong = Applet.newAudioClip(this.getClass().getResource("battleSong.wav"));
        gameSong.loop();
        userRow = 1;
        userCol = 9;
        grid.setBackground("ratCityBkgrnd.gif");
        String agif = "barrierBuilding.gif";
        ugif = "ratCityRegFit.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new RatHoriz(5,0));
        badGuys.add(new RatVert(8,7));


        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {null,null,null,agif,null,null,null,null,null,ugif},
                               {null,null,null,agif,null,null,null,null,null,null},
                               {null,null,null,agif,null,null,agif,agif,agif,agif},
                               {agif,agif,agif,agif,null,null,agif,null,null,null},
                               {null,null,null,null,null,null,agif,null,null,null},
                               {agif,agif,agif,agif,null,null,agif,null,null,null},
                               {null,null,null,agif,null,null,agif,agif,agif,agif},
                               {null,null,null,agif,null,null,null,null,null,null},
                               {null,null,null,agif,null,null,null,null,null,null},
                               {null,null,null,agif,null,null,null,null,null,nxtL}};
    
        for(Villain v: badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpRatCity2()
    {
        userRow = 1;
        userCol = 0;
        grid.setBackground("ratCityBkgrnd.gif");
        String agif = "barrierBuilding.gif";
        ugif = "ratCityRegFit.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new RatVert(1,2));
        badGuys.add(new RatHoriz(5,5));
        badGuys.add(new RatHoriz(7,0));
        badGuys.add(new RatVert(7,8));
        badGuys.add(new RatVert(10,6));

        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {ugif,null,null,null,null,agif,agif,null,null,null},
                               {null,null,null,null,null,agif,agif,null,null,null},
                               {null,null,null,null,null,agif,agif,null,null,null},
                               {agif,agif,null,null,null,null,null,null,agif,agif},
                               {agif,agif,null,null,agif,null,null,null,agif,agif},
                               {agif,agif,null,null,agif,null,null,null,agif,agif},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,agif,agif,agif,null,null,agif,null,null},
                               {null,null,agif,agif,agif,null,null,agif,null,null},
                               {null,null,agif,agif,agif,null,null,agif,null,nxtL}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;

    }
    public void setUpScubaDive()
    {
        userRow = 1;
        userCol = 5;
        grid.setBackground("scubaDiveBkgrnd.gif");
        String agif = "barrierCoral.gif";
        ugif = "scubaDiveRegFit.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new PufferFish(2,6,"up", badGuys));
        badGuys.add(new PufferFish(5,6,"up", badGuys));
        badGuys.add(new BubbleProjectile(8,4, "up"));
        badGuys.add(new PufferFish(9,6,"up", badGuys));

        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {null,null,null,agif,null,ugif,null,agif,null,null},
                               {null,null,null,agif,null,null,null,agif,null,null},
                               {null,null,null,agif,null,agif,agif,agif,null,null},
                               {null,null,null,agif,null,agif,agif,agif,null,null},
                               {null,null,null,agif,null,null,null,agif,null,null},
                               {null,null,null,agif,null,null,null,agif,null,null},
                               {null,null,null,agif,null,agif,agif,agif,null,null},
                               {null,null,null,agif,null,agif,agif,agif,null,null},
                               {null,null,null,agif,null,null,null,agif,null,null},
                               {null,null,null,agif,nxtL,null,null,agif,null,null}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpScubaDive2()
    {
        on = false;
        userRow = 1;
        userCol = 4;
        grid.setBackground("scubaDiveBkgrnd.gif");
        String agif = "barrierCoral.gif";
        ugif = "scubaDiveRegFit.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new PufferFish(2,7, "up", badGuys));
        badGuys.add(new PufferFish(4,1, "up", badGuys));
        badGuys.add(new PufferFish(6,7,"up", badGuys));
        badGuys.add(new PufferFish(8,1,"up", badGuys));
        badGuys.add(new PufferFish(9,7,"up", badGuys));
        badGuys.add(new BubbleProjectile(8,4, "up"));

        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {agif,agif,agif,agif,ugif,null,null,null,agif,agif},
                               {agif,agif,agif,agif,null,null,null,null,agif,agif},
                               {agif,null,null,null,null,agif,agif,agif,agif,agif},
                               {agif,null,null,null,null,agif,agif,agif,agif,agif},
                               {agif,agif,agif,agif,null,null,null,null,agif,agif},
                               {agif,agif,agif,agif,null,null,null,null,agif,agif},
                               {agif,null,null,null,null,agif,agif,agif,agif,agif},
                               {agif,null,null,null,null,agif,agif,agif,agif,agif},
                               {agif,agif,agif,agif,null,null,null,null,agif,agif},
                               {agif,agif,agif,agif,nxtL,agif,agif,agif,agif,agif}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpAviator()
    {
        userRow = 1;
        userCol = 4;
        grid.setBackground("aviatorBkgrnd.gif");
        ugif = "aviatorRegFit.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new WindBird(2,0));
        badGuys.add(new WindBird(4,0));
        badGuys.add(new WindBird(7,0));
        badGuys.add(new IceBirdStill(6,8,"left", badGuys));
        badGuys.add(new IceBirdStill(9,8, "left", badGuys));
        badGuys.add(new Spikes(2,9));
        badGuys.add(new Spikes(4,9));
        badGuys.add(new Spikes(7,9));
        badGuys.add(new WindProjectile(2,1,"right"));
        badGuys.add(new WindProjectile(4,1,"right"));
        badGuys.add(new WindProjectile(7,1,"right"));
        badGuys.add(new WindProjectile(2,5,"right"));
        badGuys.add(new WindProjectile(4,5,"right"));
        badGuys.add(new WindProjectile(7,5,"right"));

        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {null,null,null,null,ugif,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,nxtL,null,null,null,null,null}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpAviator2()
    {
        userRow = 1;
        userCol = 4;
        grid.setBackground("aviatorBkgrnd.gif");
        ugif = "aviatorRegFit.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new WindBird(2,0));
        badGuys.add(new WindBird(3,0));
        badGuys.add(new WindBird(4,0));
        badGuys.add(new WindProjectile(2,1,"right"));
        badGuys.add(new WindProjectile(3,1,"right"));
        badGuys.add(new WindProjectile(4,1,"right"));
        badGuys.add(new WindProjectile(2,5,"right"));
        badGuys.add(new WindProjectile(3,5,"right"));
        badGuys.add(new WindProjectile(4,5,"right"));
        badGuys.add(new Spikes(2,9));
        badGuys.add(new Spikes(3,9));
        badGuys.add(new Spikes(4,9));
        badGuys.add(new IceBirdVert(8,8,"left", badGuys));
        badGuys.add(new IceBirdVert(10,8,"left", badGuys));

        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {null,null,null,null,ugif,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,nxtL,null,null,null,null,null}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpMudpit()
    {
        userRow = 10;
        userCol = 9;
        grid.setBackground("mudpitBkgrnd.gif");
        String agif = "barrierDirt.gif";
        ugif = "mudpitRegFit.gif";
        String onBu = "barrierOnButton.gif";
        String hpac = "barrierHealthPack.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new PikaCatHoriz(1,0));
        badGuys.add(new PikaCatHoriz(5,0));
        badGuys.add(new PikaCatVert(5,9));
        badGuys.add(new RatVert(10,2));
        badGuys.add(new RatVert(1,6));
        badGuys.add(new RatVert(3,8));
        badGuys.add(new Spikes(10,1));


        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {null,null,null,null,null,null,null,null,null,onBu},
                               {hpac,null,null,null,null,null,null,null,null,agif},
                               {agif,agif,agif,agif,null,null,null,null,null,null},
                               {null,null,null,null,null,agif,agif,agif,agif,agif},
                               {null,null,null,null,null,null,null,null,null,null},
                               {agif,agif,null,null,agif,agif,agif,agif,agif,null},
                               {agif,agif,null,null,agif,null,null,null,agif,null},
                               {agif,agif,null,null,agif,null,null,null,agif,null},
                               {agif,agif,null,null,agif,null,null,null,agif,null},
                               {nxtL,null,null,null,agif,null,null,null,agif,ugif}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpMudpit2()
    {
        userRow = 10;
        userCol = 3;
        grid.setBackground("mudpitBkgrnd.gif");
        String agif = "barrierDirt.gif";
        ugif = "mudpitRegFit.gif";
        String onBu = "barrierOnButton.gif";
        String keyB = "barrierKey.gif";
        String hPac = "barrierHealthPack.gif";
        String hIce = "barrierIceBlockHoriz.gif";
        String vIce = "barrierIceBlockVert.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new Spikes(2,9,"vert"));
        badGuys.add(new PikaCatHoriz(1,3));
        badGuys.add(new IceBirdVert(2,8,"left", badGuys));
        badGuys.add(new PikaCatVert(3,0));
        badGuys.add(new IceBirdStill(6,2,"right",badGuys));
        badGuys.add(new PikaCatVert(8,4));
        badGuys.add(new RatVert(8,9));
        badGuys.add(new RatHoriz(10,8));
        badGuys.add(new PikaCatHoriz(10,0));
        badGuys.add(new BarbedWire(2,0));
        badGuys.add(new BarbedWire(1,1,"vert"));

        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {nxtL,null,null,null,agif,null,null,null,agif,keyB},
                               {null,null,null,null,hIce,null,null,null,null,null},
                               {null,null,null,null,agif,null,null,null,null,null},
                               {null,null,null,null,hIce,null,null,null,null,null},
                               {null,null,null,vIce,onBu,vIce,null,agif,agif,agif},
                               {null,null,null,null,null,null,null,agif,null,null},
                               {null,agif,agif,agif,agif,agif,null,agif,null,null},
                               {null,null,null,null,null,agif,null,null,null,null},
                               {agif,agif,agif,agif,null,agif,null,null,null,null},
                               {null,null,null,ugif,null,agif,null,null,null,hPac}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;

    }
    public void setUpHell()
    {
        userRow = 7;
        userCol = 0;
        grid.setBackground("hellBkgrnd.gif");
        ugif = "hellRegFit.gif";
        String agif = "evilLava.gif";
        String coal = "barrierCoal.gif";
        String onBu = "barrierOnButton.gif";
        String onSw = "barrierOnSwitchDown.gif";
        String hPac = "barrierHealthPack.gif";
        //startRow = 5;
        //startCol = 4;
        badGuys = new ArrayList<Villain>();
        badGuys.add(new RatHoriz(2,2));
        badGuys.add(new RatHoriz(9,2));
        //badGuys.add(new WindBird(4,4));
        //badGuys.add(new WindProjectile(5,4,"down", "vert"));
        badGuys.add(new IceBirdVert(6,4,"left",badGuys));
        badGuys.add(new PikaCatVert(7,5));
        badGuys.add(new RatVert(5,6));
        badGuys.add(new FireWall(4,6));
        badGuys.add(new FireWall(5,6));
        badGuys.add(new FireWall(6,6));
        badGuys.add(new FireWall(7,6));
        badGuys.add(new FireDragon(1,7, "down", badGuys));
        badGuys.add(new FireDragon(10,8, "up", badGuys));
        badGuys.add(new FireProjectile(2,7,"down"));
        badGuys.add(new FireProjectile(9,8, "up"));
        badGuys.add(new Spikes(5,9,"vert"));
        badGuys.add(new Spikes(7,9,"vert"));
        badGuys.add(new Spikes(6,8));




        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {coal,coal,onBu,agif,agif,agif,agif,null,null,hPac},
                               {null,null,null,agif,agif,agif,agif,null,null,null},
                               {null,coal,coal,agif,agif,agif,agif,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,nxtL},
                               {ugif,null,null,null,null,null,null,null,null,null},
                               {null,coal,coal,agif,agif,agif,agif,null,null,null},
                               {null,null,null,agif,agif,agif,agif,null,null,null},
                               {coal,coal,onSw,agif,agif,agif,agif,null,null,null}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void setUpHell2()
    {
        userRow = 6;
        userCol = 0;
        grid.setBackground("hellBkgrnd.gif");
        String agif = "evilLava.gif";
        ugif = "hellRegFit.gif";
        //startRow = 6;
        //startCol = 3;
        String coal = "barrierCoal.gif";
        String onBu = "barrierOnButton.gif";
        String onSw = "barrierOnSwitchUp.gif";
        String hPac = "barrierHealthPack.gif";
        badGuys = new ArrayList<Villain>();
        badGuys.add(new PikaCatHoriz(2,2));
        badGuys.add(new RatHoriz(4,2));
        badGuys.add(new PikaCatHoriz(9,2));
        //badGuys.add(new WindBird(5,3));
        //badGuys.add(new WindProjectile(6,3,"down", "vert"));
        badGuys.add(new IceBirdVert(6,4,"left",badGuys));
        badGuys.add(new PikaCatVert(7,6));
        //badGuys.add(new RatVert(5,6));
        badGuys.add(new FireDragon(1,8, "down", badGuys));
        badGuys.add(new FireDragon(10,7,"up", badGuys));
        badGuys.add(new Spikes(5,2));
        badGuys.add(new Spikes(6,2));
        badGuys.add(new Spikes(7,2));
        badGuys.add(new FireWall(5,6));
        badGuys.add(new FireWall(6,6));
        badGuys.add(new FireWall(7,6));
        badGuys.add(new BarbedWire(5,9));
        badGuys.add(new BarbedWire(7,9));
        badGuys.add(new BarbedWire(6,8,"vert"));
        String mess = "barrierKForKey.gif";
        String lSab= "barrierLightSaber.gif";



        String[][] gameGrid = {{hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar,hBar},
                               {coal,coal,coal,onSw,agif,agif,agif,null,null,lSab},
                               {null,null,null,null,agif,agif,agif,null,null,mess},
                               {null,coal,coal,agif,agif,agif,agif,null,null,null},
                               {null,null,null,agif,agif,agif,agif,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {ugif,null,null,null,null,null,null,null,null,nxtL},
                               {null,hPac,null,null,null,null,null,null,null,null},
                               {null,coal,coal,agif,agif,agif,agif,null,null,null},
                               {null,null,null,null,agif,agif,agif,null,null,null},
                               {coal,coal,onBu,null,agif,agif,agif,null,null,null}};

        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;
    }
    public void twentythree()
    {
        gameSong.stop();
        finalSong = Applet.newAudioClip(this.getClass().getResource("killbillSong.wav"));
        finalSong.loop();
        grid.setBackground("twentythree.gif");

        String[][] gameGrid = {{null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null},
                               {null,null,null,null,null,null,null,null,null,null}};
        for(Villain v:badGuys)
        {
            gameGrid[v.getRow()][v.getCol()] = v.getVillainName();
        }
        this.gameGrid = gameGrid;

    }
    public void twentyfour()
    {
        grid.setBackground("twentyfour.gif");
    }
    public void twentyfive()
    {
        grid.setBackground("twentyfive.gif");
    }
    public void twentysix()
    {
        grid.setBackground("twentysix.gif");
    }
    public void twentyseven()
    {
        grid.setBackground("twentyseven.gif");

    }
    public void twentyeight()
    {
        grid.setBackground("twentyeight.gif");

    }
    public void twentynine()
    {
        grid.setBackground("twentynine.gif");

    }
    public void thirty()
    {
        grid.setBackground("thirty.gif");

    }
    public void thirtyone()
    {
        grid.setBackground("thirtyone.gif");

    }
    public void end1()
    {
        finalSong.stop();
        endSong = Applet.newAudioClip(this.getClass().getResource("sirenSong.wav"));
        endSong.play();
        grid.setBackground("end1.gif");

    }
    public void end2()
    {
        grid.setBackground("end2.gif");

    }
    public void fin()
    {
        grid.setBackground("fin.gif");

    }


    public void handleKeyPress()
    {
        int key = grid.checkLastKeyPressed();
        System.out.println(key);
        if(key == 38 && userRow > 1) //up
        {
            if(gameGrid[userRow-1][userCol] != null)
            {
                if(gameGrid[userRow-1][userCol].equals(nxtL))
                {
                    level++;
                    setUpLevels();
                }
                else if(gameGrid[userRow-1][userCol].equals("barrierHealthPack.gif"))
                {
                    gameGrid[userRow-1][userCol]= null;
                    numLives++;
                    addLife();
                }
                else if(gameGrid[userRow-1][userCol].equals("barrierKey.gif") || gameGrid[userRow-1][userCol].equals("barrierLightSaber.gif"))
                {
                    gameGrid[userRow-1][userCol]= null;
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("BarbedWire") != -1)
                        {
                            v.die();
                        }
                    }
                }
                else if(gameGrid[userRow-1][userCol].equals("barrierOnButton.gif"))
                {
                    gameGrid[userRow-1][userCol] = null;
                    gameGrid[userRow-1][userCol]= "barrierOffButton.gif";
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("Spikes") != -1)
                        {
                            v.die();
                        }
                    }
                }
                else if(gameGrid[userRow-1][userCol].equals("barrierOnSwitchUp.gif"))
                {
                    gameGrid[userRow-1][userCol] = null;
                    gameGrid[userRow-1][userCol]= "barrierOffSwitchUp.gif";
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("FireWall") != -1)
                        {
                            v.die();
                        }
                    }
                }
                else if((gameGrid[userRow-1][userCol].equals("bubbles.gif") || gameGrid[userRow-1][userCol].indexOf("evilWind") != -1) && on)
                {
                    moves++;
                }
                else if(gameGrid[userRow-1][userCol].indexOf("evil") != -1)
                {
                    numLives--;
                    removeLife();
                }
                else if(gameGrid[userRow-1][userCol].indexOf("barrier") == -1)
                {
                        gameGrid[userRow][userCol]=null;
                        userRow--;
                        if(on)
                            setUserFF();
                        else
                            setUserFit();
                }
            }
            else
            {
                System.out.println("moves " + moves);
                gameGrid[userRow][userCol]=null;
                if(gameGrid[userRow-1][userCol] == null)
                    userRow--;
                moves++;
                if(on)
                    setUserFF();
                else
                    setUserFit();
                System.out.println("moves " + moves);
            }
            
        }
        if(key == 40 && userRow < grid.getNumRows()-1) //down
        {
            if(gameGrid[userRow+1][userCol] != null)
            {
                if(gameGrid[userRow+1][userCol].equals(nxtL))
                {
                    level++;
                    setUpLevels();
                }
                else if(gameGrid[userRow+1][userCol].equals("barrierHealthPack.gif"))
                {
                    gameGrid[userRow+1][userCol]= null;
                    numLives++;
                    addLife();
                }
                else if(gameGrid[userRow+1][userCol].equals("barrierOnButton.gif"))
                {
                    gameGrid[userRow+1][userCol] = null;
                    gameGrid[userRow+1][userCol]= "barrierOffButton.gif";
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("Spikes") != -1)
                        {
                            v.die();
                        }
                    }

                }
                else if(gameGrid[userRow+1][userCol].equals("barrierOnSwitchDown.gif"))
                {
                    gameGrid[userRow+1][userCol] = null;
                    gameGrid[userRow+1][userCol]= "barrierOffSwitchDown.gif";
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("FireWall") != -1)
                        {
                            v.die();
                        }
                    }
                }
                else if((gameGrid[userRow+1][userCol].equals("bubbles.gif") || gameGrid[userRow+1][userCol].indexOf("evilWind") != -1) && on)
                {
                    moves++;
                }
                else if(gameGrid[userRow+1][userCol].indexOf("evil") != -1)
                {
                    numLives--;
                    removeLife();
                }
                else if(gameGrid[userRow+1][userCol].indexOf("barrier") == -1)
                {

                        gameGrid[userRow][userCol]=null;
                        userRow++;
                        if(on)
                            setUserFF();
                        else
                            setUserFit();
                }
            }
            else
            {

                gameGrid[userRow][userCol]=null;
                userRow++;
                moves++;
                if(on)
                    setUserFF();
                else
                    setUserFit();
            }
        }
        if(key == 39 && userCol < grid.getNumCols()-1) //right
        {
            if(gameGrid[userRow][userCol+1] != null)
            {
                if(gameGrid[userRow][userCol + 1].equals(nxtL))
                {
                    level++;
                    setUpLevels();
                }
                else if(gameGrid[userRow][userCol+1].equals("barrierHealthPack.gif"))
                {
                    gameGrid[userRow][userCol+1]= null;
                    numLives++;
                    addLife();
                }
                else if(gameGrid[userRow][userCol+1].equals("barrierOnButton.gif"))
                {
                    gameGrid[userRow][userCol+1] = null;
                    gameGrid[userRow][userCol+1]= "barrierOffButton.gif";
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("Spikes") != -1)
                        {
                            v.die();
                        }
                    }

                }
                else if((gameGrid[userRow][userCol+1].equals("bubbles.gif") || gameGrid[userRow][userCol+1].indexOf("evilWind") != -1) && on)
                {
                    moves++;
                }
                else if (gameGrid[userRow][userCol + 1].indexOf("evil") != -1)
                {
                    numLives--;
                    removeLife();
                }
                else if (gameGrid[userRow][userCol + 1].indexOf("barrier") == -1)
                {
                        gameGrid[userRow][userCol]=null;
                        userCol++;
                        if(on)
                            setUserFF();
                        else
                            setUserFit();
                }
            }
            else
            {
                    gameGrid[userRow][userCol]=null;
                    userCol++;
                    moves++;
                    if(on)
                        setUserFF();
                    else
                        setUserFit();
            }
        }
        if(key == 37 && userCol > 0) //left
        {
            if(gameGrid[userRow][userCol-1] != null)
            {
                if(gameGrid[userRow][userCol-1].equals(nxtL))
                {
                    level++;
                    setUpLevels();
                }
                else if(gameGrid[userRow][userCol-1].equals("barrierHealthPack.gif"))
                {
                    gameGrid[userRow][userCol-1]= null;
                    numLives++;
                    addLife();
                }
                else if(gameGrid[userRow][userCol-1].equals("barrierOnButton.gif"))
                {
                    gameGrid[userRow][userCol-1] = null;
                    gameGrid[userRow][userCol-1]= "barrierOffButton.gif";
                    for(Villain v: badGuys)
                    {
                        if(v.getVillainName().indexOf("Spikes") != -1)
                        {
                            v.die();
                        }
                    }

                }
                else if((gameGrid[userRow][userCol-1].equals("bubbles.gif") || gameGrid[userRow][userCol-1].indexOf("evilWind") != -1) && on)
                {
                    moves++;
                }
                else if(gameGrid[userRow][userCol-1].indexOf("evil") != -1)
                {
                    numLives--;
                    removeLife();
                }
                else if(gameGrid[userRow][userCol-1].indexOf("barrier") == -1)
                {
                        gameGrid[userRow][userCol]=null;
                        userCol--;
                        if(on)
                            setUserFF();
                        else
                            setUserFit();
                }
            }
            else
            {
                    gameGrid[userRow][userCol]=null;
                    userCol--;
                    moves++;
                    if(on)
                        setUserFF();
                    else
                        setUserFit();
            }
        }
        if(key == 65) //attack
        {
            for(Villain v: badGuys)
            {
                if(v.getVillainName().equals("evilRat.gif"))
                {
                    if((v.getRow()==userRow-1 && v.getCol()==userCol) || (v.getRow()==userRow+1 && v.getCol()==userCol) || (v.getRow()==userRow && v.getCol()+1==userCol) || (v.getRow()==userRow && v.getCol()-1==userCol))
                    {
                        v.die();
                        gameGrid[userRow][userCol]=null;
                        gameGrid[userRow][userCol] = "ratCityKillFit.gif";
                    }
                }
            }

        }
        if(key == 83 && level >= 3) //shield/force field
        {
            for(Villain v: badGuys)
            {
                if(v.getVillainName().equals("bubbles.gif") || v.getVillainName().indexOf("evilWind") != -1)
                {
                    if(v.getRow()==userRow || v.getCol()==userCol)
                    {
                        on = true;
                    }
                }
            }
        }


        if(key == 68) //dig
        {
            for(Villain v: badGuys)
            {
                if(v instanceof PikaCatHoriz)
                {
                    if((v.getRow()==userRow && v.getCol()==userCol+1) || (v.getRow()==userRow && v.getCol()==userCol-1))
                    {
                        gameGrid[userRow][userCol]=null;
                        gameGrid[userRow][userCol] = "brownTile.gif";
                    }
                }
                else if(v instanceof  PikaCatVert)
                {
                    if((v.getRow()==userRow-1 && v.getCol()==userCol) || (v.getRow()==userRow+1 && v.getCol()==userCol))
                    {
                        gameGrid[userRow][userCol]=null;
                        gameGrid[userRow][userCol] = "brownTile.gif";
                    }
                }
            }
        }

        if(key == 70 && level >= 5) //fire
        {
            for(Villain v: badGuys)
            {
                if (v.getVillainName().indexOf("Ice") != -1) {
                    if ((v.getRow() == userRow || v.getCol() == userCol)) {
                        gameGrid[userRow][userCol] = null;
                        gameGrid[userRow][userCol] = "aviatorFireGunFit.gif";
                    }
                }
            }
            String ice = "barrierIceBlock";
            if(userRow > 1 && userRow < grid.getNumRows()-1 && userCol < grid.getNumCols()-1 && userCol > 0)
            {
                if(gameGrid[userRow+1][userCol] != null && gameGrid[userRow+1][userCol].indexOf(ice) != -1)
                {
                    gameGrid[userRow+1][userCol]= null;
                }
                else if(gameGrid[userRow-1][userCol] != null && gameGrid[userRow-1][userCol].indexOf(ice) != -1)
                {
                    gameGrid[userRow-1][userCol]= null;
                }
                else if( gameGrid[userRow][userCol+1] != null && gameGrid[userRow][userCol+1].indexOf(ice) != -1)
                {
                    gameGrid[userRow][userCol+1]= null;
                }
                else if(gameGrid[userRow][userCol-1] != null && gameGrid[userRow][userCol-1].indexOf(ice) != -1)
                {
                    gameGrid[userRow][userCol-1]= null;
                }
            }
        }
        if(key == 87 && level >= 9) //water
        {
            for(Villain v: badGuys)
            {
                if(v.getVillainName().equals("evilFireDragon.gif"))
                {
                    if((v.getRow()==userRow || v.getCol()==userCol))
                    {
                        gameGrid[userRow][userCol]=null;
                        gameGrid[userRow][userCol] = "hellWaterGunFit.gif";
                    }
                }
            }
        }
        if(key == 75 && level == 10) //key
        {
            if(userRow == 3 && userCol == 9)
            {
                gameGrid[userRow-1][userCol] = null;
            }
        }
        if(key == 32 && (level < 1 || level > 10)) //space
        {
            if(level == -24)
            {
                level += 3;
                setUpLevels();
            }
            else
            {
                level++;
                setUpLevels();
                System.out.println("level" + level);
            }
        }
        if(key == 10 && level == -22) //enter
        {
            level -= 2;
            setUpLevels();
        }
        if(key == 82 && numLives == 0)
        {
            level = 1;
            setUpLevels();
            numLives = 6;
        }


    }


    public void setUserFit()
    {
        if(level == 1 || level == 2)
            gameGrid[userRow][userCol] = "ratCityRegFit.gif";
        else if(level == 3 || level == 4)
            gameGrid[userRow][userCol] = "scubaDiveRegFit.gif";
        else if(level == 5 || level == 6)
            gameGrid[userRow][userCol] = "aviatorRegFit.gif";
        else if(level == 7 || level == 8)
            gameGrid[userRow][userCol] = "mudpitRegFit.gif";
        else if(level == 9 || level == 10)
            gameGrid[userRow][userCol] = "hellRegFit.gif";

    }
    public void setUserFF()
    {
        if(level == 3 || level == 4)
            gameGrid[userRow][userCol] = "scubaDiveForceFieldFit.gif";
        else if(level == 5 || level == 6)
            gameGrid[userRow][userCol] = "aviatorForceFieldFit.gif";
        else if(level == 7 || level == 8)
            gameGrid[userRow][userCol] = "mudpitForceFieldFit.gif";
        else if(level == 9 || level == 10)
            gameGrid[userRow][userCol] = "hellForceFieldFit.gif";
    }
    public void displayhearts()
    {
        if (level > 0 && level < 11)
        {
            for (int i = 2; i <= numLives + 1; i++)
            {
                gameGrid[0][i] = "heart.gif";
            }
        }
    }
    public void addLife()
    {
        if(numLives < 6)
        {
            for(int i = gameGrid[0].length-1; i > 0; i--)
            {
                if(gameGrid[0][i].equals("heart.gif"))
                {
                    gameGrid[0][i+1] = "heart.gif";
                    i = 0;
                }
            }
        }
    }
    public void removeLife()
    {
        for(int i = gameGrid[0].length-1; i >= 0; i--)
        {
            if(gameGrid[0][i].equals("heart.gif"))
            {
                gameGrid[0][i] = "healthBar.gif";
                i = 0;
            }
        }
    }
    public int getScore()
    {
        return timesGet;
    }
  
    public void updateTitle()
    {
        grid.setTitle("Kill Bill by Michelle Lee and Antonia Li");
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
        Game game = new Game();
        game.play();
    }
  
    public static void main(String[] args)
    { 
        test();
    }
}
