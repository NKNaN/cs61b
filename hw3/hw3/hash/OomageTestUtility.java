package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        for (int i = 0; i < M; i++) {
            bucket[i] = 0;
        }
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum]++;
        }
        int N = oomages.size();
        int min = bucket[0];
        int max = bucket[0];
        for (int i = 1; i < M; i++) {
            if (bucket[i] > max) {
                max = bucket[i];
            }
            if (bucket[i] < min) {
                min = bucket[i];
            }
        }
//        System.out.println(min);
//        System.out.println(max);
        return (double) min >= N / 50.0 && (double) max <= N / 2.5;
    }
}
