package Interfaces;

public interface GroundUnit {

    String GROUND_TYPE = "Наземный юнит";
    String MOVING_TYPE = "Driving";
    String[] TARGETS = {"Tank", "BTR"};
    //  Узнать проходимость
    int getPassability();
}
