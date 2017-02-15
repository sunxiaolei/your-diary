package sunxl8.your_diary.ui.memo;

import java.util.List;

import sunxl8.your_diary.base.IPresenter;
import sunxl8.your_diary.base.IView;
import sunxl8.your_diary.db.entity.MemoEntity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public interface MemoContract {

    interface View extends IView {
        void showList(List<MemoEntity> list);
    }

    interface Presenter extends IPresenter<View> {

        void addItem(Long memoId, String string);

        void getItemList(Long memoId);

        void deleteItem(Long id, Long memoId);

        void setLine(boolean addLine, MemoEntity entity);
    }
}
