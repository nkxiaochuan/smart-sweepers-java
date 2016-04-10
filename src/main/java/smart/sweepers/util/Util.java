/**
 * 
 */
package smart.sweepers.util;

/**
 * @author Andy
 *
 */
public class Util {

	public static int randomInt(int x, int y){
		return (int) (Math.random() % (y - x + 1) + x);
	}
	
	public static boolean randomBoolean(){
		if (randomInt(0, 1) == 0)
			return true;
		return false;
	}
	
	/**
	 * returns a random float between zero and 1
	 * @return
	 */
	public static float randFloat(){
		return (float) Math.random();
	}
	/**
	 * returns a random double in the range -1 < n < 1
	 * @return
	 */
	public static double randomClamped(){
		return Math.random() - Math.random();
	}
	
}
