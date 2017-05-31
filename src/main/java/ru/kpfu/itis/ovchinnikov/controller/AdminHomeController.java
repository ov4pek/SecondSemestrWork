package ru.kpfu.itis.ovchinnikov.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.ovchinnikov.model.Car;
import ru.kpfu.itis.ovchinnikov.model.Rent;
import ru.kpfu.itis.ovchinnikov.model.entity.View;
import ru.kpfu.itis.ovchinnikov.services.CarService;
import ru.kpfu.itis.ovchinnikov.services.RentService;
import ru.kpfu.itis.ovchinnikov.util.Validator;

import javax.annotation.PostConstruct;
import java.util.List;

public class AdminHomeController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private Validator validator;

    @Autowired
    @Qualifier("editCarView")
    private View editCarView;

    @Autowired
    @Qualifier("mainView")
    private View mainView;

    @Autowired
    @Qualifier("editRentView")
    private View editrentView;

    @FXML
    TableView<Rent> rentTable;
    @FXML
    TableView<Car> carsTable;
    @FXML
    TextField name;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField car;
    @FXML
    TextField startDate;
    @FXML
    TextField returnDate;
    @FXML
    Button addRentBtn;
    @FXML
    TextField carModel;
    @FXML
    TextField carMileage;
    @FXML
    TextField carYear;
    @FXML
    TextField carPower;
    @FXML
    TextField carCost;
    @FXML
    Button addNewCarBtn;
    @FXML
    TextField carId;
    @FXML
    Button removeCarBtn;
    @FXML
    Button editCarBtn;
    @FXML
    Button logoutBtn;
    @FXML
    TextField rentId;
    @FXML
    Button editRentBtn;
    @FXML
    Button removeRentBtn;

    @PostConstruct
    public void init() {
        List<Rent> reservetions = rentService.findAll();
        ObservableList<Rent> data = FXCollections.observableArrayList(reservetions);

        TableColumn<Rent, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Rent, String>("id"));
        TableColumn<Rent, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Rent, String>("client"));
        TableColumn<Rent, String> phoneColumn = new TableColumn<>("Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Rent, String>("number"));
        TableColumn<Rent, String> carColumn = new TableColumn<>("Car");
        carColumn.setCellValueFactory(new PropertyValueFactory<Rent, String>("car"));
        TableColumn<Rent, String> startColumn = new TableColumn<>("start");
        startColumn.setCellValueFactory(new PropertyValueFactory<Rent, String>("rentDate"));
        TableColumn<Rent, String> returnColumn = new TableColumn<>("Return");
        returnColumn.setCellValueFactory(new PropertyValueFactory<Rent, String>("returnDate"));
        rentTable.getColumns().setAll(idColumn, nameColumn, phoneColumn, carColumn, startColumn, returnColumn);

        rentTable.setItems(data);

        List<Car> cars = carService.findAll();
        ObservableList<Car> carData = FXCollections.observableArrayList(cars);

        TableColumn<Car, String> carIdColumn = new TableColumn<>("ID");
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("id"));
        TableColumn<Car, String> carModelColumn = new TableColumn<>("Model");
        carModelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        TableColumn<Car, String> carMileageColumn = new TableColumn<>("Mileage");
        carMileageColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("mileage"));
        TableColumn<Car, String> carPowerColumn = new TableColumn<>("Power");
        carPowerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("power"));
        TableColumn<Car, String> carYearColumn = new TableColumn<>("Year");
        carYearColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("year"));
        TableColumn<Car, String> carCostColumn = new TableColumn<>("Cost");
        carCostColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("cost"));
        carsTable.getColumns().setAll(carIdColumn, carModelColumn, carMileageColumn, carPowerColumn, carYearColumn, carCostColumn);
        carsTable.setItems(carData);
    }

    @FXML
    public void addnew(){
        String result = validator.rentCarValidate(name.getText(), phoneNumber.getText(),car.getText(), startDate.getText(), returnDate.getText());
        if(result.equals("Success")) {
        Rent rent = new Rent(name.getText(),
                phoneNumber.getText(),
                carService.getCar(car.getText()),
                startDate.getText(),
                returnDate.getText());
        rentService.save(rent);
        phoneNumber.clear();
        name.clear();
        car.clear();
        startDate.clear();
        returnDate.clear();
        refreshrent();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

    @FXML
    public void removeRent(){
        String id = rentId.getText();
        rentService.remove(id);
        rentId.clear();
        refreshrent();
    }

    @FXML
    public void editRent(){
        final Stage stage = new Stage();
        stage.setTitle("Edit rent");
        EditRentController controller = (EditRentController) editrentView.getController();
        controller.setId(rentId.getText());
        controller.init();
        stage.setScene(new Scene(editrentView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        rentId.clear();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.getScene().setRoot(new Pane());
                stage.close();
            }
        });
        stage.show();
    }

    @FXML
    public void addNewCar(){
        String result = validator.editCarValidate(carModel.getText(),carMileage.getText(),carYear.getText(),carPower.getText(),carCost.getText());
        if(result.equals("Success")) {
        Car car = new Car(
                carModel.getText(),
                Integer.parseInt(carYear.getText()),
                Integer.parseInt(carMileage.getText()),
                Integer.parseInt(carPower.getText()),
                Integer.parseInt(carCost.getText()));
        carService.save(car);
        carModel.clear();
        carMileage.clear();
        carYear.clear();
        carPower.clear();
        carCost.clear();
        refreshCars();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(result);
            alert.show();
        }
    }

    public void removeCar(){
        String id=carId.getText();
        carService.remove(Long.valueOf(id));
        carId.clear();
        refreshCars();
    }

    public void editCar(){
        final Stage stage = new Stage();
        stage.setTitle("Edit Car");
        EditCarController controller = (EditCarController) editCarView.getController();
        controller.setId(carId.getText());
        controller.init();
        stage.setScene(new Scene(editCarView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.getScene().setRoot(new Pane());
                stage.close();
            }
        });
        carId.clear();
        stage.show();
    }

    public void logout(){
        SecurityContextHolder.clearContext();
        logoutBtn.getScene().setRoot(mainView.getView());
    }

    public void refreshrent() {
        List<Rent> rents = rentService.findAll();
        ObservableList<Rent> rentData = FXCollections.observableArrayList(rents);
        rentTable.setItems(rentData);
    }

    public void refreshCars(){
        List<Car> cars = carService.findAll();
        ObservableList<Car> carData = FXCollections.observableArrayList(cars);
        carsTable.setItems(carData);
    }
}
