package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.NoSuchElementException;

class RevertLinkedTest {
    private RevertLinked<Integer> linked;

    @BeforeEach
    void init() {
        linked = new RevertLinked<>();
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        linked.add(1);
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        assertThat(linked).containsSequence(1, 2, 3, 4);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(4, 3, 2, 1);
    }

    @Test
    void whenAddAndRevertTrueAdditional() {
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.add(5);
        linked.add(6);
        assertThat(linked).containsSequence(1, 2, 3, 4, 5, 6);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(6, 5, 4, 3, 2, 1);
    }
}