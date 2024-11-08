package com.sme.app.interceptor;


import com.sme.app.entity.User;
import com.sme.app.enums.UserRole;

public class UserContext {

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    private UserContext() {
    }

    public static void setGuestEmail(String email) {
        User user = new User();
        user.setEmail(email);
        userThreadLocal.set(user);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static boolean isGuest() {
        return userThreadLocal.get() == null || userThreadLocal.get().getRole() == null;
    }

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }


    public static UserRole getRole() {
        return userThreadLocal.get().getRole();
    }

    public static boolean isSuperAdmin() {
        return !isGuest() && getRole().equals(UserRole.SUPER_ADMIN);
    }

    public static boolean isAdmin() {
        return !isGuest() && getRole().equals(UserRole.ADMIN);
    }

    public static boolean isMaker() {
        return !isGuest() && getRole().equals(UserRole.MAKER);
    }

    public static boolean isChecker() {
        return !isGuest() && getRole().equals(UserRole.CHECKER);
    }

    public static String getEmail() {
        return userThreadLocal.get() != null ? userThreadLocal.get().getEmail() : null;
    }

    public static void clear() {
        userThreadLocal.remove();
    }

}