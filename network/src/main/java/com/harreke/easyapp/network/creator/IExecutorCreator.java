package com.harreke.easyapp.network.creator;

import com.harreke.easyapp.network.executor.RequestExecutor;

/**
 由huoqisheng于2016/9/20创建
 */

public interface IExecutorCreator<EXECUTOR extends RequestExecutor<?>> {
    EXECUTOR create();
}