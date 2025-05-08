package com.lucasballonecker.transacao_api.business.services;

import com.lucasballonecker.transacao_api.controller.dtos.EstatiscasResponseDTO;
import com.lucasballonecker.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {
    public final TransacaoService transacaoService;

    public EstatiscasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){

        log.info("Busca de estatísticas de transações iniciada pelo período de tempo " + intervaloBusca);

        long start = System.currentTimeMillis();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);
        if (transacoes.isEmpty()){
            return new EstatiscasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }
        DoubleSummaryStatistics estatisticasTransacoes = transacoes
                .stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();

        System.out.println("Tempo de requisição : " + (finish - start) + "ms");

        log.info("Estatísticas retornadas com sucesso");
        return new EstatiscasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(), estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(), estatisticasTransacoes.getMax());
    }
}
