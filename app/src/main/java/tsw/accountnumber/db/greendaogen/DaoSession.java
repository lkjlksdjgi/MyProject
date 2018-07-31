package tsw.accountnumber.db.greendaogen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import tsw.accountnumber.db.greendaobean.Account_Password;
import tsw.accountnumber.db.greendaobean.Account_Type;

import tsw.accountnumber.db.greendaogen.Account_PasswordDao;
import tsw.accountnumber.db.greendaogen.Account_TypeDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig account_PasswordDaoConfig;
    private final DaoConfig account_TypeDaoConfig;

    private final Account_PasswordDao account_PasswordDao;
    private final Account_TypeDao account_TypeDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        account_PasswordDaoConfig = daoConfigMap.get(Account_PasswordDao.class).clone();
        account_PasswordDaoConfig.initIdentityScope(type);

        account_TypeDaoConfig = daoConfigMap.get(Account_TypeDao.class).clone();
        account_TypeDaoConfig.initIdentityScope(type);

        account_PasswordDao = new Account_PasswordDao(account_PasswordDaoConfig, this);
        account_TypeDao = new Account_TypeDao(account_TypeDaoConfig, this);

        registerDao(Account_Password.class, account_PasswordDao);
        registerDao(Account_Type.class, account_TypeDao);
    }
    
    public void clear() {
        account_PasswordDaoConfig.clearIdentityScope();
        account_TypeDaoConfig.clearIdentityScope();
    }

    public Account_PasswordDao getAccount_PasswordDao() {
        return account_PasswordDao;
    }

    public Account_TypeDao getAccount_TypeDao() {
        return account_TypeDao;
    }

}