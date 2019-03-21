package org.alexeyn.java;

import java.util.List;

public interface Formatter<T> {
    String fmt(T t);
}

class instances {
    static Formatter<String> string = s -> String.format("string: %s", s);
    static Formatter<Integer> integer = i -> String.format("int: %s", i);
}

class Api {
    static <T> String fmt(T t, Formatter<T> ev) {
        return ev.fmt(t);
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(Api.fmt("some string", instances.string));
        System.out.println(Api.fmt(4, instances.integer));
    }
}

class Fmt {
    String fmt(int i) {
        return "";
    }

    String fmt(String i) {
        return "";
    }

    <A> String fmt(List<A> i) {
        return "";
    }
}