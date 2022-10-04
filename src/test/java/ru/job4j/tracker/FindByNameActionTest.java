package ru.job4j.tracker;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {

    @Test
    public void whenFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item1 to find"));
        tracker.add(new Item("Item2 to find"));
        tracker.add(new Item("Item3 to find"));
        String created = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss").format(LocalDateTime.now());
        FindByNameAction rep = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Item3 to find");

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by name ===" + ln + "id: 3, name: Item3 to find, created: "
                + created + ln));
        assertThat(tracker.findByName("Item3 to find").get(0).getName(), is("Item3 to find"));
    }

    @Test
    public void whenNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        FindByNameAction rep = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Item");

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by name ===" + ln + "Items with name: Item was not found." + ln));
    }
}