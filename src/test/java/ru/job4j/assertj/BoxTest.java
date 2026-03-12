package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("TET")
                .contains("ra")
                .doesNotContain("Sphere")
                .startsWith("Tetra")
                .startsWithIgnoringCase("t")
                .endsWith("dron")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void hasFourVertex() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void hasMoreThanSixVertex() {
        Box box = new Box(8, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(4);
    }

    @Test
    void isExists() {
        Box box = new Box(8, 10);
        boolean existence = box.isExist();
        assertThat(existence).isTrue();
    }

    @Test
    void isNotExists() {
        Box box = new Box(6, 10);
        boolean existence = box.isExist();
        assertThat(existence).isFalse();
    }

    @Test
    void isAreaMoreThan5() {
        Box box = new Box(4, 2);
        double area = box.getArea();
        assertThat(area).isCloseTo(6.92d, withPrecision(0.01d))
                .isGreaterThan(5d);
    }

    @Test
    void isAreaEquals113() {
        Box box = new Box(0, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(113d, withPrecision(0.1d))
                 .isGreaterThan(100d);
    }


}