package br.crud.barbershopapi.services.query.impl;

import br.crud.barbershopapi.exception.EmailInUseException;
import br.crud.barbershopapi.exception.NotFoundException;
import br.crud.barbershopapi.exception.PhoneInUseException;
import br.crud.barbershopapi.models.ClientModel;
import br.crud.barbershopapi.repositories.IClientRepository;
import br.crud.barbershopapi.services.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class ClientQueryService implements  IClientQueryService {
    private final IClientRepository repository;

    @Override
    public ClientModel findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o cliente de id " + id)
        );
    }

    @Override
    public List<ClientModel> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(final String phone) {
        if (repository.existsByPhone(phone)) {
            var message = String.format("O telefone %s já está em uso", phone);
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(final long id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = String.format("O telefone %s já está em uso", phone);
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final String email) {
        if (repository.existsByEmail(email)) {
            var message = String.format("O e-mail  %s  já está em uso",email);
            throw new EmailInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)) {
            var message = String.format("O e-mail  %s  já está em uso",email);
            throw new EmailInUseException(message);
        }
    }
}
