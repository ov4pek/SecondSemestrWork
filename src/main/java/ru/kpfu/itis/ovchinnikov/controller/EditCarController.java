package ru.kpfu.itis.ovchinnikov.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.ovchinnikov.model.Car;
import ru.kpfu.itis.ovchinnikov.model.entity.View;
import ru.kpfu.itis.ovchinnikov.services.CarService;
import ru.kpfu.itis.ovchinnikov.util.Validator;

import javax.annotation.PostConstruct;

public class EditCarController {

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
    TextField model;
    @FXML
    TextField mileage;
    @FXML
    TextField year;
    @FXML
    TextField power;
    @FXML
    TextField cost;
    @FXML
    Button saveCarBtn;

    @PostConstruct
    public void init() {
        if(id!=null) {
            Car car = carService.getCarById(id);
            model.setText(car.getModel());
            mileage.setText(String.valueOf(car.getMileage()));
            year.setText(String.valueOf(car.getYear()));
            power.setText(String.valueOf(car.getPower()));
            cost.setText(String.valueOf(car.getCost()));
        }
    }

    @FXML
    public void saveCar(){
        String result = validator.editCarValidate(model.getText(),mileage.getText(),year.getText(),power.getText(),cost.getText());
        if(result.equals("Success")) {
        Car car = new Car(
                model.getText(),
                Integer.parseInt(year.getText()),
                Integer.parseInt(mileage.getText()),
                Integer.parseInt(power.getText()),
                Integer.parseInt(cost.getText())
        );
        car.setId(Long.valueOf(id));
        carService.save(car);
        AdminHomeController controller = (AdminHomeController) adminHomeView.getController();
        controller.refreshCars();
        Stage stage = (Stage) saveCarBtn.getScene().getWindow();
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
