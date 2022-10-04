package ru.job4j.tracker;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void whenFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item1 to find"));
        tracker.add(new Item("Item2 to find"));
        tracker.add(new Item("Item3 to find"));
        String created = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss").format(LocalDateTime.now());
        FindByIdAction rep = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(3);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln + "id: 3, name: Item3 to find, created: "
                + created + ln));
        assertThat(tracker.findById(3).getName(), is("Item3 to find"));
    }

    @Test
    public void whenNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        FindByIdAction rep = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by id ===" + ln
                + "Item with id: 1 was not found." + ln));
    }
}