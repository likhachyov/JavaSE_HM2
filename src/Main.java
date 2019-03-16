import Units.MIG39;
import Units.BTR90;
import Units.Submarine;

public class Main {

    public static void main(String[] args) {
        Submarine lodka = new Submarine(1960, "UK"); // Экземпляры юнитов
        BTR90 tank = new BTR90(1986, "USSR");
        MIG39 samolet = new MIG39(2000, "Russia");

        System.out.println(lodka);              // Работает переопределённый метод toString()
        System.out.println(lodka.hashCode());

        System.out.println(tank);
        System.out.println(tank.hashCode());    // Работает переопределённый метод hashCode()

        System.out.println(samolet);
        System.out.println(samolet.hashCode());

        System.out.println(tank.equals(new BTR90()));    // Работает переопределённый метод equals()
        System.out.println(tank.equals(new Submarine()));
    }
}
