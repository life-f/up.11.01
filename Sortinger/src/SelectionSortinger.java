public class SelectionSortinger<T extends Comparable> extends Sortinger<T> {

    @Override
    void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            T min = arr[i];
            int minId = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min.compareTo(arr[j]) == 1) {
                    min = arr[j];
                    minId = j;
                }
            }
            // замена
            T temp = arr[i];
            arr[i] = min;
            arr[minId] = temp;
        }
    }
}
