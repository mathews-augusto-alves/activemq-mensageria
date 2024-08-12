package br.com.mensageria.domain.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompositeResult {
    private Boolean isValid;
    private List<String> errors;

    public CompositeResult() {
        errors = new ArrayList<>();
    }

    public CompositeResult(Boolean isValid) {
        this.errors = new ArrayList<>();
        this.isValid = isValid;
    }
}
