package Units;

public abstract class GameUnit {

    //  Характеристики
    int defence;
    int attack;
    int speed;
    //  Мета-данные
    private int year;
    private String country;
    int hashCode;

    public abstract void init();

    public abstract String getType();

    public abstract String getTypeMoving();

    public abstract String[] getTargets();

    GameUnit() {
        init();
    }

    GameUnit(int year, String country){
        this.year = year;
        this.country = country;
        init();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;

        GameUnit unit = (GameUnit) obj;
        return unit.attack == attack && unit.defence == defence && unit.speed == speed;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return String.format("%1$s : %2$s %3$d %4$s", getType(), getClass(), year, country);
    }
}
