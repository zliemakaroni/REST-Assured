package Utils;

import Models.Test;

import java.util.Iterator;
import java.util.List;

public class TestListUtil {

    public static boolean isTestListSorted(List<Test> list) {

        if (list.isEmpty() || list.size() == 1) {
            return true;
        }

        Iterator<Test> iter = list.iterator();
        Test current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isListContainsTestsFromList(List<Test> l1, List<Test> l2) {
        return l1.containsAll(l2);
    }
}
