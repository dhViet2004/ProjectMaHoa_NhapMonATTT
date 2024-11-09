package Main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import ThuatToan.ThuatToanRSA;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThuatToanRSA nguoi1 = new ThuatToanRSA();
		ThuatToanRSA nguoi2 = new ThuatToanRSA();
		
		nguoi1.khoitao();
		nguoi2.khoitao();
		
		String thongdiepChuoi = "1"; 
		
		BigInteger thongdiep = new BigInteger(thongdiepChuoi.getBytes(StandardCharsets.UTF_8));
		
		BigInteger maHoa = nguoi1.maHoa(thongdiep, nguoi2.getN());
		
		BigInteger dagiai = nguoi2.giaiMa(maHoa);
		String dagiaiChuoi = new String(dagiai.toByteArray(), StandardCharsets.UTF_8);
		System.out.println(thongdiepChuoi);
		System.out.println(maHoa);
		System.out.println(dagiaiChuoi);
		
	}
}
