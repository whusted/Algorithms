import java.util.ArrayList;

class KnapsackSolver {

    // static structures required in tableSolver method
	static Item[] items;
    static Knapsack[][] table;
	public static void main (String[] args) {

        //Data sanitization
        if (args.length < 3 || args.length % 2 == 0) {
            throw new IllegalArgumentException();
        }

		int capacity = Integer.parseInt(args[args.length - 1]);
         
        items = new Item[1 + (args.length - 1) / 2];
    
        int j = 1;
		for (int i = 0; i < args.length - 1; i += 2) {
            items[j] = new Item(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1]));
            j++;
		}
        // 2D array of Knapsacks
        table = new Knapsack[items.length][capacity + 1];

        // Fill first row and column with empty Knapsacks
        for (int i = 0; i < items.length; i++) {
            table[i][0] = new Knapsack();
        }

        for (int i = 0; i < capacity + 1; i++) {
            table[0][i] = new Knapsack();
        }
        
        Knapsack result = tableSolver(items.length - 1, capacity);

        // print out result in a pretty way
        String stringedResult = "";
        for (int i = 0; i < result.getInventory().size(); i++) {
            stringedResult += "Take the " + result.getInventory().get(i).getWeight() + " pound item: $" + result.getInventory().get(i).getPrice() + " \n";
        }
        stringedResult += "********************\n" + "Totals: " + result.getWeightTotal() + " pounds, $" + result.getPriceTotal();

        System.out.println(stringedResult);
	}

    public static Knapsack tableSolver (int row, int column) {
        if (table[row][column] != null) {

            return table[row][column];
        }

        if (column - items[row].getWeight() < 0) {

            table[row][column] = tableSolver(row - 1, column).copy(); // copy of next row down knapsack

            return table[row][column];
        }

        if (tableSolver(row - 1, column).getPriceTotal() > tableSolver(row - 1, column - items[row].getWeight()).copy().addItem(items[row]).getPriceTotal()) {
            table[row][column] = tableSolver(row - 1, column).copy();
        } else {
            table[row][column] = tableSolver(row - 1, column - items[row].getWeight()).copy().addItem(items[row]);
        }

        return table[row][column];

    }

    static class Knapsack {
        private ArrayList <Item> inventory = new ArrayList<Item>();
        private int priceTotal;
        private int weightTotal;
        
        public Knapsack () {
            this.priceTotal = 0;
            this.weightTotal = 0;
        }

        public Knapsack (ArrayList<Item> inventory, int p, int w) {
            this.inventory = inventory;
            this.priceTotal = p;
            this.weightTotal = w;

        }

        public ArrayList<Item> getInventory () {
            return this.inventory;
        }

        public int getWeightTotal () {
            return this.weightTotal;
        }

        public int getPriceTotal () {
            return this.priceTotal;
        }

        public void setWeightTotal (int w) {
            this.weightTotal = w;
        }

        public void setPriceTotal (int p) {
            this.priceTotal = p;
        }

        public Knapsack addItem (Item i) {
            if (!(inventory.contains(i))) {
                inventory.add(i);
            }

            this.priceTotal += i.getPrice();
            this.weightTotal += i.getWeight();

            return this;
        }

        public Knapsack copy () {
            return new Knapsack(this.inventory, this.priceTotal, this.weightTotal);
        }

    }

    static class Item {
        private int price;
        private int weight;

        public Item (int price, int weight) {
            this.price = price;
            this.weight = weight;
        }

        public int getWeight () {
            return this.weight;
        }


        public int getPrice () {
            return this.price;
        }

        public void setWeight (int w) {
            this.weight = w;
        }

        public void setPrice (int p) {
            this.weight = p;
        }
    }


}