package trigues.com.trueke.presenter;

import com.trigues.entity.User;
import com.trigues.exception.ErrorBundle;
import com.trigues.usecase.ShowProfileUseCase;

import javax.inject.Inject;

import trigues.com.trueke.view.LoginActivity;
import trigues.com.trueke.view.UserProfile;

/**
 * Created by Albert on 07/04/2017.
 */

public class UserInfoPresenter {
    private UserProfile view;
    private ShowProfileUseCase showProfileUseCase;

    @Inject
    public UserInfoPresenter(UserProfile view,
                             ShowProfileUseCase showProfileUseCase) {
        this.view = view;
        this.showProfileUseCase=showProfileUseCase;
    }

    public void showProfile(Integer userID){
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

    public void showPaymentInfo(){}
    //public void showShipmentInfo(){} fa falta?
    public void changeProfile(User user){}
    public void changePaymentInfo(){}
}
