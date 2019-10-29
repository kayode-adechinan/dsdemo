package morpheusdemo;

import com.zavtech.morpheus.array.Array;
import com.zavtech.morpheus.frame.DataFrame;
import com.zavtech.morpheus.range.Range;
import com.zavtech.morpheus.util.text.parser.Parser;
import com.zavtech.morpheus.viz.chart.Chart;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Year;

public class GettingStartedWithRegression {


    public static void main(String[] args) {





      /*  DataFrame<String,String> frame = DataFrame.ofDoubles(
                Array.of("AUS", "GBR", "USA", "DEU", "ITA", "ESP", "ZAF"),
                Array.of("Random"),
                value -> Math.random() * 10d
        );


        Chart.create().withPiePlot(frame, false, chart -> {
            chart.title().withText("Pie Chart of Random Data");
            chart.subtitle().withText("Labels with Section Percent");
            chart.legend().on().right();
            chart.show();
        });
*/

      /*  final String y = "Horsepower";
        final String x = "EngineSize";
        final DataFrame<Integer,String> frame = loadCarDataset();
        final DataFrame<Integer,String> xy = frame.cols().select(y, x);
        Chart.create().withScatterPlot(xy, false, x, chart -> {
            chart.title().withText(y + " vs " + x);
            chart.plot().style(y).withColor(Color.RED);
            chart.plot().style(y).withPointsVisible(true).withPointShape(ChartShape.DIAMOND);
            chart.plot().axes().domain().label().withText(x);
            chart.plot().axes().domain().format().withPattern("0.00;-0.00");
            chart.plot().axes().range(0).label().withText(y);
            chart.plot().axes().range(0).format().withPattern("0;-0");
            chart.show(845, 450);
        });*/

        //Create a data frame to capture the median prices of Apartments in the UK'a largest cities
        DataFrame<Year,String> results = DataFrame.ofDoubles(
                Range.of(2013, 2015).map(Year::of),
                Array.of("LONDON", "BIRMINGHAM", "SHEFFIELD", "LEEDS", "LIVERPOOL", "MANCHESTER")
        );

//Process yearly data in parallel to leverage all CPU cores
        results.rows().keys().parallel().forEach(year -> {
            System.out.printf("Loading UK house prices for %s...\n", year);
            DataFrame<Integer,String> prices = loadHousePrices(year);
            prices.rows().select(row -> {
                //Filter rows to include only apartments in the relevant cities
                final String propType = row.getValue("PropertyType");
                final String city = row.getValue("City");
                final String cityUpperCase = city != null ? city.toUpperCase() : null;
                return propType != null && propType.equals("F") && results.cols().contains(cityUpperCase);
            }).rows().groupBy("City").forEach(0, (groupKey, group) -> {
                //Group row filtered frame so we can compute median prices in selected cities
                final String city = groupKey.item(0);
                final double priceStat = group.colAt("PricePaid").stats().median();
                results.data().setDouble(year, city, priceStat);
            });
        });

//Map row keys to LocalDates, and map values to be percentage changes from start date
        final DataFrame<LocalDate,String> plotFrame = results.mapToDoubles(v -> {
            final double firstValue = v.col().getDouble(0);
            final double currentValue = v.getDouble();
            return (currentValue / firstValue - 1d) * 100d;
        }).rows().mapKeys(row -> {
            final Year year = row.key();
            return LocalDate.of(year.getValue(), 12, 31);
        });

//Create a plot, and display it
        Chart.create().withLinePlot(plotFrame, chart -> {
            chart.title().withText("Median Nominal House Price Changes");
            chart.title().withFont(new Font("Arial", Font.BOLD, 14));
            chart.subtitle().withText("Date Range: 2013 - 2014");
            chart.plot().axes().domain().label().withText("Year");
            chart.plot().axes().range(0).label().withText("Percent Change from 1995");
            chart.plot().axes().range(0).format().withPattern("0.##'%';-0.##'%'");
            chart.plot().style("LONDON").withColor(Color.BLACK);
            chart.legend().on().bottom();
            chart.show();
        });


    }


    static DataFrame<Integer,String> loadCarDataset() {
        return DataFrame.read().csv(options -> {
            options.setResource("http://zavtech.com/data/samples/cars93.csv");
            options.setExcludeColumnIndexes(0);
        });
    }


    /**
     * Loads UK house price from the Land Registry stored in an Amazon S3 bucket
     * Note the data does not have a header, so columns will be named Column-0, Column-1 etc...
     * @param year      the year for which to load prices
     * @return          the resulting DataFrame, with some columns renamed
     */
    private static DataFrame<Integer,String> loadHousePrices(Year year) {
        String resource = "http://prod.publicdata.landregistry.gov.uk.s3-website-eu-west-1.amazonaws.com/pp-%s.csv";
        return DataFrame.read().csv(options -> {
            options.setResource(String.format(resource, year.getValue()));
            options.setHeader(false);
            options.setCharset(StandardCharsets.UTF_8);
            options.setIncludeColumnIndexes(1, 2, 4, 11);
            options.getFormats().setParser("TransactDate", Parser.ofLocalDate("yyyy-MM-dd HH:mm"));
            options.setColumnNameMapping((colName, colOrdinal) -> {
                switch (colOrdinal) {
                    case 0:     return "PricePaid";
                    case 1:     return "TransactDate";
                    case 2:     return "PropertyType";
                    case 3:     return "City";
                    default:    return colName;
                }
            });
        });
    }
}
