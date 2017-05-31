package ru.kpfu.itis.ovchinnikov.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.ovchinnikov.model.entity.View;
import ru.kpfu.itis.ovchinnikov.services.UserService;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @Autowired
    @Qualifier("regitrationView")
    private View registrationView;

    @Autowired
    @Qualifier("adminHomeView")
    private View adminHomeView;

    @Autowired
    @Qualifier("userHomeView")
    private View userHomeView;

    @Autowired
    private AuthenticationProvider authProvider;

    @FXML
    Label label;

    @FXML
    Button loginbtn;

    @FXML
    Button registrationbtn;

    @FXML
    TextField login;

    @FXML
    TextField password;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
    }

    @FXML
    public void login(){
        Authentication authentication = new UsernamePasswordAuthenticationToken(login.getText(), password.getText());
        try {
            authentication = authProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                login.getScene().setRoot(adminHomeView.getView());
            } else login.getScene().setRoot(userHomeView.getView());
        }catch (AuthenticationException e) {
            password.clear();
            label.setText("Wrong user or password");
        }
    }

    @FXML
    public void registration(){
        login.clear();
        password.clear();
        registrationbtn.getScene().setRoot(registrationView.getView());
    }
}