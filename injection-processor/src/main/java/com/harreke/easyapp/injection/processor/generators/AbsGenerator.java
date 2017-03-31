package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 由 huoqisheng 于 2016/6/20 创建
 */
public abstract class AbsGenerator {
    private List<InjectionElement> mElementList = new ArrayList<>();

    public void add(InjectionElement injectionElement) {
        mElementList.add(injectionElement);
    }

    protected abstract void afterLoop(JavaStringBuilder builder);

    protected abstract void beforeLoop(JavaStringBuilder builder);

    public void clear() {
        mElementList.clear();
    }

    protected abstract void emptyLoop(JavaStringBuilder builder);

    public void generate(JavaStringBuilder builder) {
        beforeLoop(builder);
        if (mElementList.size() > 0) {
            for (InjectionElement injectionElement : mElementList) {
                onLooping(injectionElement, builder);
            }
        } else {
            emptyLoop(builder);
        }
        afterLoop(builder);
    }

    protected abstract void onLooping(InjectionElement injectionElement, JavaStringBuilder builder);
}