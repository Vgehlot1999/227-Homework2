package hw2;

/**
 * Simplified model of American baseball.
 * 
 * THIS CODE WILL NOT COMPILE UNTIL YOU HAVE ADDED STUBS FOR 
 * ALL METHODS SPECIFIED IN THE JAVADOC
 * 
 * @author Vismay
 */
public class CS227Baseball
{
  /**
   * Constant indicating that a pitch results in a ball.
   */
  public static final int BALL = 0;
  
  /**
   * Constant indicating that a pitch results in a strike.
   */
  public static final int STRIKE = 1;
  
  /**
   * Constant indicating that a pitch results in an out from a fly ball.
   */
  public static final int POP_FLY = 2;
  
  /**
   * Number of strikes causing a player to be out.
   */
  public static final int MAX_STRIKES = 3;
  
  /**
   * Number of balls causing a player to walk.
   */
  public static final int MAX_BALLS = 4;
  
  /**
   * Number of outs before the teams switch.
   */
  public static final int MAX_OUTS = 3;

  
  
  
  
  // TODO: everything else
  
  
  
  
  
  
  /**
   * Returns a three-character string representing the players on base, 
   * in the order first, second, and third, where 'X' indicates a player 
   * is present and 'o' indicates no player.  For example, the string "XXo" 
   * means that there are players on first and second but not on third.
   * @return
   *   three-character string showing players on base
   */
  public String getBases()
  {
    return (playerOnFirst() ? "X" : "o") +
        (playerOnSecond() ? "X" : "o") +
        (playerOnThird() ? "X" : "o");
  }

  /**
   * Returns a one-line string representation of the current game state.
   * The format is:
   * <pre>
   *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
   * </pre>
   * The first three characters represent the players on base as returned by 
   * the <code>getBases()</code> method. The 'T' after the inning number indicates 
   * it's the top of the inning, and a 'B' would indicate the bottom.
   * 
   * @return
   *   one-line string representation of the game state
   */
  public String toString()
  {
    String bases = getBases();
    String topOrBottom = (isTop() ? "T" : "B");
    String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
    return String.format(fmt, bases, getInning(), topOrBottom, getScore(true), getScore(false), getBalls(), getStrikes(), getOuts());
  }

  /**
   * Returns a multi-line string representation of the current game state.
   * The format is:  
   * <pre>
   *     o - o    Inning:1 (T)
   *     |   |    Score:0-0
   *     o - H    Balls:0 Strikes:0 Outs:0
   * </pre>
   * The 'T' after the inning number indicates it's the top of the inning, 
   * and a 'B' would indicate the bottom.
   * @return
   *   multi-line string representation of current game state
   */
  public String toDisplayString()
  {
    String firstChar = (playerOnFirst() ? "X" : "o");
    String secondChar = (playerOnSecond() ? "X" : "o");
    String thirdChar = (playerOnThird() ? "X" : "o");
    String topOrBottom = (isTop() ? "T" : "B");
    String firstLine = String.format("%s - %s    Inning:%d (%s)\n", secondChar, firstChar, getInning(), topOrBottom);
    String secondLine = String.format("|   |    Score:%d-%d\n", getScore(true), getScore(false));
    String thirdLine = String.format("%s - H    Balls:%d Strikes:%d Outs:%d\n", thirdChar, getBalls(), getStrikes(), getOuts());
    return firstLine + secondLine + thirdLine;   
  }
  
  /**
   * current inning
   */
  private int inning;
  /**
   * total number of innings
   */
  private int numberOfInnings;
  /**
   * number of balls
   */
  private int balls;
  /**
   * number of outs
   */
  private int outs;
  /**
   * number of strikes
   */
  private int strikes;
  /**
   *boolean that determines either top or bottom of inning
   */
  private boolean top;
  /**
   * score for team 0
   */
  private int score0;
  /**
   * score for team 1
   */
  private int score1;
  /**
   * boolean that determines if there's a runner on first base
   */
  private boolean first;
  /**
   * boolean that determines if there's a runner on second base
   */
  private boolean second;
  /**
   * boolean that determines if there's a runner on third base
   */
  private boolean third;
  
  /**
   * constructs a game that has the given number of
   * innings and starts at the top of inning 1
   * @param givenNumInnings
   * 	determines how many innings the specific game will be
   */
  public CS227Baseball(int givenNumInnings)
  {
	  top = true;
	  inning = 1;
	  numberOfInnings = givenNumInnings;
  }
  
  /**
   * Advances all players on bases by one base, updating
   * the score if there is currently a player on third
   * @param newPlayerOnFirst	
   * 	if true, then a new runner will be put on first base
   * 	if false, then only players currently on bases will advance
   */
  public void advanceRunners(boolean newPlayerOnFirst)
  {
	  if (third)
	  {
		  third = false;
		  if (top)
		  {
			  score0++;
		  }
		  else
		  {
			  score1++;
		  }
	  }
	  
	  if (second)
	  {
		  second = false;
		  third = true;
	  }
	  
	  if (first)
	  {
		  first = false;
		  second = true;
	  }
	  
	  if (newPlayerOnFirst)
	  {
		  first = true;
	  }
	  
  }
  
  /**
   * returns the number of balls for the current batter
   * @return
   * 	current number of balls
   */
  public int getBalls()
  {
	  return balls;
  }
  
  /**
   * returns the current inning. Innings will always
   * start from one, and if the game is over, then the
   * total number of innings plus one will be returned
   * @return
   * 	current inning or the total number of innings plus one if the game is over
   */
  public int getInning()
  {
	  return inning;
  }
  
  /**
   * returns the number of outs for the current team
   * @return
   * 	number of outs the current team has
   */
  public int getOuts()
  {
	  return outs;
  }
  
  /**
   * returns the score of the current team at bat. This
   * is determined by checking whether it is the top of
   * the inning or the bottom by using the boolean parameter
   * @param team0
   * 	if true, return team0's score, else return team1's score
   * @return
   * 	score for the current team at bat
   */
  public int getScore(boolean team0)
  {
	  if (team0)
	  {
		  return score0;
	  }
	  return score1;
  }
  
  /**
   * returns the number of strikes for the current batter
   * @return
   * 	current number of strikes
   */
  public int getStrikes()
  {
	  return strikes;
  }
  
  /**
   * returns true if the game is over, false otherwise.
   * This is determined by finding out if the current 
   * inning exceeds the total number of innings
   * @return
   * 	true if the game is over, false otherwise
   */
  public boolean isOver()
  {
	  
	  if (inning > numberOfInnings)
	  {
		  return true;
	  }
	  return false;
  }
  
  /**
   * returns true if it's the first half of the inning
   * @return
   * 	true if its the first half of the inning, false otherwise
   */
  public boolean isTop()
  {
	  return top;
  }
  
  /**
   * pitch that doesn't result in a hit, but in either 
   * a ball, strike, or pop fly. If the number of strikes
   * or balls exceeds the max number, then an out is added
   * to the team's total outs or the player is allowed a walk,
   * respectively. If the outcome is a pop fly, an out is added
   * to the total. If the max number of outs is reached, then 
   * teams switch. If the game is over, then nothing happens.
   * @param outcome
   * 	one of three outcomes: ball, strike, or pop fly
   */
  public void pitch(int outcome)
  {
	  if (!isOver())
	  {
		  if (outcome == BALL)
		  {
			  balls++;
			  if (balls == MAX_BALLS)
			  {
				  pitchWithHit(1);
				  balls = 0;
			  }
		  }
		  
		  if (outcome == STRIKE)
		  {
			  strikes++;
			  if (strikes == MAX_STRIKES)
			  {
				  strikes = 0;
				  balls = 0;
				  outs++;
				  if (outs == MAX_OUTS)
				  {
					  switchTeam();
				  }
			  }
		  }
		  
		  if (outcome == POP_FLY)
		  {
			  strikes = 0;
			  balls = 0;
			  outs++;
			  if (outs == MAX_OUTS)
			  {
				  switchTeam();
				  outs = 0;
			  }
		  }
	  }  
  }
  
  /**
   * similar to pitch, but with runs. if player hits a single,
   * then advanceRunners method is called once as true. If a 
   * double is hit, then advanceRunners is called twice, once
   * true and the other false. if triple, then advanceRunners 
   * is called true, false, and false. If a homerun, then 
   * advanceRunners is called 4 times, once with true and the
   * rest as false. Doesn't run if the game is over. if homerun,
   * then the appropriate team's score is raised by one.
   * @param numBases
   * 	number of bases that the batter and all players on base will advance
   */
  public void pitchWithHit(int numBases)
  {
	  if(!isOver())
	  {
		  balls = 0;
		  strikes = 0;
		  if (numBases == 1)
		  {
			  advanceRunners(true);
			  
		  }
		  if (numBases == 2)
		  {
			  advanceRunners(true);
			  advanceRunners(false);
		  }
		  if (numBases == 3)
		  {
			  advanceRunners(true);
			  advanceRunners(false);
			  advanceRunners(false);
		  }
		  if (numBases == 4)
		  {
			  advanceRunners(true);
			  advanceRunners(false);
			  advanceRunners(false);
			  advanceRunners(false);
		  }
	  }
  }
  
  /**
   * Similar to pitchWithHit, except with possible outs. If the base
   * fielded has a player on it, that player is then out. If the number
   * of outs reaches max outs, then teams switch. If a player runs to
   * home and gets out, that run is not counted in the score.
   * @param numBases
   * 	number of bases that the batter and all players on base will advance
   * @param whichBaseFielded
   * 	the base to which the defense fields the ball (1, 2, 3, or 4)
   */
  public void pitchWithHitAndOut(int numBases, int whichBaseFielded)
  {
	  if (!isOver())
	  {
		  if (numBases == 1)
		  {
			  if (whichBaseFielded == 4 && third)
			  {
				  third = false;
				  outs++;
				  if (outs == MAX_OUTS)
				  {
					  switchTeam();
				  }
			  }
			  advanceRunners(true);
		  }
		  if (numBases == 2)
		  {
			  advanceRunners(true);
			  if (whichBaseFielded == 4 && third)
			  {
				  third = false;
				  outs++;
				  if (outs == MAX_OUTS)
				  {
					  switchTeam();
				  }
			  }
			  advanceRunners(false);
		  }
		  if (numBases == 3)
		  {
			  advanceRunners(true);
			  advanceRunners(false);
			  if (whichBaseFielded == 4 && third)
			  {
				  third = false;
				  outs++;
				  if (outs == MAX_OUTS)
				  {
					  switchTeam();
				  }
			  }
			  advanceRunners(false);
		  }
		  if (numBases == 4)
		  {
			  advanceRunners(true);
			  advanceRunners(false);
			  advanceRunners(false);
			  if (whichBaseFielded == 4 && third)
			  {
				  third = false;
				  outs++;
				  if (outs == MAX_OUTS)
				  {
					  switchTeam();
				  }
			  }
			  advanceRunners(false);
		  }
		  
		  if (whichBaseFielded == 1 && first)
		  {
			  first = false;
			  outs++;
			  if (outs == MAX_OUTS)
			  {
				  switchTeam();
			  }
		  }
		  if (whichBaseFielded == 2 && second)
		  {
			  second = false;
			  outs++;
			  if (outs == MAX_OUTS)
			  {
				  switchTeam();
			  }
		  }
		  if (whichBaseFielded == 3 && third)
		  {
			  third = false;
			  outs++;
			  if (outs == MAX_OUTS)
			  {
				  switchTeam();
			  }
		  }
	  }
  }
  
  /**
   * returns true if there is a player on first base, false otherwise
   * @return
   * 	true if there is a player on first, false otherwise
   */
  public boolean playerOnFirst()
  {
	  return first;
  }
  
  /**
   * returns true if there is a player on second base, false otherwise
   * @return
   * 	true if there is a player on second, false otherwise
   */
  public boolean playerOnSecond()
  {
	  return second;
  }
  
  /**
   * returns true if there is a player on third base, false otherwise
   * @return
   * 	true if there is a player on third, false otherwise
   */
  public boolean playerOnThird()
  {
	  return third;
  }
  
  /**
   * This method is called when a team meets the max number of outs.
   * This method switches the team at bat. If it is the bottom of the
   * inning, then it is changed to the top of the next inning, and 
   * the current inning value is increased by one. Bases are emptied,
   * and balls, outs, and strikes are reset.
   */
  private void switchTeam()
  {
	  if (top)
	  {
		  top = false;
	  }
	  
	  else
	  {
		  top = true;
		  inning++;
	  }
	  
	  balls = 0;
	  outs = 0;
	  strikes = 0;
	  first = false;
	  second = false;
	  third = false;
  
  }
  
}
















