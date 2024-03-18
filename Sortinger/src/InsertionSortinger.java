public class InsertionSortinger<T extends Comparable> extends Sortinger<T> {
    @Override
    void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(current) == 1) {
                arr[j + 1] = arr[j];
                j--;
            }
            // в этой точке мы вышли, так что j так же -1 
            // или в первом элементе, где текущий >= a[j]
            arr[j + 1] = current;
        }
    }
}
