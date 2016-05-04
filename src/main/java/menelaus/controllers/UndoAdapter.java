package menelaus.controllers;

/**
 * Created by frankegan on 4/16/16.
 */
public class UndoAdapter {

    /**
     * UndoAdapter constructor comment.
     */
    public UndoAdapter() {
        super();
    }

    /**
     * Empty method that will be overridden by subclasses with real functionality.
     * If a move can't be undone, then false must be returned; otherwise true signals success.
     * @return boolean false.
     */
    public boolean undoRequested() {
        return false;
    }
}