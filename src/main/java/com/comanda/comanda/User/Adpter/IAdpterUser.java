package com.comanda.comanda.User.Adpter;

import com.comanda.comanda.User.Repository.UserModelRepository;
import com.comanda.comanda.User.domain.UserBaseDto;
import com.comanda.comanda.User.domain.UserResponseDto;

public interface IAdpterUser {
    void save(UserBaseDto dto);

    boolean existByEmail(String email);

    UserModelRepository findByEmail(String email);
}
