package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class MainController implements Initializable {

    private static Model model=new Model();


    @FXML
    private ListView<String> wordList=new ListView<>();
    @FXML
    private ListView<String> ruleList=new ListView<>();
    @FXML
    private ListView<String> pointList=new ListView<>();
    @FXML
    private TextField word;
    @FXML
    private Label status;
    @FXML
    private Label error;
    @FXML
    private ListView<String> pointList2=new ListView<>();
    @FXML
    private Label totalPoints;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        wordList.setItems(model.getWordList());
        ruleList.setItems(model.getRulesSet());
        pointList.setItems(model.getCountList());
        pointList2.setItems(model.getPointTable());

    }


    public void MainPage(ActionEvent event) throws IOException {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Main.getMainPage());
        window.show();
    }

    public void PrevWordsPage(ActionEvent event) throws IOException {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Main.getPreviousWordsPage());
        window.show();
    }

    public void RulePage(ActionEvent event) throws IOException {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(Main.getRulePage());
        window.show();
    }
    public void Exit(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();

    }

    public void SubmitWord(ActionEvent event) throws IOException {

        if(!verify(word.getText())) {
            status.setText("Invalid Word: "+word.getText());
            word.clear();
            status.setTextFill(Paint.valueOf("Red"));
            return;
        }
        int p=model.getWordPoints(word.getText().toUpperCase());
        status.setText(String.format("%s submitted! You get %d Points", word.getText(), p));
        error.setText("");
        status.setTextFill(Paint.valueOf("Green"));
        model.AddWord(word.getText());
        model.TakePoints(word.getText().toUpperCase());
        totalPoints.setText(model.getTotalPoints()+"");
        wordList.setItems(model.getWordList());
        pointList.setItems(model.getCountList());
        word.clear();
    }


    private boolean verify(String text) {
        // TODO Auto-generated method stub
        if(text==null||text.length()<2||text.length()>8) {
            error.setText("Error: word length should be in range [2-8]");
            return false;
        }
        if(model.CheckWord(text)) {
            error.setText("Error: Duplicate word! Check Previous Words.");
            return false;
        }
        text=text.toUpperCase();
        for(int i=0;i<text.length();i++) {
            if(text.charAt(i)<'A'||text.charAt(i)>'Z') {
                error.setText("Error: Only alphabets allowed!");
                return false;
            }
        }
        int[] arr=new int[26];
        for(int i=0;i<text.length();i++) {
            arr[text.charAt(i)-'A']++;
        }
        if(arr['A'-'A']+arr['E'-'A']+arr['I'-'A']+arr['O'-'A']+arr['U'-'A']+arr['Y'-'A']==0) {
            error.setText("Error: One letter must be a vowel (A, E, I, O, U) or Y");
            return false;
        }
        if(!model.CheckPoints(arr)) {
            error.setText("Error:  Not enough points left for this word!");
            return false;
        }

        return true;
    }

    public void Reset(ActionEvent event ) {
        model.reset();
        wordList.setItems(model.getWordList());
        pointList.setItems(model.getCountList());
        word.clear();
        status.setText("Reset Successfull");
        status.setTextFill(Paint.valueOf("Green"));
        totalPoints.setText("0");
        error.setText("");

    }

}
