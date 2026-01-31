package br.senac.pi.oficio.application;

import br.senac.pi.oficio.domain.OficioGenerator;
import br.senac.pi.oficio.domain.OficioNumber;
import br.senac.pi.oficio.domain.OficioRepository;

public final class GenerateOficioUseCase {

    private final OficioRepository repository;
    private final OficioGenerator generator;

    public GenerateOficioUseCase(OficioRepository repository, OficioGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    public OficioNumber execute(int year) {
        int last = repository.getLastSequence(year).orElse(0);
        OficioNumber next = generator.next(year, last);
        repository.saveLastSequence(year, next.getSequence());
        return next;
    }
}
