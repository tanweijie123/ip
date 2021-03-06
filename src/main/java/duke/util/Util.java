package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class contains the utility methods for the programme.
 */
public class Util {
    private static final String TASK_DELIMITER = "`";

    /**
     * Gets the TASK_DELIMITER static variable from Utility / Config file.
     *
     * @return TASK_DELIMITER used for file exports.
     */
    public static String getTaskDelimiter() {
        return TASK_DELIMITER;
    }

    /**
     * Converts a String type date and time into a {@code LocalDateTime} object
     *
     * @param dateTime The String of Date/Time. Formats accepted: "YYYY-MM-dd HHmm", "dd/MM/yyyy HHmm"
     *                 and their date equivalent.
     * @return The {@code LocalDateTime} object from the given input.
     * @throws DukeException if the input string does not follow the above format.
     */
    public static LocalDateTime convertStringToDateTime(String dateTime) throws DukeException {
        //Allow format of "YYYY-MM-dd HHmm", "dd/MM/yyyy HHmm"; Set HHmm to 0000 if not found.

        try {
            if (!dateTime.contains(" ")) {
                dateTime = dateTime + " 0000"; //pad with time if the input only contains date.
            }

            if (dateTime.charAt(4) == '-') {
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } else if (dateTime.charAt(2) == '/') {
                return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            } else {
                throw new DukeException("Invalid Date / time format...");
            }
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid Date / time format...");
        }
    }

    /**
     * Converts a String type input into {@code Task} object.
     *
     * @param string The string to be converted.
     * @return The {@code Task} object from the given input
     * @throws DukeException if the given input does not match the input format.
     */
    public static Task convertStringToTask(String string) throws DukeException {
        String[] split = string.split(TASK_DELIMITER);
        Task t;

        //TODO: may want to check for file modification. Or invalid line input
        switch (split[0]) {
        case "T":
            t = new ToDo(split[3]);
            break;
        case "D":
            t = new Deadline(split[3], convertStringToDateTime(split[4]));
            break;
        case "E":
            t = new Event(split[3], convertStringToDateTime(split[4]));
            break;
        default:
            throw new DukeException("Error in reading this line...");
        }

        if (split[1].equals("1")) {
            t.setDone();
        }

        t.setPriority(Integer.parseInt(split[2]));  //TODO: check if parse throws any excption

        return t;
    }
}
