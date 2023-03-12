package com.comanda.comanda.User.UseCase;

import com.comanda.comanda.User.Adpter.IAdpterUser;
import com.comanda.comanda.User.Exception.UserException;
import com.comanda.comanda.User.domain.AuthResponse;
import com.comanda.comanda.User.domain.Login;
import com.comanda.comanda.User.domain.UserBaseDto;
import com.comanda.comanda.utils.ComandaException;
import com.comanda.comanda.utils.Configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase {


    private final IAdpterUser _user;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public void save(UserBaseDto dto) throws ComandaException {
        if(this._user.existByEmail(dto.getEmail())){
            throw new ComandaException(UserException.USER_EMAIL_EXIST);
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        dto.setCpf(passwordEncoder.encode(dto.getCpf()));

        this._user.save(dto);
    }

    public AuthResponse authenticate(Login login){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.email(),
                login.password()
        ));

        var user = this._user.findByEmail(login.email());

        var jwt = this.jwtService.generateToken(user);

        return AuthResponse.builder().token(jwt).build();
    }
}
