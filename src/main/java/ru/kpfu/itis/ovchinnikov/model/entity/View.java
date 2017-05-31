package ru.kpfu.itis.ovchinnikov.model.entity;

import javafx.scene.Parent;

public class View {

    private Parent view;
    private Object controller;

    public View(Parent view, Object controller) {
        this.view = view;
        this.controller = controller;
    }

    public Parent getView() {
        return view;
    }

    public void setView(Parent view) {
        this.view = view;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
