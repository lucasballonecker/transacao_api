package com.lucasballonecker.transacao_api.business.service;

import com.lucasballonecker.transacao_api.business.services.EstatisticasService;
import com.lucasballonecker.transacao_api.business.services.TransacaoService;
import com.lucasballonecker.transacao_api.controller.dtos.EstatiscasResponseDTO;
import com.lucasballonecker.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.lucasballonecker.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatiscasResponseDTO estatisticas;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new EstatiscasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso() {
        transacaoService.adicionarTransacoes(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor não deve ser inferior a 0", exception.getMessage());

    }
    @Test
    void deveLancarExcecaoCasoDataEOuHoraEstejaNoFuturo() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService
                        .adicionarTransacoes(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Não é aceito data e hora no futuro", exception.getMessage());

    }
    @Test
    void deveLimparTransacaoComSucesso() {
        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }
    @Test
    void deveBuscarTransacoesDentroDoIntervalo() {
        transacaoService.adicionarTransacoes(transacao);
        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.0, OffsetDateTime.now().minusHours(1));
        List<TransacaoRequestDTO> transacoes =transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }
}
