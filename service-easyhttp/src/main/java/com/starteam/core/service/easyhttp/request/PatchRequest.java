package com.starteam.core.service.easyhttp.request;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.starteam.core.service.easyhttp.model.HttpMethod;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/EasyHttp
 *    time   : 2020/10/07
 *    desc   : Patch 请求
 */
public final class PatchRequest extends BodyRequest<PatchRequest> {

    public PatchRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @NonNull
    @Override
    public String getRequestMethod() {
        return HttpMethod.PATCH.toString();
    }
}