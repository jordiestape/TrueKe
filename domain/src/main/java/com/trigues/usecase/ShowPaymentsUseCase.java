package com.trigues.usecase;

import com.trigues.RepositoryInterface;
import com.trigues.callback.DefaultCallback;
import com.trigues.entity.Payment;
import com.trigues.exception.ErrorBundle;
import com.trigues.executor.PostExecutionThread;
import com.trigues.executor.ThreadExecutor;
import com.trigues.interactor.BaseUseCase;
import com.trigues.interactor.Interactor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Albert on 13/04/2017.
 */

public class ShowPaymentsUseCase extends BaseUseCase<List<Payment>> implements Interactor<Integer,List<Payment>> {
    private final RepositoryInterface repository;
    private final ThreadExecutor executor;
    private ShowPaymentsUseCaseCallback callback;
    private Integer id;

    @Inject
    public ShowPaymentsUseCase(PostExecutionThread postExecutionThread, ThreadExecutor executor,
                               RepositoryInterface repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    RepositoryInterface.PaymentCallback dataCallback = new RepositoryInterface.PaymentCallback(){

        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle,callback);
        }

        @Override
        public void onSuccess(List<Payment> returnParam) {
            notifyOnSuccess(returnParam,callback);
        }
    };
    @Override
    public void run() {
        repository.showPayments(id,dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<Payment>>> void execute(Integer param, R defaultCallback) {
        this.callback = ((ShowPaymentsUseCaseCallback) defaultCallback);
        this.id = param;
        executor.execute(this);
    }

    public interface ShowPaymentsUseCaseCallback extends DefaultCallback<List<Payment>>{}
}
