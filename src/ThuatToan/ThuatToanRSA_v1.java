package ThuatToan;

import java.math.BigInteger;
import java.util.Random;

public class ThuatToanRSA_v1 {
    private static int i = 50;  // Số bit của N

    // Thuật toán lũy thừa modulo: x^b mod n
    public static BigInteger luuyThuaModulo(BigInteger x, BigInteger b, BigInteger n) {
        BigInteger y = BigInteger.ONE;
        while (b.compareTo(BigInteger.ZERO) > 0) {
            if (b.mod(BigInteger.TWO).compareTo(BigInteger.ONE) == 0) {
                y = (y.multiply(x)).mod(n);
            }
            b = b.shiftRight(1);
            x = (x.multiply(x)).mod(n);
        }
        return y;
    }

    // Phương thức để chuyển đổi chuỗi ký tự thành số nguyên
    public static BigInteger chuoiSangSo(String chuoi) {
        StringBuilder so = new StringBuilder();
        for (char kyTu : chuoi.toCharArray()) {
            so.append((int) kyTu);
        }
        return new BigInteger(so.toString());
    }

    // Phương thức để chuyển đổi số nguyên thành chuỗi ký tự
    public static String soSangChuoi(BigInteger so) {
        String chuoiSo = so.toString();
        StringBuilder chuoi = new StringBuilder();
        for (int i = 0; i < chuoiSo.length(); i += 2) {
            int kyTuAscii = Integer.parseInt(chuoiSo.substring(i, i + 2));
            chuoi.append((char) kyTuAscii);
        }
        return chuoi.toString();
    }

    // Kiểm tra số nguyên tố
    public static boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.TWO) < 0) {
            return false;
        }
        BigInteger sqrt = number.sqrt();
        for (BigInteger i = BigInteger.TWO; i.compareTo(sqrt) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }

    // Tìm số nguyên tố q trong khoảng [2^(i-1)/3, 2^i/3]
    public static BigInteger timQ() {
        BigInteger lowerBound = BigInteger.TWO.pow(i - 1).divide(BigInteger.valueOf(3));
        BigInteger upperBound = BigInteger.TWO.pow(i).divide(BigInteger.valueOf(3));
        
        Random random = new Random();
        BigInteger q;
        
        do {
            q = new BigInteger(upperBound.bitLength(), random);
        } while (q.compareTo(lowerBound) <= 0 || q.compareTo(upperBound) >= 0 || !isPrime(q));
        
        return q;
    }

    // Tính phi(N)
    public static BigInteger tinhPhiN(BigInteger p, BigInteger q) {
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    // Tìm GCD của hai số nguyên
    public static BigInteger timGCD(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // Tìm e sao cho 1 < e < phi(N) và gcd(e, phi(N)) = 1
    public static BigInteger timE(BigInteger phiN) {
        BigInteger e = BigInteger.TWO;
        while (e.compareTo(phiN) < 0) {
            if (timGCD(e, phiN).equals(BigInteger.ONE)) {
                return e;
            }
            e = e.add(BigInteger.ONE);
        }
        return null;
    }

    // Tìm d là nghịch đảo của e mod phi(N) bằng thuật toán Euclide mở rộng
    public static BigInteger timD(BigInteger e, BigInteger phiN) {
        BigInteger d = BigInteger.ZERO;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger x2 = BigInteger.ONE;
        BigInteger y1 = BigInteger.ONE;
        BigInteger tempPhiN = phiN;
        
        while (e.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] quotientAndRemainder = phiN.divideAndRemainder(e);
            BigInteger quotient = quotientAndRemainder[0];
            BigInteger remainder = quotientAndRemainder[1];

            BigInteger x = x2.subtract(quotient.multiply(x1));
            BigInteger y = d.subtract(quotient.multiply(y1));
            
            phiN = e;
            e = remainder;
            x2 = x1;
            x1 = x;
            d = y1;
            y1 = y;
        }

        if (phiN.compareTo(BigInteger.ONE) != 0) {
            System.out.println("Không có nghịch đảo của e theo mod phiN");
            return null;
        }

        if (d.compareTo(BigInteger.ZERO) < 0) {
            d = d.add(tempPhiN);
        }
        
        return d;
    }

    // Mã hóa bảo mật (C = M^e mod N)
    public static BigInteger maHoaBaoMat(BigInteger M, BigInteger e, BigInteger N) {
        return luuyThuaModulo(M, e, N);
    }

    // Giải mã bảo mật (M = C^d mod N)
    public static BigInteger giaiMaBaoMat(BigInteger C, BigInteger d, BigInteger N) {
        return luuyThuaModulo(C, d, N);
    }

    public static void main(String[] args) {
        BigInteger p = timQ();
        BigInteger q = timQ();
        BigInteger N = p.multiply(q);

        System.out.println("Số nguyên tố p: " + p);
        System.out.println("Số nguyên tố q: " + q);
        System.out.println("N = p * q = " + N);

        BigInteger phiN = tinhPhiN(p, q);
        BigInteger e = timE(phiN);
        BigInteger d = timD(e, phiN);

        System.out.println("Khóa công khai Ku = (e, N): (" + e + ", " + N + ")");
        System.out.println("Khóa riêng Kr = (d, N): (" + d + ", " + N + ")");

        // Chuyển chuỗi ký tự thành số nguyên và mã hóa
        String chuoi = "IUH";
        System.out.println("Thông điệp ban đầu: "+chuoi);
        BigInteger M = chuoiSangSo(chuoi);
        System.out.println("Thông điệp gốc (dưới dạng số nguyên) M: " + M);

        // Mã hóa bảo mật
        BigInteger C1 = maHoaBaoMat(M, e, N);
        System.out.println("Bản mã C1 (mã hóa bảo mật): " + C1);

        // Giải mã bảo mật
        BigInteger decryptedM1 = giaiMaBaoMat(C1, d, N);
        System.out.println("Thông điệp giải mã từ C1 (số nguyên): " + decryptedM1);
        
        // Chuyển đổi lại thành chuỗi
        String decryptedChuoi = soSangChuoi(decryptedM1);
        System.out.println("Thông điệp giải mã từ C1 (chuỗi): " + decryptedChuoi);
    }
}
