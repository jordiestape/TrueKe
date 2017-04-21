package trigues.com.data.datasource;

import com.trigues.callback.DefaultCallback;
import com.trigues.entity.Product;
import com.trigues.entity.User;

import java.util.List;

import trigues.com.data.entity.ApiDTO;
import trigues.com.data.entity.LoginDTO;


/**
 * Created by mbaque on 15/03/2017.
 */

public interface   ApiInterface {

    //Functions:

    void getUserProductDetails(int productId, ProductDataCallback dataCallback);

    void showProducts(int userID, ProductListDataCallback dataCallback);

    void register(User user, BooleanDataCallback dataCallback);

    void login(User user, LoginDataCallback dataCallback);

    void addProduct(Product product, BooleanDataCallback dataCallback);


    //Callbacks:

    interface ProductDataCallback extends DefaultCallback<Product> {}

    interface ProductListDataCallback extends DefaultCallback<List<Product>> {}

    interface BooleanDataCallback extends DefaultCallback<Boolean> {}

    interface VoidDataCallback extends DefaultCallback<Void> {}

    interface LoginDataCallback extends DefaultCallback<ApiDTO<LoginDTO>> {}
}
