package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/21 创建
 */
public class GenerateColor extends AbsGenerator {
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
        String[] colorNames = injectionElement.values;
        String colorName;

        if (colorNames.length == 0) {
            colorName = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendColor(colorName).append(";\n");
        } else if (colorNames.length == 1) {
            colorName = colorNames[0];
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").appendColor(colorName).append(";\n");
        } else {
            builder.appendSpace(2).append("color[] ").append(injectionElement.elementName).append(" = new color[").append(colorNames.length).append("];\n");
            for (int i = 0; i < colorNames.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendColor(colorNames[i]).append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}
