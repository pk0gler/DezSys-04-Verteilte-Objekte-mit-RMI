package command;

import java.io.Serializable;

/**
 * This interface represents a command.
 *
 * @author Alexandru Jaravete
 */
public interface Command extends Serializable {

    /**
     * Method to be executed.
     */
    void execute();
}
