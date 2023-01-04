package com.example.kullanicigirisornegi;

import java.util.Scanner;

public class DosyaİzinServisi {
    //fields
    private DosyaVeriBilgisi referansDosyaVeriBilgisi;
    private Scanner inputAl;
    private girisDTO ref_girisYapmismi;


    //parametresiz constructer
    public DosyaİzinServisi() {
        referansDosyaVeriBilgisi = new DosyaVeriBilgisi();
        inputAl = new Scanner(System.in);
        ref_girisYapmismi = new girisDTO();
    }

    //islemler
    private int dosyaIslemleri() {
        System.out.println("|- Yapmak İstediğiniz İşlemi Seçiniz -|\n" +
                "[1]  Dosya Oluştur\n" +
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

        System.out.print("-> ");
        return inputAl.nextInt();
    }

    //giris yapmısmı boolean datası
    private boolean girisYapmismi_bln() {
        boolean sonuc = false;
        String girisDurumuNe = ref_girisYapmismi.girisYapmısmı();

        if (girisDurumuNe!=null && !girisDurumuNe.equals("") && !girisDurumuNe.isEmpty()){
            sonuc = true;
            System.out.println(girisDurumuNe + " Adlı Kullanıcı, Sistemde Aktif!");
        }

        return sonuc;
    }

    //giris sonrası
    private void islemeYonlendir(){
        if (girisYapmismi_bln()){       //girisYapmismi_bln true ise
            System.out.print("İslem Menüsünü Getirtmek İçin Bir Tuşa Basınız.. ");
            inputAl.nextLine();
            islemiYap();
        }
    }


    //seçilen işlemi yap
    private void islemiYap() {
        while (true) {
            int alınanIslem = dosyaIslemleri();
            switch (alınanIslem) {
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
                    System.out.println("__ Sistemden Çıkılıyor... __");
                    logOut();
                    break;
                default:
                    System.out.println("İslem Menüsü Dışında Seçim Yapıldı");
                    break;
            }

        }
        /*

        //kullanıcı verisi
        private String kullaniciVerisi () {

        }

         */
    }

    //------------------------------------ Switch Case secim metodları
    private void dosyaOlustur() {
        System.out.println("__ Dosya Oluştur Menüsü __");
    }

    private void dosyaSil() {
        System.out.println("__ Dosya Sil Menüsü __");
    }

    private void dosyaBilgileri() {
        System.out.println("__ Dosya Bilgileri Menüsü __");
    }

    private void dosyaİzinleri() {
        System.out.println("__ Dosya İzin Bilgileri Menüsü __");
    }

    private void dizindekiDigerDosyalar() {
        System.out.println("__ Dizindeki Diğer Dosyalar Menüsü __");
    }

    private void rolBilgisi() {
        System.out.println("__ Mevcut Rol Bilgisi Menüsü __");
    }

    private void dosyaVeriYaz() {
        System.out.println("__ Seçili Dosyaya Veri Yaz Menüsü __");
    }

    private void dosyaVeriOku() {
        System.out.println("__ Seçili Dosya İçeriğini Oku Menüsü __");
    }

    private void dosyaAdıDegis() {
        System.out.println("__ Seçili Dosya Adını Değiştir Menüsü __");
    }

    private void logOut() {
        System.out.println("__ Sistemden Çıkılıyor... __");
    }

    //psvm
    public static void main(String[] args) {
        DosyaİzinServisi kontrol = new DosyaİzinServisi();
        kontrol.islemeYonlendir();
    }
}