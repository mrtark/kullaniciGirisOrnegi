package com.example.kullanicigirisornegi;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class DosyaİzinServisi {
    //fields
    private DosyaVeriBilgisi referansDosyaVeriBilgisi;
    private Scanner inputAl;
    private girisDTO ref_girisYapmismi;
    private String girisDurumuNe;

    private File dosya;

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
                    rolDegis();
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
        System.out.println("____[ Dosya Oluştur Menüsü ]____");
        dizindekiDigerDosyalar();
        System.out.print("Oluşturmak İstediğiniz Dosya Adını Giriniz: ");
        inputAl.nextLine(); //int değer aldıktan sonra string değer alınca sapıtıyor. bu şekilde önüne geçtim
        String dosyaAdi = inputAl.nextLine().concat(".txt");
        String nereye = DosyaDizinAdı.DIZINYOLU.concat(dosyaAdi);
        dosya = new File(nereye);
        try {
            if (dosya.createNewFile()){
                System.out.println(dosya + " adlı dosya başarıyla oluşturuldu.");
            }else {
               System.out.println(" => [\n" + dosya + " adlı dosya dizinde zaten mevcut!");
               //throw new MuratArıkException("=> [\n" + dosya + "Kendi Exception yapımdan uyarı. Dosya zaten var!");
            }
        }catch (IOException ioHatalarınıYakala){
            ioHatalarınıYakala.printStackTrace();
        }catch (Exception genelHataYakala){
            genelHataYakala.printStackTrace();
        }

    }
    private void dosyaSil() {
        System.out.println("__ Dosya Sil Menüsü __");
    }
    private void dosyaBilgileri() {
        System.out.println("____[ Dosya Bilgileri Menüsü ]____");
        dizindekiDigerDosyalar();
        System.out.print("Bilgilerini Görmek İstediğiniz Dosya Adını Giriniz: ");
        inputAl.nextLine(); //int değer aldıktan sonra string değer alınca sapıtıyor. bu şekilde önüne geçtim
        String dosyaAdi = inputAl.nextLine();
        dosya = new File(DosyaDizinAdı.DIZINYOLU.concat(dosyaAdi));
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın, İçerik Karakter Sayısı: " + dosya.length());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın, Yazma İzni: " + dosya.canWrite());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Okuma izni: " + dosya.canWrite());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Çalıştırma izni: " + dosya.canExecute());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Hashcode: " + dosya.hashCode());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Boyutu GB: " + dosya.getTotalSpace());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Kullanabileceğim GB: " + dosya.getFreeSpace());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Kullandığım GB: " + dosya.getUsableSpace());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Bulunduğu Dizin Bilgisi: " + dosya.getAbsolutePath());
        System.out.println("{ " + dosyaAdi + " }" + " Dosyanın Değiştirilme Tarihi: " + new Date(dosya.lastModified()));

    }
    private void dosyaİzinleri() {
        System.out.println("__ Dosya İzin Bilgileri Menüsü __");
    }
    private void dizindekiDigerDosyalar() {
        System.out.println("__ Dizindeki Diğer Dosyalar Menüsü __");

        /*
        for (File getir : new File("C:\\Users\\WORKSTATION\\Desktop\\Web Tasarım\\ecodation tekrar içerikleri\\DERS 5 notlar\\Dizin\\").listFiles()){
            System.out.println(getir.getName());
        }
        */

        for (File getir : new File(DosyaDizinAdı.DIZINYOLU).listFiles()){
            System.out.println(getir.getName());
        }
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
    private void rolDegis(){

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