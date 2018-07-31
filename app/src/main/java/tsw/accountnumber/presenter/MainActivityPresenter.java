package tsw.accountnumber.presenter;


import android.text.TextUtils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import tsw.accountnumber.db.greendaobean.Account_Type;
import tsw.accountnumber.model.MainBean;
import tsw.accountnumber.model.MainDataModel;
import tsw.accountnumber.ui.MainActivity;
import tsw.commonlibrary.base.mvp.BasePresenter;
import tsw.commonlibrary.rx.RxSchedulers;

public class MainActivityPresenter extends BasePresenter<MainDataModel, MainActivity> {
    public void loadData() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<ArrayList<MainBean>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<MainBean>> emitter) throws Exception {
                ArrayList<MainBean> arrayLists = mModel.loadMainData();
                emitter.onNext(arrayLists);
            }
        }).compose(RxSchedulers.<ArrayList<MainBean>>rxSchedulerHelper()).subscribe(new Consumer<ArrayList<MainBean>>() {
            @Override
            public void accept(ArrayList<MainBean> arrayLists) throws Exception {
                getView().loadMainDataSuccess(arrayLists);
            }
        });
        mRxManager.add(disposable);
    }


    public void addAccount(final Account_Type accountType) {
        if (TextUtils.isEmpty(accountType.getAccount_type_name())) {
            getView().onError("账号类型不能为空");
        } else if (accountType.getAccount_type_id() == -1) {
            getView().onError("账号类型ID不能为空");
        } else if (TextUtils.isEmpty(accountType.getAccount_type_describe())) {
            getView().onError("账号类型描述不能为空");
        } else {
            Disposable disposable = Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                    emitter.onNext(mModel.addAccountType(accountType));
                }
            }).compose(RxSchedulers.<Boolean>rxSchedulerHelper()).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean issuccess) throws Exception {
                    if (issuccess) {
                        getView().addAccountResult("添加成功");
                    } else {
                        getView().addAccountResult("添加失败");
                    }
                }
            });
            mRxManager.add(disposable);
        }
    }

    public void updateAccount(final Account_Type accountType) {
        if (TextUtils.isEmpty(accountType.getAccount_type_name())) {
            getView().onError("账号类型不能为空");
        } else if (accountType.getAccount_type_id() == -1) {
            getView().onError("账号类型ID不能为空");
        } else if (TextUtils.isEmpty(accountType.getAccount_type_describe())) {
            getView().onError("账号类型描述不能为空");
        } else {
            Disposable disposable = Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                    emitter.onNext(mModel.addAccountType(accountType));
                }
            }).compose(RxSchedulers.<Boolean>rxSchedulerHelper()).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean issuccess) throws Exception {
                    if (issuccess) {
                        getView().addAccountResult("添加成功");
                    } else {
                        getView().addAccountResult("添加失败");
                    }
                }
            });
            mRxManager.add(disposable);
        }
    }


}
