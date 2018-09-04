package com.ldlywt.hello.ui.home;

import com.ldlywt.hello.base.BasePresenter;
import com.ldlywt.hello.bean.ArticleListBean;
import com.ldlywt.hello.bean.BaseResult;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import javax.inject.Inject;

/**
 * <pre>
 *     author : lex
 *     e-mail : ldlywt@163.com
 *     time   : 2018/09/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }

    @Override
    public void getArticles(int page) {
        EasyHttp
                .get("/article/list/" + page + "/json")
                .execute(new CallBackProxy<BaseResult<ArticleListBean>, ArticleListBean>(new SimpleCallBack<ArticleListBean>() {
                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(ArticleListBean articleListBean) {
                        mView.updateArticleView(articleListBean.getDatas());
                    }
                }) {
                });
    }
}
