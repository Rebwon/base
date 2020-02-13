package ko.maeng.base.java8.java8inaction.chap8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {
    @Test
    public void moveRightBy(){
        Point p1 = Point.generate(5, 5);
        Point p2 = p1.moveRightBy(10);

        assertThat(p2.getX()).isEqualTo(15);
        assertThat(p2.getY()).isEqualTo(5);
    }

    @Test
    public void comparingTwoPoints(){
        Point p1 = Point.generate(10, 15);
        Point p2 = Point.generate(10, 20);

        int result = Point.compareByXAndY.compare(p1, p2);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void moveAllPointsRightBy(){
        List<Point> points = Arrays.asList(Point.generate(5, 5), Point.generate(10, 5));
        List<Point> exceptedPoints = Arrays.asList(Point.generate(15, 5), Point.generate(20, 5));
        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

        assertThat(newPoints.get(0).getX()).isEqualTo(exceptedPoints.get(0).getX());
    }
}