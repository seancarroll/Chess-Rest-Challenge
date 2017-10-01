package com.seancarroll.chess;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.domain.Move;
import com.seancarroll.chess.exceptions.PgnMoveException;

/**
 * Portable Game Notation
 * @author seancarroll
 *
 */
public class Pgn {

    // http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c8.1.1
    public static final String DATEFORMAT_PGN = "yyyy.MM.dd";
    // move text pattern
    // https://github.com/ChessCorp/chess-rules-java/blob/master/src/main/java/org/alcibiade/chess/persistence/PgnFormats.java
    public static final String PATTERN_PGN = "([RNBQKP]?)([a-h]?)([1-8]?)([x-]?)([a-h][1-8])\\+?=?([RNBQ])?([\\+#])?";
    public static final Pattern pgnPattern = Pattern.compile(PATTERN_PGN);
    public static final String PGN_CASTLE_K = "O-O";
    public static final String PGN_CASTLE_Q = "O-O-O";
    
    private Pgn() {
        // static utility
    }
    
    public static void convertToMove(String pgnMove) {
        
    }
    
    // movetext / san
    // TODO:
    public static void parsePgnString(String pgnMove) {
        Matcher pgnMatcher = pgnPattern.matcher(pgnMove);
        if (!pgnMatcher.matches()) {
            throw new PgnMoveException(pgnMove + " Does not match PGN syntax");
        }
        String pgnPiece = pgnMatcher.group(1);
        String pgnSourceX = pgnMatcher.group(2);
        String pgnSourceY = pgnMatcher.group(3);
        String pgnDestination = pgnMatcher.group(5);
        String pgnPromotion = pgnMatcher.group(6);
        
        // TODO: finish
        
    }
    
    public static String exportGame(Game game) {
        StringBuilder pgn = new StringBuilder();
        appendHeader(pgn, "Event", game.getEvent());
        appendHeader(pgn, "Site", game.getSite());
        appendHeader(pgn, "Date", game.getDate().format(DateTimeFormatter.ofPattern(DATEFORMAT_PGN)));
        appendHeader(pgn, "Round", String.valueOf(game.getRound()));
        appendHeader(pgn, "White", game.getWhitePlayer().getName());
        appendHeader(pgn, "Black", game.getBlackPlayer().getName());
        appendHeader(pgn, "Result", game.getResult().toString());
        pgn.append(System.lineSeparator());
        
        // http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c4.3
        // states that Lines with 80 or more printing characters are strongly discouraged 
        // because of the difficulties experienced by common text editors with long lines.
        StringBuilder line = new StringBuilder();
        int index = 0;
        for (Move move : game.getMoves()) {
            if (index % 2 == 0) {
                line.append((index / 2) + 1);
                line.append(". ");
            }

            line.append(move.getPgnMovetext());
            line.append(' ');

            if (line.length() > 70) {
                pgn.append(line);
                pgn.append(System.lineSeparator());
                line.setLength(0);
            }
            index++;
        }

        pgn.append(line);
        pgn.append(System.lineSeparator());

        return pgn.toString();
    }
    
    private static void appendHeader(StringBuilder text, String name, String value) {
        text.append("[");
        text.append(name);
        text.append(" \"");
        text.append(value);
        text.append("\"");
        text.append("]");
        text.append(System.lineSeparator());
    }
}
