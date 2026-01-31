package br.senac.pi.oficio.domain;

import java.util.Objects;

public final class OficioNumber {
    private static final int PAD_LENGTH = 4;

    private final int sequence;
    private final int year;

    public OficioNumber(int sequence, int year) {
        if (sequence <= 0) throw new IllegalArgumentException("Sequência deve ser >= 1.");
        if (year < 1900 || year > 3000) throw new IllegalArgumentException("Ano inválido.");
        this.sequence = sequence;
        this.year = year;
    }

    public int getSequence() {
        return sequence;
    }

    public int getYear() {
        return year;
    }

    public String format() {
        return String.format("%0" + PAD_LENGTH + "d/%d", sequence, year);
    }

    @Override
    public String toString() {
        return format();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OficioNumber)) return false;
        OficioNumber that = (OficioNumber) o;
        return sequence == that.sequence && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence, year);
    }
}
