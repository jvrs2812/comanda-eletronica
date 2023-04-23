package com.comanda.comanda.Command;

import com.comanda.comanda.Command.Domain.CommandBaseDto;
import com.comanda.comanda.Command.Domain.CommandGetDto;
import com.comanda.comanda.Command.UseCase.CommandUseCase;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommandController extends HandleValidationException {
    private final CommandUseCase _commands;
    @PostMapping("v1/api/{enterpriseId}/commands")
    public ResponseEntity save(@PathVariable("enterpriseId") String enterpriseId, @RequestBody @Valid CommandBaseDto dto) throws ComandaException {
        _commands.save(dto, enterpriseId);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("v1/api/{enterpriseId}/commands/{id}")
    public ResponseEntity<ResponseSchema<CommandGetDto>> getById(@PathVariable("enterpriseId") String enterpriseId, @PathVariable("id") String id) throws ComandaException {
        CommandGetDto response = _commands.getById(id, enterpriseId);
        return new ResponseEntity<ResponseSchema<CommandGetDto>>(new ResponseSchema<CommandGetDto>(response),HttpStatus.OK);
    }
}
