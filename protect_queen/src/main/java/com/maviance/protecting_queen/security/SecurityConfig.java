package com.maviance.protecting_queen.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.maviance.protecting_queen.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;	
	
	/*@Autowired
	private DataSource dataSource;*/
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//We use AuthenticationManagerBuilder to add the CustomUserDetailService instance
    	//This is how we tell Spring Security where and how to retrieve the application user from the database.
    	auth.userDetailsService(customUserDetailsService)
    		.passwordEncoder(passwordEncoder());
	}
 
	/*@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("crmadmin").password("crmpass").roles("ADMIN","USER").and()
        .withUser("crmuser").password("pass123").roles("USER");
    }*/
	
 
	/**
	 * This methods defines which URL paths that should be secured in the applications.
	 */
    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {
   
    	http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http
	  	.authorizeRequests()
	  	.antMatchers("/").permitAll().and()
	  	.authorizeRequests().antMatchers("/console/**").permitAll() //That is to access h2 database via http://localhost:8080/console 
	  	.antMatchers("/signup").permitAll()
	  	.antMatchers("/oauth/token").permitAll()
	  	//.antMatchers("/api/**").authenticated()
        .anyRequest().authenticated()
	  	.and()
	  	.httpBasic()
	  		.realmName("CRM_REALM");
	    
	    http.csrf().disable();
        http.headers().frameOptions().disable();
		
    }
 
	/**
	 * the AuthenticationManager authenticates the request
	 */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    /**
     * Stores the OAuth2 token in memory 
     * @return
     */
	/*@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}*/
    
    /**
     * Stores the OAuth2 token in the database 
     * @return
     */
    /*@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}*/
    
    /**
     * JwtTokenStore encodes all the data about the grant into the token itself. The JwtTokenStore doesn’t persist any data.
     * @return
     */
    @Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenEnhancer());
	}
    
    @Bean
	protected JwtAccessTokenConverter jwtTokenEnhancer() {
		/*
	    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
	    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
	    */
		//-- for the simple demo purpose, used the secret for signing.
		//-- for production, it is recommended to use public/private key pair
	    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	    converter.setSigningKey("Demo-Key-1");
	    
	    return converter;
	}	
 
 
	/**
	 * Remembers the approval decisions by consulting existing tokens
	 * @param tokenStore
	 * @return
	 */
	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	/**
	 * An ApprovalStore that works with an existing TokenStore
	 * @param tokenStore
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
	
	/**
	 * This a bean used to encode the user's password into the database
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
