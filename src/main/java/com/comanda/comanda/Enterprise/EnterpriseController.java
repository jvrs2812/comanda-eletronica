package com.comanda.comanda.Enterprise;

import com.comanda.comanda.Enterprise.UseCases.Enterprise;
import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.utils.HandleValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnterpriseController extends HandleValidationException {

    @Autowired
    private Enterprise enterprise;


    @PostMapping("v1/api/enterprise")
    public ResponseEntity postEnterprise(@Valid @RequestBody EnterpriseDto enterpriseDto){
        this.enterprise.save(enterpriseDto);
        return ResponseEntity.ok(null);
    }
}
