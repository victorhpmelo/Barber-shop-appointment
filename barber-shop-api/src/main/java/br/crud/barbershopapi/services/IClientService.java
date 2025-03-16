package br.crud.barbershopapi.services;

import br.crud.barbershopapi.models.ClientModel;

public interface IClientService {

    ClientModel save(final ClientModel entity);

    ClientModel update(final ClientModel entity);

    void delete(final long id);

}
