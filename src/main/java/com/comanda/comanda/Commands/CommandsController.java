package com.comanda.comanda.Commands;

import com.comanda.comanda.Commands.Domain.CommandsBaseDto;
import com.comanda.comanda.Commands.Domain.CommandsGetDto;
import com.comanda.comanda.Commands.UseCase.CommandsUseCase;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandsController extends HandleValidationException {

    @Autowired
    private CommandsUseCase _commands;
    @PostMapping("v1/api/commands")
    public ResponseEntity save(@RequestBody @Valid CommandsBaseDto dto){
        try {
            _commands.save(dto);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (ComandaException e) {
            return new ResponseEntity<ResponseSchema<String>>(new ResponseSchema<String>(e.listError()), HttpStatus.valueOf(e.getStatusCode()));
        }
    }

    @GetMapping("v1/api/commands/{id}")
    public ResponseEntity<ResponseSchema<CommandsGetDto>> getById(@PathVariable("id") String id){
        try {
            CommandsGetDto response = _commands.getById(id);
            return new ResponseEntity<ResponseSchema<CommandsGetDto>>(new ResponseSchema<CommandsGetDto>(response),HttpStatus.OK);
        } catch (ComandaException e) {
            return new ResponseEntity<ResponseSchema<CommandsGetDto>>(new ResponseSchema<CommandsGetDto>(e.listError()),HttpStatus.valueOf(e.getStatusCode()));
        }
    }
}
