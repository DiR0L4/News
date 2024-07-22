package training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String brief;
    private String info;
    private String imgPath;
    private int tagId;

    public News() {
    }

    public News(int id, String title, String brief, String info, String imgPath, int tagId) {
        super();
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.info = info;
        this.imgPath = imgPath;
        this.tagId = tagId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && tagId == news.tagId && Objects.equals(title, news.title) && Objects.equals(brief, news.brief) && Objects.equals(info, news.info) && Objects.equals(imgPath, news.imgPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, brief, info, imgPath, tagId);
    }
}
