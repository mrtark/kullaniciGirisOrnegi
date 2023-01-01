package com.example.kullanicigirisornegi;

public enum ERoller {
    ADMIN_PTRN(1,"admin_ptrn"),
    WRİTER_OKUYAN(2,"writer_okuyan"),
    USER_KULLANICI(3,"user_kullanici");

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
