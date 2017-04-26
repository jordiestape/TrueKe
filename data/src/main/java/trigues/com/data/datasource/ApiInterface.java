package trigues.com.data.datasource;

import com.trigues.RepositoryInterface;
import com.trigues.callback.DefaultCallback;
import com.trigues.entity.Payment;
import com.trigues.entity.Product;
import com.trigues.entity.Shipment;
import com.trigues.entity.User;

import java.util.List;

import trigues.com.data.entity.ApiDTO;
import trigues.com.data.entity.LoginDTO;
import trigues.com.data.entity.ProductDTO;


/**
 * Created by mbaque on 15/03/2017.
 */

public interface ApiInterface {

    //Functions:

    void getUserProductDetails(int productId, ProductDataCallback dataCallback);

    void showProducts(int userID, ProductListDataCallback dataCallback);

    void register(User user, BooleanDataCallback dataCallback);

    void login(User user, LoginDataCallback dataCallback);

    void addProduct(String token, ProductDTO product, BooleanDataCallback dataCallback);

    void showProfile(String token, String user,UserDataCallback dataCallback);

    void showPayments(int id, PaymentsCallback paymentDataCallback);

    void showShipments(Integer id, ShipmentsCallback shipmentsCallback);

    void changeProfile(User user, BooleanDataCallback booleanDataCallback);

    void deleteUser(int user_id, BooleanDataCallback booleanDataCallback);

    void changePayment(Payment payment, BooleanDataCallback booleanDataCallback);

    void newPayment(Payment payment, BooleanDataCallback booleanDataCallback);

    void deletePayment(int payment_id, BooleanDataCallback booleanDataCallback);

    void deleteShipment(int shipment_id, BooleanDataCallback booleanDataCallback);

    void newShipment(Shipment shipment, BooleanDataCallback booleanDataCallback);

    void changeShipment(Shipment shipment, BooleanDataCallback booleanDataCallback);

    void deleteProduct(int product_id, BooleanDataCallback booleanDataCallback);


    //Callbacks:

    interface UserDataCallback extends DefaultCallback<ApiDTO<List<User>>>{}

    interface ProductDataCallback extends DefaultCallback<Product> {}

    interface ProductListDataCallback extends DefaultCallback<List<Product>> {}

    interface BooleanDataCallback extends DefaultCallback<Boolean> {}

    interface VoidDataCallback extends DefaultCallback<Void> {}

    interface LoginDataCallback extends DefaultCallback<ApiDTO<LoginDTO>> {}

    interface PaymentsCallback extends DefaultCallback<List<Payment>>{}

    interface ShipmentsCallback extends DefaultCallback<List<Shipment>>{}
}
