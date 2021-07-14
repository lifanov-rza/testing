package ru.dvlifanov;

import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void distanceTest(){
        Point p = new Point(1, 1);
        Point p1 = new Point(1, 1);
        assert p.distance(p1) == 0;

    }
    @Test
    public void distanceTest2(){
        Point p2 = new Point(0, 0);
        Point p3 = new Point(5, 10);
        assert (p2.distance(p3) > 0);
    }


}
