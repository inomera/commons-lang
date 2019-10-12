package com.inomera.telco.commons.lang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Serdar Kuzucu
 */
@Getter
@ToString
@EqualsAndHashCode
public class Pager implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int page;
    private final int max;

    public Pager() {
        this(0, 20);
    }

    public Pager(int page, int max) {
        this.page = page;
        this.max = max;
    }

    public static Pager max() {
        return new Pager(0, Integer.MAX_VALUE);
    }

    public Pager next() {
        return new Pager(page + 1, max);
    }

    public Pager previous() {
        return new Pager(Math.max(page - 1, 0), max);
    }
}
