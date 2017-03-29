package trigues.com.trueke.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import trigues.com.trueke.R;
import trigues.com.trueke.adapter.ShowProductsAdapter;
import trigues.com.trueke.view.ShowProductsActivity;

/**
 * Created by Marc on 22/03/2017.
 */

public class ShowProductsActivityImpl extends MenuActivityImpl implements ShowProductsActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ShowProductsAdapter(this) {
            @Override
            public void onItemClick() {
                startActivity(new Intent(ShowProductsActivityImpl.this, UserProductDetailsActivityImpl.class));
            }
        };
        recyclerView.setAdapter(adapter);
    }

}

