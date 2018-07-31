package tsw.accountnumber.presenter;


import android.text.TextUtils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import tsw.accountnumber.db.greendaobean.Account_Password;
import tsw.accountnumber.model.MainBean;
import tsw.accountnumber.model.MainDataModel;
import tsw.accountnumber.ui.AccountListActivity;
import tsw.commonlibrary.base.mvp.BasePresenter;
import tsw.commonlibrary.rx.RxSchedulers;

public class AccountListPresenter extends BasePresenter<MainDataModel, AccountListActivity> {
    public void loadAccountListData(final int account_type_id) {
        if (account_type_id == -1) {
            getView().onError("account_type_id错误");
        } else {
            Disposable disposable = Observable.create(new ObservableOnSubscribe<ArrayList<MainBean>>() {
                @Override
                public void subscribe(ObservableEmitter<ArrayList<MainBean>> emitter) throws Exception {
                    ArrayList<Account_Password> arrayLists = mModel.loadAccountListData(account_type_id);
                    ArrayList<MainBean> mainBeans = new ArrayList<>();
                    for (Account_Password accountPassword : arrayLists) {
                        MainBean mainBean = new MainBean();
                        mainBean.setApplicationName(accountPassword.getApplication_name());
                        mainBean.setAccount(accountPassword.getAccount());
                        mainBean.setPassword(accountPassword.getPassword());
                        mainBean.setRepository_password(accountPassword.getRepository_password());
                        mainBean.setAssociated_mailbox(accountPassword.getAssociated_mailbox());
                        mainBean.setType(accountPassword.getAccount_type_id());
                        mainBeans.add(mainBean);
                    }
                    emitter.onNext(mainBeans);
                }
            }).compose(RxSchedulers.<ArrayList<MainBean>>rxSchedulerHelper()).subscribe(new Consumer<ArrayList<MainBean>>() {
                @Override
                public void accept(ArrayList<MainBean> arrayLists) throws Exception {
                    getView().loadMainDataSuccess(arrayLists);
                }
            });
            mRxManager.add(disposable);
        }
    }

    public void addAccountPassword(final Account_Password accountPassword) {
        if (TextUtils.isEmpty(accountPassword.getApplication_name())) {
            getView().onError("账号类型不能为空");
        } else if (accountPassword.getAccount_type_id() == -1) {
            getView().onError("账号类型ID不能为空");
        } else if (TextUtils.isEmpty(accountPassword.getAccount())) {
            getView().onError("用户名不能为空");
        } else if (TextUtils.isEmpty(accountPassword.getPassword())) {
            getView().onError("密码不能为空");
        } else {
            Disposable disposable = Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                    emitter.onNext(mModel.addAccountPassWord(accountPassword));
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
    public void updateAccountPassword(final Account_Password accountPassword) {
        if (TextUtils.isEmpty(accountPassword.getApplication_name())) {
            getView().onError("账号类型不能为空");
        } else if (accountPassword.getAccount_type_id() == -1) {
            getView().onError("账号类型ID不能为空");
        } else if (TextUtils.isEmpty(accountPassword.getAccount())) {
            getView().onError("用户名不能为空");
        } else if (TextUtils.isEmpty(accountPassword.getPassword())) {
            getView().onError("密码不能为空");
        } else {
            Disposable disposable = Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                    emitter.onNext(mModel.updateAccountPassWord(accountPassword));
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
