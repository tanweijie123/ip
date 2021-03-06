package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {
    @Test
    public void eventCreation_normalInput_success() {
        Event event = new Event("read book", LocalDateTime.of(2020,02,14,15,50));
        Assertions.assertEquals("[E][\u2718][UNCLASSIFIED] read book (at: Feb 14 2020 1550)", event.toString());
    }

    @Test
    public void eventCreation_noDesc_exceptionThrown() {
        try {
            Event event = new Event("", LocalDateTime.now());
            Assertions.fail();
        } catch (DukeException de) {
            Assertions.assertEquals(
                    new DukeException("The description cannot be empty").getMessage(),
                    de.getMessage());
        }
    }

    @Test
    public void eventCreation_normalInputWithPriority_success() {
        Event event = new Event("!2 read map", LocalDateTime.of(2020,03,16,15,50));
        Assertions.assertEquals("[E][\u2718][HIGH] read map (at: Mar 16 2020 1550)", event.toString());
    }

    @Test
    public void eventCreation_normalInputWithBadPriority_success() {
        Event event = new Event("!9 read map", LocalDateTime.of(2021,03,16,15,50));
        Assertions.assertEquals("[E][\u2718][UNCLASSIFIED] !9 read map (at: Mar 16 2021 1550)", event.toString());
    }

    @Test
    public void eventDone_setDone_success() {
        Event event = new Event("read book", LocalDateTime.parse("2019-09-11T13:40"));
        event.setDone();
        Assertions.assertEquals("[E][\u2713][UNCLASSIFIED] read book (at: Sep 11 2019 1340)", event.toString());
    }

    @Test
    public void eventExport_noInput_success() {
        Event event = new Event("wash clothes",
                LocalDateTime.parse("10/11/2018 0800", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        event.setDone();
        Assertions.assertEquals("E`1`5`wash clothes`10/11/2018 0800", event.getSaveToFileString());
    }

    @Test
    public void eventPriority_changePriority_success() {
        Event event = new Event("sleep",
                LocalDateTime.parse("10/11/2019 0800", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        event.setPriority(2);
        Assertions.assertEquals(Priority.HIGH, event.getPriority());
    }

    @Test
    public void eventPriority_changePriority_invalidPriority() {
        try {
            Event event = new Event("sleep",
                    LocalDateTime.parse("10/11/2019 0800", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
            event.setPriority(6);
            Assertions.fail();
        } catch (DukeException e) {
            Assertions.assertEquals(new DukeException("Invalid Priority Given...").getMessage(), e.getMessage());
        }
    }
}
