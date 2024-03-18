public abstract class Sortinger <T extends Comparable> {
    abstract void sort(T[] arr);
    void sortWithTime(T[] arr){
        long startTime = System.currentTimeMillis();
        this.sort(arr);
        System.out.println("Time: " + (System.currentTimeMillis()-startTime));
    }
}
