package com.comanda.comanda.User.Adpter;

import com.comanda.comanda.User.Repository.UserModelRepository;
import com.comanda.comanda.User.Repository.UserRepository;
import com.comanda.comanda.User.domain.UserBaseDto;
import com.comanda.comanda.User.domain.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AdpterUser implements IAdpterUser{

    @Autowired
    private UserRepository _repo;

    @Override
    public void save(UserBaseDto dto) {
        this._repo.save(UserModelRepository.convertToModel(dto));
    }

    @Override
    public boolean existByEmail(String email) {
        return this._repo.existbyEmail(email);
    }

    @Override
    public UserModelRepository findByEmail(String email) {
        return this._repo.findByEmail(email).get();
    }
}
