import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Integer> a = new BST<>();
        List<Integer> x = new ArrayList<>();
        List<Double> avgDepthBST = new ArrayList<>();
        List<Double> optAvgDepth = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            a.add(r.nextInt(5000));
            x.add(i + 1);
            avgDepthBST.add(a.averageDepth());
            optAvgDepth.add(ExperimentHelper.optimalAverageDepth(i + 1));
        }

        XYChart chart = new XYChartBuilder().width(1600).height(1200).xAxisTitle("# of items").yAxisTitle("y label").build();
        chart.addSeries("Average depth of random BST", x, avgDepthBST);
        chart.addSeries("Average depth of optimal BST", x, optAvgDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        int N = 1000;
        int M = 1000000;
        BST<Double> a = new BST<>();
        Random r = new Random();
        // 1.
        for (int i = 0; i < N; i++) {
            a.add(r.nextDouble());
        }
        double startingDepth = a.averageDepth();
        List<Integer> x = new ArrayList<>();
        List<Double> avgDepth = new ArrayList<>();
        List<Double> startDepth = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            ExperimentHelper.randomDeleteTakingSuccessor(a);
            ExperimentHelper.randomInsert(a);
            x.add(i + 1);
            avgDepth.add(a.averageDepth());
            startDepth.add(startingDepth);
        }

        XYChart chart = new XYChartBuilder().width(1600).height(1200).xAxisTitle("# of operations").yAxisTitle("y label").build();
        chart.addSeries("Average depth ex2", x, avgDepth);
        chart.addSeries("Starting depth" , x, startDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        int N = 1000;
        int M = 1000000;
        BST<Double> a = new BST<>();
        Random r = new Random();
        // 1.
        for (int i = 0; i < N; i++) {
            a.add(r.nextDouble());
        }
        double startingDepth = a.averageDepth();
        List<Integer> x = new ArrayList<>();
        List<Double> avgDepth = new ArrayList<>();
        List<Double> startDepth = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            ExperimentHelper.randomDeleteTakingRandom(a);
            ExperimentHelper.randomInsert(a);
            x.add(i + 1);
            avgDepth.add(a.averageDepth());
            startDepth.add(startingDepth);
        }

        XYChart chart = new XYChartBuilder().width(1600).height(1200).xAxisTitle("# of operations").yAxisTitle("y label").build();
        chart.addSeries("Average depth ex3", x, avgDepth);
        chart.addSeries("Starting depth" , x, startDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();
    }
}
