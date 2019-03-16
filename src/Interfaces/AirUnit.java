package Interfaces;

public interface AirUnit {

    String AIR_TYPE = "Воздушный юнит";
    String MOVING_TYPE = "Flying";
    String[] TARGETS = {"Plane", "BTR"};
    //  Узнать высоту полёта
    int getMaxHeight();
}
