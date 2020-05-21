/* Mohak Mohak - 200425207 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {

    static Scene startPage, mainPage, rulePage, previousWordsPage;

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Scrabble Points Generator");

            // startPage Scene
            Parent root1 = FXMLLoader.load(getClass().getResource("/sample/startPage.fxml"));
            startPage = new Scene(root1);

            // Stylesheet
            startPage.getStylesheets().add(getClass().getResource("/sample/app.css").toExternalForm());

            // mainPage Scene
            Parent root2 = FXMLLoader.load(getClass().getResource("/sample/mainPage.fxml"));
            mainPage = new Scene(root2);

            // rulePage Scene
            Parent root3 = FXMLLoader.load(getClass().getResource("/sample/rulePage.fxml"));
            rulePage = new Scene(root3);

            // PreviousWordsPage Scene
            Parent root4 = FXMLLoader.load(getClass().getResource("/sample/prevWordsPage.fxml"));
            previousWordsPage = new Scene(root4);

            primaryStage.setScene(startPage);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Run Application from here
    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getStartPage() {
        return startPage;
    }

    public static Scene getMainPage() {
        return mainPage;
    }

    public static Scene getRulePage() {
        return rulePage;
    }

    public static Scene getPreviousWordsPage() {
        return previousWordsPage;
    }


}
