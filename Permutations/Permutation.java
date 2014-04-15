import java.util.*;

public class Permutation {

    public static void main (String[] args) {
        /*Main method for testing*/
    }

    private ArrayList<Integer> digits = new ArrayList();
    private ArrayList<Integer> currentPerm = new ArrayList();

    /** Constructs an n-permutation object, representing a permutation of 0, 1, ..., n-1, and initializes it to the lexicographically first such permutation.*/
    public Permutation (int n) {

        for (int i = 0; i < n; i++) {
            this.digits.add(i);
            this.currentPerm.add(i);
        }
    }
    //  /** Constructs a specific permutation.*/
    public Permutation (int[] perm) {
        for (int i = 0; i < perm.length; i++) {
            this.currentPerm.add(perm[i]);
            this.digits.add(perm[i]);
        }
        Collections.sort(this.digits);
    }

    public int[] toArray () {
      int[] result = new int[this.digits.size()];

      for (int i = 0; i < result.length; i++) {
          result[i] = this.digits.get(i).intValue();
      }
      return result;
    }

    public int getElement (int i) {
        return this.currentPerm.get(i);
    }

    public boolean isFirstPerm () {
        return this.currentPerm.equals(this.digits);
    
    }

    public boolean isLastPerm () {
        int digitsIndex = this.digits.size() - 1;
        for (int i = 0; i < this.digits.size(); i++) {
            if (this.currentPerm.get(i) != this.digits.get(digitsIndex)) {
                return false;
            }
            digitsIndex --;
        }
        return true;
    }

    public java.lang.String toString () {
        String result = "";

        for (int i = 0; i < this.currentPerm.size() - 1; i++) {
            result += this.currentPerm.get(i) + "-";
        }
        result += this.currentPerm.get(this.currentPerm.size() - 1);

        return result;
    }

    public void reset () {
        this.currentPerm = this.digits;
    }
    
    public void advance () {
        // Find first instance of "going down"
        int index = this.currentPerm.size() - 1;
        while (index > 0 && this.currentPerm.get(index - 1) >= this.currentPerm.get(index)) {
            index--;
        }

        int swapIndex = this.currentPerm.size() - 1;
        while (this.currentPerm.get(swapIndex) <= this.currentPerm.get(index - 1)) {
            swapIndex--;
        }

        int temp = this.currentPerm.get(index - 1);
        this.currentPerm.set(index - 1, this.currentPerm.get(swapIndex));
        this.currentPerm.set(swapIndex, temp);
        
        swapIndex = this.currentPerm.size() - 1;
        // Reverse the tail
        while (index < swapIndex) {
            temp = this.currentPerm.get(index);
            this.currentPerm.set(index, this.currentPerm.get(swapIndex));
            this.currentPerm.set(swapIndex, temp);
            index++;
            swapIndex--;
        }
    }

    public static long totalPermutations (int n) {
        if (n == 0) {
            return 1;
        }
        return n * totalPermutations(n - 1);
    }

    public static Permutation randomPermutation (int n) {
        Permutation p = new Permutation(n);

        int[] randPerm = p.toArray();

        for (int i = randPerm.length - 1; i > 0; i--) {
            int temp = randPerm[i];
            int index = (int)Math.floor(Math.random() * i);
            randPerm[i] = randPerm[index];
            randPerm[index] = temp;
        }

        return new Permutation(randPerm);
    }

}