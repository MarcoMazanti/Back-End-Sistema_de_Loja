package SistemaLoja.BackEnd.Configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptador  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        int modelRecord = Integer.parseInt(request.getHeader("ModelRecord"));
        String path = request.getServletPath();

        if (path.contains("/api/pagamento")) {
            if (modelRecord >= 1 && modelRecord <= 2) return true;
        } else {
            if (modelRecord >= 1 && modelRecord <= 3) return true;
        }

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}
