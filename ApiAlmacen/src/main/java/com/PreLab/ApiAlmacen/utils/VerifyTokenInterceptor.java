package com.PreLab.ApiAlmacen.utils;

import com.PreLab.ApiAlmacen.annotations.VerifyToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONObject;

import java.io.PrintWriter;

public class VerifyTokenInterceptor implements HandlerInterceptor{

    private JWTUtil jwtUtil;

    public VerifyTokenInterceptor(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.getMethod().isAnnotationPresent(VerifyToken.class)) {

            String jwt = request.getHeader("Authorization");
            jwt = jwt.substring(7, jwt.length());
            System.out.println("jwt = " + jwt);
            if(! jwtUtil.verifyToken(jwt)){

                response.setContentType("application/json");
                JSONObject json = new JSONObject();
                json.put("msg","Unauthorized");

                PrintWriter out = response.getWriter();
                out.print(json);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized
                return false;
            }
            return true;
        }
        // If // annotation is not present, continue
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
