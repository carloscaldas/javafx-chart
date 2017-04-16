package leituradados;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application implements Listener {
	final DataCollector collector = new DataCollector(this);
	final XYChart.Series series = criarSerie();

	private XYChart.Series criarSerie() {
		XYChart.Series result = new XYChart.Series();
		result.setName("Aqui você define o nome da série");
		return result;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Leitura de XYZ...");

		BorderPane border = new BorderPane();
		border.setTop(criarBarraBotoes());
		border.setCenter(criarGrafico());
		
		primaryStage.setScene(new Scene(border, 800, 600));
		
		primaryStage.show();
	}

	private HBox criarBarraBotoes() {
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    hbox.getChildren().addAll(criarBotaoIniciar(), criarBotaoParar());
	    return hbox;
	}

	private Button criarBotaoParar() {
		Button btn = new Button("Parar");
	    btn.setPrefSize(100, 20);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				collector.stop();
			}
		});
		return btn;
	}

	private Button criarBotaoIniciar() {
		Button btn = new Button("Iniciar");
	    btn.setPrefSize(100, 20);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				collector.start();
			}
		});
		return btn;
	}
	
	private LineChart<Number, Number> criarGrafico() {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Tempo");
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Aqui você define o nome do gráfico");

		lineChart.getData().add(series);
		return lineChart;
	}

	private void iniciarLeitura() {
		collector.start();
	}

	@Override
	public void receiveData(Point p) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				series.getData().add(new XYChart.Data(p.getX(), p.getY()));
			}
		});
	}
}