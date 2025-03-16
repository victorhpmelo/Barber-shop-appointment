package br.crud.barbershopapi.services;

import br.crud.barbershopapi.models.ScheduleModel;

public interface IScheduleService {

    ScheduleModel save(final ScheduleModel entity);

    void delete(final long id);
}
