package br.crud.barbershopapi.mapper;

import br.crud.barbershopapi.controllers.request.SaveClientRequest;
import br.crud.barbershopapi.controllers.request.UpdateClientRequest;
import br.crud.barbershopapi.controllers.response.ClientDetailResponse;
import br.crud.barbershopapi.controllers.response.ListClientResponse;
import br.crud.barbershopapi.controllers.response.SaveClientResponse;
import br.crud.barbershopapi.controllers.response.UpdateClientResponse;
import br.crud.barbershopapi.models.ClientModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientModel toModel(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientModel entity);

    @Mapping(target = "schedules", ignore = true)
    ClientModel toModel(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientModel entity);

    ClientDetailResponse toDetailResponse(final ClientModel entity);

    List<ListClientResponse> toListResponse(final List<ClientModel> entities);

}