package com.brilliantbear.zhihupaper.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.db.ZhihuStory;
import com.brilliantbear.zhihupaper.mvp.view.ZhihuDetailActivity;
import com.brilliantbear.zhihupaper.utils.DateUtils;
import com.brilliantbear.zhihupaper.utils.GlideUtils;

import java.util.Date;
import java.util.List;


/**
 * Created by cx.lian on 2016/4/12.
 */
public class ZhihuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ZhihuStory> stories;
    private Context context;

    public ZhihuListAdapter(Context context, List<ZhihuStory> stories) {
        this.context = context;
        this.stories = stories;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhihu_list, parent, false);
        return new ZhihuListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final ZhihuListViewHolder listHolder = (ZhihuListViewHolder) holder;
        listHolder.tvDate.setVisibility(View.GONE);
        if (position > 0) {
            String dateStr = stories.get(position).getDate();
            if (dateStr != null && !TextUtils.equals(stories.get(position - 1).getDate(), dateStr)) {
                listHolder.tvDate.setVisibility(View.VISIBLE);
                Date date = DateUtils.parseStandardString(dateStr);
                listHolder.tvDate.setText(DateUtils.parseStandardDate(date, "yyyy-MM-dd"));
            }
        }

        final ZhihuStory story = stories.get(position);
        listHolder.tvTitle.setText(story.getTitle());
        GlideUtils.load(holder.itemView.getContext(), story.getImage(), listHolder.ivPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhihuDetailActivity.class);
                intent.putExtra("title", story.getTitle());
                intent.putExtra("id", story.getId());
                intent.putExtra("url", story.getImage());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, listHolder.ivPic, context.getString(R.string.transition_pic)).toBundle());
                } else {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories == null ? 0 : stories.size();
    }

    public static class ZhihuListViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPic;
        public TextView tvTitle;
        public TextView tvDate;

        public ZhihuListViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

}
