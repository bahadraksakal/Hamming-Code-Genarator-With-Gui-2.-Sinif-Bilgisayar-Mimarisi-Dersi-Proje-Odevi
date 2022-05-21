/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hammingcodebilgisayarmimarisiproje;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bahadr
 */
public class BitData {

    public String dataTxt;
    public Random rand = new Random();
    public List<String>[] parityBitForXORgui;
    public List<String>[] parityBitRepairForXORgui;
    public List<Integer>[] parityBitRepairForXORguiIndex;
    public int[] parityBitsArr;
    public String codeWordTxt;
    public StringBuilder codeWord;
    public StringBuilder codeWordRepaired;
    public StringBuilder codeWordManupilating;
    public StringBuilder parityBitsRepairArrStrBuilder;
    public int parityBitAdeti = -1;
    public int corruptedIndex;
    public int indexForManipule;
    public boolean[] bitArray;
    public boolean bitOrInt = false;
    public static boolean bitOrIntGUI = true;
    public boolean manipuleCheckFlag = false;

    // dataTxt ters çevir, p2 gibi olan isimlendirmeleri ters cevir.
    public BitData(String dataStr) throws NumberFormatException, RuntimeException {

        dataTxt = dataStr;
        bitOrInt = false;
        for (int i = 0; i < dataTxt.length(); i++) {
            if ((dataTxt.charAt(i) >= '0' && dataTxt.charAt(i) <= '9') || dataTxt.charAt(0) == '-') {
                if (dataTxt.charAt(i) >= '2' && dataTxt.charAt(i) <= '9' || dataTxt.charAt(0) == '-') {
                    bitOrInt = true;
                }
            } else {
                throw new NumberFormatException();
            }
        }

        if ((bitOrInt || bitOrIntGUI) && bitOrIntGUI) {
            int tempSayi = Integer.parseInt(dataTxt);
            dataTxt = Integer.toBinaryString(tempSayi);

            // bite çevir
        } else if ((!bitOrInt) && (!bitOrIntGUI)) {
            // zaten bit bir şey yapma
        } else {
            throw new NumberFormatException();
        }

        bitArray = new boolean[dataTxt.length()];
        for (int i = 0; i < dataTxt.length(); i++) {
            switch (dataTxt.charAt(i)) {
                case '1':
                    bitArray[i] = true;
                    break;
                case '0':
                    bitArray[i] = false;
                    break;
                default:
                    throw new RuntimeException();
            }
        }

    }

    public void printBitArray() {
        for (int i = 0; i < bitArray.length; i++) {
            System.out.println("bitArray elaman: " + i + ": " + bitArray[i]);
        }
    }

    public void parityBitAdetiHesapla(String dataStr, int mode) {

        switch (mode) {
            case 0 -> {
                for (int i = 0; i < 32; i++) {
                    if (dataTxt.length() + 1 + i > (int) Math.pow(2, i)) {
                        parityBitAdeti = i + 1;
                    } else {
                        break;
                    }
                }
            }
            case 1 -> {
                for (int i = 0; i < 32; i++) {
                    if ((int) Math.pow(2, i) <= dataTxt.length()) {
                        parityBitAdeti = i+1;
                    } else {
                        break;
                    }
                }
                codeWord = new StringBuilder(dataTxt);
                codeWordTxt = codeWord.toString();
            }
            default ->
                throw new RuntimeException();
        }

    }

    public void genarateParityBit() {

        //bahox patentli koddur. xd
        //datayı tersten girince sonuc doğru fakaat tersten yazdırıyor.
        //dataı dümdüz girince sonuc komple yanlıs. 
        if (parityBitAdeti == -1) {
            System.out.println("parity bit hesaplamadan bu fonksiyonu çalıştıramazsınız.");
        } else {

            parityBitForXORgui = new LinkedList[parityBitAdeti];
            parityBitsArr = new int[parityBitAdeti];
            codeWord = new StringBuilder(dataTxt);

            //bu for döngüsü codeworld.lengt() ve .chatAt() lerin düzgün çalışması için çalıştırılıyor.
            for (int i = 0; i < parityBitAdeti; i++) {
                codeWord.insert((int) (Math.pow(2, i) - 1), '?');
            }

            for (int i = 0; i < parityBitAdeti; i++) {
                int tempAtla = (int) Math.pow(2, i);
                int tempXOR = -1;
                boolean ilkEleman = true;
                //System.out.println("2.For a girdi  | i:" + i);

                for (int j = (int) (Math.pow(2, i) - 1); j < codeWord.length(); j++) {
                    if (tempAtla == 0) {
                        tempAtla = (int) Math.pow(2, i);
                        j += (int) (Math.pow(2, i) - 1);
                        continue;
                    }
                    tempAtla--;

                    if (ilkEleman) {
                        ilkEleman = false;
                        continue;
                    }

                    if (tempXOR == -1) {
                        // System.out.println("ilk tempXOR atama:"+(int)(codeWord.charAt(j)-48));
                        parityBitForXORgui[i] = new LinkedList<>();
                        parityBitForXORgui[i].add(String.valueOf(codeWord.charAt(j)));
                        tempXOR = (int) (codeWord.charAt(j) - 48);
                    } else {
                        parityBitForXORgui[i].add(String.valueOf(codeWord.charAt(j)));
                        tempXOR = tempXOR ^ (int) (codeWord.charAt(j) - 48);
                        //System.out.println("tempXOR ^ : "+tempXOR+" | XOR lana değer: "+(int)(codeWord.charAt(j)-48));
                    }
                }

                // System.out.println("sonuc parity: "+(char)(tempXOR + 48));
                codeWord.setCharAt((int) (Math.pow(2, i) - 1), (char) (tempXOR + 48));
                parityBitsArr[i] = tempXOR;
                // System.out.println(i+". parity bit: "+codeWord.toString());
            }

            //System.out.println(codeWord.toString());
            codeWordTxt = codeWord.toString();
        }
    }

    public void manipuleOneBitManuel(int index) {
        if (parityBitAdeti == -1) {
            System.out.println("parity bit hesaplamadan bu fonksiyonu çalıştıramazsınız.");
        } else {
            if (index < 0 || index >= codeWord.length()) {
                throw new NumberFormatException();
            } else {
                indexForManipule = index;
                codeWordManupilating = new StringBuilder(codeWord.toString());
                switch (codeWordManupilating.charAt(index)) {
                    case '0' ->
                        codeWordManupilating.setCharAt(index, '1');
                    case '1' ->
                        codeWordManupilating.setCharAt(index, '0');
                    default -> {
                        System.out.println("CodeWord Binary değil, yukarıda bir yerlerde hata var.");
                        throw new RuntimeException();
                    }
                }
                manipuleCheckFlag = true;
            }
        }
    }

    public void manipuleOneBitAuto() {

        if (parityBitAdeti == -1) {
            System.out.println("parity bit hesaplamadan bu fonksiyonu çalıştıramazsınız.");
        } else {
            int maxNumber = codeWord.length();
            indexForManipule = rand.nextInt(maxNumber);
            codeWordManupilating = new StringBuilder(codeWord.toString());
            switch (codeWordManupilating.charAt(indexForManipule)) {
                case '0' -> {
                    codeWordManupilating.setCharAt(indexForManipule, '1');
                    manipuleCheckFlag = true;
                }

                case '1' -> {
                    codeWordManupilating.setCharAt(indexForManipule, '0');
                    manipuleCheckFlag = true;
                }

                default -> {
                    System.out.println("CodeWord Binary değil, yukarıda bir yerlerde hata var.");
                    throw new RuntimeException();
                }
            }
        }

    }

    public void repairCodeWord() {

        if (!manipuleCheckFlag) {
            System.out.println("kodu manüpile etmeden bu fonksiyonu çalıştıramazsınız.");
        } else {
            parityBitRepairForXORgui = new LinkedList[parityBitAdeti];
            parityBitRepairForXORguiIndex = new LinkedList[parityBitAdeti];
            parityBitsRepairArrStrBuilder = new StringBuilder();
            codeWordRepaired = new StringBuilder(codeWordManupilating.toString());

            for (int i = 0; i < parityBitAdeti; i++) {
                int tempAtla = (int) Math.pow(2, i);
                int tempXOR = -1;

                for (int j = (int) (Math.pow(2, i) - 1); j < codeWordManupilating.length(); j++) {
                    if (tempAtla == 0) {
                        tempAtla = (int) Math.pow(2, i);
                        j += (int) (Math.pow(2, i) - 1);
                        continue;
                    }
                    tempAtla--;

                    if (tempXOR == -1) {
                        parityBitRepairForXORgui[i] = new LinkedList<>();
                        parityBitRepairForXORguiIndex[i] = new LinkedList<>();
                        parityBitRepairForXORgui[i].add(String.valueOf(codeWordRepaired.charAt(j)));
                        parityBitRepairForXORguiIndex[i].add(j);
                        tempXOR = (int) (codeWordRepaired.charAt(j) - 48);
                    } else {
                        parityBitRepairForXORgui[i].add(String.valueOf(codeWordRepaired.charAt(j)));
                        parityBitRepairForXORguiIndex[i].add(j);
                        tempXOR = tempXOR ^ (int) (codeWordRepaired.charAt(j) - 48);
                    }
                }
                parityBitsRepairArrStrBuilder.insert(0, tempXOR);
            }
            // hatalı index onarımı
            corruptedIndex = Integer.parseInt(parityBitsRepairArrStrBuilder.toString(), 2) - 1;
            //System.out.println("Hatalı İndex: " + corruptedIndex + " | Düzelemesi gereken Değer: " + codeWordRepaired.charAt(corruptedIndex));

            if (codeWordRepaired.charAt(corruptedIndex) == '0') {
                codeWordRepaired.setCharAt(corruptedIndex, '1');
            } else {
                codeWordRepaired.setCharAt(corruptedIndex, '0');
            }
        }
    }
}
