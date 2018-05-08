import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class MyListModel implements IMyListModel {

    ArrayList<Movie> listItems = new ArrayList<>();
    ArrayList<ListDataListener> listener = new ArrayList<>();

    @Override
    public int getSize() {
        return listItems.size();
    }

    @Override
    public Movie getElementAt(int index) {
        return listItems.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listener.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listener.remove(l);
    }

    @Override
    public void update(int index, Movie text) {
        listItems.set(index, text);
        for (ListDataListener l : listener ) {
            l.contentsChanged(new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,index,index));
        }
    }

    @Override
    public void add(Movie text) {
        if( !listItems.contains(text))
            listItems.add(text);
        for (ListDataListener l : listener ) {
            l.intervalAdded(new ListDataEvent(this,ListDataEvent.INTERVAL_ADDED,listItems.size()-1,listItems.size()-1));
        }
    }

    @Override
    public Movie remove(int index){
        Movie m = listItems.remove(index);
        for (ListDataListener l : listener ) {
            l.intervalRemoved(new ListDataEvent(this,ListDataEvent.INTERVAL_REMOVED,index, index));
        }
        return m;
    }

    @Override
    public void clear() {
        int size = listItems.size();
        listItems.clear();
        for (ListDataListener l : listener ) {
            l.intervalRemoved(new ListDataEvent(this,ListDataEvent.INTERVAL_REMOVED,0, size));
        }
    }
/*
    @Override
    public void filter(FilterStrategy strategy) {

    }
    */
}

