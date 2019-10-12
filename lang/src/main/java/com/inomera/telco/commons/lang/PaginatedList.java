package com.inomera.telco.commons.lang;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Serdar Kuzucu
 */
public class PaginatedList<T> extends DelegatingImmutableList<T> implements List<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private final Pager pager;
    private final long total;
    private final List<T> content;

    public PaginatedList(List<T> content, Pager pager, long total) {
        super(content);
        this.content = content;
        this.pager = pager;
        this.total = total;
    }

    public int getNumber() {
        return pager == null ? 0 : pager.getPage();
    }

    public int getSize() {
        return pager == null ? 0 : pager.getMax();
    }

    public int getNumberOfElements() {
        return content.size();
    }

    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    public long getTotalElements() {
        return total;
    }

    public boolean hasNext() {
        return getNumber() + 1 < getTotalPages();
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean isFirst() {
        return !hasPrevious();
    }

    public Pager nextPager() {
        return hasNext() ? pager.next() : null;
    }

    public Pager previousPager() {
        if (hasPrevious()) {
            return pager.previous();
        }

        return null;
    }

    public boolean hasContent() {
        return !content.isEmpty();
    }

    public <S> PaginatedList<S> map(Function<? super T, ? extends S> converter) {
        final List<S> convertedContent = stream().map(converter).collect(Collectors.toList());
        return new PaginatedList<>(convertedContent, pager, total);
    }
}
