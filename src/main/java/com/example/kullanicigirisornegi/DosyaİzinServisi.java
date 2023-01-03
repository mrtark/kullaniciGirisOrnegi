package com.example.kullanicigirisornegi;

import java.util.Scanner;

public class DosyaİzinServisi {
    //fields
    private DosyaVeriBilgisi referansDosyaVeriBilgisi;
    private Scanner inputAl;
    //parametresiz constructer
    public DosyaİzinServisi() {
        referansDosyaVeriBilgisi = new DosyaVeriBilgisi();
        inputAl = new Scanner(System.in);
    }
    //islemler
    private int dosyaIslemleri(){
        System.out.println("|- Yapmak İstediğiniz İşlemi Seçiniz -|" +
                "[1]  Dosya Oluştur\n"+
                "[2]  Dosya Sil\n" +
                "[3]  Dosya Bilgilerini Getir\n" +
                "[4]  Dosya İzin Bilgileri (Rol Değiştir)\n" +
                "[5]  Mevcut Dizindeki Diğer Dosyaları Göster\n" +
                "[6]  Mevcut Rol Bilgisini Getir\n" +
                "[7]  Dosyaya Veri Yaz\n" +
                "[8]  Dosya İçeriğini Oku\n" +
                "[9]  Dosya Adını Değiştir\n" +
                "[10] super code olan writer veya user rolü  ==>  super-admin olsun\n" +
                "[11] Sistemden Çık");
                );
        System.out.print("-> ");
        return inputAl.nextInt();
    }
    //seçilen işlemi yap
    private void islemiYap(){

        while (true){
        int alınanIslem = dosyaIslemleri();
        switch (alınanIslem){
            case 1:
                System.out.println("__ Dosya Oluştur Menüsü __");
                dosyaOlustur();
                break;
            case 2:
                System.out.println("__ Dosya Sil Menüsü __");
                dosyaSil();
                break;
            case 3:
                System.out.println("__ Dosya Bilgileri Menüsü __");
                dosyaBilgileri();
                break;
            case 4:
                System.out.println("__ Dosya İzin Bilgileri Menüsü __");
                dosyaİzinleri();
                break;
            case 5:
                System.out.println("__ Dizindeki Diğer Dosyalar Menüsü __");
                dizindekiDigerDosyalar();
                break;
            case 6:
                System.out.println("__ Mevcut Rol Bilgisi Menüsü __");
                rolBilgisi();
                break;
            case 7:
                System.out.println("__ Seçili Dosyaya Veri Yaz Menüsü __");
                dosyaVeriYaz();
                break;
            case 8:
                System.out.println("__ Seçili Dosya İçeriğini Oku Menüsü __");
                dosyaVeriOku();
                break;
            case 9:
                System.out.println("__ Seçili Dosya Adını Değiştir Menüsü __");
                dosyaAdıDegis();
                break;
            case 10:
                System.out.println("__ Super Code vsvs Menüsü __");

                break;
            case 11:
                System.out.println("__ Super Code vsvs Menüsü __");
                logOut();
                break;
        }

    }
    //kullanıcı verisi
    private String kullaniciVerisi(){

    }
}
