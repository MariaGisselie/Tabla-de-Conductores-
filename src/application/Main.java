package application;

import java.util.List;
import Models.DriverResult;
import Models.Season;
import Repositories.DriverResultRepository;
import Repositories.SeasonRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private TableView<DriverResult> table;
    private ComboBox<Season> yearComboBox;
    private DriverResultRepository driverResultRepository;
    private SeasonRepository seasonRepository;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Resultados Conductores");

        // Crear instancias de los repositorios
        driverResultRepository = new DriverResultRepository();
        seasonRepository = new SeasonRepository();

        // Crear columnas
        TableColumn<DriverResult, String> driverNameColumn = new TableColumn<>("DriverName");
        driverNameColumn.setMinWidth(200);
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));

        TableColumn<DriverResult, Integer> winsColumn = new TableColumn<>("Wins");
        winsColumn.setMinWidth(100);
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<DriverResult, Integer> totalPointsColumn = new TableColumn<>("TotalPoints");
        totalPointsColumn.setMinWidth(100);
        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        TableColumn<DriverResult, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setMinWidth(100);
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        // Crear TableView
        table = new TableView<>();
        table.getColumns().addAll(driverNameColumn, winsColumn, totalPointsColumn, rankColumn);

        // Crear ComboBox
        yearComboBox = new ComboBox<>();
        List<Season> seasons = seasonRepository.getSeasons();
        if (seasons != null && !seasons.isEmpty()) {
            ObservableList<Season> observableSeasons = FXCollections.observableArrayList(seasons);
            yearComboBox.setItems(observableSeasons);
            yearComboBox.setConverter(new StringConverter<Season>() {
                @Override
                public String toString(Season season) {
                    return season != null ? String.valueOf(season.getYear()) : ""; // Verificar si season es null
                }

                @Override
                public Season fromString(String string) {
                    // No necesitas implementar este método para ComboBoxes no editables
                    return null;
                }
            });
            yearComboBox.setOnAction(e -> {
                if (yearComboBox.getValue() != null) {
                    updateTable(yearComboBox.getValue().getYear());
                }
            });
        }

        // Layout
        VBox vbox = new VBox();
        vbox.getChildren().addAll(yearComboBox, table);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    private void updateTable(int year) {
        List<DriverResult> results = driverResultRepository.getResultByYear(year);
        ObservableList<DriverResult> observableResults = FXCollections.observableArrayList(results);
        table.setItems(observableResults);
    }
	
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
