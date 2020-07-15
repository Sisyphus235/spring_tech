package com.sisyphus;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@ToString
@Getter
@AllArgsConstructor
class User {
    String name;
    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User u1 = new User("a", 1);
        User u2 = new User("b", 2);

        // 设置原子引用为 u1，解决 CAS 的 ABA 问题
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t" + atomicReference.get().toString());
    }
}
