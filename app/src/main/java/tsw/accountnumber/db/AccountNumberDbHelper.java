package tsw.accountnumber.db;


import org.greenrobot.greendao.database.Database;

import java.util.List;

import tsw.accountnumber.MyApplication;
import tsw.accountnumber.db.greendaobean.Account_Password;
import tsw.accountnumber.db.greendaobean.Account_Type;
import tsw.accountnumber.db.greendaogen.Account_PasswordDao;
import tsw.accountnumber.db.greendaogen.DaoMaster;
import tsw.accountnumber.db.greendaogen.DaoSession;
import tsw.commonlibrary.db.DBHelper;
import tsw.commonlibrary.utils.CommonUtils;
import tsw.commonlibrary.utils.DevicesInfoUtils;

public class AccountNumberDbHelper extends DBHelper<DaoSession> {

    private Database encryptedReadableDb;
    private DaoMaster.DevOpenHelper mHelper;
    private static AccountNumberDbHelper accountNumberDbHelper;

    private AccountNumberDbHelper() {
        mHelper = new DaoMaster.DevOpenHelper(CommonUtils.getContext(), MyApplication.DB_NAME);
        //加密数据库
        encryptedReadableDb = mHelper.getEncryptedWritableDb(DevicesInfoUtils.getMacAddress());
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(encryptedReadableDb);
        mDaoSession = mDaoMaster.newSession();
    }

    @Override
    public DaoSession getDaoSession() {
        return (DaoSession) mDaoSession;
    }

    public static AccountNumberDbHelper getInstance() {
        if (accountNumberDbHelper == null) {
            synchronized (AccountNumberDbHelper.class) {
                if (accountNumberDbHelper == null) {
                    accountNumberDbHelper = new AccountNumberDbHelper();
                }
            }
        }
        return accountNumberDbHelper;
    }

    public void close() {
        super.close();
        getDaoSession().clear();
        mHelper.close();
        mHelper = null;
        accountNumberDbHelper = null;

        if (encryptedReadableDb != null) {
            encryptedReadableDb.close();
            encryptedReadableDb = null;
        }
    }

    public List<Account_Type> queryAccountType() {
        return getDaoSession().getAccount_TypeDao().queryBuilder().build().list();
    }

    public boolean insertAccountType(Account_Type accountTyp) {
        long insert = getDaoSession().getAccount_TypeDao().insert(accountTyp);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void updateAccountType(Account_Type accountTyp) {
        getDaoSession().getAccount_TypeDao().update(accountTyp);
    }


    public boolean insertAccountPassword(Account_Password accountPassword) {
        long insert = getDaoSession().getAccount_PasswordDao().insert(accountPassword);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void updateAccountPassWord(Account_Password accountPassword) {
        Account_Password account_password = getDaoSession().getAccount_PasswordDao().queryBuilder().where(Account_PasswordDao.Properties.Application_name.eq(accountPassword.getApplication_name()))
                .where(Account_PasswordDao.Properties.Account.eq(accountPassword.getAccount())).build().unique();
        if (account_password != null) {
            account_password.setPassword(accountPassword.getPassword());
            account_password.setAssociated_mailbox(accountPassword.getAssociated_mailbox());
            account_password.setRepository_password(accountPassword.getRepository_password());
            getDaoSession().getAccount_PasswordDao().update(account_password);
        } else {
            getDaoSession().getAccount_PasswordDao().insert(accountPassword);
        }
    }


    public List<Account_Password> queryAccountPassword(int account_type_id) {
        return getDaoSession().getAccount_PasswordDao().queryBuilder().where(Account_PasswordDao.Properties.Account_type_id.eq(account_type_id)).list();
    }

}
