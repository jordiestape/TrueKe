package trigues.com.data.datasource.impl;

import android.util.Log;

import com.trigues.entity.Payment;
import com.trigues.entity.Product;
import com.trigues.entity.Shipment;
import com.trigues.entity.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trigues.com.data.FakeInterceptor;
import trigues.com.data.datasource.ApiInterface;
import trigues.com.data.entity.ApiDTO;
import trigues.com.data.entity.LoginDTO;
import trigues.com.data.service.RetrofitErrorHandler;
import trigues.com.data.service.ServerService;

/**
 * Created by mbaque on 15/03/2017.
 */

public class ApiDataSource implements ApiInterface {

    private ServerService server;
    private FakeInterceptor interceptor;


    @Inject
    public ApiDataSource() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        interceptor = new FakeInterceptor();
        builder.addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://10.4.41.147:3000/")
                .baseUrl("http://10.4.41.147:3000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        server = retrofit.create(ServerService.class);

    }

    @Override
    public void getUserProductDetails(String token, int productId, final ProductDataCallback dataCallback) {

        server.getUserProductDetails(token, productId).enqueue(new RetrofitErrorHandler<Product>(dataCallback) {
            @Override
            public void onResponse(Product body) {
                dataCallback.onSuccess(body);
            }
        });
    }
    @Override
    public void showProfile(String token, int id, final UserDataCallback userDataCallback){
        server.getUserProfile(token,String.valueOf(id)).enqueue(new RetrofitErrorHandler<ApiDTO<List<User>>>(userDataCallback) {
            @Override
            public void onResponse(ApiDTO<List<User>> body) {
                userDataCallback.onSuccess(body);
            }
        });
    }

    @Override
    public void showPayments(String token,int id, final PaymentsCallback paymentsCallback) {
      /**  interceptor.setResponseString("[{\n" +
                "  \"id\": 1,\n" +
                "  \"user_id\": 1,\n" +
                "  \"type\": \"Visa/4B/Euro6000\",\n" +
                "  \"number\": \"123456789\",\n" +
                "  \"expireDate\": \"1990-05-06\",\n" +
                "  \"name\": \"Sancho Panza\",\n" +
                "  \"country\": \"España\",\n" +
                "  \"province\": \"Barcelona\",\n" +
                "  \"city\": \"Barcelona\",\n" +
                "  \"postalCode\": 8029,\n" +
                "  \"address\": \"Carrer Diagonal\",\n" +
                "  \"phone\": \"654654654\"\n" +
                "},\n" +
                "{\n" +
                "\"id\": 2,\n" +
                "\"user_id\": 1,\n" +
                "\"type\": \"Visa/4B/Euro6000\",\n" +
                "\"number\": \"987654321\",\n" +
                "\"expireDate\": \"1990-05-06\",\n" +
                "\"name\": \"Sancho Panza\",\n" +
                "\"country\": \"España\",\n" +
                "\"province\": \"Barcelona\",\n" +
                "\"city\": \"Barcelona\",\n" +
                "\"postalCode\": 8029,\n" +
                "\"address\": \"Carrer Diagonal\",\n" +
                "\"phone\": \"654654654\"\n" +
                "}]"); **/
        server.getPaymentInfo(token,String.valueOf(id)).enqueue(new RetrofitErrorHandler<ApiDTO<List<Payment>>>(paymentsCallback) {
            @Override
            public void onResponse(ApiDTO<List<Payment>> body) {
                paymentsCallback.onSuccess(body);
            }
        });
    }

    @Override
    public void showShipments(String token,int id, final ShipmentsCallback shipmentsCallback) {
     /**   interceptor.setResponseString("[{\n" +
                "  \"id\": 1,\n" +
                "  \"user_id\": 1,\n" +
                "  \"country\": \"Spain\",\n" +
                "  \"province\": \"Barcelona\",\n" +
                "  \"city\": \"Barcelona\",\n" +
                "  \"postalCode\": 8006,\n" +
                "  \"address\": \"Calle Falsa 123\",\n" +
                "  \"name\": \"Pepito Mendizabal\",\n" +
                "  \"idCard\": \"654845616531\",\n" +
                "  \"phone\": \"654654654\"\n" +
                "}, {\n" +
                "  \"id\": 2,\n" +
                "  \"user_id\": 1,\n" +
                "  \"country\": \"Spain\",\n" +
                "  \"province\": \"Barcelona\",\n" +
                "  \"city\": \"Barcelona\",\n" +
                "  \"postalCode\": 8029,\n" +
                "  \"address\": \"Calle Falsa 123\",\n" +
                "  \"name\": \"Pepito Mendizabal\",\n" +
                "  \"idCard\": \"654845616531\",\n" +
                "  \"phone\": \"654654654\"\n" +
                "}]"); **/

        server.getShipmentInfo(token,String.valueOf(id)).enqueue(new RetrofitErrorHandler<ApiDTO<List<Shipment>>>(shipmentsCallback) {
            @Override
            public void onResponse(ApiDTO<List<Shipment>> body) {
                shipmentsCallback.onSuccess(body);
            }
        });
    }

    @Override
    public void changeProfile(User user, final BooleanDataCallback booleanDataCallback) {
        server.changeProfile(user).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback){
            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void deleteUser(int user_id, final BooleanDataCallback booleanDataCallback) {
        server.deleteUser(user_id).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback){

            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void changePayment(Payment payment, final BooleanDataCallback booleanDataCallback) {
        server.changePayment(payment).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback){

            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void newPayment(Payment payment, final BooleanDataCallback booleanDataCallback) {
        server.newPayment(payment).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback){

            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void deletePayment(String token,int payment_id, final BooleanDataCallback booleanDataCallback) {
        server.deletePayment(token,String.valueOf(payment_id)).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback) {
            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void deleteShipment(String token,int shipment_id, final BooleanDataCallback booleanDataCallback) {
        server.deleteShipment(token, String.valueOf(shipment_id)).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback) {
            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void newShipment(Shipment shipment, final BooleanDataCallback booleanDataCallback) {
        Log.i("shipment", "newShipment: ");
        server.newShipment(shipment).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback){
            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void changeShipment(Shipment shipment, final BooleanDataCallback booleanDataCallback) {
        server.changeShipment(shipment).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(booleanDataCallback) {
            @Override
            public void onResponse(ApiDTO<Void> body) {
                booleanDataCallback.onSuccess(false);
            }
        });
    }

    @Override
    public void getMatchmakingProducts(String token, int prodID, final ProductListDataCallback dataCallback) {
        server.getMatchMakingProducts(token, prodID).enqueue(new RetrofitErrorHandler< ApiDTO<List<Product>> /*List<Product> */>(dataCallback) {
            @Override
            public void onResponse(ApiDTO<List<Product>>/*List<Product>*/ body) {
                dataCallback.onSuccess(body);
            }
        });

    }

    @Override
    public void showProducts(String token, int userID, final ProductListDataCallback dataCallback) {
        /*List<String> llista = new ArrayList<>();
        llista.add("https://photos6.spartoo.es/photos/231/231523/231523_350_A.jpg");
        List<String> llista2 = new ArrayList<>();
                llista2.add("Electrodomesticos");
        Product prod = new Product(12345, 54321, "Mochilla", "Mochila basura",llista ,
                "Accesorios", llista2, 100, 200);
        List<Product> llistaProd= new ArrayList<>();

        llistaProd.add(prod);
        llistaProd.add(prod);
        llistaProd.add(prod);
        llistaProd.add(prod);

        Gson gson = new Gson();

        interceptor.setResponseString(gson.toJson(llistaProd));*/

        server.getUserProducts(token, userID).enqueue(new RetrofitErrorHandler< ApiDTO<List<Product>> /*List<Product> */>(dataCallback) {
            @Override
            public void onResponse(ApiDTO<List<Product>>/*List<Product>*/ body) {
                dataCallback.onSuccess(body);
            }
        });

    }

    @Override
    public void register(User user, final BooleanDataCallback dataCallback) {
        server.register(user).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(dataCallback) {
            @Override
            public void onResponse(ApiDTO<Void> body) {
                dataCallback.onSuccess(body.getError());
            }
        });
    }

    @Override
    public void login(User user, final LoginDataCallback dataCallback) {
        server.login(user).enqueue(new RetrofitErrorHandler<ApiDTO<LoginDTO>>(dataCallback) {
            @Override
            public void onResponse(ApiDTO<LoginDTO> body) {
                dataCallback.onSuccess(body);
            }
        });
    }

    @Override
    public void addProduct(Product product, final BooleanDataCallback dataCallback) {
        server.addProduct(product).enqueue(new RetrofitErrorHandler<ApiDTO<Void>>(dataCallback) {
            @Override
            public void onResponse(ApiDTO<Void> body) {
                dataCallback.onSuccess(body.getError());
            }
        });
    }

}
