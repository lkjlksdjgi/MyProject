package tsw.commonlibrary.net;



public interface CallBackListener<T> {

    void onPre(boolean isDoSomething);

    void onSuccess(T t);

    //这个错误信息根据业务需求定义需要的参数
    void onError(String str);

}
