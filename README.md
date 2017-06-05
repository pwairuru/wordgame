# wordgame
Simple word game where you play against a computer trying to generate a word, each character at a time

BUILD/RUN INSTRUCTIONS

Make sure you have java8 (jdk 1.8) and above installed.
Make sure you have the commands 'java' and 'javac' in your environment variables.
change directory into 'wordgame/src/wordgame/'.
execute 'javac Wordgame.java' to compile.
execute 'java Wordgame' to run the application.

NOTES
A function has been created to:

    when the game ends and the player has won,     
    it extends the game to check whether there are other words    
    that could help the computer win. But the way the dictionary     
    is organised, will make the computer almost always win. Due to this
    I have disabled that functionality
    
   ASSUMPTIONS.
   No word starts with a number (There is a check for that)   
   No words start with Caps (There is a check for that)   
   Player is allowed only one character at a time (There is a check for that)   
   If human player types a letter forming an invalid word, the computer wont find   
   such a word and hence the human finishes the game. So looses.
   The file 'words.txt' is in the same directory as the source file 'Wordgame.java'
   The first player to play is selected randomly
.
