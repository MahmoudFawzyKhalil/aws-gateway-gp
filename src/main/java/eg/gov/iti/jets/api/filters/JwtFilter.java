package eg.gov.iti.jets.api.filters;

import eg.gov.iti.jets.api.util.JwtUtil;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.management.UserManagement;
import eg.gov.iti.jets.service.model.UserAdapter;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String userName = null;
        Integer userId = null;
        List<String> roles = null ;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(prefix + " ")) {
            jwt = authorizationHeader.substring(prefix.length() + 1).trim();
//            jwt = authorizationHeader.split(" ")[1].trim(); // 
            try {

                userName = jwtUtil.extractUsername(jwt);
                roles = jwtUtil.extractRoles(jwt);
                userId =  (Integer) jwtUtil.extractClaim(jwt,claims -> claims.get("id")); // todo cast to integer after type edit above
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            }
        }else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userService.loadUserByUsername(userName);
//            logger.warn(Arrays.toString(roles.toArray()));
            String[] rolesArray = convertStringListToArray(roles);
//            UserDetails userDetails = new User(userName,"", AuthorityUtils.createAuthorityList(rolesArray));
            User userEntity = new User();
            userEntity.setUsername(userName);
            userEntity.setId(userId); // todo set userId
            userEntity.setPassword(null);
            UserAdapter userDetails = new UserAdapter(userEntity, AuthorityUtils.createAuthorityList(rolesArray));
            logger.warn("authorities : "+userDetails.getAuthorities());
            logger.warn("authorities : "+userDetails.getId());
            /**
             * extract user privileges from JWT instead of loading user from db
             */
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String[] convertStringListToArray(List<String> roles) {
        String[] rolesArray = new String[roles.size()];
        for(int i = 0; i < roles.size(); i++){
            rolesArray[i] = roles.get(i);
        }
        return rolesArray;
    }

//    @Override
//    protected boolean shouldNotFilter(
//            HttpServletRequest request) {
//        return request.getServletPath()
//                .equals("/api/login");
//    }
}
