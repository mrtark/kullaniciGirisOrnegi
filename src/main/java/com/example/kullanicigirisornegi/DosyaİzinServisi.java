package com.example.kullanicigirisornegi;

import java.util.Scanner;

public class DosyaİzinServisi {
    //fields
    private DosyaVeriBilgisi referansDosyaVeriBilgisi;
    private Scanner inputAl;
    private girisDTO ref_girisYapmismi;
    private String girisDurumuNe;

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
        girisDurumuNe = ref_girisYapmismi.girisYapmısmı();
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

   /*
   Admin   = Create+, Delete+, Write+,Read+     / admin_ptrn        adminsifre
   Writer  = Create-, Delete-, Write+,Read+     / writer_okuyan     writersifre
   User    = Create-, Delete-, Write-,Read+     / user_kullanici    usersifre
    */

    //seçilen işlemi yap
    private void islemiYap() {
        while (true) {
            int alınanIslem = dosyaIslemleri();
            switch (alınanIslem) {
                case 1:
                    if (girisDurumuNe.equals("ADMİN_PTRN")){
                        dosyaOlustur();
                    }else {
                        System.out.println("Yetkiniz Bulunmuyor.\nSadece Admin Dosya Oluşturabilir!");
                        System.out.println("Farklı Bir İslem Seçebilir veya Rol Değiştirebilirsiniz.");
                    }
                    break;
                case 2:
                    if (girisDurumuNe.equals("ADMİN_PTRN")){
                        dosyaSil();
                    }else {
                        System.out.println("Yetkiniz Bulunmuyor.\nSadece Admin Dosya Silebilir!");
                        System.out.println("Farklı Bir İslem Seçebilir veya Rol Değiştirebilirsiniz.");
                    }
                    break;
                case 3:
                    if (girisDurumuNe.equals("ADMİN_PTRN") ||
                            girisDurumuNe.equals("WRİTER_OKUYAN") ||
                                girisDurumuNe.equals("USER_KULLANICI")){
                        dosyaBilgileri();
                    }
                    break;
                case 4:
                    if (girisDurumuNe.equals("ADMİN_PTRN") ||
                            girisDurumuNe.equals("WRİTER_OKUYAN") ||
                            girisDurumuNe.equals("USER_KULLANICI")){
                        dosyaİzinleri();
                    }
                    break;
                case 5:
                    if (girisDurumuNe.equals("ADMİN_PTRN") ||
                            girisDurumuNe.equals("WRİTER_OKUYAN") ||
                            girisDurumuNe.equals("USER_KULLANICI")){
                        dizindekiDigerDosyalar();
                    }
                    break;
                case 6:
                    rolBilgisi();
                    break;
                case 7:
                    if (girisDurumuNe.equals("ADMİN_PTRN") || girisDurumuNe.equals("WRİTER_OKUYAN")){
                        dosyaVeriYaz();
                    }else {
                        System.out.println("Yetkiniz Bulunmuyor.\nSadece Admin ve Writer Dosya Yazabilir!");
                        System.out.println("Farklı Bir İslem Seçebilir veya Rol Değiştirebilirsiniz.");
                    }
                    break;
                case 8:
                    if (girisDurumuNe.equals("ADMİN_PTRN") ||
                            girisDurumuNe.equals("WRİTER_OKUYAN") ||
                            girisDurumuNe.equals("USER_KULLANICI")){
                        dosyaVeriOku();
                    }
                    break;
                case 9:
                    dosyaAdıDegis();
                    break;
                case 10:
                    break;
                case 11:
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
        System.out.println(girisDurumuNe);
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
        System.out.println("__ Sistemden Çıkıs Yapılsın Mı? __");
        System.out.println("( E / H ) => ");
        String cevap = inputAl.nextLine().toLowerCase();
        if (cevap.equals("e")){
            System.out.println("Çıkış Yapılıyor...");
            System.exit(0);
        }
    }
    //psvm
    public static void main(String[] args) {
        DosyaİzinServisi kontrol = new DosyaİzinServisi();
        kontrol.islemeYonlendir();
    }
}