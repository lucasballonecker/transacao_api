package com.lucasballonecker.transacao_api.controller;

import com.lucasballonecker.transacao_api.business.services.EstatisticasService;
import com.lucasballonecker.transacao_api.controller.dtos.EstatiscasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {
    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatisticas das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro na busca de estatísticas de transações"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor"),
    })
    public ResponseEntity<EstatiscasResponseDTO> estatisticasTransacoes(
            @RequestParam(name = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }
}
