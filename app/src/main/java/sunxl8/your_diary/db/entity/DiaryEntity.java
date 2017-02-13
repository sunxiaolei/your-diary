package sunxl8.your_diary.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sunxl8 on 2017/2/13.
 */

@Entity
public class DiaryEntity {

    @Id
    private Long id;
    private String Title;
    private String content;
    private int weather;
    private int mood;
    private Date date;
    @Generated(hash = 1855029381)
    public DiaryEntity(Long id, String Title, String content, int weather, int mood,
            Date date) {
        this.id = id;
        this.Title = Title;
        this.content = content;
        this.weather = weather;
        this.mood = mood;
        this.date = date;
    }
    @Generated(hash = 1642920447)
    public DiaryEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getWeather() {
        return this.weather;
    }
    public void setWeather(int weather) {
        this.weather = weather;
    }
    public int getMood() {
        return this.mood;
    }
    public void setMood(int mood) {
        this.mood = mood;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
