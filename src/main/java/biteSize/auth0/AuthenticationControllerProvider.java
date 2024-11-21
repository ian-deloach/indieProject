package biteSize.auth0;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;

import javax.servlet.ServletConfig;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

class AuthenticationControllerProvider {

    private AuthenticationControllerProvider() {
    }

    private static AuthenticationController INSTANCE;

    // if multiple threads may call this, synchronize this method and consider double locking
    static AuthenticationController getInstance(Properties properties) throws UnsupportedEncodingException {
        if (INSTANCE == null) {
            String domain = properties.getProperty("domain");
            String clientId = properties.getProperty("client.id");
            String clientSecret = properties.getProperty("client.secret");

            if (domain == null || clientId == null || clientSecret == null) {
                throw new IllegalArgumentException("Missing domain, clientId, or clientSecret. Did you update src/main/webapp/WEB-INF/web.xml?");
            }

            // JwkProvider required for RS256 tokens. If using HS256, do not use.
            JwkProvider jwkProvider = new JwkProviderBuilder(domain).build();
            INSTANCE = AuthenticationController.newBuilder(domain, clientId, clientSecret)
                    .withJwkProvider(jwkProvider)
                    .build();
        }

        return INSTANCE;
    }
}