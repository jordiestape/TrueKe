package trigues.com.trueke.presenter;

import com.trigues.entity.Product;
import com.trigues.usecase.AddProductUseCase;
import com.trigues.exception.ErrorBundle;

import java.util.List;

import javax.inject.Inject;

import trigues.com.trueke.view.AddProductActivity;

/**
 * Created by Alba on 22/03/2017.
 */

public class AddProductPresenter {
    private AddProductActivity view;
    private AddProductUseCase addProductUseCase;

    @Inject
    public AddProductPresenter(AddProductActivity view,AddProductUseCase addProductUseCase) {
        this.view = view;
        this.addProductUseCase = addProductUseCase;
    }

    public void addProduct(int userId, String title, String description, List<String> images,String productCategory, List<String> desiredCategories, int priceMin, int priceMax) {

        //pasar usuario y categorias que quiere
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
                    view.goToShowProductList();
                }
                else{
                    view.onError("No se ha añadido el producto correctamente");
                }
            }
        });
    }
}