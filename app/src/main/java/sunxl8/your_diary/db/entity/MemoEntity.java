package sunxl8.your_diary.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

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
    private boolean line;
    @Generated(hash = 776636888)
    public MemoEntity() {
    }
    @Generated(hash = 1725894247)
    public MemoEntity(Long id, String memo, Long memoId, Date date, boolean line) {
        this.id = id;
        this.memo = memo;
        this.memoId = memoId;
        this.date = date;
        this.line = line;
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
    public boolean getLine() {
        return this.line;
    }
    public void setLine(boolean line) {
        this.line = line;
    }

}
