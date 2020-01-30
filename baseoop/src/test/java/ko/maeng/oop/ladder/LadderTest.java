package ko.maeng.oop.ladder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {
    @Test
    void noLine(){
        Ladder ladder = new Ladder(1,3);
        int target = ladder.run(0);
        assertThat(target).isEqualTo(0);

        target = ladder.run(2);
        assertThat(target).isEqualTo(2);
    }

    @Test
    void runWhenLineLeft() {
        // 0 1 1 플레이어 수 3명에 대응되는 선 3개가 그어졌다.
        Ladder ladder = new Ladder(1, 3);
        ladder.drawLine(0,1);

        int target = ladder.run(2);
        assertThat(target).isEqualTo(1);

        // 1 1 0
        ladder = new Ladder(1, 3);
        ladder.drawLine(0,0);

        ladder.run(0);
    }

    @Test
    void runWhenLineRight() {
        // 0 1 1
        // 0 1 1
        Ladder ladder = new Ladder(1, 3);
        ladder.drawLine(0,1);

        int target = ladder.run(1);
        assertThat(target).isEqualTo(2);

        // 0 1 1
        ladder = new Ladder(1, 3);
        ladder.drawLine(0,1);

        target = ladder.run(2);
        assertThat(target).isEqualTo(1);
    }

    @Test
    void runWhenMultiRows() {
        // 1 1 0 0
        // 0 1 1 0
        // 0 0 1 1
        Ladder ladder = new Ladder(3, 4);
        ladder.drawLine(0, 0);
        ladder.drawLine(1, 1);
        ladder.drawLine(2, 2);

        assertThat(ladder.run(0)).isEqualTo(3);
        assertThat(ladder.run(1)).isEqualTo(0);
        assertThat(ladder.run(2)).isEqualTo(1);
        assertThat(ladder.run(3)).isEqualTo(2);

    }
}