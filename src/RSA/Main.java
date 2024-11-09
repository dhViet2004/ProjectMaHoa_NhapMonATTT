package RSA;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo đối tượng ThuatToanRSA và thực hiện quá trình khởi tạo
        ThuatToanRSA rsa = new ThuatToanRSA();
        rsa.khoitao();

        // Lấy giá trị n sau khi khởi tạo để sử dụng cho quá trình mã hóa và giải mã
        BigInteger n = rsa.getN();

        // Nhập thông điệp cần mã hóa từ người dùng
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập thông điệp (dạng số nguyên): ");
        BigInteger thongDien = new BigInteger(scanner.nextLine());

        // Mã hóa thông điệp
        BigInteger maHoaThongDiep = rsa.maHoa(thongDien, n);
        System.out.println("Thông điệp sau khi mã hóa: " + maHoaThongDiep);

        // Giải mã thông điệp
        BigInteger giaiMaThongDiep = rsa.giaiMa(maHoaThongDiep);
        System.out.println("Thông điệp sau khi giải mã: " + giaiMaThongDiep);

        // Đóng scanner
        scanner.close();
    }
}
