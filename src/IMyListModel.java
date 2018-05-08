import javax.swing.*;

public interface IMyListModel extends ListModel<Movie> {
    void update(int index, Movie text);

    void add(Movie text);

    Movie remove(int index);

    void clear();

//    void filter(FilterStrategy strategy);
}
