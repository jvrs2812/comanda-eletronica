package com.comanda.comanda.User;

import com.comanda.comanda.utils.Configuration.JwtService;
import com.comanda.comanda.User.UseCase.UserUseCase;
import com.comanda.comanda.User.domain.Login;
import com.comanda.comanda.User.domain.UserBaseDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.HandleValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController extends HandleValidationException {

    @Autowired
    private UserUseCase _user;

    @Autowired
    private JwtService jwtService;


    @PostMapping("v1/api/users/register")
    public ResponseEntity postUser(@RequestBody @Valid UserBaseDto dto) throws ComandaException {
        this._user.save(dto);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("v1/login")
    public ResponseEntity postLogin(@RequestBody Login login){

        return new ResponseEntity(this._user.authenticate(login), HttpStatus.OK);
    }

}
