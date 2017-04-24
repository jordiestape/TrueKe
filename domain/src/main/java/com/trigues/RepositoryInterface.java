package com.trigues;

import com.trigues.callback.DefaultCallback;
import com.trigues.entity.Product;
import com.trigues.entity.User;

import java.util.List;

/**
 * Created by mbaque on 15/03/2017.
 */

public interface RepositoryInterface {

    //Functions:

    void getUserProductDetails(int productId, ProductCallback dataCallback);

    void showProducts(int userID, ProductListCallback dataCallback);

    void login(User user, BooleanCallback dataCallback);

    void deleteDesiredCategory(Product product, VoidCallback dataCallback);

    void register(User user, BooleanCallback dataCallback);

    void logout(VoidCallback dataCallback);

    void acceptMatch(Integer[] productID, VoidCallback dataCallback);

    void rejectMatch(Integer[] productsID, VoidCallback dataCallback);


    //Callbacks:

    interface VoidCallback extends DefaultCallback<Void> {}
    interface ProductCallback extends DefaultCallback<Product>{}
    interface ProductListCallback extends DefaultCallback<List<Product>>{}
    interface BooleanCallback extends DefaultCallback<Boolean>{}
}
