package Collection;

import Units.BTR90;
import Units.GameUnit;
import Units.MIG39;
import Units.Submarine;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Experiments {

    public static void main(String[] args) {
        Set<GameUnit> set = new TreeSet<GameUnit>(new Comparator<GameUnit>() {
            public int compare(GameUnit o1, GameUnit o2) {
                if(o1.getTypeMoving().equals(o2.getTypeMoving()))
                    return 0;
                else
                    return -1;
            }
        });
        set.add(new BTR90());
        set.addAll(new ArrayList<GameUnit>(){{
            add(new BTR90());
            add(new MIG39());
            add(new Submarine());
        }});
        set.add(new Submarine());
        System.out.println("Size: " + set.size());

        Stream<GameUnit> stream = set.stream();
        stream = stream.filter( o -> o.getType().equals(new BTR90().getType()));
        System.out.println(stream.count());

        Stream<Integer> stream2 = Stream.of(1,2,3,4,-1,-2,-3);
        stream2 = stream2.map(o -> o *33).peek(System.out::println);
        Optional<Integer> optional = stream2.reduce( (x,y) -> x*y);
        System.out.println(optional.get());


        MyLinkedList<Submarine> list = new MyLinkedList<Submarine>();
        Submarine f = new Submarine();
        list.add(f);
        list.add(new Submarine());
        list.add(new Submarine());
        list.add(new Submarine());
        list.removeAll(list);
        System.out.println(list.size());


    }
}
