package firenh.enchantmentpower.util;

import net.minecraft.util.RandomSource;

import java.util.*;

public class MiscUtil {
    public static <T> List<T> shuffleList(List<T> list, RandomSource random) {
        List<T> newList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        int index;
        final int size = list.size();

        while (newList.size() < size) {
            index = random.nextInt(size);

            if (!indexList.contains(index)) {
                newList.add(list.get(index));
                indexList.add(index);
            }
        }

        return newList;
    }
}
