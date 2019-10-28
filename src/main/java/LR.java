import com.google.common.math.Stats;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.FastMath;
import org.checkerframework.checker.units.qual.A;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.Arrays.asList;

public class LR {


    //correlation


    // slope


    // intercept


    public static void main(String[] args) {


        List<Integer> data = List.of(1, 2, 3, 4);

        Double average = data
                .stream()
                .collect(Collectors.averagingDouble(x->x));

        System.out.println(average);


        DoubleStream doubleStream = DoubleStream.of(1.2, 1.3, 1.4, 1.5, 1.6);
       // double d = doubleStream.average().orElse(-100);
        double d = doubleStream.summaryStatistics().getAverage();





        // mean
        double mean = data.stream().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println(mean);

        // Variance
        double variance = data.stream()
                .map(i -> i - mean)
                .map(i -> i*i)
                .mapToDouble(i -> i).average().getAsDouble();
        System.out.println(variance);

        //Standard Deviation
        double standardDeviation = Math.sqrt(variance);
        System.out.println(standardDeviation);




        double[] values = new double[] {65, 51 , 16, 11 , 6519, 191 ,0 , 98, 19854, 1, 32};
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (double v : values) {
            descriptiveStatistics.addValue(v);
        }

        double mean1 = descriptiveStatistics.getMean();
        double mean2 = StatUtils.mean(values);
        double std = FastMath.sqrt(StatUtils.variance(values));


        System.out.println(mean2);
        double standardDeviation1 = descriptiveStatistics.getStandardDeviation();

        DoubleStream x = DoubleStream.of(1.2, 1.3, 1.4, 1.5, 1.6);
        DoubleStream y = DoubleStream.of(1.2, 1.3, 1.4, 1.5, 1.6);


        System.out.println("Pearson's Correlation: " + new PearsonsCorrelation().correlation(x.toArray(),y.toArray()));





    }
}


class CorrelationDemo {

    // function that returns correlation coefficient.
    static float correlationCoefficient(int X[],
                                        int Y[], int n)
    {

        int sum_X = 0, sum_Y = 0, sum_XY = 0;
        int squareSum_X = 0, squareSum_Y = 0;

        for (int i = 0; i < n; i++)
        {
            // sum of elements of array X.
            sum_X = sum_X + X[i];

            // sum of elements of array Y.
            sum_Y = sum_Y + Y[i];

            // sum of X[i] * Y[i].
            sum_XY = sum_XY + X[i] * Y[i];

            // sum of square of array elements.
            squareSum_X = squareSum_X + X[i] * X[i];
            squareSum_Y = squareSum_Y + Y[i] * Y[i];
        }

        // use formula for calculating correlation
        // coefficient.
        float corr = (float)(n * sum_XY - sum_X * sum_Y)/
                (float)(Math.sqrt((n * squareSum_X -
                        sum_X * sum_X) * (n * squareSum_Y -
                        sum_Y * sum_Y)));

        return corr;
    }

    // Driver function
    public static void main(String args[])
    {

        int X[] = {15, 18, 21, 24, 27};
        int Y[] = {25, 25, 27, 31, 32};

        // Find the size of array.
        int n = X.length;

        // Function call to correlationCoefficient.
        System.out.printf("%6f",
                correlationCoefficient(X, Y, n));


    }
}

class SR {
    public static void main(String[] args) {

        // X/Y
        // Consecutive hours developer codes -> x
        // Number of bugs produced -> Y

        double[][] data = { { 2, 4 }, {3, 5 }, {5, 7 }, {7, 10 }, {9, 15 }, {11,20 }, {14, 40}};
        SimpleRegression regression = new SimpleRegression();
        regression.addData(data);

        System.out.println(regression.getIntercept());
        // displays intercept of regression line

        System.out.println(regression.getSlope());
        // displays slope of regression line

        // predict number of bugs for 13 hours of work
        System.out.println(regression.predict(13));


    }
}


class MyLinearRegresssion {

    // data
    private ArrayList<Double> XData;
    private ArrayList<Double> YData;

    public MyLinearRegresssion(ArrayList<Double> XData, ArrayList<Double> YData) {

        if (XData.size() != YData.size())
            throw new IllegalStateException("Must have equal X and Y data points");

        this.XData = XData;
        this.YData = YData;
    }

    public Double getXMean () {

        return XData.stream()
                .collect(Collectors.averagingDouble(x->x));
    }

    public Double getYMean () {

        return  YData.stream()
                .collect(Collectors.averagingDouble(x->x));

    }

    public Double getXVariance(){

        return XData.stream()
                .map(i -> i - getXMean())
                .map(i -> i*i)
                .collect(Collectors.averagingDouble(x->x));

    }

    public Double getYVariance(){

        return YData.stream()
                .map(i -> i - getYMean())
                .map(i -> i*i)
                .collect(Collectors.averagingDouble(x->x));

    }

    public Double getXStandardDeviation(){

       return Math.sqrt(getXVariance());

    }

    public Double getYStandardDeviation(){
        return Math.sqrt(getYVariance());
    }

    // correlation

    public Double getCorrelation(){

        int n = XData.size();

        double sum_X = 0.0, sum_Y = 0.0, sum_XY = 0.0;
        double squareSum_X = 0.0, squareSum_Y = 0.0;

        for (int i = 0; i < n; i++)
        {
            // sum of elements of array X.
            sum_X = sum_X + XData.get(i);

            // sum of elements of array Y.
            sum_Y = sum_Y + YData.get(i);

            // sum of X[i] * Y[i].
            sum_XY = sum_XY + XData.get(i) * YData.get(i);

            // sum of square of array elements.
            squareSum_X = squareSum_X + XData.get(i) * XData.get(i);
            squareSum_Y = squareSum_Y + YData.get(i) * YData.get(i);
        }

        // use formula for calculating correlation
        // coefficient.
        double corr = (n * sum_XY - sum_X * sum_Y)/
                        (Math.sqrt((n * squareSum_X -
                        sum_X * sum_X) * (n * squareSum_Y -
                        sum_Y * sum_Y)));

        return corr;

    }

    // slope
    public Double getSlope(){
        // b = r(stdY/stdX) -> r = correlation
        return  getCorrelation()*(getYStandardDeviation()/getXStandardDeviation());
    }

    // intercept

    public Double getIntercept(){
        // a = ymean - slope*xmean


        return  getYMean() - (getSlope()*getXMean());
    }

    // predict
    public Double predict(double value){

        // y = a + bx
        // y -> the predicted value
        // a -> intercept
        // b -> slope

        return  getIntercept() + (getSlope()*value);

    }

    // Demo
    public static void main(String[] args) {

        ArrayList<Double> XData = new ArrayList<>();

        ArrayList<Double> YData = new ArrayList<>();

        /*
            private static final List<Integer> x = asList(2, 3, 5, 7, 9, 11, 14); // Consecutive hours developer codes
    private static final List<Integer> y = asList(4, 5, 7, 10, 15, 20, 40); // Number of bugs produced
         */
        XData.add(2.0);
        XData.add(3.0);
        XData.add(5.0);
        XData.add(7.0);
        XData.add(9.0);
        XData.add(11.0);
        XData.add(14.0);

        YData.add(4.0);
        YData.add(5.0);
        YData.add(7.0);
        YData.add(10.0);
        YData.add(15.0);
        YData.add(20.0);
        YData.add(40.0);

       // DoubleStream.of(15, 18, 21, 24, 27);

        MyLinearRegresssion myLinearRegresssion = new MyLinearRegresssion(XData, YData);
        System.out.println(myLinearRegresssion.getCorrelation());
        System.out.println(myLinearRegresssion.getSlope());
        System.out.println(myLinearRegresssion.getXMean());
        System.out.println(myLinearRegresssion.getYMean());
        System.out.println(myLinearRegresssion.getXVariance());
        System.out.println(myLinearRegresssion.getYVariance());
        System.out.println(myLinearRegresssion.getXStandardDeviation());
        System.out.println(myLinearRegresssion.getYStandardDeviation());
        System.out.println(myLinearRegresssion.getIntercept());
        System.out.println(myLinearRegresssion.predict(13.0));


    }
}