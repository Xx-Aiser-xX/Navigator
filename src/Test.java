import navigator.entities.Route;

//import java.util.HashSet;
//import java.util.Set;
import struct.impl.HashSet;
import struct.Set;

import java.util.Random;

public class Test {
    private static Random random = new Random();
    public static void main(String[] args) {
        String test;
        int count = (int) Math.pow(10, 7);
        int errorRate = 0;
        Set<Integer> hash = new HashSet<>(count);
        Set<String> id = new HashSet<>(count);
        for (int i = 0; i < count; i++){
            test = randomId();
            if (!id.add(test))
                errorRate++;
            hash.add(findSlotNumber(test, count));
        }
        System.out.println("ожидаемый результат :" + 0);
        System.out.println("количество колизий :" + (count - hash.size()));
        System.out.println("погрешность :" + errorRate);
    }
    private static String randomId(){
        String id = "";
        for (int i = 0; i < 8; i++){
            id += (char) random.nextInt(65, 91);
        }
        return id;
    }

    public static int hashCode(String id) {
        if (id == null) {
            return 0;
        }
        int hashSum = 13;
        for (int i = 0; i < id.length(); i++) {
            hashSum = hashSum * 19 + id.charAt(i) * i;
        }
        return hashSum;
    }

    private static int findSlotNumber(String test, int capacity) {
        return Math.abs(hashCode(test)) % capacity;
    }
}
