class PentagonSolver {
	
	public static void main(String[] args) {
		Permutation p = new Permutation(10);
		long start = System.currentTimeMillis();
        System.out.println("Start: " + System.currentTimeMillis());
		while (!p.isLastPerm()) {
			if (allSidesEqual(p)) {
				System.out.println("Solution: " + p);
				System.out.println("Each side = " + (p.getElement(0) + p.getElement(1) + p.getElement(2)));
			}
			p.advance();
		}
		long end = System.currentTimeMillis();
        System.out.println("End: " + System.currentTimeMillis());
        System.out.println("Total time: " + (end - start));
	}

	public static boolean allSidesEqual(Permutation p) {
		int firstSide = p.getElement(0) + p.getElement(1) + p.getElement(2);
		int secondSide = p.getElement(2) + p.getElement(3) + p.getElement(4);
		int thirdSide = p.getElement(4) + p.getElement(5) + p.getElement(6);
		int fourthSide = p.getElement(6) + p.getElement(7) + p.getElement(8);
		int fifthSide = p.getElement(8) + p.getElement(9) + p.getElement(0);

		return ( (firstSide == secondSide ) && ( secondSide == thirdSide ) && ( thirdSide == fourthSide ) && ( fourthSide == fifthSide ) );

	}
}