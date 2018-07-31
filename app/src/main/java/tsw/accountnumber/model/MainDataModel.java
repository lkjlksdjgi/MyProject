package tsw.accountnumber.model;

import java.util.ArrayList;
import java.util.List;

import tsw.accountnumber.Contract;
import tsw.accountnumber.db.AccountNumberDbHelper;
import tsw.accountnumber.db.greendaobean.Account_Password;
import tsw.accountnumber.db.greendaobean.Account_Type;


public class MainDataModel implements Contract.IDataModel {
    @Override
    public ArrayList<MainBean> loadMainData() {
        List<Account_Type> query = AccountNumberDbHelper.getInstance().queryAccountType();
        ArrayList<MainBean> mainBeans = new ArrayList<>();
        for (Account_Type accountType : query) {
            MainBean mainBean = new MainBean();
            mainBean.setAccount_type_name(accountType.getAccount_type_name());
            mainBean.setAccount_type_id(accountType.getAccount_type_id());
            mainBean.setAccount_type_describe(accountType.getAccount_type_describe());
            mainBeans.add(mainBean);
        }
        AccountNumberDbHelper.getInstance().close();
        return mainBeans;
    }

    @Override
    public boolean addAccountType(Account_Type accountTyp) {
        return AccountNumberDbHelper.getInstance().insertAccountType(accountTyp);
    }

    @Override
    public boolean updateAccountType(Account_Type accountTyp) {
        AccountNumberDbHelper.getInstance().updateAccountType(accountTyp);
        return true;
    }


    @Override
    public boolean addAccountPassWord(Account_Password accountPassword) {
        return AccountNumberDbHelper.getInstance().insertAccountPassword(accountPassword);
    }

    @Override
    public boolean updateAccountPassWord(Account_Password accountPassword) {
        AccountNumberDbHelper.getInstance().updateAccountPassWord(accountPassword);
        return true;
    }


    @Override
    public ArrayList<Account_Password> loadAccountListData(int account_type_id) {
        ArrayList<Account_Password> account_passwords = (ArrayList<Account_Password>) AccountNumberDbHelper.getInstance().queryAccountPassword(account_type_id);
        AccountNumberDbHelper.getInstance().close();
        return account_passwords;
    }


}
