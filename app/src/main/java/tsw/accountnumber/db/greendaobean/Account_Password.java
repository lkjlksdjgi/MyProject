package tsw.accountnumber.db.greendaobean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(createInDb = true, nameInDb = "account_password")
public class Account_Password {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "account")
    private String account;

    @Property(nameInDb = "password")
    private String password;

    @Property(nameInDb = "repository_password")
    private String repository_password;

    @Property(nameInDb = "associated_mailbox")
    private String associated_mailbox;

    @Property(nameInDb = "account_type_id")
    private int account_type_id;

    @Property(nameInDb = "application_name")
    private String application_name;

    @Generated(hash = 408334403)
    public Account_Password(Long id, String account, String password,
            String repository_password, String associated_mailbox,
            int account_type_id, String application_name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.repository_password = repository_password;
        this.associated_mailbox = associated_mailbox;
        this.account_type_id = account_type_id;
        this.application_name = application_name;
    }

    @Generated(hash = 46280686)
    public Account_Password() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepository_password() {
        return this.repository_password;
    }

    public void setRepository_password(String repository_password) {
        this.repository_password = repository_password;
    }

    public String getAssociated_mailbox() {
        return this.associated_mailbox;
    }

    public void setAssociated_mailbox(String associated_mailbox) {
        this.associated_mailbox = associated_mailbox;
    }

    public int getAccount_type_id() {
        return this.account_type_id;
    }

    public void setAccount_type_id(int account_type_id) {
        this.account_type_id = account_type_id;
    }

    public String getApplication_name() {
        return this.application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    


}
