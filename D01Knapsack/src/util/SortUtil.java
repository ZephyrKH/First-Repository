package util;

import entity.ItemGroup;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {
    public static void sortByRatio3(List<ItemGroup> list) {
        Collections.sort(list, new Comparator<ItemGroup>() {
            @Override
            public int compare(ItemGroup o1, ItemGroup o2) {
                return Double.compare(o2.getRatio3(), o1.getRatio3());
            }
        });
    }
}