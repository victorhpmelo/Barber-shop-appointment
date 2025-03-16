package br.crud.barbershopapi.services.query;

import br.crud.barbershopapi.models.ClientModel;

import java.util.List;


public interface IClientQueryService {

    ClientModel findById(final long id);

    List<ClientModel> list();

    void verifyPhone(final String phone);

    void verifyPhone(final long id, final String phone);

    void verifyEmail(final String email);

    void verifyEmail(final long id,final String email);

}
