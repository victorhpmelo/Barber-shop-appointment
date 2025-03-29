package br.crud.barbershopapi.services.impl;

import br.crud.barbershopapi.models.ClientModel;
import br.crud.barbershopapi.repositories.IClientRepository;
import br.crud.barbershopapi.services.IClientService;
import br.crud.barbershopapi.services.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final IClientRepository repository;
    private final IClientQueryService queryService;

    @Override
    public ClientModel save(final ClientModel model) {
        queryService.verifyEmail(model.getEmail());
        queryService.verifyPhone(model.getPhone());

        return repository.save(model);
    }

    @Override
    public ClientModel update(final ClientModel model) {
        queryService.verifyEmail(model.getId(), model.getEmail());
        queryService.verifyPhone(model.getId(), model.getPhone());

        var stored = queryService.findById(model.getId());
        stored.setName(model.getName());
        stored.setPhone(model.getPhone());
        stored.setEmail(model.getEmail());
        return repository.save(stored);
    }

    @Override
    public void delete(final long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
