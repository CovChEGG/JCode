public class heapSort {
    public static void main(String[] args) {
        int sizeOfArray = 10,
                minRandNum = -1000,
                maxRandNum = 2000;
        int[] intArray = Gen(sizeOfArray, minRandNum, maxRandNum);
        System.out.print("\033[H\033[J");
        System.out.printf("Initialized array of %d random elements from %d to %d\n",
                sizeOfArray, minRandNum, maxRandNum);
        PrintArray(intArray);
        heapSorting(intArray, sizeOfArray);
        System.out.println("Sorted array from heap:");
        PrintArray(intArray);
    }

    public static void heapSorting(int array[], int heapSize) {
        for (int rootIndex = heapSize / 2 - 1; rootIndex >= 0; rootIndex--) {
            heapify(array, heapSize, rootIndex);
        }
        System.out.println("Heapified array:");
        PrintArray(array);
        for (heapSize--; heapSize >= 0; heapSize--) {
            swap(array, heapSize, 0);
            heapify(array, heapSize, 0);
        }
    }

    public static void heapify(int[] array, int heapSize, int rootIndex) {
        int biggest = rootIndex;
        int leftPart = 2 * rootIndex + 1;
        int rightPart = leftPart + 1;
        if (leftPart < heapSize && array[leftPart] > array[biggest]) {
            biggest = leftPart;
        }
        if (rightPart < heapSize && array[rightPart] > array[biggest]) {
            biggest = rightPart;
        }
        if (biggest != rootIndex) {
            swap(array, rootIndex, biggest);
            heapify(array, heapSize, biggest);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void PrintArray(int[] array) {
        for (int i : array) {
            System.out.printf("%d ", i);
        }
        System.out.printf("\n\n");
    }

    public static int[] Gen(int len, int min, int max) {
        int[] res = new int[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * (max - min + 2) + min - 1);
        }
        return res;
    }
}
