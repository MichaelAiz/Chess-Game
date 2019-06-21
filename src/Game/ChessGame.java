package Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import GameBoard.Board;
import Pieces.Colour;
import Pieces.Type;
import java.util.Scanner;

/**
 * This class is used to run the actual game. It keeps track of which colour 
 * can go next and takes the input for the moves. It also converts these moves and
 * checks if the game is over. 
 * @author Misha
 */
public class ChessGame {

    static int numTurn = 1; //used to determine whos turn it tis

    public static void main(String[] args) {
        Board board = new Board();
        board.printGameBoard();
        while (true) {
            Scanner move = new Scanner(System.in);
            System.out.println("Enter the next move");
            System.out.println("");
            String nextMove = move.next();
            nextMove = nextMove.toUpperCase();
            //convertCoordinates(nextMove, startX, startY, endX, endY);
            board.moveTo(convertStartX(nextMove), convertStartY(nextMove), convertEndX(nextMove), convertEndY(nextMove));
            System.out.println("");
            board.printGameBoard();
            System.out.println("");
            if (isGameOver(board)) { //checks if it is checkmate
                break;
            }
            if (numTurn % 2 != 0) {
                System.out.println("White Turn");
            } else {
                System.out.println("Black Turn");
            }
            System.out.println("");

        }
        System.out.println("Game Over");
    }

    /**
     * isGameOver Method
     * This method is used after each move to determine
     * whether or not the game is over. The game is over when one of the
     * player's kings can no longer move. To determine if the king can no longer
     * move into a safe position the program will first use a nested for loop to
     * run through the board array and find the a king. It will then again use a
     * nested for loop to check if the given king can move to any tile on the
     * board. If this is not possible the program will check if any piece can
     * remove the king from check. If this is not possible the king is in check
     * mate and the game is over.
     *
     * @param
     * @return
     * @param board
     */
    public static boolean isGameOver(Board board) {

        for (int x = 0; x < 8; x++) { //finds the king
            for (int y = 0; y < 8; y++) {
                if (board.tiles[x][y].isOccupied && board.tiles[x][y].piece.type == Type.KING && isKingInCheck(board, x, y) == true) { //checks that the given tile is occupied and if it contains a king
                    if (board.isMoveValid(x, y, x + 1, y) && board.putsKingInCheck(x, y, x + 1, y, board.tiles[x][y].piece.colour) == false) {
                        return false;
                    } else if (board.isMoveValid(x, y, x + 1, y + 1) && board.putsKingInCheck(x, y, x + 1, y, board.tiles[x][y].piece.colour) == false) {
                        return false;
                    } else if (board.isMoveValid(x, y, x, y + 1) && board.putsKingInCheck(x, y, x, y + 1, board.tiles[x][y].piece.colour) == false) {
                        return false;
                    } else if (board.isMoveValid(x, y, x - 1, y) && board.putsKingInCheck(x, y, x - 1, y, board.tiles[x][y].piece.colour) == false) {
                        return false;
                    } else if (board.isMoveValid(x, y, x - 1, y - 1) && board.putsKingInCheck(x, y, x - 1, y - 1, board.tiles[x][y].piece.colour) == false) {
                        return false;
                    } else if (board.isMoveValid(x, y, x, y - 1) && board.putsKingInCheck(x, y, x, y - 1, board.tiles[x][y].piece.colour) == false) {
                        return false;
                    } else if (isDefence(board, board.tiles[x][y].piece.colour) == false) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is any possible move that can be made to take the given
     * king out of check. To do this the program iterates through every piece on
     * the board, and checks if it can possibly move anywhere where the given
     * king will no longer be in check. This is used to decide if the game is
     * over.
     *
     * @param board
     * @param colour
     * @return
     */
    public static boolean isDefence(Board board, Colour colour) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board.tiles[y][x].isOccupied && board.tiles[y][x].piece.colour == colour) {
                    for (int a = 0; a < 8; a++) {
                        for (int b = 0; b < 8; b++) {
                            if (board.isMoveValid(y, x, a, b) && board.putsKingInCheck(y, x, a, b, colour) == false) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns the value of numTurn.
     *
     * @return
     */
    public static int getTurn() {
        return numTurn;
    }

    /**
     * This method increments the value of numTurn.
     *
     * @return
     */
    public static void nextTurn() {
        numTurn++;
    }

    /**
     * isKingInCheck This method is ran to determine if the players king is in
     * check.If the king is in check, only a move with the king will be
     * allowed.The program determines this by first iterating through the tile
     * array to find the king that corresponds to the colour that is currently
     * being moved. Following this, it will again iterate through the tile
     * array, for each tile checking that if there is a piece there, if it can
     * move to the spot of the king. If this is true, then the king is in a
     * state of check and must be moved.
     *
     * @param board
     * @param x
     * @param y
     * @return
     */
    public static boolean isKingInCheck(Board board, int x, int y) {

        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {
                if (board.tiles[b][a].isOccupied && board.isMoveValid(b, a, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method is used to convert the letter location of the initial piece
     * that is given by the user to the corresponding integer x - coordinate.
     *
     * @param nextMove
     * @return
     */
    public static int convertStartX(String nextMove) {
        int startX = 0;
        nextMove.replaceAll(" ", "");
        switch (nextMove.charAt(0)) {
            case 'A':
                startX = 0;
                return startX;
            case 'B':
                startX = 1;
                return startX;
            case 'C':
                startX = 2;
                return startX;
            case 'D':
                startX = 3;
                return startX;
            case 'E':
                startX = 4;
                return startX;
            case 'F':
                startX = 5;
                return startX;
            case 'G':
                startX = 6;
                return startX;
            case 'H':
                startX = 7;
                return startX;
            default:
                throw new RuntimeException("Please enter a valid move");
        }
    }

    /**
     * convertEndX This method is used to convert the letter location of the
     * destination to the corresponding integer x-coordinate.
     *
     * @param nextMove
     * @return
     */
    public static int convertEndX(String nextMove) {
        int endX = 0;
        switch (nextMove.charAt(3)) {
            case 'A':
                endX = 0;
                return endX;
            case 'B':
                endX = 1;
                return endX;
            case 'C':
                endX = 2;
                return endX;
            case 'D':
                endX = 3;
                return endX;
            case 'E':
                endX = 4;
                return endX;
            case 'F':
                endX = 5;
                return endX;
            case 'G':
                endX = 6;
                return endX;
            case 'H':
                endX = 7;
                return endX;
            default:
                throw new RuntimeException("Please enter a valid move");
        }
    }

    /**
     * convertStartY This method is used to convert the initial y-coordinate
     * given by the user to the corresponding one in the 0-7 array.
     *
     * @param nextMove
     * @return
     */
    public static int convertStartY(String nextMove) {
        int startY = Integer.parseInt(nextMove.substring(1, 2));
        startY = startY - 1;
        return startY;
    }

    /**
     * This method is used to convert the initial y-coordinate given by the user
     * to the corresponding one in the 0-7 array.
     *
     * @param nextMove
     * @return
     */
    public static int convertEndY(String nextMove) {
        int endY = Integer.parseInt(nextMove.substring(4));
        endY = endY - 1;
        return endY;
    }

}
