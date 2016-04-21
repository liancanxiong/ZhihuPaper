package com.brilliantbear.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx.lian on 2016/4/18.
 */
public class Banner extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager mPager;
    private TextView tvTitle;
    private LinearLayout mIndicator;
    private List<ImageView> mImages;
    private List<String> mTitles;
    private Context mContext;
    private BannerAdapter mAdapter;

    private final ImageView.ScaleType[] scaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY,
            ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER,
            ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    private final int[] gravityArray = {Gravity.LEFT, Gravity.RIGHT, Gravity.CENTER};
    private final int[] directionArray = {-1, 1};

    private boolean loop;
    private boolean showIndicator;
    private boolean showTitle;
    private int titleGravity;
    private int indicatorGravity;
    private float titleSize;
    private int titleColor;
    private int titleBackground;
    private int loopSpeed;
    private int scaleType;
    private int indicatorBackground;
    private int selectedColor;
    private int unselectedColor;
    private int direction;


    private OnBannerItemClickListener listener;

    public interface OnBannerItemClickListener {
        void onBannerItemClick(View view, int position);
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        this.listener = listener;
    }

    public void removeOnBanneritemClickListener(OnBannerItemClickListener listener) {
        if (this.listener == listener) {
            this.listener = null;
        }
    }

    private final int START = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    int currentItem = mPager.getCurrentItem();
                    int nextItem = currentItem + directionArray[direction];
                    if (nextItem < 0)
                        nextItem = Integer.MAX_VALUE / 2;

                    mPager.setCurrentItem(nextItem);
                    handler.sendEmptyMessageDelayed(START, loopSpeed);
                    break;
            }
        }
    };


    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(context, attrs);
        initView(context);

        initData();

    }

    private void initData() {
        mImages = new ArrayList<>();
        mAdapter = new BannerAdapter(mImages);
        mPager.setAdapter(mAdapter);
    }

    private void initView(Context context) {
        View mRootView = LayoutInflater.from(context).inflate(R.layout.layout, this, true);
        mPager = (ViewPager) mRootView.findViewById(R.id.pager);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        mIndicator = (LinearLayout) mRootView.findViewById(R.id.indicator);

        tvTitle.setGravity(gravityArray[titleGravity]);
        mPager.addOnPageChangeListener(this);
    }


    public void showTitle(boolean isShow) {
        showTitle = isShow;
        if (showTitle) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setTextSize(DensityUtils.px2sp(mContext, titleSize));
            tvTitle.setTextColor(titleColor);
            tvTitle.setBackgroundColor(titleBackground);
        } else {
            tvTitle.setVisibility(View.INVISIBLE);
        }
    }

    public void start() {
        stop();
        handler.sendEmptyMessageDelayed(START, loopSpeed);
    }

    public void stop() {
        handler.removeMessages(START);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        loop = typedArray.getBoolean(R.styleable.Banner_loop, true);
        showIndicator = typedArray.getBoolean(R.styleable.Banner_show_indicator, true);
        showTitle = typedArray.getBoolean(R.styleable.Banner_show_title, false);
        titleGravity = typedArray.getInt(R.styleable.Banner_title_gravity, 0);
        indicatorGravity = typedArray.getInt(R.styleable.Banner_indicator_gravity, 2);
        titleSize = typedArray.getDimension(R.styleable.Banner_title_size, DensityUtils.sp2px(mContext, 20));
        titleColor = typedArray.getColor(R.styleable.Banner_title_color, Color.WHITE);
        titleBackground = typedArray.getColor(R.styleable.Banner_title_background, Color.parseColor("#66000000"));
        indicatorBackground = typedArray.getColor(R.styleable.Banner_indicator_background, Color.parseColor("#66000000"));
        selectedColor = typedArray.getColor(R.styleable.Banner_indicator_selected, Color.RED);
        unselectedColor = typedArray.getColor(R.styleable.Banner_indicator_unselected, Color.WHITE);
        loopSpeed = typedArray.getInt(R.styleable.Banner_loop_speed, 2000);
        scaleType = typedArray.getInt(R.styleable.Banner_scaleType, 3);
        direction = typedArray.getInt(R.styleable.Banner_direction, 1);
        typedArray.recycle();
    }


    public void setTitles(List<String> titles) {
        if (null == titles)
            return;

        showTitle(true);
        if (mTitles == null) {
            mTitles = new ArrayList<>();
        } else {
            mTitles.clear();
        }
        mTitles.addAll(titles);

        if (mTitles.size() > 0) {
            tvTitle.setText(mTitles.get(0));
        }
    }

    public void setImageUrl(List<String> urls) {
        if (null == urls || urls.size() <= 0)
            return;

        mImages.clear();
        for (String url : urls) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(scaleTypeArray[scaleType]);
            Glide.with(mContext).load(url).into(imageView);
            mImages.add(imageView);
        }
        mAdapter.notifyDataSetChanged();

        if (showIndicator) {
            mIndicator.removeAllViews();
            mIndicator.setBackgroundColor(indicatorBackground);
            mIndicator.setGravity(gravityArray[indicatorGravity]);
            for (int i = 0; i < urls.size(); i++) {
                View view = new View(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(mContext, 5), DensityUtils.dp2px(mContext, 5));
                view.setBackgroundResource(R.drawable.indicator_bg);
                if (i != 0) {
                    params.leftMargin = DensityUtils.dp2px(mContext, 5);
                }
                params.bottomMargin = DensityUtils.dp2px(mContext, 3);
                params.topMargin = DensityUtils.dp2px(mContext, 3);
                view.setLayoutParams(params);
                mIndicator.addView(view);
            }
        }

        mPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % urls.size()));

    }

    public int getLoopSpeed() {
        return loopSpeed;
    }

    public void setLoopSpeed(int loopSpeed) {
        this.loopSpeed = loopSpeed;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(mAdapter.getRealCount() == 0)
            return;

        position = position % mAdapter.getRealCount();

        if (mTitles != null && showTitle) {
            if (mTitles.size() > position) {
                tvTitle.setText(mTitles.get(position));
            } else {
                tvTitle.setText("");
            }
        }

        if (showIndicator) {
            int childCount = mIndicator.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = mIndicator.getChildAt(i);
                GradientDrawable background = (GradientDrawable) child.getBackground();
                if (i == position) {
                    background.setColor(selectedColor);
                } else {
                    background.setColor(unselectedColor);
                }
            }
        }
    }

    public int getCount() {
        return mImages == null ? 0 : mImages.size();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public class BannerAdapter extends PagerAdapter {

        private List<ImageView> imageViews;

        public BannerAdapter(List<ImageView> imageViews) {
            this.imageViews = imageViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (getRealCount() > 0) {
                ImageView imageView = imageViews.get(position % getRealCount());
                ViewParent parent = imageView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(imageView);
                }
                container.addView(imageView);
                if(null != listener){
                    imageView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onBannerItemClick(v, position % getRealCount());
                        }
                    });
                }
                return imageView;
            }
            return null;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }


        public int getRealCount() {
            return imageViews == null ? 0 : imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stop();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                start();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


}
