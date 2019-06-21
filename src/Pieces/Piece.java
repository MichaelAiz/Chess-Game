package Pieces;

/**
 * This class is used to create the general properties of any piece in the game.
 * It is inherited by all the different types of piece classes.
 *
 * @author Michael Aizenshtadt
 */
import GameBoard.Board;

public abstract class Piece {

    public Type type; // type of piece that it is (e.g Bishop), recieves an enumerator from the enumerator class "Type"
    public Colour colour; // the colour of the piece (White/Black), also receives an enum value
    public String name; // The name that is registered to the piece, based on the type of piece and its colour(a white pawn will have name "WP").
    // used to print out the gameboard with each piece name representing the various pieces
    public Board board; //The board field is used to give each piece the ability to reference the board which it is on. This is used in the isMoveValid methods for each piece.
    public boolean hasMoved; //the hasMoved field is used only for the requirements of castling

    /**
     * This constructor creates a new Piece object with an x and a y coordinate,
     * as well as a colour and the board which it is located on.
     *
     * @param colour
     * @param board
     */
    public Piece(Colour colour, Board board) {
        this.colour = colour;
        this.board = board;
    }

    @Override
    public String toString() { // method that returns the type of the implicit Piece object, used for testing
        if (this.name == null) {
            return ("no piece");
        }
        return (this.type + "");
    }

    /**
     * This method is used to call the individual isMoveValid methods of each
     * piece. It is called by the general isMoveValid method of the Board class.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        switch (this.board.tiles[startX][startY].piece.type) { //checks what piece is on the given start tile, then calls the corresponding isMoveValidMethod
            case PAWN:
                return ((Pawn) this).isMoveValid(startX, startY, endX, endY);
            case BISHOP:
                return ((Bishop) this).isMoveValid(startX, startY, endX, endY);
            case KNIGHT:
                return ((Knight) this).isMoveValid(startX, startY, endX, endY);
            case ROOK:
                return ((Rook) this).isMoveValid(startX, startY, endX, endY);
            case QUEEN:
                return ((Queen) this).isMoveValid(startX, startY, endX, endY);
            case KING:
                return ((King) this).isMoveValid(startX, startY, endX, endY);
        }
        return false;
    }
}
