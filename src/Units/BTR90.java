package Units;

import Interfaces.GroundUnit;

public class BTR90 extends GameUnit implements GroundUnit {

    private static final int PASSABILITY = 50;

    public BTR90() {
        super();
    }

    public BTR90(int year, String country) {
        super(year, country);
    }

    @Override
    public int getPassability() {
        return PASSABILITY;
    }

    @Override
    public void init() {
        defence = 100;
        attack = 150;
        speed = 100;
        hashCode = ((int) System.currentTimeMillis()+attack)/defence*speed-PASSABILITY;
    }

    @Override
    public String getType() {
        return GROUND_TYPE;
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
