# Chess Game
This is a two player game of chess programmed in Java. The output and input are text based. 

## Installation
To play this game simply download the code and run it in any Java IDE. 

## How To Play
The rules of the game are as in any game of chess. 
Upon runtime the program will generate a text based representation of the chess board. 
It will then promt the user to enter their next move, accepting only inputs in the form of
(starting coordinates,end coordinates), any other input will result in a runtime exception.
If the move is valid the corresponding piece will be moved and the game will ask for the next players move. 

## Technicalities
The program uses various classes to create the components of a chess game. 
The pieces are created using individual classes of each type, which all inherit from a generic "Piece" class. The board is
stored in a "Board" class, while the instructions for the running of the game are stored in the "ChessGame" class, which 
contains the main method. The chess board is generated through the creation of a 2D array of Tile objects. Each Tile object 
has a piece registered to it and most of the operations that the various methods perform envoke the fields of a given Tile object.

## Screenshots


### Bugs and Improvements
A major bug is found in check mate. The response for a check mate situation should be a game over
message. However such a message is not displayed, all further moves are simply not allowed. 
There is an enourmous potential for improvement with the creation of a GUI, and the implementation of 
a minimax algorithm to make the game single player. 
