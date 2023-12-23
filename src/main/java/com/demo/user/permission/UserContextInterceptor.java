package com.demo.user.permission;


import com.demo.user.exception.AppErrorKeys;
import com.demo.user.exception.AppExceptionResponse;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserContextInterceptor implements HandlerInterceptor, Ordered {

    private final UserRepo userRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String email = request.getHeader("email");
        if (email == null) throw new AppExceptionResponse(AppErrorKeys.EMAIL_IS_REQUIRED, HttpStatus.UNAUTHORIZED);
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            UserContext.setUser(user.get());
        } else {
            UserContext.setGuestEmail(email);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.clear();
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
