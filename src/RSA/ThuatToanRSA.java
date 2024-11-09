package RSA;

import java.math.BigInteger;
import java.util.Random;

public class ThuatToanRSA {
	public static final int VERSION = 1024;
	public static final BigInteger E = new BigInteger("65537");	
	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger phiN;
	//Verson 2.0
	private BigInteger dP;
	private BigInteger dQ;
	private BigInteger qInv;
	
	
	public void khoitao() {
		BigInteger checkTruoc = BigInteger.TWO.pow(VERSION - 1);
		BigInteger checkSau = BigInteger.TWO.pow(VERSION);     
		do {
			p = BigInteger.probablePrime(VERSION/2, new Random());
			q = BigInteger.probablePrime(VERSION/2, new Random());
			n = p.multiply(q);
		}while( n.compareTo(checkTruoc) <= 0 || n.compareTo(checkSau) >= 0);
		phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		
		//d = E.modInverse(phiN); Verson 1.0
		
		dP = E.modInverse(p.subtract(BigInteger.ONE)); 
	 	dQ = E.modInverse(q.subtract(BigInteger.ONE)); 

		if(p.compareTo(q) > 0) {
			qInv = q.modInverse(p);
		}else {
			qInv = p.modInverse(q);
		}
	}
	public BigInteger maHoa(BigInteger thongdien, BigInteger pN) {
		return thongdien.modPow(E, pN);
	}
	public BigInteger giaiMa(BigInteger maDAHOA) {
//		return maDAHOA.modPow(d, n); Verson 1.0
		BigInteger m1 = maDAHOA.modPow(dP, p);
		BigInteger m2 = maDAHOA.modPow(dQ, q);
		BigInteger h = (qInv.multiply(m1.subtract(m2))).mod(p);
		return m2.add(q.multiply(h));
	}
	public BigInteger getN() {
		return n;
	}
}
