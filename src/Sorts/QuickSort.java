package Sorts;

public class QuickSort {

    public static void quickSort(int[] A){
        quickSort(A, 0, A.length-1);
    }

    public static void randomizedQuickSort(int[] A){
       randomizedQuickSort(A, 0, A.length-1);
    }

    public static void quickSort(int[] A, int p, int r){

        if (p < r){
            int q = partition(A,p,r);
            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }
    }

    public static void randomizedQuickSort(int[] A, int p, int r){

        if (p < r){
            int q = randomizedPartition(A,p,r);
            randomizedQuickSort(A, p, q-1);
            randomizedQuickSort(A, q+1, r);
        }
    }

    public static int partition(int[] A, int p, int r){
        int i = p-1;
        for(int  j = p;j < r;j++){
            if(A[j] <= A[r]){
                i++;
                swap(A, i, j);
            }
        }
        swap(A, ++i, r);
        return i;
    }

    public static int randomizedPartition(int[] A, int p, int r){
        int i = ((int)(Math.random()*(r-p)))+p;
        swap(A, i, r);
        return partition(A, p, r);
    }

    private static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
