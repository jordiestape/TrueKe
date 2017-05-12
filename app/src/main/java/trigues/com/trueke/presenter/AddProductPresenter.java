package trigues.com.trueke.presenter;

import android.util.Log;

import com.trigues.entity.Product;
import com.trigues.usecase.AddImagesProductUseCase;
import com.trigues.usecase.AddImagesUseCase;
import com.trigues.usecase.AddProductUseCase;
import com.trigues.exception.ErrorBundle;

import java.util.List;

import javax.inject.Inject;

import trigues.com.data.datasource.InternalStorageInterface;
import trigues.com.data.entity.ProductDTO;
import trigues.com.trueke.view.AddProductActivity;

/**
 * Created by Alba on 22/03/2017.
 */

public class AddProductPresenter {
    private AddProductActivity view;
    private AddProductUseCase addProductUseCase;
    private InternalStorageInterface internalStorage;
    private AddImagesUseCase addImagesUseCase;
    private AddImagesProductUseCase addImagesProductUseCase;
    private String image_md5;

    @Inject
    public AddProductPresenter(AddProductActivity view,AddProductUseCase addProductUseCase, InternalStorageInterface internalStorage,
                               AddImagesUseCase addImagesUseCase, AddImagesProductUseCase addImagesProductUseCase) {
        this.view = view;
        this.addProductUseCase = addProductUseCase;
        this.internalStorage = internalStorage;
        this.addImagesUseCase = addImagesUseCase;
        this.addImagesProductUseCase = addImagesProductUseCase;
    }

    public void addProduct(String title, String description, List<String> images,String productCategory, List<String> desiredCategories, int priceMin, int priceMax) {

        //pasar usuario y categorias que quiere
        int userId = Integer.valueOf(internalStorage.getUser().getId());
        Product product = new Product(-1, userId, title, description, images, productCategory, desiredCategories, priceMin, priceMax);
        view.showProgress("Creando producto...");
        addProductUseCase.execute(product, new AddProductUseCase.AddProductCallback(){
            @Override
            public void onError(ErrorBundle errorBundle) {
                view.hideProgress();
                view.onError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Boolean returnParam) {
                view.hideProgress();
                if(returnParam){
                  // view.goToShowProductList();
                }
                else{
                    view.onError("No se ha añadido el producto correctamente");
                }
            }
        });

    }
    public void addImagesProduct(List<String> images) {
        //para cada imagen
        for(String image : images){
            if(image!="") {
                addImagesUseCase.execute(image, new AddImagesUseCase.AddImagesCallback() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        view.onError(errorBundle.getErrorMessage());
                    }

                    @Override
                    public void onSuccess(String returnParam) {
                        image_md5 = returnParam;
                    }
                });
                /*addImagesProductUseCase.execute(image_md5, new AddImagesProductUseCase.AddImagesProductCallback() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        view.onError(errorBundle.getErrorMessage());
                    }

                    @Override
                    public void onSuccess(Boolean returnParam) {
                        if(!returnParam){
                            view.goToShowProductList();
                        }
                    }
                });*/
            }
        }
    }

}