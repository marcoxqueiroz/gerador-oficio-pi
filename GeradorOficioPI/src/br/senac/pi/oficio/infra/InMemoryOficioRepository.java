package br.senac.pi.oficio.infra;

import br.senac.pi.oficio.domain.OficioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryOficioRepository implements OficioRepository {

    private final Map<Integer, Integer> lastByYear = new HashMap<>();

    @Override
    public Optional<Integer> getLastSequence(int year) {
        return Optional.ofNullable(lastByYear.get(year));
    }

    @Override
    public void saveLastSequence(int year, int sequence) {
        lastByYear.put(year, sequence);
    }
}
