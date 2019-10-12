package com.inomera.telco.commons.lang;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * @author Serdar Kuzucu
 */
public class DelegatingImmutableList<T> implements List<T> {
    private final List<T> content;

    public DelegatingImmutableList(List<T> content) {
        this.content = Collections.unmodifiableList(content);
    }

    @Override
    public int size() {
        return content.size();
    }

    @Override
    public boolean isEmpty() {
        return content.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return content.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public Object[] toArray() {
        return content.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return content.toArray(a);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return content.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public void sort(Comparator<? super T> c) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public void clear() {
        content.clear();
    }

    @Override
    public boolean equals(Object o) {
        return content.equals(o);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public T get(int index) {
        return content.get(index);
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("This list is immutable!");
    }

    @Override
    public int indexOf(Object o) {
        return content.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return content.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return content.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return content.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return content.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<T> spliterator() {
        return content.spliterator();
    }
}
