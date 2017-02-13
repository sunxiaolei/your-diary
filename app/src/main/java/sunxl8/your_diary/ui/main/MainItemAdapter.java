package sunxl8.your_diary.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sunxl8.your_diary.R;
import sunxl8.your_diary.db.entity.ItemEntity;
import sunxl8.your_diary.ui.diary.DiaryActivity;
import sunxl8.your_diary.ui.memo.MemoActivity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.ItemViewHolder> {

    private Context mContext;
    private List<ItemEntity> mList;

    public MainItemAdapter(Context mContext, List<ItemEntity> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = new ItemViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ItemEntity entity = mList.get(position);
        holder.tvItemName.setText(entity.getItemTitle());
        holder.tvItemCount.setText(entity.getItemCount() + "");
        switch (entity.getItemType()) {
            case 0:
                holder.ivItemType.setBackgroundResource(R.drawable.ic_plus_contact);
                break;
            case 1:
                holder.ivItemType.setBackgroundResource(R.drawable.ic_plus_diary);
                break;
            case 2:
                holder.ivItemType.setBackgroundResource(R.drawable.ic_plus_alert);
                break;
        }
        holder.layouItemMain.setOnClickListener(view -> {
            switch (entity.getItemType()) {
                case 0:
                    break;
                case 1:
                    DiaryActivity.startDiaryActivity(mContext);
                    break;
                case 2:
                    MemoActivity.startMemoActivity(mContext, entity.getId(), entity.getItemTitle());
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item_main)
        RelativeLayout layouItemMain;
        @BindView(R.id.tv_item_main_title)
        TextView tvItemName;
        @BindView(R.id.tv_item_main_count)
        TextView tvItemCount;
        @BindView(R.id.iv_item_main_type)
        ImageView ivItemType;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
