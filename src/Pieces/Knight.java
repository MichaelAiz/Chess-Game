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
public class Knight extends Piece {

    public Knight(Colour colour, Board board) {
        super(colour, board);
        this.type = Type.KNIGHT;
        if (colour == Colour.WHITE) {
            this.name = "wN";
        } else {
            this.name = "bN";
        }
    }

    /**
     * IsMoveValid Method This method is used to check if a given move with the
     * knight piece is valid. A move with the knight is valid if the change in x is 2 
     * the absolute value of the change in y is one, or if the absolute value of the 
     * change in y is 2, and in x it is 1. 
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if (Math.abs(endX - startX) == 1 && Math.abs(endY - startY) == 2) {
            return true;
        } else if (Math.abs(endX - startX) == 2 && Math.abs(endY - startY) == 1) {
            return true;
        }
        return false;
    }
}
