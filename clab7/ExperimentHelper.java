import java.util.Random;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    private static int completeOIPL(int height) {
        if (height == 0) { return 0; }
        int sum = 0;
        for (int i = 0; i < Math.pow(2, height); i++) {
            sum += height;
        }
        return sum + completeOIPL(height - 1);
    }
    public static int optimalIPL(int N) {
        int height = (int) Math.floor(Math.log(N) / Math.log(2));
        if (height == 0) { return 0; }
        int sum = completeOIPL(height - 1);
        return sum + height * (N - (int) Math.pow(2, height) + 1);
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void randomInsert(BST<Double> b) {
        Random r = new Random();
        double item = r.nextDouble();
        while (b.contains(item)) {
            item = r.nextDouble();
        }
        b.add(item);
    }

    public static void randomDeleteTakingSuccessor(BST<Double> b) {
        double key = b.getRandomKey();
        b.deleteTakingSuccessor(key);
    }

    public static void randomDeleteTakingRandom(BST<Double> b) {
        double key = b.getRandomKey();
        b.deleteTakingRandom(key);
    }

//    public static void main(String[] args) {
//        System.out.println(ExperimentHelper.optimalIPL(1));
//        System.out.println(ExperimentHelper.optimalIPL(2));
//        System.out.println(ExperimentHelper.optimalIPL(3));
//        System.out.println(ExperimentHelper.optimalIPL(4));
//        System.out.println(ExperimentHelper.optimalIPL(5));
//        System.out.println(ExperimentHelper.optimalIPL(6));
//        System.out.println(ExperimentHelper.optimalIPL(7));
//        System.out.println(ExperimentHelper.optimalIPL(8));
//        System.out.println(ExperimentHelper.optimalIPL(9));
//        System.out.println(ExperimentHelper.optimalAverageDepth(1));
//        System.out.println(ExperimentHelper.optimalAverageDepth(5));
//        System.out.println(ExperimentHelper.optimalAverageDepth(8));
//    }
}
