package Units;

import Interfaces.AirUnit;

public class MIG39 extends GameUnit implements  AirUnit {

    private static final int MAX_HEIGHT = 8000;

    public MIG39() {
        super();
    }

    public MIG39(int year, String country) {
        super(year, country);
    }

    @Override
    public int getMaxHeight() {
        return MAX_HEIGHT;
    }

    @Override
    public void init() {
        defence = 200;
        attack = 500;
        speed = 3000;
        hashCode = ((int) System.currentTimeMillis()+attack)/defence*speed-MAX_HEIGHT;
    }

    @Override
    public String getType() {
        return AIR_TYPE;
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
