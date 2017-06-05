

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author wairuru
 */
public class Wordgame {

    List wordList = new ArrayList<String>();
    int firstPlayer = 1;
    String currWordRound = "";
    char currPlayer = 'C';      //character to track which player is the current player

    public Wordgame() {
    }

    private void playGame() {
        /*randomly pick who plays
        Assume player number 1 is the computer
        and the human player is number 2 
         */
        Random rand = new Random();
        firstPlayer = rand.nextInt(3 - 1) + 1;

        System.out.println("first player : "+firstPlayer);
        if (firstPlayer == 1) {
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'a');
            System.out.println("Computer selected to play first\n");
            currWordRound +=c;
            currPlayer = 'H';

        } else if(firstPlayer == 2){
            System.out.println("You've been selected to play first\n");
            getPlayerInput();
            currPlayer = 'C';
        }
        
        System.out.println("-----------------\n"+currWordRound+"\n-----------------");

        while (true) {
            //first check if a complete word has been formed
            if(wordList.contains(currWordRound)){
                if(currPlayer == 'C'){
                    System.err.println("Game over : The computer won");
                    break;
                }else if(currPlayer == 'H'){
                     //if(!extendGame()){
                        System.err.println("Game over : You won");
                        break;
                     //}
                }
                
            }
            if (currPlayer == 'C') {
                System.out.println("Computer To play\n------------------------------------------");
                getComputerInput();
                currPlayer = 'H';
            } else if (currPlayer == 'H') {
                System.out.println("You play\n------------------------------------------");
                getPlayerInput();
                currPlayer = 'C';
            }
            System.out.println("-----------------\n"+currWordRound+"\n-----------------");
            
        }
    }

    private void getPlayerInput() {
//        just make sure we constantly keep asking the user for proper output
        while(true){
        Scanner scanner = new Scanner(System.in);
        String character = scanner.next();
        if (character.length() > 1 || Character.isDigit(character.charAt(0))  || Character.isUpperCase(character.charAt(0))   ) {
            System.err.println("Input Error:either more than one character/digit/capital letter input\nPlease try again");
            continue;
        } else {
            currWordRound += character;
            break;
        }
        }
    }

    private void getComputerInput() {
        int i = 0;
        String word = "";

        while (i < wordList.size()) {
            //first check if word starts with our currently constructed sequence
            if (!wordList.get(i).toString().startsWith(currWordRound)) {
                i++;
                continue;
            }
            
            //we have a word that starts the way we want
            word = wordList.get(i).toString();
            //try to do some computer cheating
            if (firstPlayer == 1) { //computer played first 
                if (word.length() % 2 == 0) {
                    word = wordList.get(i).toString();
                }else{}
                }
            if (firstPlayer == 2) { //computer played first 
                if (word.length() % 2 != 0) {
                    word = wordList.get(i).toString();
                }else{}
                }
            i++;
            break;
        }
            //System.out.println("Selected w - "+word);
            currWordRound = currWordRound + word.substring(currWordRound.length(), currWordRound.length() + 1);
        //append to the currently word (How far we are)
    }
    
    /*
    Extend the game for the computer in cases where there could be another longer word
    e.g habergeon and habergeons
    */
    private boolean extendGame() {
        int i = 0;
        String word = "";
        int wordCount = 0;

        while (i < wordList.size()) {
            if (!wordList.get(i).toString().startsWith(currWordRound)) {
                i++;
                continue;
            }
            
            //we have a word that starts the way we want
            word = wordList.get(i).toString();
            wordCount +=1;
            
            i++;
            break;
        }
        if(wordCount > 0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Wordgame lendable = new Wordgame();
        lendable.readWords();
        lendable.playGame();
    }

    //this is just a function to read the words into a list
    private void readWords() {
        try {
            for (String lines : Files.readAllLines(Paths.get("words.txt"))) {
                wordList.add(lines);
            }
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("Error while reading the wordlist file\n"
                    + "Make sure the file words.txt is in the same directory as this source file");
        }
    }

}