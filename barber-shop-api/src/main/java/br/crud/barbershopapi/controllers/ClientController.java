package br.crud.barbershopapi.controllers;

import br.crud.barbershopapi.controllers.request.SaveClientRequest;
import br.crud.barbershopapi.controllers.request.UpdateClientRequest;
import br.crud.barbershopapi.controllers.response.ClientDetailResponse;
import br.crud.barbershopapi.controllers.response.ListClientResponse;
import br.crud.barbershopapi.controllers.response.SaveClientResponse;
import br.crud.barbershopapi.controllers.response.UpdateClientResponse;
import br.crud.barbershopapi.mapper.IClientMapper;
import br.crud.barbershopapi.services.IClientService;
import br.crud.barbershopapi.services.query.IClientQueryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var model = mapper.toModel(request);
        service.save(model);
        return mapper.toSaveResponse(model);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var model = mapper.toModel(id, request);
        service.update(model);
        return mapper.toUpdateResponse(model);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var model = queryService.findById(id);
        return mapper.toDetailResponse(model);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }

}