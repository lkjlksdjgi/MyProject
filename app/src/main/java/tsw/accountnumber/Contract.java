package tsw.accountnumber;

import java.util.ArrayList;

import tsw.accountnumber.db.greendaobean.Account_Password;
import tsw.accountnumber.db.greendaobean.Account_Type;
import tsw.accountnumber.model.MainBean;
import tsw.commonlibrary.base.mvp.BaseModel;
import tsw.commonlibrary.base.mvp.BaseView;

public class Contract {

    public interface IDataModel extends BaseModel {
        ArrayList<MainBean> loadMainData();

        boolean addAccountType(Account_Type accountType);

        boolean updateAccountType(Account_Type accountType);

        boolean addAccountPassWord(Account_Password accountPassword);

        boolean updateAccountPassWord(Account_Password accountPassword);

        ArrayList<Account_Password> loadAccountListData(int account_type_id);
    }

    public interface IMyView extends BaseView {
        void loadMainDataSuccess(ArrayList<MainBean> result);

        void addAccountResult(String isSuccess);

        void onError(String msg);
    }





}
