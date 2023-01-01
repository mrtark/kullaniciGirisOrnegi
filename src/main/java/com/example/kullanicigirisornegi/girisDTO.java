package com.example.kullanicigirisornegi;

import java.util.Scanner;

public class girisDTO {
    private String kullaniciAdi;
    private String sifre;

    public girisDTO() {
    }

    public girisDTO(String kullaniciAdi, String sifre) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }
    //toString
    @Override
    public String toString() {
        return "girisDTO{" +
                "kullaniciAdi='" + kullaniciAdi + '\'' +
                ", sifre='" + sifre + '\'' +
                '}';
    }

    //metotlar
    /*
    Aşağıdaki bilgiler tek boyutlu dizide saklayalım
    admin : username(admin)  ,password(passwd)
    writer: username(writer) ,password(passwd)
    user  : username(user)   ,password(passwd)
    */
    public girisDTO[] tumKullanicilarGirisVerisi(){
        girisDTO[] kullanici = new girisDTO[3]; //admin-writer-users için
        kullanici[0] = new girisDTO(ERoller.ADMIN_PTRN.getRolDeğeri(),"adminsifre");
        kullanici[1] = new girisDTO(ERoller.WRİTER_OKUYAN.getRolDeğeri(),"writersifre");
        kullanici[2] = new girisDTO(ERoller.USER_KULLANICI.getRolDeğeri(),"usersifre");

        return kullanici;
    }

    //kullanıcının giriş yapıp yapmadığını kt edicem
    public String girisYapmısmı(){
        String kullaniciAdiGkt,sifreGkt;
        Scanner girisKt = new Scanner(System.in);
        System.out.print("Lütfen Kullanıcı Adınızı Giriniz: ");
        kullaniciAdiGkt= girisKt.nextLine();
        System.out.print("Lütfen Kullanıcı Şifrenizi Giriniz: ");
        sifreGkt= girisKt.nextLine();

        girisDTO[] referansKullanici = tumKullanicilarGirisVerisi();

        if( kullaniciAdiGkt.equals(referansKullanici[0].getKullaniciAdi())
                &&
                sifreGkt.equals(referansKullanici[0].getSifre()) ){
            System.out.println("- ** Admin olarak Giriş Yapıldı! ** -");
        } else if (kullaniciAdiGkt.equals(referansKullanici[1].getKullaniciAdi())
                &&
                sifreGkt.equals(referansKullanici[1].getSifre())) {
            System.out.println("- ** Writer olarak Giriş Yapıldı! ** -");
        } else if (kullaniciAdiGkt.equals(referansKullanici[2].getKullaniciAdi())
                &&
                sifreGkt.equals(referansKullanici[2].getSifre())) {
            System.out.println("- ** User olarak Giriş Yapıldı! ** -");
        }else {
            System.out.println("Rol Dışı Giriş Denendi");
        }

        return "";
    }

    //getter setter
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }


    public static void main(String[] args) {
        girisDTO girisDenemesi = new girisDTO();
        girisDenemesi.girisYapmısmı();
    }
}
