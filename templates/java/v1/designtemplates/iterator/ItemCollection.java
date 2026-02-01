package designtemplates.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemCollection implements Iterable<Item> {
    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
