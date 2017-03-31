package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由huoqisheng于2016/6/20创建
 */
public class GenerateView extends AbsGenerator {
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
        String[] viewNames = injectionElement.values;
        String viewName;

        if (viewNames.length == 0) {
            viewName = injectionElement.elementName;
            builder.appendSpace(2).append("target.").append(viewName).append(" = (").append(injectionElement.typeName).append(") ").appendView(viewName)
                    .append(";\n");
        } else if (viewNames.length == 1) {
            viewName = viewNames[0];
            builder.appendSpace(2).append("target.").append(viewName).append(" = (").append(injectionElement.typeName).append(") ").appendView(viewName)
                    .append(";\n");
        } else {
            String typeName = injectionElement.typeName.replace("[]", "");
            builder.appendSpace(2).append(injectionElement.typeName).append(" ").append(injectionElement.elementName).append(" = new ").append(typeName)
                    .append("[").append(viewNames.length).append("];\n");
            for (int i = 0; i < viewNames.length; i++) {
                builder.appendSpace(2).append(injectionElement.elementName).append("[").append(i).append("] = ").appendViewCast(viewNames[i], typeName)
                        .append(";\n");
            }
            builder.appendSpace(2).append("target.").append(injectionElement.elementName).append(" = ").append(injectionElement.elementName).append(";\n");
        }
    }
}