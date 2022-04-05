package ru.job4j.oop;

import org.junit.Test;
import org.junit.Assert;

public class PointTest {

    @Test
    public void when1To0To2To0ToMinus1ToMinus1Then3dot3166() {
        Point c = new Point(1, 0, 2);
        Point d = new Point(0, -1, -1);
        double expected = 3.3166;
        double delta = 0.0001;
        Assert.assertEquals(c.distance3d(d), expected, delta);
    }

    @Test
    public void when2To6ToMinus2To1To0To4Then8dot5440() {
        Point c = new Point(2, 6, -2);
        Point d = new Point(1, 0, 4);
        double expected = 8.5440;
        double delta = 0.0001;
        Assert.assertEquals(c.distance3d(d), expected, delta);
    }
}