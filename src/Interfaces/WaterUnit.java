package Interfaces;

public interface WaterUnit{

    String WATER_TYPE = "Водный юнит";
    String MOVING_TYPE = "Swimming";
    String[] TARGETS = {"Submarine", "Ship"};
//  Узнать водоизмещение
    int getDisplacement();

}
