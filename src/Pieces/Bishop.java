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
public class Bishop extends Piece { // this class inherits from the generic Piece class

    /**
     * Creates a new Bishop object with a colour and an associated board.
     *
     * @param colour
     * @param board
     */
    public Bishop(Colour colour, Board board) {
        super(colour, board);
        this.type = Type.BISHOP;
        if (colour == Colour.WHITE) {
            this.name = "wB";
        } else {
            this.name = "bB";
        }
    }

    /**
     * isMoveValid Method This method is used to check if a specific move with a
     * bishop is valid.A bishop move is valid if it stays on the same diagonal,
     * and does not jump over any pieces in its path of movement. To make sure
     * that the bishop piece stays on the same diagonal the program checks the
     * change in the absolute values of the x and y coordinates. If the change
     * is equal, then the bishop stayed on the same diagonal. Otherwise the move
     * is considered invalid. In addition, the isPathBlocked method described
     * below is used to check if the path of bishop is blocked. This method is
     * non-static due to the fact that it uses the non-static method
     * isPathBlocked, and non-static methods can not be called in a static
     * context.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if (Math.abs(endX - startX) == Math.abs(endY - startY)) {
            return this.isPathBlocked(startX, startY, endX, endY) == false;
        }
        return false;
    }

    /**
     * isPathBlocked Method This method is used to check if the path of the
     * bishop piece is blocked by another piece. To determine this the program
     * uses a for loop to effectively trace out the path of the bishop. Since
     * the change in x is equal to the change in y for the path of a bishop, the
     * program only uses one for loop. It runs a loop that iterates for every
     * one change in x-coordinate. Each time the loop iterates it either
     * increases or decreases the initial values of x and y by one. Whether it
     * increases or decreases depends on if the change in x and y was positive
     * or negative. If it was positive this means that the path of the bishop
     * was in the positive direction, and the initial value increases, the
     * inverse is true for a negative change. Once it creates the new coordinate
     * the program checks if the corresponding Tile is occupied and if so then
     * if it contains a piece. If this is true, the path of the bishop is
     * blocked. If the loop finishes and false has not been returned, the path
     * is not blocked.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isPathBlocked(int startX, int startY, int endX, int endY) {
        int newX = startX; //varaiable to store new x-value
        int newY = startY;//variable to store new y-value
        int changeX = 0;
        int changeY = 0;
        // The following if statements decide whether change in x and y is positive or negative, dictating how the initial set of coordinates must change.
        if (endX - startX > 0) {
            changeX = 1;
        } else if (endX - startX < 0) {
            changeX = -1;
        }
        if (endY - startY > 0) {
            changeY = 1;
        } else {
            changeY = -1;
        }
        for (int i = 1; i <= (Math.abs(endY - startY)); i++) {
            newX += changeX;
            newY += changeY;
            if (newX == endX && newY == endY && this.board.tiles[endX][endY].isOccupied && this.board.tiles[startX][startY].piece.colour != this.board.tiles[endX][endY].piece.colour) //if the next tile to be checked is the destination, and it does not contain a piece of the same colour, move is valid
            {
                return false;
            } else if (this.board.tiles[newX][newY].isOccupied) {
                return true;
            }
        }
        return false;
    }

}
