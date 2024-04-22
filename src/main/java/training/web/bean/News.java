package training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String brief;

    public News() {
    }

    public News(String title, String brief) {
        this.title = title;
        this.brief = brief;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) && Objects.equals(brief, news.brief);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, brief);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
