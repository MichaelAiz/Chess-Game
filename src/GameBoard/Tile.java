/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard;

import Pieces.Piece;

/**
 * This class is used to create individual tiles that make up the chess board.
 * Just like in a regular chess game each Tile has a set of coordinates. In
 * addition, the tiles can be either occupied or empty. The tiles that are
 * occupied also have a value allocated to the piece field. If the tile is not
 * occupied the piece field is set as null.
 *
 * @author Misha
 */
public class Tile {

    public int x; //x-coordinate of tile
    public int y; //y-coordinate of tile
    public boolean isOccupied;
    ;
    public Piece piece; //the piece that is on the tile

    /**
     * This constructor creates a new Tile object that has x and y coordinates,
     * along with an isOccupied boolean value, and a piece.
     *
     * @param x
     * @param y
     * @param isOccupied
     * @param piece
     */
    public Tile(int x, int y, boolean isOccupied, Piece piece) {
        this.x = x;
        this.y = y;
        this.isOccupied = isOccupied;
        if (isOccupied) { //checks if the current tile is occupied 
            this.piece = piece; //if the new Tile is occupied, sets the piece to the piece parameter
        } else {
            this.piece = null; //if the Tile is not occupied, sets the piece field to null
        }
    }

    @Override
    public String toString() { //method used to return the colour and type of the piece that is on the implicit Tile object
        if (this.piece == null) {
            return ("empty");
        }
        return (this.piece.colour + "," + this.piece.type);
    }

}
