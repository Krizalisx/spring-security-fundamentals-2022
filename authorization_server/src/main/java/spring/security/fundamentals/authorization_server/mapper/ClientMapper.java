package spring.security.fundamentals.authorization_server.mapper;

import lombok.experimental.UtilityClass;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import spring.security.fundamentals.authorization_server.entity.Client;

@UtilityClass
public class ClientMapper {

    public static RegisteredClient mapToRegisteredClient(Client client) {
        if (client == null) {
            return null;
        }
        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType()))
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                        .build())
                .build();
    }

    public static Client mapToClient(RegisteredClient registeredClient) {
        return Client.builder()
                .id(Long.valueOf(registeredClient.getId()))
                .clientId(registeredClient.getClientId())
                .secret(registeredClient.getClientSecret())
                .authMethod(registeredClient.getClientAuthenticationMethods().stream().findFirst().get().toString())
                .grantType(registeredClient.getAuthorizationGrantTypes().stream().findFirst().get().toString())
                .redirectUri(registeredClient.getRedirectUris().stream().findFirst().get())
                .scope(registeredClient.getScopes().stream().findFirst().get())
                .build();
    }
}
