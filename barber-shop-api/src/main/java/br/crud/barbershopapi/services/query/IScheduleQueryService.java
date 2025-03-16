package br.crud.barbershopapi.services.query;

import br.crud.barbershopapi.models.ScheduleModel;

import java.time.OffsetDateTime;
import java.util.List;

public interface IScheduleQueryService {

    ScheduleModel findbyId(final long id);

    List<ScheduleModel> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
