package hw2;

import static hw2.CS227Baseball.*;

public class BaseballTest 
{
	public static void main(String[] args)
	{
		CS227Baseball game = new CS227Baseball(3);
		
//		game.pitch(STRIKE);
//		System.out.println(game);
//		game.pitch(BALL);
//		System.out.println(game);
//		game.pitch(STRIKE);
//		System.out.println(game);
//		game.pitch(STRIKE);
//		System.out.println(game);
//		game.pitch(POP_FLY);
//		System.out.println(game);
//		game.pitch(POP_FLY);
//		System.out.println(game);
//		
//		game.pitch(POP_FLY);
//		System.out.println(game);
//		game.pitch(POP_FLY);
//		System.out.println(game);
//		game.pitch(POP_FLY);
//		System.out.println(game);
		
//		game.advanceRunners(true);
//		System.out.println(game.playerOnFirst());
//		System.out.println(game.toDisplayString());
//		
//		game.advanceRunners(false);
//		System.out.println(game.playerOnFirst());
//		System.out.println(game.playerOnSecond());
//		System.out.println(game.toDisplayString());
//		
//		game.advanceRunners(false);
//		System.out.println(game.playerOnFirst());
//		System.out.println(game.playerOnSecond());
//		System.out.println(game.playerOnThird());
//		System.out.println(game.toDisplayString());
//		
//		game.advanceRunners(false);
//		System.out.println(game.toDisplayString());
		
		game.pitchWithHit(4);
		System.out.println(game.toDisplayString());
	}

}
