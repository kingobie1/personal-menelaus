package menelaus.model.move;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.board.HintPiece;
import menelaus.model.board.Tile;

/**
 * Created by @author frankegan on 5/2/16.
 */
public class MakeHintBuilderMove extends MakePieceBuilderMove {

    HintPiece hintToPlace = null;
/**
 * Constructor
 * @param manager
 */
    public MakeHintBuilderMove(BuilderManager manager) {
        super(manager);
    }

    @Override
    /**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully completed, false otherwise
	 */
    public boolean doMove(Level level) {
        if (hintToPlace == null) {
            if (!valid(level)) return false;
            hintToPlace = new HintPiece(allSelected.get(0));
            for (int i = 1; i < allSelected.size(); i++) {
                hintToPlace.addTile(new Tile(allSelected.get(i).subtract(allSelected.get(0))));
            }
        } else if (!redoValid(level)) return false;
        //place piece
        level.getBoard().addHintPiece(hintToPlace);
        manager.clearSelectedPoints();

        return true;
    }
}
