package com.comanda.comanda.Enterprise.UseCases;

import com.comanda.comanda.Enterprise.Repository.EnterpriseModelRepository;
import com.comanda.comanda.Enterprise.Repository.EnterpriseRepository;
import com.comanda.comanda.Enterprise.domain.EnterpriseDto;
import com.comanda.comanda.Enterprise.domain.EnterpriseFullDto;
import com.comanda.comanda.User.Repository.UserRepository;
import com.comanda.comanda.utils.Configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Enterprise {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public void save(EnterpriseDto enterpriseDto){


        EnterpriseModelRepository model = EnterpriseModelRepository.convertToModel(enterpriseDto,
                this.jwtService.getUserContextSecurity());

        this.enterpriseRepository.save(model);

    }
}
