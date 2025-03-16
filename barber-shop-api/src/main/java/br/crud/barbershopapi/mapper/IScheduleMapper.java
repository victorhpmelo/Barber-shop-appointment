package br.crud.barbershopapi.mapper;

import br.crud.barbershopapi.controllers.request.SaveScheduleRequest;
import br.crud.barbershopapi.controllers.response.ClientScheduleAppointmentResponse;
import br.crud.barbershopapi.controllers.response.SaveScheduleResponse;
import br.crud.barbershopapi.controllers.response.ScheduleAppointmentMonthResponse;
import br.crud.barbershopapi.models.ScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleModel toModel(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleModel entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleModel> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleModel> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleModel entity);
}