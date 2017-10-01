package com.seancarroll.chess.domain;

public enum PieceColor {
    WHITE("w", "white"), 
    BLACK("b", "black");
    
    private final String shortName;
    private final String fullName;

    PieceColor(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public static PieceColor valueOfShortName(String name) {
        PieceColor result = null;

        for (PieceColor color : values()) {
            if (color.getShortName().equals(name)) {
                result = color;
                break;
            }
        }

        return result;
    }

}
