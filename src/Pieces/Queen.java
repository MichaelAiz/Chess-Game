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
public class Queen extends Piece {

    public Queen(Colour colour, Board board) {
        super(colour, board);
        this.type = Type.QUEEN;
        if (colour == Colour.WHITE) {
            this.name = "wQ";
        } else {
            this.name = "bQ";
        }
    }

    /**
     * isMoveValid Method This method is used to check if a given move with the
     * Queen is valid. A move with the queen is valid if it moves any amount
     * either horizontally, vertically, or diagonally. In other words, it has
     * the ability to move as either a bishop or a rook, but not a knight. To
     * determine if this is the case, this method will simply check to see that
     * the given move follows the rules of either a rook or a bishop. Since the
     * isMoveValid methods of both the bishop and rook employ their own
     * isPathBlocked methods, one is not necessary for the queen.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        Bishop tempBishop = new Bishop(this.colour, this.board); //creates a temperorary Bishop object with the same properties as the Queen on the Tile
        Rook tempRook = new Rook(this.colour, this.board); ////creates a temperorary Bishop object with the same properties as the Queen on the Tile
        return tempBishop.isMoveValid(startX, startY, endX, endY) || tempRook.isMoveValid(startX, startY, endX, endY); //checks that the move with the queen follows either the rules of the bishop or rook
    }
}
