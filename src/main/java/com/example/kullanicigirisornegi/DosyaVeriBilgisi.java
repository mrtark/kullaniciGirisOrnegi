package com.example.kullanicigirisornegi;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Data
@Log4j2

public class DosyaVeriBilgisi {
    //field verileri
    private String idNumarasi;
    private String dizinyolu;
    private File dosyaYapisi;
    //start - - parametresiz constructer
    public DosyaVeriBilgisi() {
        this.idNumarasi = UUID.randomUUID().toString();
        this.dizinyolu = DosyaDizinAdı.DIZINYOLU;

        //dosya oluştur
        dosyaYapisi =  new File(dizinyolu);
        try {
            if (dosyaYapisi.createNewFile()){
                log.info("ID No: "+idNumarasi + " => [\n" + dosyaYapisi + " ]" + " adlı dosya başarıyla oluşturuldu.");
                //System.out.println(dosyaYapisi + " adlı dosya başarıyla oluşturuldu.");
            }else {
                log.error("ID No: "+idNumarasi + " => [\n" + dosyaYapisi + " adlı dosya dizinde zaten mevcut!");
                System.out.println(" => [\n" + dosyaYapisi + " adlı dosya dizinde zaten mevcut!");
                throw new MuratArıkException("=> [\n" + dosyaYapisi + "Kendi Exception yapımdan uyarı. Dosya zaten var!");
            }
        }catch (IOException ioHatalarınıYakala){
            ioHatalarınıYakala.printStackTrace();
        }catch (Exception genelHataYakala){
            genelHataYakala.printStackTrace();
        }
    } //end - - parametresiz constructer

    public static void main(String[] args) {
        DosyaVeriBilgisi yeniDosya = new DosyaVeriBilgisi();

    }
}
