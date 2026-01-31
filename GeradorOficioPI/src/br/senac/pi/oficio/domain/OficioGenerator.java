package br.senac.pi.oficio.domain;

public final class OficioGenerator {

    public OficioNumber next(int year, int lastSequenceOrZero) {
        if (year < 1900 || year > 3000) {
            throw new IllegalArgumentException("Ano inválido.");
        }
        if (lastSequenceOrZero < 0) {
            throw new IllegalArgumentException("Última sequência não pode ser negativa.");
        }

        int next = lastSequenceOrZero + 1;
        return new OficioNumber(next, year);
    }
}
