import java.util.*;

class DiceProb {
public static void main(String[] args) {
	int[] dice = new int[12];

	//They each must ONLY have a single 1 on each dice
	dice[0] = 1;
	dice[1] = 1;

	for (int i = 2; i < dice.length; i++) {
		dice[i] = 2;
	}

    int[] soReal = {1,1,2,3,2,4,3,5,3,6,4,8};

    if (!makeFaces(2, dice)) {
    	System.out.println("Impossible");
    } else {
    	printDie(dice);
    }
	
}

public static boolean makeFaces (int face, int [] arr1) {
	System.out.println("Face in makeFaces: " + face);
	for (int i = 2; i < 12; i++) {
        arr1[face] = i;
        //printDie(arr1);
		if (isPartialSolution(face, arr1)) {
			System.out.println("yep it's partial");
			if (isFinalSolution(arr1)) {
    			System.out.println("YES");
    			return true;
    		} else {
    			if (makeFaces(face + 1, arr1)) {
    			    return true;
    			}
    		}
    		
		}
	arr1[face] = 0;
	}
    return false;
}

public static boolean isFinalSolution(int[] arr1) {

	int[] correctCoefficients = {0,1,2,3,4,5,6,5,4,3,2,1,0};

	return Arrays.equals(multiplyPoly(arr1), correctCoefficients);
}

public static boolean isPartialSolution(int n, int[] arr1) {
	int[] correctCoefficients = {0,1,2,3,4,5,6,5,4,3,2,1,0};
    int[] coefficients = new int[13];
    coefficients = multiplyPoly(arr1);
    printDie(arr1);
	// Make sure none of the coefficients are greater than the correcCoef
	for (int i = 1; i < n; i++) {
		// System.out.println("Real: " + coefficients[i]);
		// System.out.println("Expected: " + correctCoefficients[i]);
		if (coefficients[i] > correctCoefficients[i]) {
			System.out.println();
			System.out.println (i + " " + coefficients[i] + " is greater than " + correctCoefficients[i]);
			return false;
		}
	}
	return true;
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
            	result[0] += 1; //Garbage can
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