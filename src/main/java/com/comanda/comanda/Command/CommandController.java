package com.comanda.comanda.Command;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;
import com.comanda.comanda.Command.UseCase.CommandUseCase;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController extends HandleValidationException {

    @Autowired
    private CommandUseCase _commands;
    @PostMapping("v1/api/commands")
    public ResponseEntity save(@RequestBody @Valid CommandBaseDto dto) throws ComandaException {
        _commands.save(dto);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("v1/api/commands/{id}")
    public ResponseEntity<ResponseSchema<CommandGetDto>> getById(@PathVariable("id") String id) throws ComandaException {
        CommandGetDto response = _commands.getById(id);
        return new ResponseEntity<ResponseSchema<CommandGetDto>>(new ResponseSchema<CommandGetDto>(response),HttpStatus.OK);
    }
}
