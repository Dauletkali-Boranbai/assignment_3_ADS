class Experiment {

    private Sorter sorter;
    private Searcher searcher;

    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }

    long measureSortTime(int[] arr, String type) {
        long start = System.nanoTime();

        if (type.equals("basic")) {
            sorter.basicSort(arr);
        } else {
            sorter.advancedSort(arr);
        }

        long end = System.nanoTime();
        return end - start;
    }

    long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();

        searcher.search(arr, target);

        long end = System.nanoTime();
        return end - start;
    }

    void runAllExperiments() {

        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {

            int[] randomArr = sorter.generateRandomArray(size);

            int[] sortedArr = randomArr.clone();
            sorter.basicSort(sortedArr);

            int target = randomArr[size / 2];

            long basicTimeRandom = measureSortTime(randomArr.clone(), "basic");
            long advancedTimeRandom = measureSortTime(randomArr.clone(), "advanced");

            long basicTimeSorted = measureSortTime(sortedArr.clone(), "basic");
            long advancedTimeSorted = measureSortTime(sortedArr.clone(), "advanced");

            long searchTimeRandom = measureSearchTime(randomArr, target);
            long searchTimeSorted = measureSearchTime(sortedArr, target);

            System.out.println("=== SIZE: " + size + " ===");

            System.out.println("Random Array:");
            System.out.println("Basic Sort: " + basicTimeRandom);
            System.out.println("Advanced Sort: " + advancedTimeRandom);
            System.out.println("Search: " + searchTimeRandom);

            System.out.println("Sorted Array:");
            System.out.println("Basic Sort: " + basicTimeSorted);
            System.out.println("Advanced Sort: " + advancedTimeSorted);
            System.out.println("Search: " + searchTimeSorted);

            if (basicTimeRandom > advancedTimeRandom) {
                System.out.println("Quick Sort is faster on random data.");
            } else {
                System.out.println("Bubble Sort is faster on random data.");
            }

            System.out.println("----------------------------");
        }
    }
}
