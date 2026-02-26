package SistemaLoja.BackEnd.Configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        // 50 requisições permitidas a cada 1 minuto
        Refill refill = Refill.intervally(50, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(50, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String ipCliente = request.getRemoteAddr(); // Pega o IP
        Bucket bucket = buckets.computeIfAbsent(ipCliente, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response); // Autorizado
        } else {
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Limite de requisições excedido. Tente novamente mais tarde.");
        }
    }
}