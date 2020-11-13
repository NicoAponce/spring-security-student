package project.spring.securitystudent.security;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.core.userdetails.User;import org.springframework.security.core.userdetails.UserDetails;import org.springframework.security.core.userdetails.UserDetailsService;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.security.provisioning.InMemoryUserDetailsManager;import static project.spring.securitystudent.security.ApplicationUserRole.*;@Configuration@EnableWebSecurity@EnableGlobalMethodSecurity(prePostEnabled = true)public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {    @Autowired    private PasswordEncoder passwordEncoder;    @Override    protected void configure(HttpSecurity http) throws Exception {        http                .csrf().disable()                .authorizeRequests()                .antMatchers("/", "index").permitAll()                .antMatchers("/api/**").hasRole(STUDENT.name())                /*.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())                .antMatchers( "/management/api/**").hasAnyRole(ADMIN.name(), MODERATOR.name())*/                .anyRequest()                .authenticated()                .and()                .httpBasic();    }    @Override    @Bean    protected UserDetailsService userDetailsService() {        UserDetails nicolas = User.builder()                .username("nicolas")                .password(passwordEncoder.encode("nicolas"))                //.roles(STUDENT.name())                .authorities(STUDENT.getGrantedAuthorities())                .build();        UserDetails dyana = User.builder()                .username("dyana")                .password(passwordEncoder.encode("dyana"))                //.roles(ADMIN.name())                .authorities(ADMIN.getGrantedAuthorities())                .build();        UserDetails maria = User.builder()                .username("maria")                .password(passwordEncoder.encode("maria"))                //.roles(MODERATOR.name())                .authorities(MODERATOR.getGrantedAuthorities())                .build();        return new InMemoryUserDetailsManager(nicolas, dyana, maria);    }}