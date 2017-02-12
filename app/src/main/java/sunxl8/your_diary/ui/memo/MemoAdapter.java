package sunxl8.your_diary.ui.memo;

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
import sunxl8.your_diary.db.entity.MemoEntity;

/**
 * Description: <br>
 * author: sun<br>
 * date: 2017/2/11.<br>
 * Email：xiaoleisun92@gmail.com
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ItemViewHolder> {

    private Context mContext;
    private List<MemoEntity> mList;

    private boolean isEdit = false;
    private MemoPresenter mPresenter;

    public MemoAdapter(Context mContext, List<MemoEntity> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public MemoAdapter(Context mContext, List<MemoEntity> list, boolean isEdit, MemoPresenter presenter) {
        this.mContext = mContext;
        this.mList = list;
        this.isEdit = isEdit;
        this.mPresenter = presenter;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder holder = new ItemViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_memo, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final MemoEntity entity = mList.get(position);
        holder.tvContent.setText(entity.getMemo());
        if (isEdit) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder.ivDelete.setVisibility(View.GONE);
        }
        holder.ivDelete.setOnClickListener(view -> {
            mPresenter.deleteItem(entity.getId());
            mList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item_memo)
        RelativeLayout layouItemMain;
        @BindView(R.id.tv_memo_content)
        TextView tvContent;
        @BindView(R.id.iv_memo_dot)
        ImageView ivDot;
        @BindView(R.id.iv_memo_delete)
        ImageView ivDelete;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
