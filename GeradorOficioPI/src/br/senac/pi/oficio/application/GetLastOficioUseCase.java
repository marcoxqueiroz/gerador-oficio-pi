package br.senac.pi.oficio.application;

import br.senac.pi.oficio.domain.OficioNumber;
import br.senac.pi.oficio.domain.OficioRepository;

import java.util.Optional;

public final class GetLastOficioUseCase {

    private final OficioRepository repository;

    public GetLastOficioUseCase(OficioRepository repository) {
        this.repository = repository;
    }

    public Optional<OficioNumber> execute(int year) {
        return repository.getLastSequence(year).map(seq -> new OficioNumber(seq, year));
    }
}
