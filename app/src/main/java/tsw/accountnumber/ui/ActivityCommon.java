package tsw.accountnumber.ui;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;

import tsw.accountnumber.Contract;
import tsw.accountnumber.R;
import tsw.accountnumber.model.MainBean;
import tsw.commonlibrary.base.mvp.BaseModel;
import tsw.commonlibrary.base.mvp.BaseMvpActivity;
import tsw.commonlibrary.base.mvp.BasePresenter;
import tsw.commonlibrary.dialog.BaseDialog;
import tsw.commonlibrary.utils.CommonUtils;

public abstract class ActivityCommon<P extends BasePresenter, M extends BaseModel> extends BaseMvpActivity<P, M> implements Contract.IMyView, View.OnClickListener, OnRefreshListener {
    public BaseDialog dialog;
    public RecyclerView recycleview;
    public SmartRefreshLayout smart_refresh_layout;
    public ArrayList<MainBean> result;
    public RecyclerView.Adapter<RecyclerView.ViewHolder> myAdapter;
    public EmptyWrapper<Object> emptyWrapper;
    public MainBean mainBean;
    public EditText application_name;
    public EditText account_name;
    public EditText password;
    public EditText mailbox_name;
    public EditText repertory_psw;
    public EditText account_type_name;
    public EditText account_type_id;
    public EditText account_type_describe;
    public ImageView add;
    public TextView title;

    @Override
    public void loadMainDataSuccess(ArrayList<MainBean> result) {
        this.result = result;
        emptyWrapper.notifyDataSetChanged();
        smart_refresh_layout.finishRefresh(true);
    }

    @Override
    public void addAccountResult(String isSuccess) {
        Toast.makeText(CommonUtils.getContext(), isSuccess, Toast.LENGTH_SHORT).show();
        dimissDialog();
        smart_refresh_layout.autoRefresh();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(CommonUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
        dimissDialog();
        smart_refresh_layout.finishRefresh(false);
    }

    private void dimissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @Override
    public void initView() {
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
        mainBean = getIntent().getParcelableExtra("mainBean");
        smart_refresh_layout = (SmartRefreshLayout) findViewById(R.id.smart_refresh_layout);
        smart_refresh_layout.setEnableLoadMore(false);
        smart_refresh_layout.setEnableRefresh(true);
        smart_refresh_layout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommonUtils.getContext());
        recycleview.setLayoutManager(linearLayoutManager);
        result = new ArrayList<MainBean>();
        myAdapter = initAdapter();
        emptyWrapper = new EmptyWrapper<>(myAdapter);
        emptyWrapper.setEmptyView(R.layout.item_nodata);
        recycleview.setAdapter(emptyWrapper);
        initTitle();
        initData();
    }

    protected void initTitle() {
        add = (ImageView) findViewById(R.id.add);
        title = (TextView) findViewById(R.id.title);
        if (mainBean == null) {
            title.setText("密码保险箱");
        } else {
            title.setText(mainBean.getAccount_type_name());
        }

        mImmersionBar.titleBar(R.id.toolbar).init();
        mImmersionBar.statusBarColor(R.color.colorPrimary).init();
        add.setOnClickListener(this);
    }

    public void showAddDialog(int resID, MainBean mainBean) {
        if (dialog == null) {
            BaseDialog.Builder builder = new BaseDialog.Builder(this);
            builder.setStyle(R.style.basedialog).setPadding(new int[]{20, 0, 20, 0}).setContentView(resID);
            builder.setOnClickListener(R.id.make_sure, this);
            if (resID == R.layout.dialog_add) {
                application_name = (EditText) builder.build().getView().findViewById(R.id.application_name);
                account_name = (EditText) builder.build().getView().findViewById(R.id.account_name);
                password = (EditText) builder.build().getView().findViewById(R.id.password);
                mailbox_name = (EditText) builder.build().getView().findViewById(R.id.mailbox_name);
                repertory_psw = (EditText) builder.build().getView().findViewById(R.id.repertory_psw);
            } else {
                account_type_name = (EditText) builder.build().getView().findViewById(R.id.account_type_name);
                account_type_id = (EditText) builder.build().getView().findViewById(R.id.account_type_id);
                account_type_describe = (EditText) builder.build().getView().findViewById(R.id.account_type_describe);
            }
            dialog = builder.build();
        }
        setDialog(resID, mainBean);
        dialog.show();
    }

    private void setDialog(int resID, MainBean mainBean) {
        if (resID == R.layout.dialog_add) {
            if (mainBean != null) {
                application_name.setText(mainBean.getApplicationName());
                account_name.setText(mainBean.getAccount());
                password.setText(mainBean.getPassword());
                mailbox_name.setText(mainBean.getAssociated_mailbox());
                repertory_psw.setText(mainBean.getRepository_password());
            }else {
                application_name.setText("");
                account_name.setText("");
                password.setText("");
                mailbox_name.setText("");
                repertory_psw.setText("");
            }
        } else {
            if (mainBean != null) {
                account_type_name.setText(mainBean.getAccount_type_name());
                account_type_id.setText(mainBean.getAccount_type_id() + "");
                account_type_describe.setText(mainBean.getAccount_type_describe());
            }else {
                account_type_name.setText("");
                account_type_id.setText("");
                account_type_describe.setText("");
            }
        }
    }

    protected abstract void initData();


    protected abstract RecyclerView.Adapter<RecyclerView.ViewHolder> initAdapter();


}
