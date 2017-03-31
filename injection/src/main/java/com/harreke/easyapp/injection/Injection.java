package com.harreke.easyapp.injection;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashMap;

/**
 * 由huoqisheng于2016/6/9创建
 */
public class Injection {
    private static final HashMap<Class, IInject<Object>> mInjectMap = new HashMap<>();
    private static final HashMap<Class, IJsonInject> mJsonInjectMap = new HashMap<>();
    private static final HashMap<Class, ILayoutInject<Object>> mLayoutInjectMap = new HashMap<>();
    private static final HashMap<Class, IMenuInject<Object>> mMenuInjectMap = new HashMap<>();
    
    @SuppressWarnings("unchecked")
    private static IInject<Object> getInject(Object target) {
        Class targetClass = target.getClass();
        String className = targetClass.getCanonicalName() + "$$Injector";

        while (!className.startsWith("android.") && !className.startsWith("java.")) {
            try {
                Class<?> injectClass = Class.forName(className);
                IInject<Object> inject = mInjectMap.get(injectClass);
                if (inject != null) {
                    return inject;
                } else {
                    inject = (IInject<Object>) injectClass.newInstance();
                    mInjectMap.put(injectClass, inject);
                    return inject;
                }
            } catch (ClassNotFoundException e) {
                //                Logger.e("ClassNotFoundException for " + className);
                targetClass = targetClass.getSuperclass();
                className = targetClass.getCanonicalName() + "$$Injector";
            } catch (InstantiationException e) {
                //                Logger.e("InstantiationException for " + className);
                return null;
            } catch (IllegalAccessException e) {
                //                Logger.e("IllegalAccessException for " + className);
                return null;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private static IJsonInject getJsonInject(Class targetClass) {
        String className = targetClass.getCanonicalName() + "$$JsonInjector";

        while (!className.startsWith("android.") && !className.startsWith("java.")) {
            try {
                Class injectClass = Class.forName(className);
                IJsonInject inject = mJsonInjectMap.get(injectClass);
                if (inject != null) {
                    return inject;
                } else {
                    inject = (IJsonInject) injectClass.newInstance();
                    mJsonInjectMap.put(injectClass, inject);
                    return inject;
                }
            } catch (ClassNotFoundException e) {
                //                Logger.e("ClassNotFoundException for " + className);
                targetClass = targetClass.getSuperclass();
                className = targetClass.getCanonicalName() + "$$JsonInjector";
            } catch (InstantiationException e) {
                //                Logger.e("InstantiationException for " + className);
                return null;
            } catch (IllegalAccessException e) {
                //                Logger.e("IllegalAccessException for " + className);
                return null;
            }
        }

        return null;
    }
    
    @SuppressWarnings("unchecked")
    private static ILayoutInject<Object> getLayoutInject(Object target) {
        Class targetClass = target.getClass();
        String className = targetClass.getCanonicalName() + "$$LayoutInjector";
        
        while (!className.startsWith("android.") && !className.startsWith("java.")) {
            try {
                Class<?> injectClass = Class.forName(className);
                ILayoutInject<Object> inject = mLayoutInjectMap.get(injectClass);
                if (inject != null) {
                    return inject;
                } else {
                    inject = (ILayoutInject<Object>) injectClass.newInstance();
                    mLayoutInjectMap.put(injectClass, inject);
                    return inject;
                }
            } catch (ClassNotFoundException e) {
                //                Logger.e("ClassNotFoundException for " + className);
                targetClass = targetClass.getSuperclass();
                className = targetClass.getCanonicalName() + "$$LayoutInjector";
            } catch (InstantiationException e) {
                //                Logger.e("InstantiationException for " + className);
                return null;
            } catch (IllegalAccessException e) {
                //                Logger.e("IllegalAccessException for " + className);
                return null;
            }
        }
        
        return null;
    }
    
    @SuppressWarnings("unchecked")
    private static IMenuInject<Object> getMenuInject(Object target) {
        Class targetClass = target.getClass();
        String className = targetClass.getCanonicalName() + "$$MenuInjector";
        
        while (!className.startsWith("android.") && !className.startsWith("java.")) {
            try {
                Class<?> injectClass = Class.forName(className);
                IMenuInject<Object> inject = mMenuInjectMap.get(injectClass);
                if (inject != null) {
                    return inject;
                } else {
                    inject = (IMenuInject<Object>) injectClass.newInstance();
                    mMenuInjectMap.put(injectClass, inject);
                    return inject;
                }
            } catch (ClassNotFoundException e) {
                //                Logger.e("ClassNotFoundException for " + className);
                targetClass = targetClass.getSuperclass();
                className = targetClass.getCanonicalName() + "$$MenuInjector";
            } catch (InstantiationException e) {
                //                Logger.e("InstantiationException for " + className);
                return null;
            } catch (IllegalAccessException e) {
                //                Logger.e("IllegalAccessException for " + className);
                return null;
            }
        }
        
        return null;
    }

    public static void inject(Object target, View view) {
        IInject<Object> inject = getInject(target);

        if (inject != null) {
            inject.inject(target, view);
        }
    }

    public static void inject(Activity activity) {
        View view = activity.findViewById(android.R.id.content);

        if (view != null) {
            inject(activity, view);
        }
    }

    public static void inject(Fragment fragment) {
        View view = fragment.getView();

        if (view != null) {
            inject(fragment, view);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <ITEM> ITEM injectJsonDeserializable(String json, Class<ITEM> targetClass) {
        IJsonInject<ITEM> inject = getJsonInject(targetClass);

        if (inject != null) {
            return inject.toObject(json);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static String injectJsonSerializable(Object item) {
        IJsonInject inject = getJsonInject(item.getClass());

        if (inject != null) {
            return inject.toJson(item);
        } else {
            return null;
        }
    }

    public static int injectLayout(Object target, Context context) {
        ILayoutInject<Object> inject = getLayoutInject(target);

        if (inject != null) {
            return inject.layout(target, context);
        } else {
            return 0;
        }
    }

    public static int injectLayout(Activity activity) {
        return injectLayout(activity, activity);
    }
    
    public static int injectLayout(Fragment fragment) {
        Context context = fragment.getContext();

        if (context != null) {
            return injectLayout(fragment, fragment.getContext());
        } else {
            return 0;
        }
    }

    public static int injectMenu(Activity activity) {
        IMenuInject<Object> inject = getMenuInject(activity);

        if (inject != null) {
            return inject.menu(activity);
        } else {
            return 0;
        }
    }
}