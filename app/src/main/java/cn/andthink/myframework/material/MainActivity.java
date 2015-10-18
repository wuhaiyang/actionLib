package cn.andthink.myframework.material;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lidroid.xutils.util.LogUtils;

import cn.andthink.myframework.R;

/**
 * Created by wuhaiyang on 2015/7/31.
 */
public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabBtn;
    private CoordinatorLayout rootLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
//    private TabLayout mTabLayout;

    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mt_activity_main);

        initView();

        setAttribute();

        bindAction();
    }

    private void setAttribute() {
        setSupportActionBar(mToolbar);
        initTabLayoutInstance();

        setNavigation();
    }

    private void setNavigation() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        Toast.makeText(MainActivity.this, "navItem1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navItem2:
                        Toast.makeText(MainActivity.this, "navItem2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navItem3:
                        Toast.makeText(MainActivity.this, "navItem3", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void initTabLayoutInstance() {
        collapsingToolbarLayout.setTitle("Design Library");
    }

    private void bindAction() {
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });
    }

    private void showSnackbar() {
        Snackbar.make(rootLayout, "Hello , I am SnackBar!", Snackbar.LENGTH_SHORT)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "do action", Toast.LENGTH_SHORT).show();
                    }
                }).show();

    }

    private void initView() {
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        rootLayout = (CoordinatorLayout) findViewById(R.id.fl_rootLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        navigation = (NavigationView) findViewById(R.id.navigation);
    }
}
