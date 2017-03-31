package com.harreke.easyapp.controllerlayout;

import android.view.ViewPropertyAnimator;

/**
 * 由 Harreke 于 2016/1/5 创建
 */
public class ResponseUtil {
    private static void checkCanAutoHide(IControllerWidget controllerWidget, boolean animate) {
        AutoResponse autoResponse = controllerWidget.getAutoResponse();

        if (autoResponse == AutoResponse.Show_Hide || autoResponse == AutoResponse.Hide) {
            controllerWidget.hide(animate);
        }
    }

    private static void checkCanAutoShow(IControllerWidget controllerWidget, boolean animate) {
        AutoResponse autoResponse = controllerWidget.getAutoResponse();

        if (autoResponse == AutoResponse.Show_Hide || autoResponse == AutoResponse.Show) {
            controllerWidget.show(animate);
        }
    }

    private static void checkCanHideWhenFullScreen(IControllerWidget controllerWidget, boolean animate) {
        FullScreenResponse fullScreenResponse = controllerWidget.getFullScreenResponse();

        if (fullScreenResponse == FullScreenResponse.Both || fullScreenResponse == FullScreenResponse.FullScreen) {
            checkCanAutoHide(controllerWidget, animate);
        }
    }

    private static void checkCanHideWhenLocked(boolean fullScreen, IControllerWidget controllerWidget, boolean animate) {
        LockResponse lockResponse = controllerWidget.getLockResponse();

        if (lockResponse == LockResponse.Both || lockResponse == LockResponse.Locked) {
            if (fullScreen) {
                checkCanHideWhenFullScreen(controllerWidget, animate);
            } else {
                checkCanHideWhenNonFullScreen(controllerWidget, animate);
            }
        }
    }

    private static void checkCanHideWhenNonFullScreen(IControllerWidget controllerWidget, boolean animate) {
        FullScreenResponse fullScreenResponse = controllerWidget.getFullScreenResponse();

        if (fullScreenResponse == FullScreenResponse.Both || fullScreenResponse == FullScreenResponse.NonFullScreen) {
            checkCanAutoHide(controllerWidget, animate);
        }
    }

    private static void checkCanHideWhenUnlocked(boolean fullScreen, IControllerWidget controllerWidget, boolean animate) {
        LockResponse lockResponse = controllerWidget.getLockResponse();

        if (lockResponse == LockResponse.Both || lockResponse == LockResponse.Unlock) {
            if (fullScreen) {
                checkCanHideWhenFullScreen(controllerWidget, animate);
            } else {
                checkCanHideWhenNonFullScreen(controllerWidget, animate);
            }
        }
    }

    private static void checkCanShowWhenFullScreen(IControllerWidget controllerWidget, boolean animate) {
        FullScreenResponse fullScreenResponse = controllerWidget.getFullScreenResponse();

        if (fullScreenResponse == FullScreenResponse.Both || fullScreenResponse == FullScreenResponse.FullScreen) {
            checkCanAutoShow(controllerWidget, animate);
        }
    }

    private static void checkCanShowWhenLocked(boolean fullScreen, IControllerWidget controllerWidget, boolean animate) {
        LockResponse lockResponse = controllerWidget.getLockResponse();

        if (lockResponse == LockResponse.Both || lockResponse == LockResponse.Locked) {
            if (fullScreen) {
                checkCanShowWhenFullScreen(controllerWidget, animate);
            } else {
                checkCanShowWhenNonFullScreen(controllerWidget, animate);
            }
        }
    }

    private static void checkCanShowWhenNonFullScreen(IControllerWidget controllerWidget, boolean animate) {
        FullScreenResponse fullScreenResponse = controllerWidget.getFullScreenResponse();

        if (fullScreenResponse == FullScreenResponse.Both || fullScreenResponse == FullScreenResponse.NonFullScreen) {
            checkCanAutoShow(controllerWidget, animate);
        }
    }

    private static void checkCanShowWhenUnlocked(boolean fullScreen, IControllerWidget controllerWidget, boolean animate) {
        LockResponse lockResponse = controllerWidget.getLockResponse();

        if (lockResponse == LockResponse.Both || lockResponse == LockResponse.Unlock) {
            if (fullScreen) {
                checkCanShowWhenFullScreen(controllerWidget, animate);
            } else {
                checkCanShowWhenNonFullScreen(controllerWidget, animate);
            }
        }
    }

    public static void checkHideLocked(boolean locked, boolean fullScreen, IControllerWidget controllerWidget, boolean animate) {
        if (locked) {
            checkCanHideWhenLocked(fullScreen, controllerWidget, animate);
        } else {
            checkCanHideWhenUnlocked(fullScreen, controllerWidget, animate);
        }
    }

    public static void checkShowLocked(boolean locked, boolean fullScreen, IControllerWidget controllerWidget, boolean animate) {
        if (locked) {
            checkCanShowWhenLocked(fullScreen, controllerWidget, animate);
        } else {
            checkCanShowWhenUnlocked(fullScreen, controllerWidget, animate);
        }
    }

    private static boolean isShowing(IControllerWidget controllerWidget) {
        AutoResponse autoResponse = controllerWidget.getAutoResponse();

        return (autoResponse != AutoResponse.None) && controllerWidget.isShowing();
    }

    public static boolean isShowingLocked(boolean locked, boolean fullScreen, IControllerWidget controllerWidget, ViewPropertyAnimator animate) {
        if (locked) {
            return isShowingWhenLocked(fullScreen, controllerWidget);
        } else {
            return isShowingWhenUnlocked(fullScreen, controllerWidget);
        }
    }

    private static boolean isShowingWhenFullScreen(IControllerWidget controllerWidget) {
        FullScreenResponse fullScreenResponse = controllerWidget.getFullScreenResponse();

        return (fullScreenResponse == FullScreenResponse.Both || fullScreenResponse == FullScreenResponse.FullScreen) && isShowing(controllerWidget);
    }

    private static boolean isShowingWhenLocked(boolean fullScreen, IControllerWidget controllerWidget) {
        LockResponse lockResponse = controllerWidget.getLockResponse();

        if (lockResponse == LockResponse.Both || lockResponse == LockResponse.Locked) {
            if (fullScreen) {
                return isShowingWhenFullScreen(controllerWidget);
            } else {
                return isShowingWhenNonFullScreen(controllerWidget);
            }
        } else {
            return false;
        }
    }

    private static boolean isShowingWhenNonFullScreen(IControllerWidget controllerWidget) {
        FullScreenResponse fullScreenResponse = controllerWidget.getFullScreenResponse();

        return (fullScreenResponse == FullScreenResponse.Both || fullScreenResponse == FullScreenResponse.NonFullScreen) && isShowing(controllerWidget);
    }

    private static boolean isShowingWhenUnlocked(boolean fullScreen, IControllerWidget controllerWidget) {
        LockResponse lockResponse = controllerWidget.getLockResponse();

        if (lockResponse == LockResponse.Both || lockResponse == LockResponse.Unlock) {
            if (fullScreen) {
                return isShowingWhenFullScreen(controllerWidget);
            } else {
                return isShowingWhenNonFullScreen(controllerWidget);
            }
        } else {
            return false;
        }
    }
}