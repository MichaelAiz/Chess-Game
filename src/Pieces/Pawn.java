/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import GameBoard.Board;

/**
 *
 * @author Misha
 */
public class Pawn extends Piece {

    /**
     * Creates a new Pawn Object with a colour and an associated board
     *
     * @param colour
     * @param board
     */
    public Pawn(Colour colour, Board board) {
        super(colour, board); //sends the paramters back to the Piece superclass 
        this.type = Type.PAWN;
        if (colour == Colour.WHITE) //decides which colour the piece is and then assigns the proper name to it
        {
            this.name = "wP"; //if pawn is white calls it "WP"
        } else {
            this.name = "bP"; //if pawn is black calls it "BP"
        }
    }

    /**
     * This method is used to check if a specific move with a pawn is valid. A
     * move with a pawn is valid if it does not move in any other direction than
     * forward, and only one tile. Two exceptions to this rule are if the pawn
     * is in its starting position, in which case it can move two places, and if
     * it is capturing another piece in which case it can move diagonally.
     * However the pawn can not capture pieces directly in front of it, so if
     * there is a piece in front of it of any colour, it can not take its spot.
     * Other restrictions include that if it is allowed to move two places, that
     * it is not jumping over any pieces of any colour. To check if the path of
     * the pawn is blocked a isPathBlocked method is NOT employed, because
     * there is only at most one tile that needs to be checked.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if ((this.colour == Colour.WHITE && endY - startY == 1 && endX == startX) || (this.colour == Colour.BLACK && endY - startY == -1 && endX == startX)) { //checks if the pawn moved one square forward
            if (this.board.tiles[endX][endY].isOccupied == false) { //checks that the destination tile is empty
                return true;
            }
        } else if ((this.colour == Colour.WHITE && endY - startY == 1 && (Math.abs(endX - startX) == 1)) || (this.colour == Colour.BLACK && endY - startY == -1 && (Math.abs(endX - startX) == 1))) { //checks if the pawn moved diagonally
            if (this.board.tiles[endX][endY].isOccupied) { //checks that the destination tile is not empty
                if (this.board.tiles[endX][endY].piece.colour != this.board.tiles[startX][startY].piece.colour) { //checks that the piece on the destination tile is not the same colour as the pawn
                    return true;
                }
            } else {
                return false;
            }
        } else if ((this.colour == Colour.WHITE && endY - startY == 2 && endX == startX) || (this.colour == Colour.BLACK && endY - startY == -2 && endX == startX)) { //checks if the pawn moved two squares forward
            if (startY == 1 || startY == 6) { //checks that the initial position of the pawn was in the second row
                if ((this.colour == Colour.WHITE && this.board.tiles[endX][endY - 1].isOccupied == false) || (this.colour == Colour.BLACK && this.board.tiles[endX][endY + 1].isOccupied == false) && this.board.tiles[endX][endY].isOccupied == false) { //checks that the tile in betwen the starting tile of the pawn and the destination is not blocked
                    return true;
                }
            }
        }
        return false;
    }
}
