package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class JobTest {

    @Test
    public void whenAscByName() {
        Comparator<Job> comb = new JobAscByName();
        int rsl = comb.compare(new Job("Buy", 1), new Job("Sell", 2));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenDescByName() {
        Comparator<Job> comb = new JobDescByName();
        int rsl = comb.compare(new Job("Sell", 2), new Job("Buy", 1));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenAscByPriority() {
        Comparator<Job> comb = new JobAscByPriority();
        int rsl = comb.compare(new Job("Sell", 2), new Job("Buy", 1));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenDescByPriority() {
        Comparator<Job> comb = new JobDescByPriority();
        int rsl = comb.compare(new Job("Sell", 2), new Job("Buy", 1));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorJobDescByNameAndJobDescByPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorJobAscByPriorityAndJobAscByName() {
        Comparator<Job> cmpNamePriority = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 2),
                new Job("Fix bug", 2)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorJobDescByPriorityAndJobAscByName() {
        Comparator<Job> cmpNamePriority = new JobDescByPriority().thenComparing(new JobAscByName());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 1),
                new Job("Fix bug", 2)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorJobAscByNameAndJobDescByPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Task", 2),
                new Job("Task", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}