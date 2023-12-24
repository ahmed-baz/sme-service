package com.demo.user.permission;


import com.demo.user.model.User;
import com.demo.user.vo.UserRole;

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

    public static boolean isAdmin() {
        return !isGuest() && getRole().equals(UserRole.ADMIN);
    }

    public static String getEmail() {
        return userThreadLocal.get() != null ? userThreadLocal.get().getEmail() : null;
    }

    public static void clear() {
        userThreadLocal.remove();
    }

}