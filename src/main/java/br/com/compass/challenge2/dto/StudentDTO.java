package br.com.compass.challenge2.dto;

import br.com.compass.challenge2.entity.Group;
import br.com.compass.challenge2.entity.Squad;

public record StudentDTO(Long id, String name, String email, Group group, Squad squad) {
}
