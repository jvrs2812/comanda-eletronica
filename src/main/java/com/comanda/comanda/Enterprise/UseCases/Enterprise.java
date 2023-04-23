package com.comanda.comanda.Enterprise.UseCases;

import com.comanda.comanda.Enterprise.Adpter.IAdpterEnterprise;
import com.comanda.comanda.Enterprise.Exception.EnterpriseException;
import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Enterprise.Repository.EnterpriseRepository;
import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.User.Repository.UserRepository;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Enterprise {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAdpterEnterprise adapterEnterprise;

    public void save(EnterpriseDto enterpriseDto) throws ComandaException {

        if(this.adapterEnterprise.existCnpj(enterpriseDto.getCnpj()))
            throw new ComandaException(EnterpriseException.ENTERPRISE_CNPJ_EXISTS);

        this.adapterEnterprise.save(enterpriseDto, this.jwtService.getUserContextSecurity().toDto());

    }

    public List<EnterpriseDto> getMyEnterprise(){
        return this.adapterEnterprise.findAllEnterpriseByUserId(this.jwtService.getIdUserContextSecurity());
    }
}
