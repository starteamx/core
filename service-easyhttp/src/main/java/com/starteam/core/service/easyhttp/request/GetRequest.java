package com.starteam.core.service.easyhttp.request;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.starteam.core.service.easyhttp.model.HttpMethod;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/EasyHttp
 *    time   : 2019/07/20
 *    desc   : Get 请求
 */
public final class GetRequest extends UrlRequest<GetRequest> {

    public GetRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @NonNull
    @Override
    public String getRequestMethod() {
        return HttpMethod.GET.toString();
    }
}