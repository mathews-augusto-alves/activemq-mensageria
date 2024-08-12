package br.com.mensageria.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String email;
    private String content;
    private BigDecimal amount;
    private String currency;
    private List<Item> items;
}
