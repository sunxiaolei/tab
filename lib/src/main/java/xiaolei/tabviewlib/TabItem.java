package xiaolei.tabviewlib;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sunxl8 on 2017/6/28.
 */

public class TabItem extends RelativeLayout {

    private Context mContext;
    private TextView tvTitle;
    private View line;

    private int tabIndicatorColor;
    private float tabIndicatorHeight;
    private int tabSelectedTextColor;
    private float tabSelectedTextSize;
    private int tabTextColor;
    private float tabTextSize;

    public TabItem(Context context, int tabIndicatorColor, float tabIndicatorHeight, int tabSelectedTextColor, float tabSelectedTextSize, int tabTextColor, float tabTextSize) {
        super(context);
        mContext = context;
        this.tabIndicatorColor = tabIndicatorColor;
        this.tabIndicatorHeight = tabIndicatorHeight;
        this.tabSelectedTextColor = tabSelectedTextColor;
        this.tabSelectedTextSize = tabSelectedTextSize;
        this.tabTextColor = tabTextColor;
        this.tabTextSize = tabTextSize;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.item_tab, this);
        tvTitle = (TextView) findViewById(R.id.tv_tab_item);
        line = findViewById(R.id.line_tab_item);
        line.setBackgroundColor(tabIndicatorColor);
        tvTitle.setTextColor(tabTextColor);
    }

    public void setSelected(boolean selected) {
        if (selected) {
            tvTitle.setTextSize(tabSelectedTextSize);
            tvTitle.setTextColor(tabSelectedTextColor);
            line.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setTextSize(tabTextSize);
            tvTitle.setTextColor(tabTextColor);
            line.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

}
