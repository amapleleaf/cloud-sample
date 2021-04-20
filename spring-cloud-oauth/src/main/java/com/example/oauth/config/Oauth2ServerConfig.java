package com.example.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired(required = false)
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired(required = false)
    private UserDetailsService userDetailsService;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints
                ////启用oauth2管理，密码模式使用
                .authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器,此时默认启动
                .accessTokenConverter(jwtAccessTokenConverter())
                //一个包含了 tokenStore、TokenConverter……
                //.tokenServices()
                //不设置的时候：1、设置JwtAccessTokenConverter，则默认JwtTokenStore 2、默认为InMemoryTokenStore
                //.tokenStore();
                //1、refresh_token时会检查这个账号是否有效的功能
                //2、密码模式的用户及权限存到了数据库
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 使用非对称加密算法来对Token进行签名
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessToken();
        // 导入证书
       /* KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));*/
        converter.setKeyPair(keyPair());
        return converter;
    }
    @Bean
    public  KeyPair keyPair() {
        try {
            String privateExponent = "56557897653969520649796227281217172110227214428023358367538307469612616613002352481603705816622033743651359548398765949581729679775572332935398762466851282824042662272461451340078980272035854729480564936804765861835433279342665020760244548233856319480419630690325869502363526033279472117692095710204357956809";
            String modulus = "104775275985759122447513789957574972682476212001282382263429857156796846961764286813095007154477717880478252840077166634755287843046377137224389538281652860737102616870881394312672185738531246498049647832875824847737051665535226278140495479629793906633047713122484834470354171579048331895684061042809912982987";
            String publicExponent = "65537";

            RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
            RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return new KeyPair(factory.generatePublic(publicSpec), factory.generatePrivate(privateSpec));
        } catch ( Exception e ) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 在内存中存储测试客户端与凭证(实际应用用数据库,oauth有标准的数据库模板)
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("myappid")
                // secret
                .secret(bCryptPasswordEncoder.encode("123"))
                // 授权模式
                .authorizedGrantTypes("authorization_code","implicit","client_credentials","password","refresh_token")
                // 授权范围
                .scopes("user-api","cust-api")
                // 成功之后跳转
                .redirectUris("http://www.baidu.com")
                // 授权资源
                .resourceIds("goods")
        .autoApprove(false);

    }
    /*
      (授权端，授权码模式使用)
     * http://localhost:8089/oauth/authorize?response_type=code&client_id=myappid&redirect_uri=http://www.baidu.com
     (令牌端，获取 token)
	 * 授权码模式:http://localhost:8089/oauth/token?grant_type=authorization_code&code=aTIF85&client_id=myappid&client_secret=123&redirect_uri=http://www.baidu.com
	 * 密码模式:http://localhost:8089/oauth/token?username=user&password=123&grant_type=password&scope=user-api&client_id=myappid&client_secret=123
	 * 客户端模式:http://localhost:8089/oauth/token?grant_type=client_credentials&scope=select&client_id=myappid&client_secret=123
	 * refresh token:http://localhost:8089/oauth/token?grant_type=refresh_token&refresh_token=8495d597-0560-4598-95ef-143c0855363c&client_id=myappid&client_secret=123
     *
	 **(资源服务器用来校验token的有效性)
	   http://localhost:8089/oauth/check_token?token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ29vZHMiXSwidXNlcl9uYW1lIjoidXNlciIsInNjb3BlIjpbInVzZXItYXBpIiwiY3VzdC1hcGkiXSwiZXhwIjoxNjE4NTA2NzA3LCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6ImM0NzYyZjEzLTBiYmYtNDE4ZC1hNDllLTg0MzAzMDVkNTQ3MiIsImNsaWVudF9pZCI6Im15YXBwaWQifQ.daZZmGW2jDns40Rhl4QhID9M7i_osuB6CZ1UfLcfsIHRSAPx0M-J1uUP3ARs-FpPhRJ7gUkcZzM1ukyJq3AWNkmunB_DwCle9r9Li2BOI3k8pjfP6qURC8vriMZAH6vH5-HWomFhPqs1gqLMC_YnC6DjFF_b2EAbLrlBbMq_5MM/oauth/check_token
	 * /oauth/confirm_access(用户发送确认授权)
	 * /oauth/error(认证失败)
	 * /oauth/token_key(如果使用JWT，可以获的公钥用于 token 的验签)
	 * @param security
	 * @throws Exception
	 */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients()
                // 用于检查令牌（/oauth/check_token和/oauth/token_key）
                // 端点默认受保护denyAll()
                .checkTokenAccess("permitAll()")
                .tokenKeyAccess("isAuthenticated()");
    }
}
