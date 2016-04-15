package com.brilliantbear.zhihupaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.db.ZhihuStory;
import com.brilliantbear.zhihupaper.mvp.view.ZhihuDetailActivity;
import com.brilliantbear.zhihupaper.utils.GlideUtils;

import java.util.List;


/**
 * Created by cx.lian on 2016/4/12.
 */
public class ZhihuListAdapter extends RecyclerView.Adapter<ZhihuListAdapter.ZhihuListViewHolder> {

    private List<ZhihuStory> stories;
    private Context context;

    public ZhihuListAdapter(Context context, List<ZhihuStory> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public ZhihuListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhihu_list, parent, false);
        return new ZhihuListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ZhihuListViewHolder holder, int position) {
        final ZhihuStory story = stories.get(position);
        holder.tvTitle.setText(story.getTitle());
        GlideUtils.load(holder.itemView.getContext(), story.getImage(), holder.ivPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhihuDetailActivity.class);
                intent.putExtra("title", story.getTitle());
                intent.putExtra("id", story.getId());
                intent.putExtra("url", story.getImage());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class ZhihuListViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPic;
        public TextView tvTitle;

        public ZhihuListViewHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

}
