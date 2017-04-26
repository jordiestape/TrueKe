package com.trigues.usecase;

import com.trigues.RepositoryInterface;
import com.trigues.callback.DefaultCallback;
import com.trigues.entity.Product;
import com.trigues.exception.ErrorBundle;
import com.trigues.executor.PostExecutionThread;
import com.trigues.executor.ThreadExecutor;
import com.trigues.interactor.BaseUseCase;
import com.trigues.interactor.Interactor;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Eduard on 21/04/2017.
 */

public class AcceptMatchUseCase extends BaseUseCase<Void> implements Interactor<Integer[], Void > {
    private final RepositoryInterface repository;
    private final ThreadExecutor executor;
    private AcceptMatchCallback callback;
    private Integer[] productsID = new Integer[2];

    RepositoryInterface.VoidCallback dataCallback = new RepositoryInterface.VoidCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Void acceptVoid) {
            notifyOnSuccess(acceptVoid, callback);
        }
    };

    @Inject
    public AcceptMatchUseCase(PostExecutionThread postExecutionThread, ThreadExecutor executor, RepositoryInterface repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Integer[] productID, R defaultCallback) {
        this.callback = ((AcceptMatchCallback) defaultCallback);
        this.productsID[1] = productID[1];
        this.productsID[2] = productID[2];

        executor.execute(this);

    }
    @Override
    public void run() {
        repository.acceptMatch(productsID, dataCallback);

    }





    public interface AcceptMatchCallback extends DefaultCallback<Void>{}


}