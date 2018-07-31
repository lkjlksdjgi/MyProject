package tsw.accountnumber.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import tsw.accountnumber.R;
import tsw.accountnumber.db.greendaobean.Account_Password;
import tsw.accountnumber.model.MainBean;
import tsw.accountnumber.model.MainDataModel;
import tsw.accountnumber.presenter.AccountListPresenter;
import tsw.commonlibrary.utils.CommonUtils;

public class AccountListActivity extends ActivityCommon<AccountListPresenter, MainDataModel> {
    private ImageView back;

    @Override
    public int getLayoutID() {
        return R.layout.activity_account_list;
    }

    @Override
    public void initView() {
        super.initView();
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter.loadAccountListData(mainBean.getAccount_type_id());
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initData();
    }


    @Override
    protected RecyclerView.Adapter<RecyclerView.ViewHolder> initAdapter() {
        return new MyAdapter();
    }

    //用来区分是添加还是修改，默认是添加
    public boolean isAdd = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.make_sure:
                Account_Password accountPassword = new Account_Password();
                accountPassword.setApplication_name(application_name.getText().toString());
                accountPassword.setAccount(account_name.getText().toString());
                accountPassword.setPassword(password.getText().toString());
                accountPassword.setAssociated_mailbox(mailbox_name.getText().toString());
                accountPassword.setAccount_type_id(mainBean.getAccount_type_id());
                accountPassword.setRepository_password(repertory_psw.getText().toString());
                if (isAdd) {
                    mPresenter.addAccountPassword(accountPassword);
                }else {
                    isAdd = true;
                    mPresenter.updateAccountPassword(accountPassword);
                }
                break;
            case R.id.add:
                showAddDialog(R.layout.dialog_add, null);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CommonUtils.getContext()).inflate(R.layout.accountlist_item_recycle, parent, false);
            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final MyAdapter.MyViewHolder myViewHolder = (MyAdapter.MyViewHolder) holder;
            final MainBean mainBean = result.get(position);
            myViewHolder.application_name.setText(mainBean.getApplicationName());
            myViewHolder.account_name.setText("用户名：" + mainBean.getAccount());

            myViewHolder.password.setText("密码：********");
            if (TextUtils.isEmpty(mainBean.getAssociated_mailbox())) {
                myViewHolder.mailbox_name.setVisibility(View.GONE);
            } else {
                myViewHolder.mailbox_name.setVisibility(View.VISIBLE);
                myViewHolder.mailbox_name.setText("关联邮箱：" + mainBean.getAssociated_mailbox());
            }
            setRepositoryPassword(myViewHolder, TextUtils.isEmpty(mainBean.getRepository_password()), "********");
            myViewHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewHolder.password.setText("密码：" + mainBean.getPassword());
                    setRepositoryPassword(myViewHolder, TextUtils.isEmpty(mainBean.getRepository_password()), mainBean.getRepository_password());
                }
            });
            myViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isAdd = false;
                    showAddDialog(R.layout.dialog_add, mainBean);
                    return false;
                }
            });
        }

        private void setRepositoryPassword(MyViewHolder myViewHolder, boolean isEmepty, String pas) {
            if (isEmepty) {
                myViewHolder.repertory_psw.setVisibility(View.GONE);
            } else {
                myViewHolder.repertory_psw.setVisibility(View.VISIBLE);
                myViewHolder.repertory_psw.setText("仓库密码：" + pas);
            }
        }

        @Override
        public int getItemCount() {
            return result.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout root;
            private TextView application_name;
            private TextView account_name;

            private TextView password;

            private TextView mailbox_name;
            private TextView repertory_psw;

            public MyViewHolder(View itemView) {
                super(itemView);
                root = (LinearLayout) itemView.findViewById(R.id.root);
                application_name = (TextView) itemView.findViewById(R.id.application_name);
                account_name = (TextView) itemView.findViewById(R.id.account_name);

                password = (TextView) itemView.findViewById(R.id.password);

                mailbox_name = (TextView) itemView.findViewById(R.id.mailbox_name);
                repertory_psw = (TextView) itemView.findViewById(R.id.repertory_psw);
            }
        }
    }
}
