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

public class EditRentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("adminHomeView")
    private View adminHomeView;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

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
    Button saverentBtn;

    @PostConstruct
    public void init() {
        if(id!=null) {
            Rent rent = rentService.findById(id);
            name.setText(rent.getClient());
            number.setText(rent.getNumber());
            car.setText(rent.getCar());
            startDate.setText(rent.getRentDate());
            returnDate.setText(rent.getReturnDate());
        }
    }

    @FXML
    public void saveRent(){
        String result = validator.rentCarValidate(name.getText(),number.getText(),car.getText(),startDate.getText(),returnDate.getText());
        if(result.equals("Success")) {
        Rent rent = new Rent(
                name.getText(),
                number.getText(),
                carService.getCar(car.getText()),
                startDate.getText(),
                returnDate.getText()
        );
        rent.setId(Long.valueOf(id));
        name.clear();
        number.clear();
        car.clear();
        startDate.clear();
        returnDate.clear();
        rentService.update(rent);
        AdminHomeController controller = (AdminHomeController) adminHomeView.getController();
        controller.refreshrent();
        Stage stage = (Stage) saverentBtn.getScene().getWindow();
        stage.getScene().setRoot(new Pane());
        stage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

}
