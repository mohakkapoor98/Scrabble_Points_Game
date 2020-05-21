package sample;

import java.util.Arrays;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private int pointBag [];
    private int leftInTheBag =10;
    private int totalPoints;
    private int pointList [] = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};


    private ObservableList<String> rulesSet =
            FXCollections.observableArrayList("1. You can use minimum of 2 letters and maximum of 8 letters","2. One letter must be vowel\r\n" +
            " A, E, I, O, U or Y","3. You can only enter 10 letters" ,"4. You cannot use duplicate words");

    private HashSet<String> wordSet;
    private ObservableList<String> wordList= FXCollections.observableArrayList();

    public Model(){

        System.out.println("The app is working");
        pointBag=new int[26];
        Arrays.fill(pointBag, leftInTheBag);
        wordSet=new HashSet<String>();
        totalPoints = 0;
    }

    public int[] getPointBag() {
        return pointBag;
    }
    public ObservableList<String> getRulesSet() {
        return rulesSet;
    }

    public ObservableList<String> getWordList() {
        return wordList;
    }

    public HashSet<String> getWordSet() {
        return wordSet;
    }
    public int getWordPoints(String word) {
        int p=0;
        int l=word.length();
        for(int i=0;i<l;i++) {
            p+=pointList[word.charAt(i)-'A'];
        }
        return p;
    }
    public int getTotalPoints() {
        return totalPoints;
    }

    public void AddWord(String word) {
        wordList.add(word);
        wordSet.add(word.toUpperCase());
        totalPoints += getWordPoints(word.toUpperCase());
    }

    public void TakePoints(String word) {
        int l=word.length();
        for(int i=0;i<l;i++) {
            pointBag[word.charAt(i)-'A']--;
        }
    }

    public ObservableList<String> getCountList() {
        ObservableList<String> list=FXCollections.observableArrayList();
        char ch;
        for(int i=65;i<=90;i++) {
            ch=(char) i;
            if(pointBag[i-65]==0) {
                list.add(ch+" -> _");
            }else {
                list.add(ch+" -> "+pointBag[i-65]);
            }
        }
        return list;
    }

    public ObservableList<String> getPointTable() {
        ObservableList<String> list=FXCollections.observableArrayList();
        char ch;
        for(int i=65;i<=90;i++) {
            ch=(char) i;
            list.add(ch+" -> "+pointList[i-65]);
        }
        return list;
    }

    public boolean CheckWord(String text) {
        return wordSet.contains(text.toUpperCase());
    }

    public boolean CheckPoints(int[] arr) {
        // TODO Auto-generated method stub
        for(int i=0;i<26;i++) {
            if(arr[i]>pointBag[i]) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        // TODO Auto-generated method stub
        Arrays.fill(pointBag, leftInTheBag);
        wordList.clear();
        wordSet.clear();
        totalPoints=0;
    }
}
