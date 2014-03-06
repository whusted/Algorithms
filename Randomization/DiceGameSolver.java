import java.util.ArrayList;

class DiceGameSolver {
  public static void main (String[] args) {
    Die[] diceA = new Die[6];
    Die[] diceB = new Die[9];
    for (int i = 0; i < 6; i++) {
        diceA[i] = new Die(6);
    }
    
    for (int i = 0; i < 9; i++) {
        diceB[i] = new Die(4);
    }


    int playerASum = 0;
    int playerBSum = 0;
    double trials = 1000.0;
    double probability;
    double aWins = 0;
   

    for (int i = 0; i < trials; i++) {
      if (diceSum(diceA) > diceSum(diceB)) {
        aWins++;
      }
    }
    probability = aWins / trials;
    System.out.println("Probablitly A Wins in " + trials + " trials: " + probability);
  }

  public static double diceSum (Die[] dice) {
    double sum = 0;
      for (int j = 0; j < dice.length; j++) {
        sum += dice[j].roll();
      }
    return sum;
  }

  static class Die {
    private int sides;
    public Die (int n) {
      this.sides = n;
    }

    public double roll () {
      return Math.floor(Math.random() * this.sides + 1);
    }

  }

}