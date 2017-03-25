package trigues.com.trueke.view.impl;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.trigues.entity.Product;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import trigues.com.trueke.R;
import trigues.com.trueke.adapter.DesiredCategoriesAdapter;
import trigues.com.trueke.adapter.ImageViewPageAdapter;
import trigues.com.trueke.dependencyinjection.App;
import trigues.com.trueke.dependencyinjection.activity.ActivityModule;
import trigues.com.trueke.dependencyinjection.view.ViewModule;
import trigues.com.trueke.presenter.UserProductDetailsPresenter;
import trigues.com.trueke.view.UserProductDetailsActivity;

/**
 * Created by mbaque on 23/03/2017.
 */

public class UserProductDetailsActivityImpl extends BaseActivityImpl implements UserProductDetailsActivity {

    @BindView(R.id.product_detail_viewpager)
    ViewPager viewPager;

    @BindView(R.id.product_detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.product_detail_dots_layout)
    LinearLayout dotsLayout;

    @BindView(R.id.product_detail_category_list)
    RecyclerView categoriesRecyclerview;

    @Inject
    UserProductDetailsPresenter presenter;

    ImageViewPageAdapter viewPageAdapter;
    int numImages;
    ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userproduct_detail);

        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);

        ButterKnife.bind(this);

        int productId = getIntent().getIntExtra("user_product_id", -1);

        presenter.getProductDetails(productId);

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onDetailsRetrieved(Product returnParam) {
        setUpViewPager(returnParam.getImages());

        setUpDotCounter();

        setUpDesiredCategoriesList(returnParam.getDesiredCategories());
    }

    private void setUpDesiredCategoriesList(List<String> desiredCategories) {
        this.categoriesRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.categoriesRecyclerview.setAdapter(new DesiredCategoriesAdapter(this, desiredCategories) {
            @Override
            public void onCategoryDeleteButtonClick(String category) {
                presenter.onCategoryDeleteButtonClick(category);
            }
        });
    }

    private void setUpViewPager(List<String> images) {
        //TODO: Set info to views
        this.viewPageAdapter = new ImageViewPageAdapter(this, images);
        viewPager.setAdapter(viewPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < numImages; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.user_product_details_dot_not_selected));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.user_product_details_dot_selected));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpDotCounter() {
        numImages = viewPageAdapter.getCount();
        dots = new ImageView[numImages];

        for (int i = 0; i < numImages; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.user_product_details_dot_not_selected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            dotsLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.user_product_details_dot_selected));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
