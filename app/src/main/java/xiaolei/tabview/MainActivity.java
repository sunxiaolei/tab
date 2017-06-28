package xiaolei.tabview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import xiaolei.tabviewlib.TabView;

public class MainActivity extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabView tab = (TabView) findViewById(R.id.tab);
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("热点");
        list.add("社会");
        list.add("娱乐");
        list.add("科技");
        tab.setData(list);

        vp = (ViewPager) findViewById(R.id.vp_main);
        List<Fragment> listFragment = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listFragment.add(new TestFragment());
        }
        vp.setAdapter(new FragmentAdapter(getSupportFragmentManager(), listFragment));
        tab.setupWithViewPager(vp);
        tab.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> listFragment;

        public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            listFragment = list;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }
    }
}
