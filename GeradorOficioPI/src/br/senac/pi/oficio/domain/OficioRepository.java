package br.senac.pi.oficio.domain;

import java.util.Optional;

public interface OficioRepository {
    Optional<Integer> getLastSequence(int year);
    void saveLastSequence(int year, int sequence);
}
