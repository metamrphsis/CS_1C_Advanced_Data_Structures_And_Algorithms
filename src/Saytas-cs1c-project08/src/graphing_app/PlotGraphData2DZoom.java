package graphing_app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import utilities.GetGraphData;

/**
 * The PlotGraphData2DZoom class plots the data that is
 * read via GetGraphData class and zooms in within a
 * certain range
 *
 * @author Bita Mazloom, Selahittin Saytaş
 */
public class PlotGraphData2DZoom extends Application
{
    /**
     * The main method refers to array of the
     * type String by the name args this will
     * be used as input during usage of command
     * line argument
     * @param args  An array of String objects
     *              contains the supplied
     *              command-line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * The method is a main entry point for JavaFX
     * applications. JavaFX application starts up,
     * it creates a root Stage object which is passed
     * to the start(Stage primaryStage) method of the
     * root class of your JavaFX application
     * @param stage A top-level container that hosts
     *              a Scene, which consists of visual
     *              elements
     */
    @Override
    public void start(Stage stage)
    {
        GetGraphData graphData = new GetGraphData();
        stage.setTitle("Recursion Limit");

        // Defining the axis
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Recursion Limit");
        yAxis.setLabel("Run Time(s) - 1000s");

        // Creating the chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Recursion Limit vs. Run Time");
        Scene scene = new Scene(lineChart, 2000, 1000);

        // Defining the series
        for(int i = 12; i < graphData.getNumPlots(); i++)
        {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Quick Sort Data: " + i);

            // Populating the series with data
            for(int j = 0; j < 80; j++)
            {
//                int temp = graphData.getData()[i][j] - 1000;
//                series.getData().add(new XYChart.Data(graphData.getLimit()[j], temp));
                series.getData().add(new XYChart.Data(graphData.getLimit()[j], graphData.getData()[i][j]));
            }

            lineChart.getData().add(series);
        }

        stage.setScene(scene);
        stage.show();
    }
}