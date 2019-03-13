import java.util.Random;
import java.util.Arrays;

public class VDriver{
    public static void main(String[]args){

        //TEST RUNTIME
        
        int[]ary = { 2, 10, 15, 23, 0,  5, 1, 3, -1, -1 ,-1 , 2, 3, 4, 1, 4,5, 10, 199, -19372};  //sorted :  {0,2,5,10,15,23}
        /*System.out.println(Quick.quickselect( ary , 0 )); //would return 0
        System.out.println(Quick.quickselect( ary , 1 ));  //would return 2
        System.out.println(Quick.quickselect( ary , 2 ));  //would return 5
        System.out.println(Quick.quickselect( ary , 3 ));  //would return 10
        System.out.println(Quick.quickselect( ary , 4 )); // would return 15
        System.out.println(Quick.quickselect( ary , 5 ));  //would return 23*/
        long[] arytimes = sortTimes(ary);
        System.out.println(arytimes[0]);
        System.out.println(arytimes[1]);
        

        for (int i = 1; i < 1000; i++) {
            try {
                Random gen = new Random();
                int[] test = new int[i];
                int[] duplicate = new int[i];
                for (int j = 0; j < test.length; j++) {
                    int randGenInt = gen.nextInt() % 1000;
                    test[j] = randGenInt;
                    duplicate[j] = randGenInt;
                }
                Quick.quicksort(test);
                if (!hasSortedRight(test, duplicate)) {
                    //System.out.println("test: "+Arrays.toString(test));
                    System.out.print("FAILURE ON LENGTH " + i);
                    System.exit(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("FAILURE ON LENGTH " + i);
                System.exit(1);
            }
            //System.out.println("--------");
        }
        System.out.println("R: SUCCESS"); //R for random

        
        Random why = new Random();
        int[] ugh = new int[10000];
        int[] duplicate1 = new int[10000];
        for (int i = 0; i < ugh.length; i++){
            int randGenInt = Math.abs(why.nextInt(2));
            ugh[i] = randGenInt;
            duplicate1[i] = randGenInt;
        }
        Quick.quicksort(ugh);
        System.out.println(hasSortedRight(ugh, duplicate1));

        Random why2 = new Random();
        int[] ugh2 = new int[10000];
        int[] duplicate2 = new int[10000];
        for (int i = 0; i < ugh2.length; i++){
            int randGenInt = Math.abs(why2.nextInt(1));
            ugh2[i] = randGenInt;
            duplicate2[i] = randGenInt;
        }
        Quick.quicksort(ugh2);
        System.out.println(hasSortedRight(ugh2, duplicate2));

        
        for (int i = 1; i < 5000; i++) {
            try {
                Random gen = new Random();
                int[] test = new int[i];
                int[] duplicate = new int[i];
                for (int j = 0; j < test.length; j++) {
                    int randGenInt = gen.nextInt() % 1000;
                    test[j] = randGenInt;
                    duplicate[j] = randGenInt;
                }
                long[] sortTimesAry = sortTimes(test);
                long quickDiffMillis = sortTimesAry[0];
                long selectionDiffMillis = sortTimesAry[1];
                if (sortTimesAry[1] < sortTimesAry[0]){
                    System.out.println("selection is "+((quickDiffMillis - selectionDiffMillis))+" milliseconds faster");
                    System.out.println("SLOWER ON LENGTH " + i);
                    System.out.println("--------------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("FAILURE ON LENGTH " + i);
            }
        }
        
    }

    public static boolean hasSortedRight(int[] test, int[] duplicate){
        selectionSort(duplicate);
        for (int i = 0; i < test.length; i++){
            if (test[i] != duplicate[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean test(int[] data, int k, int kthElement){
        for (int i = 0; i < data.length; i++){
            if (i < k){
                if (data[i] > kthElement){
                    return false;
                }
            }
            else if (i > k){
                if (data[i] < kthElement){
                    return false;
                }
            }
        }
        return true;
    }

    public static void selectionSort(int [] ary) { //O(n^2); n passes with n work each
        int min;
        int indexOfMin = -1;
        int storer;
        for (int i = 0; i < ary.length-1; i++){
            min = ary[i];
            indexOfMin = i;
            //for loop has "i+1" so that the "first value" being iterated through doesn't
            //include the already established values to be minimums
            for (int n = i+1; n < ary.length; n++){
                if (ary[n] < min){
                    min = ary[n];
                    indexOfMin = n;
                }
            }
            //swaps the minimum and the "first" value
            storer = ary[i];
            ary[i] = min;
            ary[indexOfMin] = storer;
        }
    }

    //[0] is quicksort time, [1] is selectionsort time
    public static long[] sortTimes(int[] test){
        int[] duplicate = new int[test.length];
        for (int i = 0; i < test.length; i++){
            duplicate[i] = test[i];
        }
        long quickStartMillis = System.currentTimeMillis();
        Quick.quicksort(test);
        long quickEndMillis = System.currentTimeMillis();
        long quickDiffMillis = (quickEndMillis - quickStartMillis) / 1000;
        long selectionStartMillis = System.currentTimeMillis();
        selectionSort(duplicate);
        long selectionEndMillis = System.currentTimeMillis();
        long selectionDiffMillis = (selectionEndMillis - selectionStartMillis) / 1000;
        long[] sortTimes = new long[]{quickDiffMillis, selectionDiffMillis};
        return sortTimes;
    }
}