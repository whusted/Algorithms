class FibRecur {
	public static void main(String[] args) {
        long seconds1 = System.currentTimeMillis();
        System.out.println(fib(48));
        long seconds2 = System.currentTimeMillis();
        System.out.println(seconds2 - seconds1);

	}

    public static long fib(long n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}