package com.starteam.core.service.easyhttp.request;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.starteam.core.service.easyhttp.model.HttpMethod;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/EasyHttp
 *    time   : 2020/10/07
 *    desc   : Head 请求
 */
public final class HeadRequest extends UrlRequest<HeadRequest> {

    public HeadRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @NonNull
    @Override
    public String getRequestMethod() {
        return HttpMethod.HEAD.toString();
    }
}