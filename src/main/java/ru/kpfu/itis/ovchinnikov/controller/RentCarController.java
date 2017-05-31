package ru.kpfu.itis.ovchinnikov.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.ovchinnikov.model.Rent;
import ru.kpfu.itis.ovchinnikov.model.entity.View;
import ru.kpfu.itis.ovchinnikov.services.CarService;
import ru.kpfu.itis.ovchinnikov.services.RentService;
import ru.kpfu.itis.ovchinnikov.util.Validator;

import javax.annotation.PostConstruct;

public class RentCarController {

    @Autowired
    private CarService carService;

    @Autowired
    private RentService rentService;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("userHomeView")
    private View userHomeView;

    @FXML
    TextField name;
    @FXML
    TextField number;
    @FXML
    TextField car;
    @FXML
    TextField startDate;
    @FXML
    TextField returnDate;
    @FXML
    Button rentCarBtn;
    @FXML
    Button cancelBtn;

    @PostConstruct
    public void init() {
    }

    public void rentCar(){
        String result = validator.rentCarValidate(name.getText(),number.getText(),car.getText(),startDate.getText(),returnDate.getText());
        if(result.equals("Success")) {
            if (carService.getCar(car.getText()) != null) {
                Rent rent = new Rent(
                        name.getText(),
                        number.getText(),
                        carService.getCar(car.getText()),
                        startDate.getText(),
                        returnDate.getText()
                );
                rentService.save(rent);
                UserHomeController controller = (UserHomeController) userHomeView.getController();
                controller.refresh();
                cancel();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

    public void cancel(){
        Stage stage = (Stage) rentCarBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
    }
}
