package xiaolei.tabviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxl8 on 2017/6/28.
 */

public class TabView extends LinearLayout {

    private Context mContext;

    private int tabBackground;
    private int tabIndicatorColor;
    private float tabIndicatorHeight;
    private int tabSelectedTextColor;
    private float tabSelectedTextSize;
    private int tabTextColor;
    private float tabTextSize;

    private LinearLayout layout;
    private ViewPager mViewPager;

    private TabAdapter mAdapter;

    private List<TabItem> mViews;

    public TabView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TabView);
        tabBackground = ta.getColor(R.styleable.TabView_tabBackgroundColor, Color.WHITE);
        tabIndicatorColor = ta.getColor(R.styleable.TabView_tabIndicatorColor, Color.BLACK);
        tabIndicatorHeight = ta.getDimension(R.styleable.TabView_tabIndicatorHeight, 2);
        tabSelectedTextColor = ta.getColor(R.styleable.TabView_tabSelectedTextColor, Color.BLACK);
        tabSelectedTextSize = ta.getDimension(R.styleable.TabView_tabSelectedTextSize, 20);
        tabTextColor = ta.getColor(R.styleable.TabView_tabTextColor, Color.GRAY);
        tabTextSize = ta.getDimension(R.styleable.TabView_tabTextSize, 15);
        ta.recycle();
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.view_tab, this);

        layout = (LinearLayout) findViewById(R.id.layout_vp);
        layout.setBackgroundColor(tabBackground);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(3);
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        LayoutParams lp = (LayoutParams) mViewPager.getLayoutParams();
        lp.setMarginStart(width / 4 + (int) tabTextSize * 4);
        lp.setMarginEnd(width / 4 + (int) tabTextSize * 4);
        mViewPager.setLayoutParams(lp);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mViews = new ArrayList<>();
        mAdapter = new TabAdapter();
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setItemSelected(mViews, position);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        layout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }

    private void setItemSelected(List<TabItem> list, int pos) {
        for (TabItem item : list) {
            item.setSelected(false);
        }
        list.get(pos).setSelected(true);
    }

    public void setData(List<String> data) {
        for (String s : data) {
            TabItem item = new TabItem(mContext, tabIndicatorColor, tabIndicatorHeight, tabSelectedTextColor, tabSelectedTextSize, tabTextColor, tabTextSize);
            item.setTitle(s);
            mViews.add(item);
        }
        setItemSelected(mViews, 0);
        mAdapter.setViews(mViews);
    }

    public void setupWithViewPager(ViewPager vp) {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener){
        mViewPager.addOnPageChangeListener(listener);
    }

    class TabAdapter extends PagerAdapter {

        private List<TabItem> mViews;


        public void setViews(List<TabItem> views) {
            mViews = views;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mViews == null ? 0 : mViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(mViews.get(position));
            return mViews.get(position);
        }

    }
}
