package aspguidp;

import aspguidp.gui.window.WindowSizeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        WindowSizeManager.setPrimaryStage(primaryStage);

        // setup primary stage of application
        primaryStage.setTitle("Project Planning by Niklas Natter");
        primaryStage.getIcons().add(new Image("/icon/aspguidp_192.png"));
        primaryStage.getIcons().add(new Image("/icon/aspguidp_96.png"));
        primaryStage.getIcons().add(new Image("/icon/aspguidp_48.png"));
        primaryStage.setWidth(800);

        // load content for primary stage and show stage
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/application.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // update min window size to the current content
        WindowSizeManager.updateMinWindowSize();
    }

    /**
     * Entry point of the program, which starts the javafx graphical user interface application.
     *
     * @param args program arguments which were passed to the program on execution
     */
    public static void main(String[] args) {
        launch(args);
    }
}
