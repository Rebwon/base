package ko.maeng.base.java8.java8inaction.chap5;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

public class TraderTests {
    private List<Transaction> transactions;

    @Before
    public void setup(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void question1() {
        List<Transaction> list = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        assertThat(list.get(0).getValue()).isEqualTo(300);
    }

    @Test
    public void question2(){
        List<String> list = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        assertThat(list).hasSize(2);
    }

    @Test
    public void question3(){
        List<Trader> list = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        assertThat(list.get(0).getName()).isEqualTo("Alan");
        assertThat(list.get(1).getName()).isEqualTo("Brian");
    }

    @Test
    public void question4(){
        String list = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(","));

        assertThat(list).isEqualTo("Alan,Brian,Mario,Raoul");
    }

    @Test
    public void question5(){
        boolean milan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        assertThat(milan).isTrue();
    }

    @Test
    public void question6(){
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    public void question7(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        assertThat(max.get()).isEqualTo(1000);
    }

    @Test
    public void question8(){
        Optional<Transaction> min = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> min1 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        assertThat(min.get().getValue()).isEqualTo(300);
    }
}