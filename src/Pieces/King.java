/*
 */
package Pieces;

import GameBoard.Board;
import Game.ChessGame;

/**
 *
 * @author Misha
 */
public class King extends Piece {

    public King(Colour colour, Board board) {
        super(colour, board);
        this.type = Type.KING;
        if (colour == Colour.WHITE) {
            this.name = "wK";
        } else {
            this.name = "bK";
        }
        this.hasMoved = false;
    }

    /**
     * This method is used to check if a given move with the king is valid.
     * A move with the king is valid if it either moves one space to the right, 
     * left, up, or down. The exception to this is if castling is done. In this 
     * case the king can move 2 spaces. 
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        if (Math.abs(endX - startX) == 1 && (endY - startY) == 0) {
            return true;
        } else if (Math.abs(endY - startY) == 1 && (endX - startX) == 0) {
            return true;
        } else if (Math.abs(endY - startY) == 1 && Math.abs(endX - startX) == 1) {
            return true;
        } else if (Math.abs(endX - startX) == 2 && endY - startY == 0 && (startY == 0 || startY == 7) && startX == 4) {
            if (this.board.tiles[startX][startY].piece.hasMoved == false) {
                if (this.isPathBlocked(startX, startY, endX, endY) == false) {
                    this.castle(startX, startY, endX, endY);
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * This method is used to perform the operation of castling. In castling the
     * king moves two places either to the left or the right, and the rook hops
     * over the king. This can only be done if both pieces are in their starting
     * positions and have not previously moved. To perform this action, the
     * program determines first in which direction the kings is moving, then
     * moving the corresponding rook to the correct location.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public void castle(int startX, int startY, int endX, int endY) {
        if (startY == 0 && endX - startX > 0 && this.board.tiles[7][0].piece.hasMoved == false) { //checks if the king starts at 0 and moves to the right and has not moved before
            this.board.tiles[endX - 1][endY].piece = this.board.tiles[7][0].piece;
            this.board.tiles[endX - 1][endY].isOccupied = true;
            this.board.tiles[7][0].isOccupied = false;
        } else if (startY == 0 && endX - startX < 0 && this.board.tiles[0][0].piece.hasMoved == false) {
            this.board.tiles[endX + 1][endY].piece = this.board.tiles[0][0].piece;
            this.board.tiles[endX + 1][endY].isOccupied = true;
            this.board.tiles[0][0].isOccupied = false;
        } else if (startY == 7 && endX - startX > 0 && this.board.tiles[7][7].piece.hasMoved == false) {
            this.board.tiles[endX - 1][endY].piece = this.board.tiles[7][7].piece;
            this.board.tiles[endX - 1][endY].isOccupied = true;
            this.board.tiles[7][7].isOccupied = false;
        } else if (startY == 7 && endX - startX < 0 && this.board.tiles[0][7].piece.hasMoved == false) {
            this.board.tiles[endX + 1][endY].piece = this.board.tiles[0][7].piece;
            this.board.tiles[endX + 1][endY].isOccupied = true;
            this.board.tiles[0][7].isOccupied = false;
        }
    }

    /**
     * This method is used to determine if the path of the king is blocked by
     * another piece. In reality this method is only useful during castling,
     * since that is the only time the king can move more than one square.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isPathBlocked(int startX, int startY, int endX, int endY) {
        int newX = 0;
        int newY = 0;
        int changeX = 0;
        if (endX - startX > 0) {
            changeX = 1;
        } else if (endX - startX < 0) {
            changeX = -1;
        }
        newX = startX;
        newY = startY;
        newX += changeX;
        return this.board.tiles[newX][newY].isOccupied && this.board.tiles[newX][newY].piece.colour == this.board.tiles[startX][startY].piece.colour;
    }

}
