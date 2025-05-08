package com.lucasballonecker.transacao_api.controller.dtos;

public record EstatiscasResponseDTO(Long count,
                                    Double sum,
                                    Double avg,
                                    Double min,
                                    Double max) {
}
