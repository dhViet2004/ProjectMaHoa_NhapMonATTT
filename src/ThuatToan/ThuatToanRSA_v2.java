package ThuatToan;

import java.math.BigInteger;
import java.util.Random;

public class ThuatToanRSA_v2 {
	public static final int VERSION = 1024;
	public static final BigInteger E = new BigInteger("65537");	
	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger phiN;
	// ->> Version 2.0
	private BigInteger dP;
	private BigInteger dQ;
	private BigInteger qInv;
	// <<-
	public ThuatToanRSA_v2() {
		// TODO Auto-generated constructor stub
		BigInteger checkTruoc = BigInteger.TWO.pow(VERSION - 1);
		BigInteger checkSau = BigInteger.TWO.pow(VERSION);     
		do {
			p = BigInteger.probablePrime(VERSION/2, new Random());
			q = BigInteger.probablePrime(VERSION/2, new Random());
			n = p.multiply(q);
		}while( n.compareTo(checkTruoc) <= 0 || n.compareTo(checkSau) >= 0);
		
		phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		// ->> Version 2.0
//		qInv = E.modInverse(phiN);
		// <<- 
		// ->> Version 2.1
		dP = E.modInverse(p.subtract(BigInteger.ONE)); 
	 	dQ = E.modInverse(q.subtract(BigInteger.ONE)); 

		if(p.compareTo(q) > 0) {
			qInv = q.modInverse(p);
		}else {
			qInv = p.modInverse(q);
		}
		// <<-
	}
	
	public BigInteger getP() {
		return p;
	}
	public void setP(BigInteger p) {
		this.p = p;
	}
	public BigInteger getQ() {
		return q;
	}
	public void setQ(BigInteger q) {
		this.q = q;
	}
	public BigInteger getPhiN() {
		return phiN;
	}
	public void setPhiN(BigInteger phiN) {
		this.phiN = phiN;
	}
	public BigInteger getdP() {
		return dP;
	}
	public void setdP(BigInteger dP) {
		this.dP = dP;
	}
	public BigInteger getdQ() {
		return dQ;
	}
	public void setdQ(BigInteger dQ) {
		this.dQ = dQ;
	}
	public BigInteger getqInv() {
		return qInv;
	}
	public void setqInv(BigInteger qInv) {
		this.qInv = qInv;
	}
	public static int getVersion() {
		return VERSION;
	}
	public static BigInteger getE() {
		return E;
	}
	public void setN(BigInteger n) {
		this.n = n;
	}
	public BigInteger maHoa(BigInteger thongdien, BigInteger pN) {
		return thongdien.modPow(E, pN);
	}
	public BigInteger giaiMa(BigInteger maDAHOA) {
		// ->> Version 2.0
//		 return maDAHOA.modPow(qInv, n);
		// <<- 
		// ->> Version 2.1
		BigInteger m1 = maDAHOA.modPow(dP, p);
		BigInteger m2 = maDAHOA.modPow(dQ, q);
		BigInteger h = (qInv.multiply(m1.subtract(m2))).mod(p);
		return m2.add(q.multiply(h));
		// <<-
	}
	public BigInteger getN() {
		return n;
	}
}
