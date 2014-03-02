class FibIter {
    public static void main(String[] args) {
        long seconds1 = System.currentTimeMillis();
        System.out.println(fib(48));
        long seconds2 = System.currentTimeMillis();
        System.out.println(seconds2 - seconds1);
    }

    public static long fib(int k) {
        int[] solns = new int[1 + k];
        solns[0] = 0;
        solns[1] = 1;
        for (int i = 2; i < 1 + k; i++) {
            solns[i] = solns[i - 2] + solns[i - 1];
        }

        return solns[k];
    }
}