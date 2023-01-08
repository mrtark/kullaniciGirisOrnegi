package com.example.kullanicigirisornegi;

public enum ERoller {
    ADMIN(1,"admin"),
    WRİTER(2,"writer"),
    USER(3,"user");

    private final int rolAnahtarı;
    private final String rolDeğeri;

    //constructer
    private ERoller(int rolAnahtarı, String rolDeğeri) {
        this.rolAnahtarı = rolAnahtarı;
        this.rolDeğeri = rolDeğeri;
    }
    //getter
    public int getRolAnahtarı() {
        return rolAnahtarı;
    }

    public String getRolDeğeri() {
        return rolDeğeri;
    }
}
