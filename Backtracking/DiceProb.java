import java.util.*;

class DiceProb {
public static void main(String[] args) {
	int[] die1 = new int[6];
	int[] die2 = new int[6];

	//They each must ONLY have a single 1 on each dice
	die1[0] = 1;
	die2[0] = 1;

	//2-11 are the remaining options for each side
	// for (int i = 1; i < die1.length; i++) {
 //        die1[i] = 2;
 //        die2[i] = 2;
	// }

	printDie(die1);
	printDie(die2);
    

    if (!makeFaces(2, die1, die2)) {
    	printDie(die1);
    	printDie(die2);
    	System.out.println("Impossible");
    } else {
    	printDie(die1);
    	printDie(die2);
    }
	
}

public static boolean makeFaces (int face, int [] arr1, int[] arr2) {
	System.out.println("Face in makeFaces: " + face);
	for (int i = 2; i < 12; i++) {
		if (face < 7) {
			arr1[face - 1] = i;
		} else {
			arr2[face - 7] = i;
		}
		printDie(arr1);
    	printDie(arr2);

		if (isPartialSolution(face, arr1, arr2)) {
			System.out.println("yep it's partial");
			if (isFinalSolution(arr1, arr2)) {
    			System.out.println("YES");
    			return true;
    		} else {
    			if (makeFaces(face + 1, arr1, arr2)) {
    			    return true;
    			}
    		}
    		
		}
	}
    return false;
}

public static boolean isFinalSolution(int[] arr1, int[] arr2) {

	int[] correctCoefficients = {0,1,2,3,4,5,6,5,4,3,2,1,0};

	return Arrays.equals(multiplyPoly(arr1, arr2), correctCoefficients);
}

public static boolean isPartialSolution(int face, int[] arr1, int[] arr2) {
	System.out.println("Face in PS: " + face);
	int desired;
	switch (face) {
		case 1: desired = 0;
		        break;
		case 2: desired = 1;
		        break;
		case 3: desired = 2;
		        break;
		case 4: desired = 3;
		        break;
		case 5: desired = 4;
		        break;
	    case 6: desired = 5;
		        break;
		case 7: desired = 6;
		        break;
		case 8: desired = 5;
		        break;
		case 9: desired = 4;
		        break;
		case 10: desired = 3;
		        break;
		case 11: desired = 2;
		        break;
	    case 12: desired = 1;
		        break;
		case 13: desired = 0;
		        break;
		default: desired = 0;
		        break;	
	}

    int[] result = new int[13];
    result = multiplyPoly(arr1, arr2);
    return result[face - 1] == desired;
}

public static int[] multiplyPoly (int[] arr1, int[] arr2) {
	int[] result = new int[13];
	for (int i = 0; i < result.length; i++) {
		result[i] = 0;
	}
	for (int i = 0; i < arr1.length; i++) {
		for (int j = 0; j < arr2.length; j++) {
            int bin = arr1[i] + arr2[j];
            if (bin > 0) {
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