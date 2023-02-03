package com.comanda.comanda.Table;

import com.comanda.comanda.Table.UseCase.TableUseCase;
import com.comanda.comanda.Table.domain.TableBaseDto;
import com.comanda.comanda.Table.domain.TableResponseDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import com.comanda.comanda.utils.commom.ResponsePageable;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TableController extends HandleValidationException {

    @Autowired
    private TableUseCase _table;
    @PostMapping("v1/api/table")
    public ResponseEntity createTable(@RequestBody @Valid TableBaseDto dto) throws ComandaException {
        _table.save(dto);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("v1/api/tables")
    public ResponseEntity<ResponseSchema<ResponsePageable<TableResponseDto>>> getAll(@RequestParam("page") int page){
        return new ResponseEntity<ResponseSchema<ResponsePageable<TableResponseDto>>>(new ResponseSchema<ResponsePageable<TableResponseDto>>(_table.getAll(page)), HttpStatus.OK);
    }

    @DeleteMapping("v1/api/table/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws ComandaException {
        _table.delete(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
