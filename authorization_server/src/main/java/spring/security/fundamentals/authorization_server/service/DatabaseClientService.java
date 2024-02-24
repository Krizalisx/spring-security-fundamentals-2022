package spring.security.fundamentals.authorization_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import spring.security.fundamentals.authorization_server.mapper.ClientMapper;
import spring.security.fundamentals.authorization_server.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class DatabaseClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(ClientMapper.mapToClient(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        return ClientMapper.mapToRegisteredClient(clientRepository.findById(Long.valueOf(id)).orElse(null));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return ClientMapper.mapToRegisteredClient(clientRepository.findByClientId(clientId).orElse(null));
    }
}
