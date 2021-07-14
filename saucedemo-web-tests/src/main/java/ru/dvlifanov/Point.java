package ru.dvlifanov;// TODO Задание 2

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow(Math.abs(this.x - p.x), 2) + Math.pow(Math.abs(this.y - p.y), 2));
    }
}
