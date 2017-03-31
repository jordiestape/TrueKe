package com.trigues;

import com.trigues.callback.DefaultCallback;
import com.trigues.entity.Product;

import java.util.List;

/**
 * Created by mbaque on 15/03/2017.
 */

public interface RepositoryInterface {

    //Functions:

    void getUserProductDetails(int productId, GetUserProductDetailsCallback dataCallback);


    void showProducts(int userID, showProductsCallback dataCallback);


    //Callbacks:

    interface GetUserProductDetailsCallback extends DefaultCallback<Product>{}
    interface showProductsCallback extends DefaultCallback<List<Product>>{}
}
