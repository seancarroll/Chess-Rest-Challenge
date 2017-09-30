package com.seancarroll.chess;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seancarroll.chess.domain.Game;
import com.seancarroll.chess.domain.Move;

/**
 * Portable Game Notation
 * @author seancarroll
 *
 */
public class Pgn {

    public static final String DATEFORMAT_PGN = "yyyy.MM.dd";
    public static final String PATTERN_COMMENTS = "\\{.*?\\}";
    public static final String PATTERN_HEADER = "\\[(.*) \"(.*)\"\\]";
    public static final String PATTERN_PGN = "([RNBQKP]?)([a-h]?)([1-8]?)([x-]?)([a-h][1-8])\\+?=?([RNBQ])?([\\+#])?";
    public static final Pattern pgnPattern = Pattern.compile(PATTERN_PGN);
    public static final String PGN_CASTLE_K = "O-O";
    public static final String PGN_CASTLE_Q = "O-O-O";
    
    private Pgn() {
        // static utility
    }
    
    public static void convertToMove(String pgnMove) {
        
    }
    
    // movetext
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
        
        // if piece is empty then its a pawn?
        // if both x and y are not empty that would mean pawn is taking a opponent?
//        if (pgnPiece.isEmpty() && !pgnSourceX.isEmpty() && !pgnSourceY.isEmpty()) {
//            ChessBoardCoord sourceCoord = new ChessBoardCoord(pgnSourceX + pgnSourceY);
//            ChessPiece piece = position.getPiece(sourceCoord);
//            pgnPiece = piece.getType().getShortName().toString();
//        }
//        
//        ChessBoardCoord dst = new ChessBoardCoord(pgnDestination);
//        Set<ChessMovePath> availableMoves = chessRules.getAvailableMoves(position);
//        Set<ChessBoardCoord> selectedSources = new HashSet<>();
//        for (ChessBoardPath path : availableMoves) {
//            if (!ObjectUtils.equals(path.getDestination(), dst)) {
//                // Skip moves not aiming at destination square
//                continue;
//            }
//            ChessBoardCoord attacker = path.getSource();
//            ChessPiece piece = position.getPiece(attacker);
//            assert piece != null;
//            boolean selected = true;
//            if (StringUtils.isEmpty(pgnPiece)) {
//                if (!StringUtils.equalsIgnoreCase(piece.getType().getShortName().toString(),
//                        ChessPieceType.PAWN.getShortName().toString())) {
//                    selected = false;
//                }
//            } else {
//                if (!StringUtils.equalsIgnoreCase(piece.getType().getShortName().toString(), pgnPiece)) {
//                    selected = false;
//                }
//            }
//            if (StringUtils.isNotEmpty(pgnSourceX)
//                    && ChessBoardCoord.getColFromName(pgnSourceX) != attacker.getCol()) {
//                selected = false;
//            }
//            if (StringUtils.isNotEmpty(pgnSourceY)
//                    && ChessBoardCoord.getRowFromName(pgnSourceY) != attacker.getRow()) {
//                selected = false;
//            }
//            if (selected) {
//                selectedSources.add(attacker);
//            }
//        }
//        if (selectedSources.isEmpty()) {
//            if (log.isDebugEnabled()) {
//                log.debug(position.toString());
//                log.debug(availableMoves.toString());
//            }
//            throw new PgnMoveException(pgnMove, "No piece can reach square " + pgnDestination);
//        } else if (selectedSources.size() > 1) {
//            throw new PgnMoveException(pgnMove, "Several pieces can reach square " + pgnDestination);
//        }
//
//        ChessPieceType promoted = ChessPieceType.QUEEN;
//        if (StringUtils.isNotEmpty(pgnPromotion)) {
//            log.debug("PGN promotion is {}", pgnPromotion);
//            assert pgnPromotion.length() == 1;
//            promoted = ChessPieceType.getPgnType(pgnPromotion);
//            assert promoted != null;
//        }
//
//        ChessBoardCoord src = selectedSources.iterator().next();
//        ChessMovePath path = new ChessMovePath(src, dst, promoted);
//        return path;
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
