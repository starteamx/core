package com.starteam.core.service.easyhttp.config;

import androidx.annotation.NonNull;

import com.starteam.core.service.easyhttp.model.BodyType;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/EasyHttp
 *    time   : 2020/01/01
 *    desc   : 请求接口配置
 */
public interface IRequestType {

    /**
     * 获取参数的提交类型
     */
    @NonNull
    BodyType getBodyType();
}