package ru.kpfu.itis.ovchinnikov.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.ovchinnikov.controller.*;
import ru.kpfu.itis.ovchinnikov.model.entity.View;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class MyConfig {
    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("view/login.fxml");
    }

    @Bean(name = "regitrationView")
    public View getRegistrationView() throws IOException {
        return loadView("view/registration.fxml");
    }

    @Bean(name = "adminHomeView")
    public View getAdminHomeView() throws IOException {
        return loadView("view/adminHome.fxml");
    }

    @Bean(name = "userHomeView")
    public View getUserHomeView() throws IOException {
        return loadView("view/userHome.fxml");
    }

    @Bean(name = "editCarView")
    public View getEditCarView() throws IOException {
        return loadView("view/editCar.fxml");
    }

    @Bean(name = "editRentView")
    public View getEditRentView() throws IOException {
        return loadView("view/editRent.fxml");
    }

    @Bean(name = "rentCarView")
    public View getRentCarView() throws IOException {
        return loadView("view/rentCar.fxml");
    }

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public RegistrationController getRegistrationController() throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }

    @Bean
    public AdminHomeController getAdminHomeController() throws IOException {
        return (AdminHomeController) getAdminHomeView().getController();
    }

    @Bean
    public UserHomeController getUserHomeController() throws IOException {
        return (UserHomeController) getUserHomeView().getController();
    }

    @Bean
    public EditCarController getEditCarController() throws IOException {
        return (EditCarController) getEditCarView().getController();
    }

    @Bean
    public EditRentController getEditrentController() throws IOException {
        return (EditRentController) getEditRentView().getController();
    }

    @Bean
    public RentCarController getRentCarController() throws IOException {
        return (RentCarController) getRentCarView().getController();
    }

    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View((Parent) loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

}
