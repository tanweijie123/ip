package duke.task;

import duke.exception.DukeException;

/**
 * This class is a ToDo type of task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param desc The description of this task.
     */
    public ToDo(String desc) {
        super(desc);
        assert(!this.desc.isBlank());
    }

    /**
     * {@inheritDoc}
     */
    public String getSaveToFileString() {
        assert(!this.desc.isBlank());

        return String.format("T%s%s", TASK_DELIMITER, super.getSaveToFileString());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
