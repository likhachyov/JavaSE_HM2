package Units;

import Interfaces.WaterUnit;

public class Submarine extends GameUnit implements WaterUnit {

    private static final int DISPLACEMENT = 500;

    public Submarine() {
        super();
    }

    public Submarine(int year, String country) {
        super(year, country);
    }

    @Override
    public int getDisplacement() {
        return DISPLACEMENT;
    }

    @Override
    public void init() {
        defence = 300;
        attack = 550;
        speed = 70;
        hashCode = ((int) System.currentTimeMillis()+attack)/defence*speed-DISPLACEMENT;
    }

    @Override
    public String getType() {
        return WATER_TYPE;
    }

    @Override
    public String getTypeMoving() {
        return MOVING_TYPE;
    }

    @Override
    public String[] getTargets() {
        return TARGETS;
    }
}
