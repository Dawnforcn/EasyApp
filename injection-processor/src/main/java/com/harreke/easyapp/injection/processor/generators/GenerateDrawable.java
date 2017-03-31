package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateDrawable extends AbsGenerator {
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
        String[] drawableNames = injectionElement.values;
        String drawableName;

        if (drawableNames.length == 0) {
            drawableName = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendDrawable(drawableName).append(";\n");
        } else if (drawableNames.length == 1) {
            drawableName = drawableNames[0];
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendDrawable(drawableName).append(";\n");
        } else {
            builder.appendSpace(2).append("Drawable[] ").append(injectionElement.elementName).append(" = new Drawable[").append(drawableNames.length)
                    .append("];\n");
            for (int i = 0; i < drawableNames.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendDrawable(drawableNames[i]).append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}