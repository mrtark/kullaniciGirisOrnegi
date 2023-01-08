package com.example.kullanicigirisornegi;

import java.util.Scanner;

public class girisDTO {
    private String kullaniciAdi;
    private String sifre;

    private static int GIRISHAKKI = 3;
    private static String kazanılanRol = "";
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
        kullanici[0] = new girisDTO(ERoller.ADMIN.getRolDeğeri(),"adminsifre");
        kullanici[1] = new girisDTO(ERoller.WRİTER.getRolDeğeri(),"writersifre");
        kullanici[2] = new girisDTO(ERoller.USER.getRolDeğeri(),"usersifre");

        return kullanici;
    }

    //Kullanıcıdan admin ve şifre bilgisini alan metod
    public String[] kullanıcıdanInputAl(){
        Scanner girisKt = new Scanner(System.in);
        String kullaniciAdiGkt,sifreGkt;

        String[] alınanGirisBilgileri = new String[2];  //alınacak bilgi, tek boyutlu ama 2 elemanlı olacak

        System.out.print("Lütfen Kullanıcı Adınızı Giriniz: ");
        kullaniciAdiGkt= girisKt.nextLine();
        System.out.print("Lütfen Kullanıcı Şifrenizi Giriniz: ");
        sifreGkt= girisKt.nextLine();

        alınanGirisBilgileri[0] = kullaniciAdiGkt;
        alınanGirisBilgileri[1] = sifreGkt;
        return alınanGirisBilgileri;
    }


    //kullanıcının giriş yapıp yapmadığını kt edicem
    public String girisYapmısmı(){
        String[] ref_kullanıcıdanInputAl = new String[2];
        ref_kullanıcıdanInputAl = kullanıcıdanInputAl();

        girisDTO[] referansKullanici = tumKullanicilarGirisVerisi();



        if (GIRISHAKKI > 1){
            if( ref_kullanıcıdanInputAl[0].equals(referansKullanici[0].getKullaniciAdi())
                    &&
                    ref_kullanıcıdanInputAl[1].equals(referansKullanici[0].getSifre()) ){
                System.out.println("- ** Admin olarak Giriş Yapıldı! ** -");
                kazanılanRol = "ADMİN_PTRN";
            } else if (ref_kullanıcıdanInputAl[0].equals(referansKullanici[1].getKullaniciAdi())
                    &&
                    ref_kullanıcıdanInputAl[1].equals(referansKullanici[1].getSifre())) {
                System.out.println("- ** Writer olarak Giriş Yapıldı! ** -");
                kazanılanRol = "WRITER_OKUYAN";
            } else if (ref_kullanıcıdanInputAl[0].equals(referansKullanici[2].getKullaniciAdi())
                    &&
                    ref_kullanıcıdanInputAl[1].equals(referansKullanici[2].getSifre())) {
                System.out.println("- ** User olarak Giriş Yapıldı! ** -");
                kazanılanRol = "USER_KULLANICI";
            }else {
                GIRISHAKKI -= 1;
                System.out.println("* Kullanıcı Adı veya Şifre Hatalı!\n" +
                        "Kalan Giris Deneme Hakkınız: " + "[" + GIRISHAKKI + "]");
                girisYapmısmı();
            }
        }
        else {
            System.out.println("-- Giriş Hakkınız Bitti  --");
            System.out.println("Sonlandırılıyor....");
        }
    return kazanılanRol;
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
