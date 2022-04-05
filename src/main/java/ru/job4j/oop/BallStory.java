package ru.job4j.oop;

public class BallStory {

    public static void main(String[] args) {
        Ball ball = new Ball();
        Hare hare = new Hare();
        AngryWolf angryWolf = new AngryWolf();
        Fox fox = new Fox();
        hare.tryEat(ball);
        angryWolf.tryEat(ball);
        fox.tryEat(ball);
    }
}
