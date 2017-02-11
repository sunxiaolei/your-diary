package sunxl8.your_diary.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

@Entity
public class MemoEntity {

    @Id
    private Long id;
    private String memo;
    private Long memoId;
    private Date date;
    @Generated(hash = 541473078)
    public MemoEntity(Long id, String memo, Long memoId, Date date) {
        this.id = id;
        this.memo = memo;
        this.memoId = memoId;
        this.date = date;
    }
    @Generated(hash = 776636888)
    public MemoEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Long getMemoId() {
        return this.memoId;
    }
    public void setMemoId(Long memoId) {
        this.memoId = memoId;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
