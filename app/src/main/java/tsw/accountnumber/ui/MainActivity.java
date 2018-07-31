package tsw.accountnumber.ui;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import tsw.accountnumber.R;
import tsw.accountnumber.db.greendaobean.Account_Type;
import tsw.accountnumber.model.MainBean;
import tsw.accountnumber.model.MainDataModel;
import tsw.accountnumber.presenter.MainActivityPresenter;
import tsw.commonlibrary.dialog.BaseDialog;
import tsw.commonlibrary.utils.CommonUtils;

public class MainActivity extends ActivityCommon<MainActivityPresenter, MainDataModel> implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
    }
    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        initData();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onItemClick(MainBean mainBean, View view) {
        Intent intent = new Intent(MainActivity.this, AccountListActivity.class);
        intent.putExtra("mainBean", mainBean);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,view,"title").toBundle());
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
                Account_Type accountType = new Account_Type();
                accountType.setAccount_type_name(account_type_name.getText().toString());
                accountType.setAccount_type_id(TextUtils.isEmpty(account_type_id.getText().toString().trim()) ? -1 : Integer.parseInt(account_type_id.getText().toString().trim()));
                accountType.setAccount_type_describe(account_type_describe.getText().toString());
                if (isAdd) {
                    mPresenter.addAccount(accountType);
                }else {
                    isAdd = true;
                    mPresenter.updateAccount(accountType);
                }

                break;
            case R.id.add:
                showAddDialog(R.layout.activity_main_dialog_add,null);
                break;
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CommonUtils.getContext()).inflate(R.layout.main_item_recycle, parent, false);
            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyAdapter.MyViewHolder myViewHolder = (MyAdapter.MyViewHolder) holder;
            final MainBean mainBean = result.get(position);
            myViewHolder.name.setText(mainBean.getAccount_type_name());
            myViewHolder.describe.setText(mainBean.getAccount_type_describe());
            myViewHolder.root.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    onItemClick(mainBean,v);
                }
            });
            myViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isAdd = false;
                    showAddDialog(R.layout.activity_main_dialog_add,mainBean);
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return result.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout root;
            private TextView name;
            private TextView describe;

            public MyViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
                describe = (TextView) itemView.findViewById(R.id.describe);
                root = (LinearLayout) itemView.findViewById(R.id.root);
            }
        }
    }
}
