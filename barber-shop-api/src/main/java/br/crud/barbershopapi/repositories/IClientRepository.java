package br.crud.barbershopapi.repositories;

import br.crud.barbershopapi.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IClientRepository extends JpaRepository<ClientModel, Long> {

    boolean existsByEmail( final String email);

    Optional<ClientModel> findByEmail(final String email);

    boolean existsByPhone( final String phone);

    Optional<ClientModel> findByPhone(final String phone);
}
