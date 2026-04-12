package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentOpt = findBy(parent);
        if (parentOpt.isPresent() && findBy(child).isEmpty()) {
            Node<E> parentNode = parentOpt.get();
            parentNode.children.add(new Node<>(child));
            return true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(node -> Objects.equals(node.value, value));
    }

    public boolean isBinary() {
        return findByPredicate(node -> node.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        if (root == null) {
            return result;
        }
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> current = data.poll();
            if (condition.test(current)) {
                result = Optional.of(current);
                return result;
            }
            data.addAll(current.children);
        }
        return result;
    }
}