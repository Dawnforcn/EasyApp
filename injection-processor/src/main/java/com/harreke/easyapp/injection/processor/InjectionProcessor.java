package com.harreke.easyapp.injection.processor;

import com.harreke.easyapp.injection.annotation.InjectAutoDestroy;
import com.harreke.easyapp.injection.annotation.InjectBoolean;
import com.harreke.easyapp.injection.annotation.InjectButtonsCheck;
import com.harreke.easyapp.injection.annotation.InjectCheck;
import com.harreke.easyapp.injection.annotation.InjectClick;
import com.harreke.easyapp.injection.annotation.InjectColor;
import com.harreke.easyapp.injection.annotation.InjectDimension;
import com.harreke.easyapp.injection.annotation.InjectDrawable;
import com.harreke.easyapp.injection.annotation.InjectGroupCheck;
import com.harreke.easyapp.injection.annotation.InjectInt;
import com.harreke.easyapp.injection.annotation.InjectIntArray;
import com.harreke.easyapp.injection.annotation.InjectJson;
import com.harreke.easyapp.injection.annotation.InjectLayout;
import com.harreke.easyapp.injection.annotation.InjectMenu;
import com.harreke.easyapp.injection.annotation.InjectString;
import com.harreke.easyapp.injection.annotation.InjectStringArray;
import com.harreke.easyapp.injection.annotation.InjectToolbar;
import com.harreke.easyapp.injection.annotation.InjectTouch;
import com.harreke.easyapp.injection.annotation.InjectView;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;


/**
 由huoqisheng于2016/6/2创建
 */
public class InjectionProcessor extends AbstractProcessor {
    public static Elements elementUtils;
    public static Map<Element, InjectionGenerator> mGeneratorMap;
    //    public static Map<Element, InjectionJsonGenerator> mJsonGeneratorMap;
    public static Map<Element, InjectionLayoutGenerator> mLayoutGeneratorMap;
    public static Map<Element, InjectionMenuGenerator> mMenuGeneratorMap;
    public static Messager messager;
    public static Types typeUtils;

    private void generateJava() {
        for (Element target : mGeneratorMap.keySet()) {
            InjectionGenerator generator = mGeneratorMap.get(target);
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(generator.getInjectorName(), target);
                Writer writer = jfo.openWriter();
                writer.write(generator.generate());
                writer.flush();
                writer.close();
            } catch (IOException ignored) {
            }
        }
    }

    //    private void generateJsonJava() {
    //        for (Element target : mJsonGeneratorMap.keySet()) {
    //            InjectionJsonGenerator jsonGenerator = mJsonGeneratorMap.get(target);
    //            try {
    //                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(jsonGenerator.getInjectorName(), target);
    //                Writer writer = jfo.openWriter();
    //                messager.printMessage(Diagnostic.Kind.WARNING, "json java:\n" + jsonGenerator.generate());
    //                writer.write(jsonGenerator.generate());
    //                writer.flush();
    //                writer.close();
    //            } catch (IOException ignored) {
    //            }
    //        }
    //    }

    private void generateLayoutJava() {
        for (Element target : mLayoutGeneratorMap.keySet()) {
            InjectionLayoutGenerator layoutGenerator = mLayoutGeneratorMap.get(target);
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(layoutGenerator.getInjectorName(), target);
                Writer writer = jfo.openWriter();
                writer.write(layoutGenerator.generate());
                writer.flush();
                writer.close();
            } catch (IOException ignored) {
            }
        }
    }

    private void generateMenuJava() {
        for (Element target : mMenuGeneratorMap.keySet()) {
            InjectionMenuGenerator menuGenerator = mMenuGeneratorMap.get(target);
            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(menuGenerator.getInjectorName(), target);
                Writer writer = jfo.openWriter();
                writer.write(menuGenerator.generate());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> sets = new HashSet<>();

        sets.add(InjectAutoDestroy.class.getCanonicalName());
        sets.add(InjectBoolean.class.getCanonicalName());
        sets.add(InjectButtonsCheck.class.getCanonicalName());
        sets.add(InjectCheck.class.getCanonicalName());
        sets.add(InjectClick.class.getCanonicalName());
        sets.add(InjectColor.class.getCanonicalName());
        sets.add(InjectDimension.class.getCanonicalName());
        sets.add(InjectDrawable.class.getCanonicalName());
        sets.add(InjectGroupCheck.class.getCanonicalName());
        sets.add(InjectInt.class.getCanonicalName());
        sets.add(InjectIntArray.class.getCanonicalName());
        sets.add(InjectString.class.getCanonicalName());
        sets.add(InjectStringArray.class.getCanonicalName());
        sets.add(InjectToolbar.class.getCanonicalName());
        sets.add(InjectTouch.class.getCanonicalName());
        sets.add(InjectView.class.getCanonicalName());

        sets.add(InjectLayout.class.getCanonicalName());
        sets.add(InjectMenu.class.getCanonicalName());
        sets.add(InjectJson.class.getCanonicalName());

        return sets;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mGeneratorMap = new HashMap<>();
        mLayoutGeneratorMap = new HashMap<>();
        mMenuGeneratorMap = new HashMap<>();
        //        mJsonGeneratorMap = new HashMap<>();

        processInjectAutoDestroy(roundEnv.getElementsAnnotatedWith(InjectAutoDestroy.class));
        processInjectBoolean(roundEnv.getElementsAnnotatedWith(InjectBoolean.class));
        processInjectButtonsCheck(roundEnv.getElementsAnnotatedWith(InjectButtonsCheck.class));
        processInjectCheck(roundEnv.getElementsAnnotatedWith(InjectCheck.class));
        processInjectClick(roundEnv.getElementsAnnotatedWith(InjectClick.class));
        processInjectColor(roundEnv.getElementsAnnotatedWith(InjectColor.class));
        processInjectDimension(roundEnv.getElementsAnnotatedWith(InjectDimension.class));
        processInjectDrawable(roundEnv.getElementsAnnotatedWith(InjectDrawable.class));
        processInjectGroupCheck(roundEnv.getElementsAnnotatedWith(InjectGroupCheck.class));
        processInjectInt(roundEnv.getElementsAnnotatedWith(InjectInt.class));
        processInjectIntArray(roundEnv.getElementsAnnotatedWith(InjectIntArray.class));
        processInjectString(roundEnv.getElementsAnnotatedWith(InjectString.class));
        processInjectStringArray(roundEnv.getElementsAnnotatedWith(InjectStringArray.class));
        processInjectToolbar(roundEnv.getElementsAnnotatedWith(InjectToolbar.class));
        processInjectTouch(roundEnv.getElementsAnnotatedWith(InjectTouch.class));
        processInjectView(roundEnv.getElementsAnnotatedWith(InjectView.class));

        processInjectLayout(roundEnv.getElementsAnnotatedWith(InjectLayout.class));
        processInjectMenu(roundEnv.getElementsAnnotatedWith(InjectMenu.class));
        //        processInjectJson(roundEnv.getElementsAnnotatedWith(InjectJson.class));

        generateJava();
        generateLayoutJava();
        generateMenuJava();
        //        generateJsonJava();

        messager = null;
        elementUtils = null;
        typeUtils = null;
        mGeneratorMap.clear();
        mGeneratorMap = null;
        mLayoutGeneratorMap.clear();
        mLayoutGeneratorMap = null;
        mMenuGeneratorMap.clear();
        mMenuGeneratorMap = null;
        //        mJsonGeneratorMap.clear();
        //        mJsonGeneratorMap = null;

        return true;
    }

    private void processInjectAutoDestroy(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.IDestroyable);
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addAutoDestroy(field);
            }
        }
    }

    private void processInjectBoolean(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectBoolean.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Boolean);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Boolean);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.Boolean);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addBoolean(field, values);
            }
        }
    }

    private void processInjectButtonsCheck(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            String[] paramsCanonicalNames = new String[]{JavaStringBuilder.CompoundButton, JavaStringBuilder.Int};
            for (Element method : elements) {
                String[] values = method.getAnnotation(InjectButtonsCheck.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(method);
                ProcessorUtil.checkKind(method, ElementKind.METHOD);
                ProcessorUtil.checkReturnTypeForMethod(method, TypeKind.VOID);
                ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                ProcessorUtil.checkAnnotationValuesLengthFor(ElementKind.METHOD, method, values, 2);
                ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, method.getEnclosingElement()).addButtonCheck(method, values);
            }
        }
    }

    private void processInjectCheck(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            String[] paramsCanonicalNames = new String[]{JavaStringBuilder.Boolean};
            String[] massParamsCanonicalNames = new String[]{JavaStringBuilder.CompoundButton, JavaStringBuilder.Boolean};
            for (Element method : elements) {
                String[] values = method.getAnnotation(InjectCheck.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(method);
                ProcessorUtil.checkKind(method, ElementKind.METHOD);
                ProcessorUtil.checkReturnTypeForMethod(method, TypeKind.VOID);
                if (values.length == 0) {
                    ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                } else if (values.length == 1) {
                    ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                } else {
                    ProcessorUtil.checkParametersForMethod(method, massParamsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, method.getEnclosingElement()).addCheck(method, values);
            }
        }
    }

    private void processInjectClick(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            String[] massParamsCanonicalNames = new String[]{JavaStringBuilder.View};
            for (Element method : elements) {
                String[] values = method.getAnnotation(InjectClick.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(method);
                ProcessorUtil.checkKind(method, ElementKind.METHOD);
                ProcessorUtil.checkReturnTypeForMethod(method, TypeKind.VOID);
                if (values.length == 0) {
                    ProcessorUtil.checkParametersForMethod(method, null);
                } else if (values.length == 1) {
                    ProcessorUtil.checkParametersForMethod(method, null);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                } else {
                    ProcessorUtil.checkParametersForMethod(method, massParamsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, method.getEnclosingElement()).addClick(method, values);
            }
        }
    }

    private void processInjectColor(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectColor.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Int);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Int);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.Int);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addColor(field, values);
            }
        }
    }

    private void processInjectDimension(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectDimension.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Float);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Float);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.Float);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addDimension(field, values);
            }
        }
    }

    private void processInjectDrawable(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectDrawable.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Drawable);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Drawable);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.Drawable);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addDrawable(field, values);
            }
        }
    }

    private void processInjectGroupCheck(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            String[] paramsCanonicalNames = new String[]{JavaStringBuilder.RadioButton, JavaStringBuilder.Int};
            String[] massParamsCanonicalNames = new String[]{JavaStringBuilder.RadioGroup, JavaStringBuilder.RadioButton, JavaStringBuilder.Int};
            for (Element method : elements) {
                String[] values = method.getAnnotation(InjectGroupCheck.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(method);
                ProcessorUtil.checkKind(method, ElementKind.METHOD);
                ProcessorUtil.checkReturnTypeForMethod(method, TypeKind.VOID);
                if (values.length == 0) {
                    ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                } else if (values.length == 1) {
                    ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                } else {
                    ProcessorUtil.checkParametersForMethod(method, massParamsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, method.getEnclosingElement()).addGroupCheck(method, values);
            }
        }
    }

    private void processInjectInt(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectInt.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Int);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.Int);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.Int);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addInt(field, values);
            }
        }
    }

    private void processInjectIntArray(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String value = field.getAnnotation(InjectIntArray.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.Int);
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addIntArray(field, value);
            }
        }
    }

    //    private void processInjectJson(Set<? extends Element> elements) {
    //        if (elements.size() > 0) {
    //            for (Element clazz : elements) {
    //                ProcessorUtil.checkModifierIsNotPrivate(clazz);
    //                ProcessorUtil.checkKind(clazz, ElementKind.CLASS);
    //                ProcessorUtil.getOrCreateJsonGenerator(mJsonGeneratorMap, clazz);
    //            }
    //        }
    //    }

    private void processInjectLayout(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element clazz : elements) {
                String value = clazz.getAnnotation(InjectLayout.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(clazz);
                ProcessorUtil.checkKind(clazz, ElementKind.CLASS);
                ProcessorUtil.getOrCreateLayoutGenerator(mLayoutGeneratorMap, clazz, value);
            }
        }
    }

    private void processInjectMenu(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element clazz : elements) {
                String value = clazz.getAnnotation(InjectMenu.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(clazz);
                ProcessorUtil.checkKind(clazz, ElementKind.CLASS);
                ProcessorUtil.checkTypeFor(ElementKind.CLASS, clazz, JavaStringBuilder.AppCompatActivity);
                ProcessorUtil.getOrCreateMenuGenerator(mMenuGeneratorMap, clazz, value);
            }
        }
    }

    private void processInjectString(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectString.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.String);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.String);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.String);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addString(field, values);
            }
        }
    }

    private void processInjectStringArray(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String value = field.getAnnotation(InjectStringArray.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.String);
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addStringArray(field, value);
            }
        }
    }

    private void processInjectToolbar(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element clazz : elements) {
                ProcessorUtil.checkModifierIsNotPrivate(clazz);
                ProcessorUtil.checkKind(clazz, ElementKind.CLASS);
                ProcessorUtil.checkTypeFor(ElementKind.CLASS, clazz, JavaStringBuilder.AppCompatActivity);
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, clazz).addToolbar(clazz, clazz.getAnnotation(InjectToolbar.class).value());
            }
        }
    }

    private void processInjectTouch(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            String[] paramsCanonicalNames = new String[]{JavaStringBuilder.MotionEvent};
            String[] massParamsCanonicalNames = new String[]{JavaStringBuilder.View, JavaStringBuilder.MotionEvent};
            for (Element method : elements) {
                String[] values = method.getAnnotation(InjectTouch.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(method);
                ProcessorUtil.checkKind(method, ElementKind.METHOD);
                ProcessorUtil.checkReturnTypeForMethod(method, TypeKind.BOOLEAN);
                if (values.length == 0) {
                    ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                } else if (values.length == 1) {
                    ProcessorUtil.checkParametersForMethod(method, paramsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                } else {
                    ProcessorUtil.checkParametersForMethod(method, massParamsCanonicalNames);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.METHOD, method, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, method.getEnclosingElement()).addTouch(method, values);
            }
        }
    }

    private void processInjectView(Set<? extends Element> elements) {
        if (elements.size() > 0) {
            for (Element field : elements) {
                String[] values = field.getAnnotation(InjectView.class).value();
                ProcessorUtil.checkModifierIsNotPrivate(field);
                ProcessorUtil.checkKind(field, ElementKind.FIELD);
                if (values.length == 0) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.View);
                } else if (values.length == 1) {
                    ProcessorUtil.checkTypeFor(ElementKind.FIELD, field, JavaStringBuilder.View);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                } else {
                    ProcessorUtil.checkTypeArrayForField(field, JavaStringBuilder.View);
                    ProcessorUtil.checkAnnotationValuesHasNoEmptyStringFor(ElementKind.FIELD, field, values);
                }
                ProcessorUtil.getOrCreateGenerator(mGeneratorMap, field.getEnclosingElement()).addView(field, values);
            }
        }
    }
}