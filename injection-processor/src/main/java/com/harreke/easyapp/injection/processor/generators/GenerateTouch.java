package com.harreke.easyapp.injection.processor.generators;

import com.harreke.easyapp.injection.processor.InjectionElement;
import com.harreke.easyapp.injection.processor.JavaStringBuilder;

/**
 * 由 huoqisheng 于 2016/6/20 创建
 */
public class GenerateTouch extends AbsGenerator {
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
        String methodName = injectionElement.elementName;
        String[] viewNames = injectionElement.values;
        boolean massInjection = viewNames.length > 0;

        builder.appendSpace(2).append("View.OnTouchListener ").append(methodName).append("TouchListener = new View.OnTouchListener() {\n");
        builder.appendSpace(3).appendOverride();
        builder.appendSpace(3).append("public boolean onTouch(View v, MotionEvent event) {\n");
        builder.appendSpace(4).append("return target.").append(methodName).append(massInjection ? "(v, event)" : "(event)").append(";\n");
        builder.appendSpace(3).append("}\n");
        builder.appendSpace(2).append("};\n");
        if (massInjection) {
            for (String viewName : viewNames) {
                builder.appendSpace(2).appendView(viewName).append(".setOnTouchListener(").append(methodName).append("TouchListener);\n");
            }
        } else {
            builder.appendSpace(2).appendView(methodName).append(".setOnTouchListener(").append(methodName).append("TouchListener);\n");
        }
    }
}
