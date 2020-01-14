package com.it.controller;

import com.it.dto.request.ClientRequestDto;
import com.it.dto.response.ClientResponseDto;
import com.it.model.Client;
import com.it.service.ClientService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Client controller.
 */
@RestController
@RequestMapping("/clients")
@Transactional
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private Mapper mapper;

    /**
     * Read all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> readAll() {
        final List<Client> entity = clientService.findAll();

        final List<ClientResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, ClientResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Read response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> read(@PathVariable Long id) {
        Client entity = clientService.findById(id);

        final ClientResponseDto responseDto = mapper.map(entity, ClientResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Create response entity.
     *
     * @param requestDto the request dto
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@Valid @RequestBody ClientRequestDto requestDto) throws Exception {
        final Client entity = mapper.map(requestDto, Client.class);
        clientService.save(entity);

        final ClientResponseDto responseDto = mapper.map(entity, ClientResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Update response entity.
     *
     * @param id         the id
     * @param requestDto the request dto
     * @return the response entity
     * @throws Exception the exception
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDto requestDto) throws Exception {
        final Client entity = mapper.map(requestDto, Client.class);
        clientService.update(entity);

        final ClientResponseDto responseDto = mapper.map(entity, ClientResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}

