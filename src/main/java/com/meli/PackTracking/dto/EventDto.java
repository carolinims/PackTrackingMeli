package com.meli.PackTracking.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meli.PackTracking.domain.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventDto {
	private String pacoteId;
	private String localizacao;
	private String descricao;
	private Date dataHora;
	
	public static EventDto convertEventFromDomain(Event event) {
		return new EventDto(event.getPack().getId_pack(), event.getLocation(), event.getDescription(), event.getDateHour());
	}
}
