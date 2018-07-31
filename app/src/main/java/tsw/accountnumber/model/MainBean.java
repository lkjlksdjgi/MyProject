package tsw.accountnumber.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MainBean implements Parcelable {
    private String applicationName;
    private String account;
    private String password;
    private String repository_password;
    private String associated_mailbox;
    private int type;

    private String account_type_name;
    private int account_type_id;
    private String account_type_describe;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepository_password() {
        return repository_password;
    }

    public void setRepository_password(String repository_password) {
        this.repository_password = repository_password;
    }

    public String getAssociated_mailbox() {
        return associated_mailbox;
    }

    public void setAssociated_mailbox(String associated_mailbox) {
        this.associated_mailbox = associated_mailbox;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount_type_name() {
        return account_type_name;
    }

    public void setAccount_type_name(String account_type_name) {
        this.account_type_name = account_type_name;
    }

    public int getAccount_type_id() {
        return account_type_id;
    }

    public void setAccount_type_id(int account_type_id) {
        this.account_type_id = account_type_id;
    }

    public String getAccount_type_describe() {
        return account_type_describe;
    }

    public void setAccount_type_describe(String account_type_describe) {
        this.account_type_describe = account_type_describe;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", repository_password='" + repository_password + '\'' +
                ", associated_mailbox='" + associated_mailbox + '\'' +
                ", type=" + type +
                ", account_type_name='" + account_type_name + '\'' +
                ", account_type_id=" + account_type_id +
                ", account_type_describe='" + account_type_describe + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.applicationName);
        dest.writeString(this.account);
        dest.writeString(this.password);
        dest.writeString(this.repository_password);
        dest.writeString(this.associated_mailbox);
        dest.writeInt(this.type);
        dest.writeString(this.account_type_name);
        dest.writeInt(this.account_type_id);
        dest.writeString(this.account_type_describe);
    }

    public MainBean() {
    }

    protected MainBean(Parcel in) {
        this.applicationName = in.readString();
        this.account = in.readString();
        this.password = in.readString();
        this.repository_password = in.readString();
        this.associated_mailbox = in.readString();
        this.type = in.readInt();
        this.account_type_name = in.readString();
        this.account_type_id = in.readInt();
        this.account_type_describe = in.readString();
    }

    public static final Parcelable.Creator<MainBean> CREATOR = new Parcelable.Creator<MainBean>() {
        @Override
        public MainBean createFromParcel(Parcel source) {
            return new MainBean(source);
        }

        @Override
        public MainBean[] newArray(int size) {
            return new MainBean[size];
        }
    };
}
