package br.crud.barbershopapi.services.impl;

import br.crud.barbershopapi.models.ScheduleModel;
import br.crud.barbershopapi.repositories.IScheduleRepository;
import br.crud.barbershopapi.services.IScheduleService;
import br.crud.barbershopapi.services.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleModel save(final ScheduleModel model) {
        queryService.verifyIfScheduleExists(model.getStartAt(), model.getEndAt());

        return repository.save(model);
    }

    @Override
    public void delete(final long id) {
        queryService.findbyId(id);
        repository.deleteById(id);
    }
}