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
public class Rook extends Piece {

    public Rook(Colour colour, Board board) {
        super(colour, board);
        this.type = Type.ROOK;
        if (colour == Colour.WHITE) {
            this.name = "wR";
        } else {
            this.name = "bR";
        }
        this.hasMoved = false;
    }

    /**
     * isMoveValid This methods checks if a given move with a rook is valid.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if ((Math.abs(endX - startX) != 0) && (Math.abs(endY - startY) == 0)) {
            return this.isPathBlocked(startX, startY, endX, endY) == false;
        } else if ((Math.abs(endX - startX) == 0) && (Math.abs(endY - startY) != 0)) {
            return this.isPathBlocked(startX, startY, endX, endY) == false;
        }
        return false;
    }

    /**
     * This method is used to check if the path of the rook is blocked by
     * another piece. To determine if the path is blocked the program first
     * checks if the move was in the positive or negative x/y direction.
     * Following this, the program decides if the rook moved in the x or the y
     * direction, since a rook can only move vertically or horizontally. If the
     * change was in the x direction, a for loop that runs for the total change
     * in x is used. The for loop draws out the path of the rook, incrementing
     * through the array, and at each increment checking if the corresponding
     * tile contains a piece.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isPathBlocked(int startX, int startY, int endX, int endY) {
        int newX = 0; //varaiable to store new x-value
        int newY = 0;//variable to store new y-value
        int changeX = 0;
        int changeY = 0;
        if (endX - startX > 0) {
            changeX = 1;
        } else if (endX - startX < 0) {
            changeX = -1;
        }
        if (endY - startY > 0) {
            changeY = 1;
        } else if (endY - startY < 0) {
            changeY = -1;
        }
        if (changeX != 0) { //checks if the rook moved horizontally
            newX = startX;
            newY = startY + changeY;
            for (int i = 1; i <= (Math.abs(endX - startX)); i++) {
                newX += changeX;
                if (newX == endX && newY == endY && this.board.tiles[startX][startY].isOccupied && this.board.tiles[endX][endY].isOccupied && this.board.tiles[startX][startY].piece.colour != this.board.tiles[endX][endY].piece.colour) {
                    return false;
                } else if (newX == endX && newY == endY && this.board.tiles[newX][newY].isOccupied && this.board.tiles[startX][startY].piece.colour != this.board.tiles[newX][newY].piece.colour) {
                    return false;
                } else {
                    return this.board.tiles[newX][newY].isOccupied;
                }
            }
        } else if (changeY != 0) { //checks if the rook moved verticaly 
            newX = startX + changeX;
            newY = startY;
            for (int i = 1; i <= (Math.abs(endY - startY)); i++) {
                newY += changeY;
                if (newX == endX && newY == endY && this.board.tiles[startX][startY].isOccupied && this.board.tiles[endX][endY].isOccupied && this.board.tiles[startX][startY].piece.colour != this.board.tiles[endX][endY].piece.colour) {
                    return false;
                } else if (newX == endX && newY == endY && this.board.tiles[startX][startY].isOccupied) {
                    return false;
                } else if (this.board.tiles[newX][newY].isOccupied) {
                    return true;
                }
            }
        }
        return true;
    }
}
