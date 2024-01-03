package com.sme.app.permission;


import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.entity.User;
import com.sme.app.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserContextInterceptor implements HandlerInterceptor, Ordered {

    private final UserRepo userRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        if (!requestURI.contains("api/v1")) {
            return true;
        }
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
