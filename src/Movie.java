public class Movie {

    private String title, producer;

    public Movie(String title, String producer) {

        this.title = title;
        this.producer = producer;
    }

    public String getTitle() {
        return title;

    }

    public String getProducer() {
        return producer;
    }

    @Override
    public String toString() {
        return getTitle() + " - " + getProducer();

    }
}
