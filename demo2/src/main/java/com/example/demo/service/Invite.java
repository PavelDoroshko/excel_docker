package com.example.demo.service;

public class Invite {
private String name;

    public Invite(String name) {
        this.name = name;
    }
    public synchronized  void invite (Invite obj) throws InterruptedException {
        System.out.println(name + " invites " + obj.name.toUpperCase());
        System.out.println("1thread");
       // obj.action();

    }

    private synchronized void action() {
        System.out.println(name+"invites");
        System.out.println("2thread");
    }


}
