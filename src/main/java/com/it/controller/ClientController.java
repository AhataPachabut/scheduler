package com.it.controller;

import com.it.dto.request.ClientRequestDto;
import com.it.dto.response.ClientResponseDto;
import com.it.model.Client;
import com.it.service.ClientService;
import com.it.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientRepository;

    @Autowired
    private UserService userRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> readAll() {
        final List<Client> entity = clientRepository.findAll();

        final List<ClientResponseDto> responseDto = entity.stream()
                .map((i) -> mapper.map(i, ClientResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> read(@PathVariable Long id) {
        Client entity = clientRepository.findById(id);

        final ClientResponseDto responseDto = mapper.map(entity, ClientResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@Valid @RequestBody ClientRequestDto requestDto) throws Exception {
        final Client entity = mapper.map(requestDto, Client.class);
        entity.setUser(userRepository.findById(requestDto.getUser()));
        clientRepository.save(entity);

        final ClientResponseDto responseDto = mapper.map(entity, ClientResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDto requestDto) throws Exception {
        Client entity = clientRepository.findById(id);
        entity.setUser(userRepository.findById(requestDto.getUser()));
        clientRepository.update(entity);

        final ClientResponseDto responseDto = mapper.map(entity, ClientResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}

