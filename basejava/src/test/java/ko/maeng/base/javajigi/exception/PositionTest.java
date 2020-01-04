package ko.maeng.base.javajigi.exception;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @Test
    public void create_00() throws InValidPositionException {
        Position p = new Position("a1");
        assertThat(p.getX()).isEqualTo(0);
        assertThat(p.getY()).isEqualTo(0);
    }

    @Test
    public void create_77() throws InValidPositionException {
        Position p = new Position("h8");
        assertThat(p.getX()).isEqualTo(7);
        assertThat(p.getY()).isEqualTo(7);
    }

    @Test
    public void 길이가_2가_아닌_경우() throws InValidPositionException {
        new Position("a");
    }

    @Test
    public void notValid_0보다_작은_X() throws InValidPositionException {
        new Position("Z1");
    }

    @Test
    public void notValid_7보다_큰_X() throws InValidPositionException {
        new Position("i1");
    }

    @Test
    public void notValid_0보다_작은_Y() throws InValidPositionException {
        new Position("a0");
    }

    @Test
    public void notValid_7보다_큰_Y() throws InValidPositionException {
        new Position("a9");
    }
}