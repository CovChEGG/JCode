import java.util.Arrays;

public class mergeSort {
    public static void main(String[] args) {
        int sizeOfArray = 10,
            minRandNum = -1000,
            maxRandNum = 2000;     
        int[] intArray = Gen(sizeOfArray, minRandNum, maxRandNum);
        System.out.print("\033[H\033[J");
        System.out.printf("Initialized array of %d random elements from %d to %d\n",
                            sizeOfArray, minRandNum, maxRandNum);
        PrintArray(intArray);
        Merging(intArray, 0, intArray.length-1);
        System.out.println("Sorted array by merge:");
        PrintArray(intArray);
    }

    public static int[] Gen(int len, int min, int max) {
        int[] res = new int[len];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int)(Math.random()*(max - min + 2) + min - 1);
        }        
        return res;
    }

    public static void Merging(int[] array, int first, int last) {

        if (last <= first)
            return;
        int middle = first + (last - first) / 2;
        Merging(array, first, middle);
        Merging(array, middle + 1, last);

        int[] buf = Arrays.copyOf(array, array.length);
    
        for (int k = first; k <= last; k++)
            buf[k] = array[k];
    
        int i = first, j = middle + 1;
        for (int k = first; k <= last; k++) {
    
            if (i > middle) {
                array[k] = buf[j];
                j++;
            } else if (j > last) {
                array[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                array[k] = buf[j];
                j++;
            } else {
                array[k] = buf[i];
                i++;
            } 
        }
    }

    public static void PrintArray(int[] array) {
        for (int i : array) {
            System.out.printf("%d ", i);
        }
        System.out.printf("\n\n");
    }
}