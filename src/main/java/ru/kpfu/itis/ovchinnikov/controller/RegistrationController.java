package ru.kpfu.itis.ovchinnikov.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.ovchinnikov.model.User;
import ru.kpfu.itis.ovchinnikov.model.entity.View;
import ru.kpfu.itis.ovchinnikov.services.UserService;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class RegistrationController {

    @Autowired
    @Qualifier("mainView")
    private View mainView;

    @Autowired
    private UserService userService;

    @FXML
    private Button backbtn;

    @FXML
    private Button registrationbtn;

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    @PostConstruct
    public void init() {

    }

    public void back(){
        backbtn.getScene().setRoot(mainView.getView());
    }

    public void registration(){
        String usernameString = username.getText();
        String passwordString = password.getText();
        username.clear();
        password.clear();
        if(userService.findByUsername(usernameString)==null){
            User user = new User();
            user.setUsername(usernameString);
            user.setPassword(passwordString);
            userService.save(user);
            back();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User already exists");
            alert.show();
        }
    }
}
