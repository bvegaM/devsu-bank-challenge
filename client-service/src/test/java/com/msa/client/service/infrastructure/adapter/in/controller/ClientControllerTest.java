package com.msa.client.service.infrastructure.adapter.in.controller;

import com.msa.client.service.application.ports.ClientIPort;
import com.msa.client.service.domain.models.Client;
import com.msa.client.service.infrastructure.adapters.in.controller.ClientController;
import com.msa.client.service.infrastructure.adapters.in.mapper.ClientDtoMapper;
import com.msa.client.service.infrastructure.exceptions.GlobalExceptionHandler;
import com.msa.client.service.server.models.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    @Mock
    private ClientIPort clientIPort;

    @Mock
    private ClientDtoMapper clientDtoMapper;

    private MockMvc mockMvc;

    private ClientController clientController;

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        clientController = spy(new ClientController(clientIPort, clientDtoMapper));
        globalExceptionHandler = spy(new GlobalExceptionHandler());

        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(globalExceptionHandler).build();
    }

    @Test
    void getClientById_ShouldReturnClientDto() throws Exception {
        // given
        Integer clientId = 1;
        Client client = new Client();
        client.setId(clientId);
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientId);

        when(clientIPort.getById(clientId)).thenReturn(client);
        when(clientDtoMapper.toClientDto(client)).thenReturn(clientDto);

        // when & then
        mockMvc.perform(get("/clients/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(clientId));
    }
}
