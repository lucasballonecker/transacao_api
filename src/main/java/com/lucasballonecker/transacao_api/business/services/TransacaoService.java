package com.lucasballonecker.transacao_api.business.services;

import com.lucasballonecker.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.lucasballonecker.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {
        log.info("Iniciando o processamento de gravar transações " + dto );
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora posteriores à data atual");
            throw new UnprocessableEntity("Não é aceito data e hora no futuro");
        }
        if (dto.valor() < 0) {
            log.error("Valor não deve ser inferior a 0");
            throw new UnprocessableEntity("Valor não deve ser inferior a 0");
        }
        listaTransacoes.add(dto);
        log.info("Transações adicionadas com sucesso");


    }

    public void limparTransacoes() {
        log.info("Processamento para deletar informações iniciado");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Buscas de transações por tempo " + intervaloBusca + " iniciadas");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);
        log.info("Retorno de transações realizado com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo))
                .toList();
    }

}

