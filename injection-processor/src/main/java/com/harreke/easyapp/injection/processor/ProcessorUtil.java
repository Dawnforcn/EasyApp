package com.harreke.easyapp.injection.processor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * Created by huoqisheng on 2016/6/12.
 */
public class ProcessorUtil {
    public static void checkAnnotationValuesHasNoEmptyStringFor(ElementKind kind, Element element, String[] values) {
        for (String value : values) {
            if (isEmpty(value)) {
                throw new IllegalArgumentException(getErrorHint(kind, element) + " annotation is using multiple-injection mode but has an empty-string value!");
            }
        }
    }

    public static void checkAnnotationValuesLengthFor(ElementKind kind, Element element, String[] values, int count) {
        if (values.length < count) {
            throw new IllegalArgumentException(getErrorHint(kind, element) + " must have " + count + " annotation value(s)!");
        }
    }

    public static void checkKind(Element element, ElementKind kind) {
        if (element.getKind() != kind) {
            throw new IllegalArgumentException(getErrorHint(element) + " must be " + kind.name() + "!");
        }
    }

    public static void checkModifierIsNotPrivate(Element element) {
        Iterator<Modifier> iterator = element.getModifiers().iterator();
        Modifier modifier;

        if (!iterator.hasNext()) {
            return;
        }
        while (iterator.hasNext()) {
            modifier = iterator.next();
            if (modifier == Modifier.PUBLIC || modifier == Modifier.PROTECTED) {
                return;
            }
        }
        throw new IllegalArgumentException(getErrorHint(element) + " must has public or default modifier!");
    }

    public static void checkParametersForMethod(Element method, String[] paramsCanonicalNames) {
        int requiredCount = paramsCanonicalNames == null ? 0 : paramsCanonicalNames.length;
        List<? extends TypeMirror> paramList = ((ExecutableType) method.asType()).getParameterTypes();
        if (paramList.size() == 0 && requiredCount == 0) {
            return;
        }
        if (paramList.size() == requiredCount) {
            int i;
            TypeMirror param;
            for (i = 0; i < requiredCount; i++) {
                param = paramList.get(i);
                if (!param.toString().equals(paramsCanonicalNames[i])) {
                    break;
                }
            }
            if (i == requiredCount) {
                return;
            }
        }
        throw new IllegalArgumentException(getErrorHint(ElementKind.METHOD, method) + " must have " + requiredCount + " parameters: " +
                (paramsCanonicalNames == null ? "void" : Arrays.toString(paramsCanonicalNames)));
    }

    public static void checkReturnTypeForMethod(Element method, TypeKind type) {
        if (!(method instanceof ExecutableElement) || ((ExecutableElement) method).getReturnType().getKind() != type) {
            throw new IllegalArgumentException(getErrorHint(ElementKind.METHOD, method) + " must return type " + type.name() + "!");
        }
    }

    public static boolean isArray(TypeMirror typeMirror) {
        return typeMirror instanceof ArrayType;
    }

    public static boolean isPrimitive(TypeMirror typeMirror) {
        return typeMirror instanceof PrimitiveType;
    }

    public static TypeMirror getReturnTypeForMethod(Element method) {
        if (method instanceof ExecutableElement) {
            return ((ExecutableElement) method).getReturnType();
        } else {
            throw new IllegalArgumentException(getErrorHint(ElementKind.METHOD, method) + " is has not return type!");
        }
    }

    public static void checkTypeArrayForField(Element field, String canonicalName) {
        TypeMirror typeMirror = field.asType();
        TypeMirror genericType = ((ArrayType) typeMirror).getComponentType();
        while (genericType != null) {
            if (genericType.toString().equals(canonicalName)) {
                return;
            }
            genericType = getTypeSuper(genericType);
        }
        throw new IllegalArgumentException(getErrorHint(ElementKind.FIELD, field) + " is not generic type of " + canonicalName + "!");
    }
    
    public static void checkTypeFor(ElementKind kind, Element element, String canonicalName) {
        TypeMirror typeMirror = element.asType();

        while (typeMirror != null) {
            if (typeMirror.toString().equals(canonicalName)) {
                return;
            }
            typeMirror = getTypeSuper(typeMirror);
        }
        throw new IllegalArgumentException(getErrorHint(kind, element) + " must be extended or implemented from " + canonicalName + "!");
    }
    
    public static void checkTypeGenericFor(ElementKind kind, Element field, String canonicalName) {
        TypeMirror typeMirror = field.asType();

        if (typeMirror instanceof DeclaredType) {
            DeclaredType declaredType = (DeclaredType) typeMirror;
            for (TypeMirror declaredTypeMirror : declaredType.getTypeArguments()) {
                if (declaredTypeMirror.toString().equals(canonicalName)) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException(getErrorHint(kind, field) + " is not generic type of " + canonicalName + "!");
    }

    public static void checkTypeIsArrayForField(Element field) {
        if (field.asType() instanceof ArrayType) {
            return;
        }
        throw new IllegalArgumentException(getErrorHint(ElementKind.FIELD, field) + " is not array!");
    }

    public static String getErrorHint(Element field) {
        return "Element \"" + field.getSimpleName() + "\" in " + field.getEnclosingElement().getSimpleName();
    }

    public static String getErrorHint(ElementKind kind, Element field) {
        return kind.name() + "\"" + field.getSimpleName() + "\" in " + field.getEnclosingElement().getSimpleName();
    }

    public static InjectionGenerator getOrCreateGenerator(Map<Element, InjectionGenerator> generatorMap, Element target) {
        InjectionGenerator generator = generatorMap.get(target);

        if (generator == null) {
            generator = new InjectionGenerator(target, InjectionProcessor.elementUtils.getPackageOf(target).getQualifiedName().toString());
            generatorMap.put(target, generator);
        }

        return generator;
    }
    
//    public static InjectionJsonGenerator getOrCreateJsonGenerator(Map<Element, InjectionJsonGenerator> layoutGeneratorMap, Element target) {
//        InjectionJsonGenerator generator = layoutGeneratorMap.get(target);
//
//        if (generator == null) {
//            generator = new InjectionJsonGenerator(target);
//            layoutGeneratorMap.put(target, generator);
//        }
//
//        return generator;
//    }

    public static InjectionLayoutGenerator getOrCreateLayoutGenerator(Map<Element, InjectionLayoutGenerator> layoutGeneratorMap, Element target, String value) {
        InjectionLayoutGenerator generator = layoutGeneratorMap.get(target);

        if (generator == null) {
            generator = new InjectionLayoutGenerator(target, InjectionProcessor.elementUtils.getPackageOf(target).getQualifiedName().toString(), value);
            layoutGeneratorMap.put(target, generator);
        }

        return generator;
    }
    
    public static InjectionMenuGenerator getOrCreateMenuGenerator(Map<Element, InjectionMenuGenerator> layoutGeneratorMap, Element target, String value) {
        InjectionMenuGenerator generator = layoutGeneratorMap.get(target);
        
        if (generator == null) {
            generator = new InjectionMenuGenerator(target, InjectionProcessor.elementUtils.getPackageOf(target).getQualifiedName().toString(), value);
            layoutGeneratorMap.put(target, generator);
        }
        
        return generator;
    }

    public static TypeMirror getTypeSuper(TypeMirror typeMirror) {
        Element element = InjectionProcessor.typeUtils.asElement(typeMirror);
        if (element instanceof TypeElement) {
            return ((TypeElement) element).getSuperclass();
        } else {
            return null;
        }
    }

    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }
}