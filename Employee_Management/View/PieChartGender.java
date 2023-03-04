/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author duy
 */
import Controller.EmployeeDAO;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PieChartGender extends ApplicationFrame {

    public PieChartGender(String title) throws ClassNotFoundException, SQLException {
        super(title);

        // Create a dataset for the pie chart
        DefaultPieDataset dataset = new DefaultPieDataset();
        EmployeeDAO emp = new EmployeeDAO();

        int m = emp.getNumberOfMale();
        int f = emp.getNumberOfFemale();
        dataset.setValue("Male", m);
        dataset.setValue("Female", f);

        // Create the pie chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Gender", // chart title
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
        );

        // Customize the pie chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelGenerator(null);

        // Add the pie chart to a panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        JFrame j = new JFrame();
        j.add(panel);
        j.setVisible(true);
        j.setSize(480, 346);
        j.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    }
}
