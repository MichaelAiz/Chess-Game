/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import Game.ChessGame;
import Pieces.Type;
import Pieces.Bishop;
import Pieces.Colour;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

/**
 * This class is used to generate and interact with the chess board It contains
 * methods that relate to any actions done on a real board in a game of chess.
 *
 * @author Misha
 */
public class Board {

    public Tile[][] tiles = new Tile[8][8]; //creates a 2D array called "tiles" to store the individual tiles.

    /**
     * This constructor populates the "tiles" array with 64 Tile objects,
     * thereby creating the chess board. It does this by using a nested for loop
     * to increment through the array, and then creating a new Tile object in
     * each position. The values of i, the counter for the first loop, and j,
     * the counter for the second loop are passed as the coordinates of each new
     * Tile object. However when they are being passed the values are reversed,
     * for example the location that is being accessed in the array may be
     * (0,1), but the Tile object receives (1,0). This is done because Java
     * reads arrays starting with the y-value, while regular references to
     * tables are made using the x-value first. Therefore by switching the two
     * values when passing them as parameters to create the coordinates all
     * following references to the coordinates can be done according to the
     * convention of (0,0) being the bottom left corner.
     */
    public Board() {
        for (int i = 0; i < tiles.length; i++) { //increments the y values of the tiles
            for (int j = 0; j < tiles.length; j++) { //for each y-value increments x values up to 8
                if (i == 1) {
                    this.tiles[j][i] = new Tile(j, i, true, (new Pawn(Colour.WHITE, this)));
                } else if (i == 6) {
                    this.tiles[j][i] = new Tile(j, i, true, (new Pawn(Colour.BLACK, this)));
                } else if (i == 0 && j == 0) {
                    this.tiles[j][i] = new Tile(j, i, true, (new Rook(Colour.WHITE, this)));
                } else if (i == 0 && j == 1) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Knight(Colour.WHITE, this)));
                } else if (i == 0 && j == 2) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Bishop(Colour.WHITE, this)));
                } else if (i == 0 && j == 3) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Queen(Colour.WHITE, this)));
                } else if (i == 0 && j == 4) {
                    this.tiles[j][i] = new Tile(i, j, true, (new King(Colour.WHITE, this)));
                } else if (i == 0 && j == 5) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Bishop(Colour.WHITE, this)));
                } else if (i == 0 && j == 6) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Knight(Colour.WHITE, this)));
                } else if (i == 0 && j == 7) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Rook(Colour.WHITE, this)));
                } else if (i == 6) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Pawn(Colour.BLACK, this)));
                } else if (i == 7 && j == 0) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Rook(Colour.BLACK, this)));
                } else if (i == 7 && j == 1) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Knight(Colour.BLACK, this)));
                } else if (i == 7 && j == 2) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Bishop(Colour.BLACK, this)));
                } else if (i == 7 && j == 3) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Queen(Colour.BLACK, this)));
                } else if (i == 7 && j == 4) {
                    this.tiles[j][i] = new Tile(i, j, true, (new King(Colour.BLACK, this)));
                } else if (i == 7 && j == 5) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Bishop(Colour.BLACK, this)));
                } else if (i == 7 && j == 6) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Knight(Colour.BLACK, this)));
                } else if (i == 7 && j == 7) {
                    this.tiles[j][i] = new Tile(i, j, true, (new Rook(Colour.BLACK, this)));
                } else {
                    this.tiles[j][i] = new Tile(i, j, false, (new Rook(Colour.BLACK, this))); //creates a new tile in the apporpraite place on the board, assuming it is not occupied
                }
            }
        }
    }

    /**
     * This method is used to print out the current state of the game board.
     *
     */
    public void printGameBoard() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < 8; j++) {
                if (this.tiles[j][i].isOccupied) {
                    System.out.print(this.tiles[j][i].piece.name + " ");
                } else {
                    if (i % 2 == 0) {
                        if (j % 2 != 0) {
                            System.out.print("-- ");
                        } else {
                            System.out.print("## ");
                        }
                    } else if (i % 2 != 0) {
                        if (j % 2 == 0) {
                            System.out.print("-- ");
                        } else {
                            System.out.print("## ");
                        }
                    } else {
                        System.out.print("## ");
                    }

                }
            }
            System.out.println("");
        }
        for (int i = 1; i <= 8; i++) {
            switch (i) {
                case 1:
                    System.out.print(" |A|");
                    break;
                case 2:
                    System.out.print("|B|");
                    break;
                case 3:
                    System.out.print("|C|");
                    break;
                case 4:
                    System.out.print("|D|");
                    break;
                case 5:
                    System.out.print("|E|");
                    break;
                case 6:
                    System.out.print("|F|");
                    break;
                case 7:
                    System.out.print("|G|");
                    break;
                case 8:
                    System.out.print("|H|");
                    break;
            }
        }
        System.out.println("");
    }

    /**
     * This method is used to perform the movement of a piece from one tile to
     * another. It functions by first checking if the movement is valid using
     * both the broad isMoveValid method found in this class, and the specific
     * isMoveValid Method that pertains to whatever piece is on the initial
     * tile. Following this the program "moves" the piece by assigning the Piece
     * that is on the initial tile to the destination tile. It also changes the
     * isOccupied value of the destination Tile to true. After this it changes
     * the value of the Piece field on the initial Tile to null, and sets the
     * isOccupied value to false.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY tiles[endX][endY].piece = tiles[startX][startY].piece;
     * tiles[endX][endY].isOccupied = true; tiles[startX][startY].isOccupied =
     * false; tiles[startX][startY].piece = null;
     */
    public void moveTo(int startX, int startY, int endX, int endY) {
        if (isMoveValid(startX, startY, endX, endY) && putsKingInCheck(startX, startY, endX, endY, tiles[startX][startY].piece.colour) == false) {
            tiles[endX][endY].piece = tiles[startX][startY].piece;
            tiles[endX][endY].piece.hasMoved = true;
            tiles[endX][endY].isOccupied = true;
            tiles[startX][startY].isOccupied = false;
            tiles[startX][startY].piece = null;
            ChessGame.nextTurn();
        } else {
            System.out.println("");
            System.out.println("Invalid Move");
        }
    }

    /**
     * This method determines whether or not a move from the initial tile on the
     * board to another is valid. This method does not evaluate the validity of
     * the movement of the specific piece that is on the implicit tile. Instead
     * it simply checks to see first if the implicit Tile has a piece, and then
     * if the destination Tile is on the board. Following this it checks that if
     * the destination Tile has a Piece registered to it, it is not the same
     * Colour as the Piece on the initial Tile. If any of these cases are false,
     * the move is invalid. If all of these are true, it goes on to check the if
     * the move is valid for the specific type of piece that is on the given
     * tile. To do this it calls the isMoveValid method of that piece. The
     * method returns true the move is completely valid.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if ((tiles[startX][startY].isOccupied) && ((ChessGame.getTurn() % 2 != 0 && tiles[startX][startY].piece.colour == Colour.WHITE) || (ChessGame.getTurn() % 2 == 0 && tiles[startX][startY].piece.colour == Colour.BLACK))) {
            if (startX >= 0 && startX <= 7 && startY >= 0 && startY <= 7 && endX >= 0 && endX <= 7 && endY >= 0 && endY <= 7) { //checks if both initial and final positions are on the board
                if (tiles[endX][endY].isOccupied == false || tiles[endX][endY].piece.colour != tiles[startX][startY].piece.colour) {
                    return tiles[startX][startY].piece.isMoveValid(startX, startY, endX, endY);
                }
            }
        }

        return false;
    }

    /**
     * putsKingInCheck Method This method is used to evaluate if a given move
     * will place the king of the colour that is being moved into check. To do
     * this the program first stores the piece that is on the destination tile
     * in a variable called tempPiece. After this it runs the code to move the
     * piece the user wants to move to the indicated square. After this move it
     * checks if the correct king is in check. If it is, the program reverses
     * the move that was made, placing the original piece back in the initial
     * spot, and returning true. If it is not in check the program still
     * reverses the move, but returns false.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param colour
     * @return
     */
    public boolean putsKingInCheck(int startX, int startY, int endX, int endY, Colour colour) {
        Piece tempPiece = tiles[endX][endY].piece;
        tiles[endX][endY].piece = tiles[startX][startY].piece;
        tiles[endX][endY].isOccupied = true;
        tiles[startX][startY].isOccupied = false;
        for (int i = 0; i < 8; i++) {
            for (int a = 0; a < 8; a++) {
                if (tiles[i][a].isOccupied == true && tiles[i][a].piece.type == Type.KING && tiles[i][a].piece.colour == colour) {
                    ChessGame.nextTurn(); //numTurn is incremented because to check if the other coloured pieces can move, it must be their turn
                    for (int b = 0; b < 8; b++) {
                        for (int c = 0; c < 8; c++) {
                            if (this.isMoveValid(b, c, i, a)) {
                                tiles[endX][endY].piece = tempPiece;
                                tiles[endX][endY].isOccupied = tiles[endX][endY].piece != null;
                                tiles[startX][startY].isOccupied = true;
                                ChessGame.nextTurn(); //numTurn is returned to the value for the original colour
                                return true;
                            }
                        }
                    }
                }
            }
        }
        tiles[endX][endY].piece = tempPiece;
        tiles[endX][endY].isOccupied = tiles[endX][endY].piece != null;
        tiles[startX][startY].isOccupied = true;
        ChessGame.nextTurn();
        return false;
    }

}
