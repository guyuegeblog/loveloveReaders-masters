package com.de.blinkt.openvpn.ovpn.wxapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bccnw.reader.meimeilovereader.HomeActivity;
import com.bccnw.reader.meimeilovereader.R;
import com.bccnw.reader.meimeilovereader.api.weibo.UsersAPI;
import com.bccnw.reader.meimeilovereader.api.weibo.WBConstants;
import com.bccnw.reader.meimeilovereader.api.weixin.WXConstants;
import com.bccnw.reader.meimeilovereader.config.qq.qqConfig;
import com.bccnw.reader.meimeilovereader.model.weixin.WxTokent;
import com.bccnw.reader.meimeilovereader.model.weixin.WxUserInfo;
import com.bccnw.reader.meimeilovereader.net.okhttp.OkHttp;
import com.bccnw.reader.meimeilovereader.thirdparty.qqLogin.BaseUIListener;
import com.bccnw.reader.meimeilovereader.tool.ConstantsImageUrl;
import com.bccnw.reader.meimeilovereader.tool.NetTool;
import com.bccnw.reader.meimeilovereader.tool.RandomTool;
import com.bccnw.reader.meimeilovereader.view.T;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Bind(R.id.iv_pic)
    ImageView ivPic;
    @Bind(R.id.tv_jump)
    TextView tvJump;
    @Bind(R.id.iv_defult_pic)
    ImageView ivDefultPic;
    @Bind(R.id.activity_transition)
    RelativeLayout activityTransition;
    @Bind(R.id.qq_login)
    TextView qqlogin;
    @Bind(R.id.weibo_login)
    TextView weiBoLogin;
    @Bind(R.id.weixin_login)
    TextView weixinLogin;
    @Bind(R.id.iv_pic_show)
    ImageView ivPicShow;
    private boolean animationEnd;
    private boolean isIn;
    private WXEntryActivity mContext;
    //qq授权相关信息
    private String mAppId = null;
    private static Tencent mTencent = null;
    private static Intent mPrizeIntent = null;
    private static boolean isServerSideLogin = false;
    private static UserInfo mInfo = null;
    //微博授权相关信息
    /**
     * 显示认证后的信息，如 AccessToken
     */
    private TextView mTokenText;

    private AuthInfo mAuthInfo;

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;
    //微信授权
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        //先显示默认图，再显示网络数据库优先推荐图片
        Glide.with(mContext)
                .load(ConstantsImageUrl.TRANSITION_URLS[RandomTool.getRandom(0, ConstantsImageUrl.TRANSITION_URLS.length)])
                .crossFade()
                .placeholder(R.drawable.huaban_default)
                .error(R.drawable.huaban_default)
                .into(new GlideDrawableImageViewTarget(ivPic) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        ivDefultPic.setVisibility(View.GONE);
                    }
                });
        //第2张显示图片
        Glide.with(mContext)
                .load(ConstantsImageUrl.TRANSITION_URLS[RandomTool.getRandom(0, ConstantsImageUrl.TRANSITION_URLS.length)])
                .crossFade()
                .placeholder(R.drawable.huaban_default)
                .error(R.drawable.huaban_default)
                .into(new GlideDrawableImageViewTarget(ivPicShow) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        ivDefultPic.setVisibility(View.GONE);
                        ivPic.setVisibility(View.GONE);
                    }
                });

    }

    private void initData() {
        //..................有待完善
    }

    /****
     * qq授权登陆
     */
    private void qqLogin() {
        // 获取tencnet
        if (mTencent == null) {
            mTencent = Tencent.createInstance(qqConfig.qqAppId, this);
        }
        // 获取有奖分享的intent信息
        if (null != getIntent()) {
            mPrizeIntent = getIntent();
        }
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", qqloginListener);
            isServerSideLogin = false;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", qqloginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            //updateUserInfo();
            updateLoginButton();
        }

    }

    IUiListener qqloginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            //updateUserInfo();
            updateLoginButton();
        }
    };

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                //"返回为空", "登录失败"
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                //"返回为空", "登录失败"
                return;
            }
            //"登录成功"
            doComplete((JSONObject) response);
            //获取qq账户信息资料
            mInfo = new UserInfo(mContext, mTencent.getQQToken());
            if (ready(mContext)) {
                mInfo.getUserInfo(new BaseUIListener(mContext, "get_simple_userinfo"));
            }

        }

        protected void doComplete(JSONObject values) {
        }

        @Override
        public void onError(UiError e) {
        }

        @Override
        public void onCancel() {
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }

    public static boolean ready(Context context) {
        if (mTencent == null) {
            return false;
        }
        boolean ready = mTencent.isSessionValid()
                && mTencent.getQQToken().getOpenId() != null;
        if (!ready) {
            Toast.makeText(context, "login and get openId first, please!",
                    Toast.LENGTH_SHORT).show();
        }
        return ready;
    }

    private void updateLoginButton() {
        if (mTencent != null && mTencent.isSessionValid()) {
            if (isServerSideLogin) {
                qqlogin.setTextColor(mContext.getResources().getColor(R.color.colorTheme));
                qqlogin.setText("腾讯 QQ 登陆");
            } else {
                qqlogin.setTextColor(mContext.getResources().getColor(R.color.colorTheme));
                qqlogin.setText("退出 QQ 帐号");
            }
        } else {
            qqlogin.setTextColor(mContext.getResources().getColor(R.color.colorTheme));
            qqlogin.setText("腾讯 QQ 登陆");
        }
    }

    /***
     * 新浪微博授权登陆
     */
    private void weiBoLogin() {
        // 创建微博实例
        //mWeiboAuth = new WeiboAuth(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        // 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
        mAuthInfo = new AuthInfo(this, WBConstants.APP_KEY, WBConstants.REDIRECT_URL, WBConstants.SCOPE);
        mSsoHandler = new SsoHandler(mContext, mAuthInfo);
        //仅仅存在微博客户端授权
        mSsoHandler.authorizeClientSso(new AuthListener());
        //没有客户端,web授权
        //mSsoHandler.authorizeWeb(new AuthListener());
    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(final Bundle values) {
            // 从 Bundle 中解析 Token
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = Oauth2AccessToken.parseAccessToken(values);
                    //从这里获取用户输入的 电话号码信息
                    String phoneNum = mAccessToken.getPhoneNum();
                    if (mAccessToken.isSessionValid()) {
                        // 显示 Token
                        updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(mContext, mAccessToken);
                        //授权成功
                        try {
                            UsersAPI mUsersAPI = new UsersAPI(mContext, WBConstants.APP_KEY, mAccessToken);
                            long uid = Long.parseLong(mAccessToken.getUid());
                            mUsersAPI.show(uid, mListener); //获取用户基本信息
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 以下几种情况，您会收到 Code：
                        // 1. 当您未在平台上注册的应用程序的包名与签名时；
                        // 2. 当您注册的应用程序包名与签名不正确时；
                        // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                        //授权失败
                        String code = values.getString("code");
//                        if (!TextUtils.isEmpty(code)) {
//                            message = message + "\nObtained the code: " + code;
//                        }
//                        Toast.makeText(WBAuthActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    }

    //获取用户信息的回调
    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            Log.i("weiboStr", response);
            if (!TextUtils.isEmpty(response)) {
                // 调用 User#parse 将JSON串解析成User对象，所有的用户信息全部在这里面
//                User user = User. parse(response);
//                thirdUser.setNickName(user. name); // 昵称
//                thirdUser.setIcon(user. avatar_hd); // 头像
//                thirdUser.setGender(user. gender.equals( "m") ? "男" : "女" );
//                ThirdUserVerify. verifyUser(LoginActivity.this, thirdUser, 2);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.i("weiboStr", e.getMessage());
//            MyProgressDialog. closeDialog();
//            ErrorInfo info = ErrorInfo. parse(e.getMessage());
//            ToastUtil. showShort(LoginActivity.this, info.toString());
        }
    };

    /**
     * 显示当前 Token 信息。
     *
     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
     */
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new Date(mAccessToken.getExpiresTime()));
        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
        mTokenText.setText(String.format(format, mAccessToken.getToken(), date));

        String message = String.format(format, mAccessToken.getToken(), date);
        if (hasExisted) {
            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
        }
        mTokenText.setText(message);
    }

    /***
     * 腾讯微信用户登录
     */
    private void weiXinLogin() {
        //api
        api = WXAPIFactory.createWXAPI(mContext, WXConstants.APP_ID, true);
        api.registerApp(WXConstants.APP_ID);
        api.handleIntent(getIntent(), mContext);
        SendAuth.Req req = new SendAuth.Req();
        //授权读取用户信息
        req.scope = "snsapi_userinfo";
        //自定义信息
        req.state = "wechat_sdk_demo_test";
        //向微信发送请求
        api.sendReq(req);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp instanceof SendAuth.Resp) {
            SendAuth.Resp newResp = (SendAuth.Resp) resp;
            //获取微信传回的code
            String loginCode = newResp.code;
            getWxTokenOpenId(loginCode);
        }
    }

    //修复WXEntryActivity中的错误，在onResp方法中获取到code，然后通过下面的接口获取到token和openid：
    private void getWxTokenOpenId(String loginCode) {
        if (!NetTool.isConnected(this)) {
            T.showTextToast(this, "您的网络没有连接，请检查您的网络");
            return;
        }
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WXConstants.APP_ID + "&secret=" + WXConstants.APP_SECRET + "&code=" + loginCode + "&grant_type=authorization_code";
        OkHttpClient mOkHttpClient = OkHttp.getInstance();
        RequestBody formBody = new FormBody.Builder()
                .add("", "")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    WxTokent wxTokent = JSON.parseObject(json, WxTokent.class);
                    getWxUserInfo(wxTokent);
                } catch (Exception e) {
                }
            }
        });
    }

    //如果需要获取用户的信息，例如昵称，头像，可以使用下面的接口：
    private void getWxUserInfo(WxTokent wxTokent) {
        if (!NetTool.isConnected(this)) {
            T.showTextToast(this, "您的网络没有连接，请检查您的网络");
            return;
        }
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + wxTokent.getAccess_token() + "&openid=" + wxTokent.getOpenid() + "";
        OkHttpClient mOkHttpClient = OkHttp.getInstance();
        RequestBody formBody = new FormBody.Builder()
                .add("", "")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    Log.i("wxuserinfostr", json);
                    WxUserInfo wxUserInfo = JSON.parseObject(json, WxUserInfo.class);
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * 实现监听跳转效果;过度动画
     */
    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            animationEnd();
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

    private void animationEnd() {
        synchronized (WXEntryActivity.this) {
            if (!animationEnd) {
                animationEnd = true;
                ivPic.clearAnimation();
                toMainActivity();
            }
        }
    }

    private void toMainActivity() {
        //到主界面
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //qq
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, qqloginListener);
        }
        //weibo
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        //用户不能按返回键
        return;
    }

    @OnClick({R.id.tv_jump, R.id.qq_login, R.id.weibo_login, R.id.weixin_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jump:
                break;
            case R.id.qq_login:
                qqLogin();
                break;
            case R.id.weibo_login:
                weiBoLogin();
                break;
            case R.id.weixin_login:
                weiXinLogin();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
