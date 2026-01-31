package br.senac.pi.oficio.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class OficioGeneratorTest {

    @Test
    public void deveGerarPrimeiroOficioQuandoNaoHaUltimo() {
        OficioGenerator gen = new OficioGenerator();

        OficioNumber n = gen.next(2026, 0);

        assertEquals(1, n.getSequence());
        assertEquals(2026, n.getYear());
        assertEquals("0001/2026", n.format());
    }

    @Test
    public void deveIncrementarSequenciaMantendoAno() {
        OficioGenerator gen = new OficioGenerator();

        OficioNumber n = gen.next(2026, 9);

        assertEquals("0010/2026", n.format());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoParaAnoInvalido() {
        new OficioGenerator().next(1800, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoParaSequenciaNegativa() {
        new OficioGenerator().next(2026, -1);
    }
}
