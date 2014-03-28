import java.util.*;

class DiceBrute {

    public static void main(String[] args) {
    	int[] dice = new int[12];

	    // They each must ONLY have a single 1 on each die
	    dice[0] = 1;
	    dice[1] = 1;
	    // Initialize "tweeners" to 2, all tweeners must be between 2 and the max
	    for (int i = 2; i < dice.length - 2; i++) {
		        dice[i] = 2;
	    }

	    // Last two faces (slots) must add to 12. We'll start at 7 & 5
	    dice[10] = 5;
	    dice [11] = 7;
        
        int counter = 0;
        int max = 6;
        while (!isFinalSolution(dice)) {
	        if (counter < 1000000) {
	        	dice[2] = randomNumber(max);
	        	dice[3] = randomNumber(max);
	        	dice[4] = randomNumber(max);
	        	dice[5] = randomNumber(max);
	        	dice[6] = randomNumber(max);
	        	dice[7] = randomNumber(max);
	        	dice[8] = randomNumber(max);
	        	dice[9] = randomNumber(max);
	        	counter++;
	        } else {
	        	counter = 0;
	        	dice[11] = dice[11] + 1;
	        	dice[10] = dice[10] - 1;
	        	max++;
	        }
	        
	    }

	    formatDice(dice);

    }

    public static void formatDice (int[] arr1) {
    	int[] die1 = new int[6];
    	int[] die2 = new int[6];
    	int slot = 0;
        for (int i = 0; i < 11; i += 2) {
            die1[slot] = arr1[i];
            slot++;
        }
        slot = 0;
        for (int i = 1; i < 12; i += 2) {
            die2[slot] = arr1[i];
            slot++;
        }

        System.out.println("Die 1:");
        printDie(die1);
        System.out.println("Die 2:");
        printDie(die2);

    }

    public static int randomNumber(int max) {
    	return (int)Math.floor((Math.random() * (max - 2)) + 2);
    } 

    public static boolean isFinalSolution(int[] arr1) {
	    int[] correctCoefficients = {0,1,2,3,4,5,6,5,4,3,2,1,0};
	    return Arrays.equals(multiplyPoly(arr1), correctCoefficients);
    }

    public static int[] multiplyPoly (int[] arr1) {
	    int[] result = new int[13];
	    for (int i = 0; i < result.length; i++) {
		    result[i] = 0;
	    }
	    for (int i = 0; i < 11; i += 2) {
		    for (int j = 1; j < 12; j += 2) {
                int bin = arr1[i] + arr1[j];
                if (bin > 0 && bin < 13) {
                    result[bin - 1] += 1;
                } else {
            	    result[0] += 1;
                }
		    }		
	    }
	    return result;
    }

    public static void printDie(int[] arr) {
	    for (int i = 0; i < arr.length; i++) {
		    System.out.println(arr[i]);
	    }
    }
    
}