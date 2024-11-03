package com.msa.client.service.domain;

import com.msa.client.service.domain.exceptions.ClientServiceException;
import com.msa.client.service.domain.models.Client;

import com.msa.client.service.domain.models.enums.GenreEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = Client.builder()
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
    @DisplayName("Should encrypt password successfully")
    void encryptPassword_ShouldEncryptPassword() {
        // given
        String originalPassword = client.getPassword();

        // when
        client.encryptPassword();

        // then
        assertNotEquals(originalPassword, client.getPassword(), "Password should be encrypted");
    }

    @Test
    @DisplayName("Should set status to zero when deactivating user")
    void deactivateUser_ShouldSetStatusToZero() {
        // given
        assertEquals(1, client.getStatus(), "Initial status should be 1");

        // when
        client.deactivateUser();

        // then
        assertEquals(0, client.getStatus(), "Status should be set to 0 after deactivation");
    }

    @Test
    @DisplayName("Should update fields when new client values are different")
    void updateClient_ShouldUpdateFieldsWhenNewValuesAreDifferent() {
        // given
        Client newClient = Client.builder()
                .name("Jane Doe")
                .identification("987654321")
                .age(28)
                .genre(GenreEnum.FEMALE)
                .direction("456 Oak St")
                .phone("555-5678")
                .password("newpassword")
                .status(1)
                .build();

        // when
        client.updateClient(newClient);

        // then
        assertEquals("Jane Doe", client.getName());
        assertEquals("987654321", client.getIdentification());
        assertEquals(28, client.getAge());
        assertEquals(GenreEnum.FEMALE, client.getGenre());
        assertEquals("456 Oak St", client.getDirection());
        assertEquals("555-5678", client.getPhone());
        assertEquals("newpassword", client.getPassword());
        assertEquals(1, client.getStatus());
    }

    @Test
    @DisplayName("Should throw exception when new client is null")
    void updateClient_ShouldThrowExceptionWhenNewClientIsNull() {
        // given
        Client newClient = null;

        // when & then
        Exception exception = assertThrows(ClientServiceException.class, () -> client.updateClient(newClient));
        assertEquals("No existe nuevo cliente para actualizar", exception.getMessage());
    }
}
