package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/20 创建
 */
public class GenerateToolbar extends AbsGenerator {
    @Override
    protected void afterLoop(JavaStringBuilder builder) {
    }

    @Override
    protected void beforeLoop(JavaStringBuilder builder) {
        builder.appendSpaceEnter(2);
    }

    @Override
    protected void emptyLoop(JavaStringBuilder builder) {
    }

    @Override
    protected void onLooping(InjectionElement injectionElement, JavaStringBuilder builder) {
        String toolbarName = injectionElement.values[0];
        if (toolbarName.length() == 0) {
            toolbarName = "toolbar";
        }
        builder.appendSpace(2).append("target.setSupportActionBar(").appendViewCast(toolbarName, "Toolbar").append(");\n");
    }
}
