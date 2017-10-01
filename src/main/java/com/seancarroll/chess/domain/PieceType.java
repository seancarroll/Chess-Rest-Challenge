package com.seancarroll.chess.domain;

/**
 * 
 * @author seancarroll
 *
 */
public enum PieceType {
    PAWN('P', "pawn"), 
    KNIGHT('N', "knight"), 
    BISHOP('B', "bishop"),
    ROOK('R', "rook"), 
    QUEEN('Q', "queen"), 
    KING('K', "king");

    private final Character shortName;
    private final String fullName;

    PieceType(Character shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public static PieceType getPgnType(String s) {
        PieceType result = null;

        for (PieceType type : PieceType.values()) {
            if (type.getShortName() == s.toUpperCase().charAt(0)) {
                result = type;
                break;
            }
        }

        return result;
    }

    public Character getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
