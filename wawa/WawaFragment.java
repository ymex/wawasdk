package com.shiguang.wawa;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WawaFragment extends Fragment {
    private BrowserView browser;
    private OnLoadingProgressListener mOnLoadingProgressListener;


    public WawaFragment() {
        if (!WawaConfig.isConfig) {
            throw new IllegalArgumentException("please call WawaCofig.init() in Application!");
        }
    }

    public static WawaFragment instance(String mobile, String code, String sign, String nonce) {
        WawaFragment fragment = new WawaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mobile", mobile);
        bundle.putString("code", code);
        bundle.putString("sign", sign);
        bundle.putString("nonce_str", nonce);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnLoadingProgressListener) {
            this.mOnLoadingProgressListener = (OnLoadingProgressListener) context;
        }
    }

    private BrowserView.Progressable progressable = new BrowserView.Progressable() {
        @Override
        public void onProgressChanged(int progress) {
            if (mOnLoadingProgressListener != null) {
                mOnLoadingProgressListener.onLoadingProgress(progress);
            }
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onFinish() {

        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getContext();
        assert context != null;
        FrameLayout frameLayout = new FrameLayout(context);
        browser = new BrowserView(context);
        browser.getWebView().setWebViewClient(new BrowserClient(progressable));
        browser.getWebView().setWebChromeClient(new BrowserChromeClient(progressable, null));

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        frameLayout.addView(browser, params);
        return frameLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("----------------------ov::::" + pageUrl());
        browser.loadUrl(pageUrl());
    }

    private String pageUrl() {
        String mobile = getStringParam("mobile");
        String code = getStringParam("code");
        String sign = getStringParam("sign");
        String nonce = getStringParam("nonce_str");
        StringBuilder builder = new StringBuilder(WawaConfig.baseUrl);
        builder.append("?mobile=").append(mobile);
        builder.append("&code=").append(code);
        builder.append("&sign=").append(sign);
        builder.append("&nonce_str=").append(nonce);
        return builder.toString();
    }

    private String getStringParam(String key) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new IllegalArgumentException("please instance WawaFragment by WawaFragment.instance()!");
        }
        return getArguments().getString(key);
    }

    private boolean hasEmpty(String... args) {
        for (String arg : args) {
            if (TextUtils.isEmpty(arg)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mOnLoadingProgressListener = null;
    }
}
