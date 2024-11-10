package Main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import ThuatToan.ThuatToanRSA_v2;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThuatToanRSA_v2 nguoi1 = new ThuatToanRSA_v2();
		ThuatToanRSA_v2 nguoi2 = new ThuatToanRSA_v2();	
		String thongdiepChuoi = "IUH"; 
		BigInteger thongdiep = new BigInteger(thongdiepChuoi.getBytes(StandardCharsets.UTF_8));
		
		BigInteger maHoa = nguoi1.maHoa(thongdiep, nguoi2.getN());
		
		BigInteger dagiai = nguoi2.giaiMa(maHoa);
		String dagiaiChuoi = new String(dagiai.toByteArray(), StandardCharsets.UTF_8);
		System.out.println(thongdiepChuoi);
		System.out.println(maHoa);
		System.out.println(dagiaiChuoi);
		
	}
}
