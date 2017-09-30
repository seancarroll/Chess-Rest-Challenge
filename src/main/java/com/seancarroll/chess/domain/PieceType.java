package com.seancarroll.chess.domain;

public enum PieceType {
    PAWN('p', "pawn"), 
    KNIGHT('n', "knight"), 
    BISHOP('b', "bishop"),
    ROOK('r', "rook"), 
    QUEEN('q', "queen"), 
    KING('k', "king");

    private final Character shortName;
    private final String fullName;

    PieceType(Character shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public static PieceType getPgnType(String s) {
        PieceType result = null;

        for (PieceType type : PieceType.values()) {
            if (type.getShortName() == s.toLowerCase().charAt(0)) {
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
