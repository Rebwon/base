package ko.maeng.designpattern.study.chain;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessingObjectTest {
    @Test
    void chainResponsibility(){
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really Sexy?!");
        assertThat(result).isEqualTo("From Raoul, Mario and Alan: Aren't lambdas really Sexy?!");
    }

    @Test
    void chainResponsibilityUseLambda(){
        UnaryOperator<String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;

        UnaryOperator<String> spellCheckProcessing =
                (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckProcessing);

        String result = pipeline.apply("Aren't labdas really Sexy?!");
        assertThat(result).isEqualTo("From Raoul, Mario and Alan: Aren't lambdas really Sexy?!");
    }
}