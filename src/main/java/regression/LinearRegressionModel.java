package regression;

/**
 * A RegressionModel that fits a straight line to a data set
 */
public class LinearRegressionModel extends RegressionModel {

    // The y intercept of the straight line
    private double a;

    // The gradient of the line
    private double b;

    /**
     * Construct a new LinearRegressionModel with the supplied data set
     *
     * @param x
     * The x data points
     * @param y
     * The y data points
     */
    public LinearRegressionModel(double[] x, double[] y) {
        super(x, y);
        a = b = 0;
    }

    /**
     * Get the coefficents of the fitted straight line
     *
     * @return An array of coefficients {intercept, gradient}
     *
     * @see RegressionModel#getCoefficients()
     */
    @Override
    public double[] getCoefficients() {
        if (!computed)
            throw new IllegalStateException("Model has not yet computed");

        return new double[] { a, b };
    }

    /**
     * Compute the coefficients of a straight line the best fits the data set
     *
     * @see RegressionModel#compute()
     */
    @Override
    public void compute() {

        // throws exception if regression can not be performed
        if (xValues.length < 2 | yValues.length < 2) {
            throw new IllegalArgumentException("Must have more than two values");
        }

        // get the value of the gradient using the formula b = cov[x,y] / var[x]
        b = MathUtils.covariance(xValues, yValues) / MathUtils.variance(xValues);

        // get the value of the y-intercept using the formula a = ybar + b \* xbar
        a = MathUtils.mean(yValues) - b * MathUtils.mean(xValues);

        // set the computed flag to true after we have calculated the coefficients
        computed = true;
    }

    /**
     * Evaluate the computed model at a certain point
     *
     * @param x The point to evaluate at
     * @return The value of the fitted straight line at the point x
     * @see RegressionModel#evaluateAt(double)
     */
    @Override
    public double evaluateAt(double x) {
        if (!computed)
            throw new IllegalStateException("Model has not yet computed");
        return a + b * x;
    }
}