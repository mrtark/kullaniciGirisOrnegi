package com.example.kullanicigirisornegi;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class DosyaİzinServisi {
    //fields
    private DosyaVeriBilgisi referansDosyaVeriBilgisi;
    private Scanner inputAl;
    private girisDTO ref_girisYapmismi;
    private String girisDurumuNe;   //rol bilgisini taşıyor
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
                "[4]  Dosya İzin Bilgileri (Roller Hangi İşlemleri Yapabilir)\n" +
                "[5]  Mevcut Dizindeki Diğer Dosyaları Göster\n" +
                "[6]  Mevcut Rol Bilgisini Getir\n" +
                "[7]  Dosyaya Veri Yaz\n" +
                "[8]  Seçili Dosya İçeriğini Oku\n" +
                "[9]  Seçili Dosya Adını Değiştir\n" +
                "[10] Rol Değiştir (SuperCode ile Admin Olabilirsiniz)\n" +
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
                    if (girisDurumuNe.equals("ADMİN_PTRN")){
                        System.out.println("Rolünüz Zaten Admin");
                    }else {
                        rolDegis();
                    }
                    break;
                case 11:
                    logOut();
                    break;
                default:
                    System.out.println("İslem Menüsü Dışında Seçim Yapıldı");
                    break;
            }
        }
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
        dizindekiDigerDosyalar();
        System.out.print("Silmek İstediğiniz Dosya Adını Giriniz: ");
        inputAl.nextLine(); //int değer aldıktan sonra string değer alınca sapıtıyor. bu şekilde önüne geçtim
        String dosyaAdi = inputAl.nextLine();
        String nereye = DosyaDizinAdı.DIZINYOLU.concat(dosyaAdi);
        dosya = new File(nereye);

        try {
            if (dosya.exists()){    //girilen dosya adında dosya var ise silecek
                dosya.delete();
                System.out.println("{ " + dosyaAdi + " }" + " Adlı Dosya Silindi.");
            }else {
                System.out.println("Dosya Silinemedi");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
        System.out.println("- Admin  = C+,D+,W+,R+\n" +
                "- Writer = C-,D-,W+,R+\n" +
                "- User   = C-,D-,W-,R+");
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
        System.out.println("Giriş Yapılan Rol: " + girisDurumuNe);
    }
    private void dosyaVeriYaz() {
        System.out.println("__ Seçili Dosyaya Veri Yaz Menüsü __");
        dizindekiDigerDosyalar();
        System.out.print("Yazmak İstediğiniz Dosya Adını Giriniz: ");
        inputAl.nextLine(); //int değer aldıktan sonra string değer alınca sapıtıyor. bu şekilde önüne geçtim
        String dosyaAdi = inputAl.nextLine();
        String nereye = DosyaDizinAdı.DIZINYOLU.concat(dosyaAdi);
        dosya = new File(nereye);

        System.out.print("Dosya içeriğinin üzerine mi yazılsın?" + "( E / H ) = ");
        String cevap = inputAl.nextLine().toLowerCase();
        if (cevap.equals("e")){                                                         //false ise, içerik silinir ve yazılan içerik
            try (BufferedWriter refYaz = new BufferedWriter(new FileWriter(dosya,false))) {
                System.out.println("Dosya üzerine yazılacak Veriyi - Verileri Giriniz: ");
                String veri = inputAl.nextLine();
                refYaz.write(veri);
                refYaz.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (cevap.equals("h")){
            try (BufferedWriter refYaz = new BufferedWriter(new FileWriter(dosya,true))) {
                System.out.println("Dosya İçerisine eklenecek - yazılacak Veriyi - Verileri Giriniz: ");
                String veri = inputAl.nextLine();
                refYaz.write(veri);
                refYaz.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Geçersiz Cevap...");
            System.out.println("Veri Yaz Menüsüne Yönlendiriliyorsunuz");
            dosyaVeriYaz();
        }
    }
    private void dosyaVeriOku() {
        System.out.println("__ Seçili Dosya İçeriğini Oku Menüsü __");
        dizindekiDigerDosyalar();
        System.out.print("İçeriğini Okumak İstediğiniz Dosya Adını Giriniz: ");
        inputAl.nextLine(); //int değer aldıktan sonra string değer alınca sapıtıyor. bu şekilde önüne geçtim
        String dosyaAdi = inputAl.nextLine();
        String nerede = DosyaDizinAdı.DIZINYOLU.concat(dosyaAdi);
        dosya = new File(nerede);
        try (BufferedReader refOku = new BufferedReader(new FileReader(nerede))) {
            StringBuilder refBuilder = new StringBuilder();
            String satır = "";
            while ( ( satır = refOku.readLine() )!=null ){
                refBuilder.append(satır).append("\n");
            }
            String donOkuVeri = refBuilder.toString();
            System.out.println("{ " + dosyaAdi + " }" + " Dosya Başarıyla Okundu!\nİçeriği: " + donOkuVeri);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void dosyaAdıDegis() {
        System.out.println("__ Seçili Dosya Adını Değiştir Menüsü __");
    }
    private void rolDegis(){
        System.out.println("____[ Rol Değiştirme Menüsü ]____");
        System.out.println("Mevcut Rolünüz: " + girisDurumuNe);
        System.out.print("Rol Değiştirmek için Super Code Bilgisini Giriniz: ");
        inputAl.nextLine();
        String rolDegis = inputAl.nextLine();
        if (rolDegis.equals("XLMsuper1")){
            girisDurumuNe = "ADMIN_PTRN";
            System.out.println("Rol Değiştirme Başarılı.\n" + "Yeni Rol: " + girisDurumuNe);
        }else {
            System.out.println("Super Code Bilgisi Yanlış!\nRol Değiştirilemedi.");
            System.out.println("Rolünüz: " + girisDurumuNe);
        }
    }
    private void logOut() {
        System.out.println("__ Sistemden Çıkıs Yapılsın Mı? __");
        System.out.print("( E / H ) = ");
        inputAl.nextLine();
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