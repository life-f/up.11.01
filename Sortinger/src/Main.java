import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[10000];
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            arr[i] = rand.nextInt(1000000);
        }
//        System.out.println(Arrays.toString(arr));
        BubbleSortinger bubbleSortinger = new BubbleSortinger();
        bubbleSortinger.sortWithTime(arr.clone());

        InsertionSortinger insertionSortinger = new InsertionSortinger();
        insertionSortinger.sortWithTime(arr.clone());

        SelectionSortinger selectionSortinger = new SelectionSortinger();
        selectionSortinger.sortWithTime(arr.clone());
    }
}
