package Main;

import ThuatToan.ThuatToanRSA_v1;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private static BigInteger p, q, N, e, d;
    private static BigInteger thongDiep; // Biến BigInteger lưu thông điệp

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int luaChon;

        do {
            System.out.println("===== Menu RSA =====");
            System.out.println("1. Tạo p, q, N, e, d và Mã hóa thông điệp");
            System.out.println("2. Nhập thông điệp mã hóa và Giải mã");
            System.out.println("0. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            luaChon = Integer.parseInt(scanner.nextLine()); // Đọc lựa chọn

            switch (luaChon) {
                case 1:
                    taoKhoaVaMaHoaThongDiep(scanner);
                    break;
                case 2:
                    nhapThongDiepMaHoaVaGiaiMa(scanner);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
            System.out.println();
        } while (luaChon != 0);

        scanner.close();
    }

    // Bước 1: Tạo khóa và mã hóa thông điệp
    private static void taoKhoaVaMaHoaThongDiep(Scanner scanner) {
        // Tạo khóa
        p = ThuatToanRSA_v1.timQ();
        q = ThuatToanRSA_v1.timQ();
        N = p.multiply(q);
        BigInteger phiN = ThuatToanRSA_v1.tinhPhiN(p, q);
        e = ThuatToanRSA_v1.timE(phiN);
        d = ThuatToanRSA_v1.timD(e, phiN);

        System.out.println("Các khóa đã được tạo:");
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("N: " + N);
        System.out.println("e (khóa công khai): " + e);
        System.out.println("d (khóa riêng): " + d);

        // Nhập thông điệp và mã hóa
        System.out.print("Nhập thông điệp (dưới dạng chuỗi): ");
        String thongDiepChuoi = scanner.nextLine(); // Đọc thông điệp dưới dạng chuỗi
        System.out.println("Thông điệp đã nhập: " + thongDiepChuoi);

        // Chuyển chuỗi thành BigInteger bằng cách sử dụng getBytes
        byte[] thongDiepBytes = thongDiepChuoi.getBytes(StandardCharsets.UTF_8); // Lấy mảng byte từ chuỗi
        thongDiep = new BigInteger(thongDiepBytes); // Chuyển mảng byte thành BigInteger

        System.out.println("Thông điệp dưới dạng BigInteger: " + thongDiep);

        // Mã hóa thông điệp
        BigInteger thongDiepMaHoa = ThuatToanRSA_v1.maHoaBaoMat(thongDiep, e, N); // Mã hóa thông điệp

        // In ra thông điệp đã mã hóa
        System.out.println("Thông điệp đã mã hóa: " + thongDiepMaHoa);
    }

    // Bước 2: Nhập thông điệp mã hóa và giải mã
    private static void nhapThongDiepMaHoaVaGiaiMa(Scanner scanner) {
        if (d == null || N == null) {
            System.out.println("Vui lòng tạo khóa trước.");
            return;
        }

        // Nhập thông điệp đã mã hóa
        System.out.print("Nhập thông điệp mã hóa (dưới dạng BigInteger): ");
        String thongDiepMaHoaChuoi = scanner.nextLine();
        BigInteger thongDiepMaHoa = new BigInteger(thongDiepMaHoaChuoi); // Chuyển chuỗi nhập thành BigInteger

        // Giải mã thông điệp
        BigInteger thongDiepGiaiMa = ThuatToanRSA_v1.giaiMaBaoMat(thongDiepMaHoa, d, N); // Giải mã thông điệp

        // Chuyển BigInteger về dạng chuỗi
        String thongDiepChuoiGiaiMa = new String(thongDiepGiaiMa.toByteArray(), StandardCharsets.UTF_8);

        // In ra thông điệp đã giải mã
        System.out.println("Thông điệp đã giải mã: " + thongDiepChuoiGiaiMa);
    }
}
