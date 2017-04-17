package trigues.com.trueke.presenter;

import android.util.Log;

import com.trigues.entity.Payment;
import com.trigues.entity.Shipment;
import com.trigues.entity.User;
import com.trigues.exception.ErrorBundle;
import com.trigues.usecase.ChangeProfileUseCase;
import com.trigues.usecase.ShowPaymentInfoUseCase;
import com.trigues.usecase.ShowProfileUseCase;
import com.trigues.usecase.ShowShipmentInfoUseCase;

import javax.inject.Inject;
import trigues.com.trueke.view.UserProfileActivity;

/**
 * Created by Albert on 07/04/2017.
 */

public class UserInfoPresenter {
    private UserProfileActivity view;
    private ShowProfileUseCase showProfileUseCase;
    private ShowPaymentInfoUseCase showPaymentInfoUseCase;
    private ShowShipmentInfoUseCase showShipmentInfoUseCase;
    private ChangeProfileUseCase changeProfileUseCase;

    @Inject
    public UserInfoPresenter(UserProfileActivity view,
                             ShowProfileUseCase showProfileUseCase,
                             ShowPaymentInfoUseCase showPaymentInfoUseCase,
                             ShowShipmentInfoUseCase showShipmentInfoUseCase,
                             ChangeProfileUseCase changeProfileUseCase) {
        this.view = view;
        this.showProfileUseCase=showProfileUseCase;
        this.showPaymentInfoUseCase = showPaymentInfoUseCase;
        this.showShipmentInfoUseCase = showShipmentInfoUseCase;
        this.changeProfileUseCase = changeProfileUseCase;
    }

    public void showProfile(){
        int userID=1;
        showProfileUseCase.execute(userID, new ShowProfileUseCase.ShowProfileUseCaseCallback(){

            @Override
            public void onError(ErrorBundle errorBundle) {
                view.onError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(User returnParam) {
                view.onProfileRetrieved(returnParam);
            }
        });
    }

    public void showPaymentInfo(){
        int userID=1;
        showPaymentInfoUseCase.execute(userID, new ShowPaymentInfoUseCase.ShowPaymentInfoUseCaseCallback(){

            @Override
            public void onError(ErrorBundle errorBundle) {
                view.onError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Payment returnParam) {
                view.onPaymentRetrieved(returnParam);
            }
        });

    }
    public void showShipmentInfo(){
        int userID=1;
        showShipmentInfoUseCase.execute(userID, new ShowShipmentInfoUseCase.ShowShipmentInfoUseCaseCallback(){

            @Override
            public void onError(ErrorBundle errorBundle) {
                view.onError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Shipment returnParam) {
                view.onShipmentRetrieved(returnParam);
            }
        });
    }
    public void changeProfile(User user){
        changeProfileUseCase.execute(user, new ChangeProfileUseCase.ChangeProfileUseCaseCallback(){

            @Override
            public void onError(ErrorBundle errorBundle) {
                Log.i("presenter", "onError: ");
                view.onError(errorBundle.getErrorMessage());
            }

            @Override
            public void onSuccess(Boolean returnParam) {
                Log.i("presenter", "onSuccess: ");
                view.onChangeProfileRetrieved(returnParam);
            }
        });
    }
    public void changePaymentInfo(Payment payment){}
    public void changeShipmentInfo(Shipment shipment){}
}
