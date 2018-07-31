package tsw.accountnumber.db.greendaobean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(createInDb = true, nameInDb = "account_type")
public class Account_Type {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "account_type_name")
    private String account_type_name;

    @Property(nameInDb = "account_type_id")
    private int account_type_id;

    @Property(nameInDb = "account_type_describe")
    private String account_type_describe;

    @Generated(hash = 1992394798)
    public Account_Type(Long id, String account_type_name, int account_type_id,
            String account_type_describe) {
        this.id = id;
        this.account_type_name = account_type_name;
        this.account_type_id = account_type_id;
        this.account_type_describe = account_type_describe;
    }

    @Generated(hash = 66740348)
    public Account_Type() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_type_name() {
        return this.account_type_name;
    }

    public void setAccount_type_name(String account_type_name) {
        this.account_type_name = account_type_name;
    }

    public int getAccount_type_id() {
        return this.account_type_id;
    }

    public void setAccount_type_id(int account_type_id) {
        this.account_type_id = account_type_id;
    }

    public String getAccount_type_describe() {
        return this.account_type_describe;
    }

    public void setAccount_type_describe(String account_type_describe) {
        this.account_type_describe = account_type_describe;
    }



   


}
