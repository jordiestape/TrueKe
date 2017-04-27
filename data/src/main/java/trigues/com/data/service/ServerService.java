package trigues.com.data.service;

import com.trigues.entity.Payment;
import com.trigues.entity.Product;
import com.trigues.entity.Shipment;
import com.trigues.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import trigues.com.data.entity.ApiDTO;
import trigues.com.data.entity.LoginDTO;
import trigues.com.data.entity.ProductDTO;

/**
 * Created by mbaque on 18/03/2017.
 */

public interface ServerService {

    @POST("authenticate")
    Call<ApiDTO<LoginDTO>> login(@Body User user);

    @POST("users")
    Call<ApiDTO<Void>> register(@Body User user);

    @GET("/")
    Call<List<Product>> getUserProducts(/*@Body  Integer user_id*/);

    @GET("/")
    Call<Product> getUserProductDetails();

    //products
    @POST("products")
    Call<ApiDTO<Void>> addProduct(@Header("token") String token,@Body ProductDTO product);

    @DELETE("products/:id")
    Call<ApiDTO<Void>> deleteProduct(@Header("token") String token, int product_id);

    //bienvenidos a mi hood

    //user info
    @GET("users/{id}")
    Call<ApiDTO<List<User>>> getUserProfile(@Header("token") String token, @Path("id") String id);

    @PUT("users/:id")
    Call<ApiDTO<Void>> changeProfile(@Body User user);

    @DELETE("users/:id")
    Call<ApiDTO<Void>> deleteUser(int user_id);

    //payments
    @GET("paymentmethods/:user_id")
    Call<List<Payment>> getPaymentInfo();

    @PUT("paymentmethods/:id")
    Call<ApiDTO<Void>> changePayment(@Body Payment payment);

    @POST("paymentmethods")
    Call<ApiDTO<Void>> newPayment(@Body Payment payment);

    @DELETE("paymentmethods/:id")
    Call<ApiDTO<Void>> deletePayment(int payment_id);

    //shipments
    @GET("shipmentmethods/1")
    Call<List<Shipment>> getShipmentInfo();

    @PUT("shipmentmethods/:id")
    Call<ApiDTO<Void>> changeShipment(@Body Shipment shipment);

    @POST("shipmentmethods")
    Call<ApiDTO<Void>> newShipment(@Body Shipment shipment);

    @DELETE("shipmentmethods/:id")
    Call<ApiDTO<Void>> deleteShipment(int shipment_id);

}
