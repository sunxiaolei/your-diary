package sunxl8.your_diary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import org.greenrobot.greendao.database.Database;

import sunxl8.your_diary.db.dao.DaoMaster;
import sunxl8.your_diary.db.dao.DiaryEntityDao;
import sunxl8.your_diary.db.dao.ItemEntityDao;
import sunxl8.your_diary.db.dao.MemoEntityDao;
import sunxl8.your_diary.db.entity.DiaryEntity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {

    public GreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.getInstance().migrate(db,
                ItemEntityDao.class,
                MemoEntityDao.class,
                DiaryEntityDao.class);
    }
}
