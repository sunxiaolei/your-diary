package sunxl8.your_diary.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;


/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

@Entity
public class ItemEntity {

    @Id
    private Long id;
    private Date date;

    private String account;

    private String itemTitle;

    private int itemType;

    private int itemCount;

    @Generated(hash = 832177396)
    public ItemEntity(Long id, Date date, String account, String itemTitle,
            int itemType, int itemCount) {
        this.id = id;
        this.date = date;
        this.account = account;
        this.itemTitle = itemTitle;
        this.itemType = itemType;
        this.itemCount = itemCount;
    }

    @Generated(hash = 365170573)
    public ItemEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
