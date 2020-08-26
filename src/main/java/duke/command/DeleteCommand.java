package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {

    private int idx;

    public DeleteCommand (int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task t = tasks.remove(idx);
            ui.showRemoveTask(t, tasks.size());
        } catch (IndexOutOfBoundsException iooob) {
            throw new DukeException("I cannot delete this element: " + idx);
        }
    }
}