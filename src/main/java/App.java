import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import view.InterfazInicio;



public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        InterfazInicio interfazInicio = new InterfazInicio(controller);
        interfazInicio.start(primaryStage);

    }
    //BUG: Actualmente no corre por Error: JavaFX runtime components are missing, and are required to run this application
}
