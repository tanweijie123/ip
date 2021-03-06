package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a event type of task. Users are able to store the event time.
 */
public class Event extends Task {

    private LocalDateTime taskDateTime;

    /**
     * Constructs a Event-type task with a description and event time.
     *
     * @param desc The description of the event.
     * @param taskDateTime The date and time of this event occurring.
     */
    public Event(String desc, LocalDateTime taskDateTime) {
        super(desc);

        this.taskDateTime = taskDateTime;

        assert(!this.desc.isBlank() && this.taskDateTime != null);
    }

    /**
     * {@inheritDoc}
     */
    public String getSaveToFileString() {
        assert(!this.desc.isBlank() && this.taskDateTime != null);

        return "E" + TASK_DELIMITER + super.getSaveToFileString() + TASK_DELIMITER +
                taskDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                taskDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
