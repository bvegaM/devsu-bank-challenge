package com.msa.client.service.application.service;

import com.msa.client.service.application.ports.ClientOPort;
import com.msa.client.service.domain.models.Client;
import com.msa.client.service.domain.models.enums.GenreEnum;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {


    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientOPort clientOPort;

    private Client client;

    @BeforeEach
    void setUp() {
        client = Client.builder()
                .id(1)
                .name("John Doe")
                .identification("123456789")
                .age(30)
                .genre(GenreEnum.MALE)
                .direction("123 Main St")
                .phone("555-1234")
                .password("password123")
                .status(1)
                .build();
    }

    @Test
    void save_ShouldEncryptPasswordAndSaveClient() {
        // given
        when(clientOPort.save(any(Client.class))).thenReturn(client);

        // when
        Client savedClient = clientService.save(client);

        // then
        verify(clientOPort, times(1)).save(any(Client.class));
        assertNotEquals("password123", savedClient.getPassword(), "Password should be encrypted");
    }

    @Test
    void getById_ShouldReturnClient() {
        // given
        Integer id = 1;
        when(clientOPort.getById(id)).thenReturn(client);

        // when
        Client retrievedClient = clientService.getById(id);

        // then
        verify(clientOPort, times(1)).getById(id);
        assertEquals(client, retrievedClient);
    }

    @Test
    void deleteById_ShouldDeactivateClientAndDelete() {
        // given
        Integer id = 1;
        when(clientOPort.getById(id)).thenReturn(client);

        // when
        clientService.deleteById(id);

        // then
        verify(clientOPort, times(1)).getById(id);
        assertEquals(0, client.getStatus(), "Client status should be set to 0 (deactivated)");
        verify(clientOPort, times(1)).deleteById(client);
    }

    @Test
    void updateById_ShouldUpdateAndSaveClient() {
        // given
        Integer id = 1;
        Client updatedClient = Client.builder()
                .name("Jane Doe")
                .identification("987654321")
                .age(28)
                .genre(GenreEnum.FEMALE)
                .direction("456 Oak St")
                .phone("555-5678")
                .password("newpassword")
                .status(1)
                .build();

        when(clientOPort.getById(id)).thenReturn(client);
        when(clientOPort.updateById(any(Client.class))).thenReturn(client);

        // when
        clientService.updateById(id, updatedClient);

        // then
        verify(clientOPort, times(1)).getById(id);
        verify(clientOPort, times(1)).updateById(any(Client.class));
        assertEquals("Jane Doe", client.getName());
        assertEquals("987654321", client.getIdentification());
    }
}
