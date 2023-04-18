package com.comanda.comanda.Enterprise;

import com.comanda.comanda.Enterprise.UseCases.Enterprise;
import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import com.comanda.comanda.utils.ResponseSchema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnterpriseController extends HandleValidationException {

    @Autowired
    private Enterprise enterprise;

    @GetMapping("v1/api/enterprise")
    public ResponseEntity<ResponseSchema<List<EnterpriseDto>>> getMyEnterprise(){
        return new ResponseEntity<ResponseSchema<List<EnterpriseDto>>>(new ResponseSchema<List<EnterpriseDto>>(this.enterprise.getMyEnterprise()), HttpStatus.OK);
    }


    @PostMapping("v1/api/enterprise")
    public ResponseEntity postEnterprise(@Valid @RequestBody EnterpriseDto enterpriseDto) throws ComandaException {
        this.enterprise.save(enterpriseDto);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
