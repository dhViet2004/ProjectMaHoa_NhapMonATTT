package ThuatToan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
public class AES {
	public static void showWord( int w) { // in ra các giá trị 32 bit ( 4 byte ) ở dạng hexan
		for(int i =1; i <=8; i++) {
			int hexan = (w>>(32-i*4)) &0xF;
			System.out.print(Integer.toHexString(hexan));
		}
	}
	
	public static int rotWord(int w) { // hàm con trong hàm g(w)
		int byte1 = (w >> 24) & 0xFF;
		int byte234 = w & 0xFFFFFF;
		int rot = (byte234 << 8) | byte1;
		return rot;
	}
	
	public static int subWord(int w) { // hàm subwword là hàm con trong hàm g(w)
		  int S[] = {0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76, 
					0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0, 
					0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 
					0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75, 
					0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 
					0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF, 
					0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 
					0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2, 
					0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73, 
					0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB, 
					0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79, 
					0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 
					0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A, 
					0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 
					0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF, 
					0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
				};
		 int kq = 0;
		 for (int i =1; i <= 4; i++) {
			 int bytei = (w>>(32 - i*8)) & 0xFF;
			 int sub8 = S[bytei];
			 kq = (kq << 8) | sub8;
		 }
//		 System.out.print("Subword = ");
//		 showWord(kq);
		 return kq;	
	}
	
	public static int XorRcon(int w, int j) { // hàm XorRcon là hàm con trong hàm g(w)
		 int Rc[] = {
	                0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
	                0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39
	            };
		 int byte1 = (w >> 24) & 0xFF;
		 int kqXor = (byte1 ^ Rc[j]) & 0xFF;
		 int byte234 = w & 0xFFFFFF;
		 int kq = (kqXor << 24 ) | byte234;
		 return kq;
	}
	
	public static int G (int w, int j) { // hàm g(w) bao gồm 3 hàng con: rotword, subwword, XorRcon
		int rotW = rotWord(w);
		int subW = subWord(rotW);
		int kq = XorRcon(subW, j);
//		System.out.printf("G(%s) = ", w); // của ng ta ra chữ + số
//		showWord(kq);
		return kq;
	}
	
	public static int[] keyExpansion(int[] key) { // Hàm sinh các khóa mở rộng
		int[] w = new int[44];
		w[0] = key[0];
		w[1] = key[1];
		w[2] = key[2];
		w[3] = key[3];
		for (int i =4; i < 44; i++) {
			if(i % 4 == 0) w[i] = G(w[i-1], i/4) ^ w[i-4];
			else w[i] = w[i-1] ^ w[i-4];
//			System.out.printf("\nw[%d] = ", i); showWord(w[i]);
			
		}
		return w;
	}
	
	public static int[] addRoundKey(int[] state, int[] k) {
		int[] kq = new int[4];
		kq[0] = state[0] ^ k[0];
		kq[1] = state[1] ^ k[1];
		kq[2] = state[2] ^ k[2];
		kq[3] = state[3] ^ k[3];
//		System.out.println("\n AddRoundKey: ");
//		for(int i =0; i < 4; i++) {
//			System.out.print("\n\t"); showWord(kq[i]);
//		}
		return kq;
	}
	
	public static int[] subBytes(int[] state) {
		int[] kq = new int[4];
		for (int i =0; i < 4; i++) {
			kq[i] = subWord(state[i]);
		}
//		System.out.println("\nSubByte");
//		for(int i =0; i< 4; i++) {
//			System.out.print("\n\t"); showWord(kq[i]);
//		}
		return kq;
	}
	
	public static int[] shiftRows(int[] state) {
		int[] kq = new int[4];
		for(int i =0; i < 4; i++) {
			int byte1 = state[i] & 0xFF000000;
			int byte2 = state[(i + 1) % 4] & 0xFF0000;
			int byte3 = state[(i + 2) % 4] & 0xFF00;
			int byte4 = state[(i + 3) % 4] & 0xFF;
			kq[i] = byte1 | byte2 | byte3 | byte4;
		}
		
//		System.out.println("\nShiftRows");;
//		for(int i =0; i < 4; i++) {
//			System.out.print("\n\t"); showWord(kq[i]);
//		}
		return kq;
	}
	
	public static int nhan2 ( int w) {
		int kq = w << 1;
		if ( kq > 256) kq = kq ^ 0x11b;
		kq = kq & 0xFF;
		return kq;
	}
	
	public static int nhan3 ( int w) {
		int kq = w ^ nhan2(w);
		kq = kq & 0xFF;
		return kq;
	}
	
	public static int nhanCot(int w) {
		int kq;
		int byte1 = (w >> 24) & 0xFF;
		int byte2 = (w >> 16) & 0xFF;
		int byte3 = (w >> 8) & 0xFF;
		int byte4 = w & 0xFF;
		int kq1 = nhan2(byte1) ^ nhan3(byte2) ^ byte3 ^ byte4;
		int kq2 = byte1 ^ nhan2(byte2) ^ nhan3(byte3) ^ byte4;
		int kq3 = byte1 ^ byte2 ^ nhan2(byte3) ^ nhan3(byte4);
		int kq4 = nhan3(byte1) ^ byte2 ^ byte3 ^ nhan2(byte4);
		kq = (kq1 << 24) | (kq2 << 16) | (kq3 << 8) | kq4;
//		System.out.print("\n\t"); showWord(kq);
		return kq;
		
	}
	
	public static int[] mixColums (int[] state) {
		int[] kq = new int[4];
//		System.out.print("MixColumns: ");
		for(int i =0; i < 4; i++) {
			kq[i]  = nhanCot(state[i]);
		}
		return kq;
	}

	public static void showMatrix (int[] w) {
		for (int i =0; i < 4; i++) {
			System.out.print("\n\t");
			showWord(w[i]);
		}
	}
	
	public static int[] maHoaAES(int[] state, int[] key ) {
		int[] w = keyExpansion(key);
		state = addRoundKey(state, new int[]{w[0], w[1], w[2], w[3]}); // add round key cho vòng đầu tiên
		for(int j = 1; j <=9; j++) {
			state = subBytes(state);
			state = shiftRows(state);
			state = mixColums(state);
			
			 int[] roundKey = {w[4 * j], w[4 * j + 1], w[4 * j + 2], w[4 * j + 3]};// add round key
			state = addRoundKey(state, roundKey);
			
//			System.out.printf("\nVong %d:\n", j); // Hiển thị trạng thái sau mỗi vòng 
//			showMatrix(state);
		}
			
//			System.out.print("\nVong 10: \n"); // Vòng thứ 10
			state = subBytes(state);
			state = shiftRows(state);
			int[] finalRoundKey = {w[40], w[41], w[42], w[43]};
			state = addRoundKey(state, finalRoundKey);
			
//			showMatrix(state);
			
			
		
		int[] kq = new int[4]; // kết quả sau khi mã hóa
		kq = state;
		return kq;
	}
	
	// Giải mã
	public static int[] invShiftRows(int[] state) {
		int[] kq = new int[4];
		for(int i =0; i < 4; i++) {
			int byte1 = state[i] & 0xFF000000;
			int byte2 = state[(i + 3) % 4] & 0xFF0000;
			int byte3 = state[(i + 2) % 4] & 0xFF00;
			int byte4 = state[(i + 1) % 4] & 0xFF;
			kq[i] = byte1 | byte2 | byte3 | byte4;
		}
//		System.out.println("\nInvShiftRows: ");
//		for(int i =0; i < 4; i++) {
//			System.out.print("\n\t"); 
//			showWord(kq[i]);
//		}
		return kq;
	}
	
	public static int InvSubWord(int w) {
		int InvS[] = {
		           0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb, 
				   0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb, 
				   0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e, 
				   0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25, 
				   0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92, 
				   0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84, 
				   0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06, 
				   0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b, 
				   0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73, 
				   0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e, 
				   0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b, 
				   0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4, 
				   0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f, 
				   0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef, 
				   0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61, 
				   0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
				};
		
		int kq = 0;
		for(int i =1; i <= 4; i++) {
			int bytei = (w >> (32 - i*8)) & 0xFF;
			int subB = InvS[bytei];
			kq = (kq << 8) | subB;
		}
		return kq;
	}
	
	public static int[] InvSubBytes(int[] state) {
		int[] kq = new int[4];
		for(int i =0; i < 4; i++) {
			kq[i] = InvSubWord(state[i]);
		}
		return kq;
	}
	
	public static int nhan9( int w) {
		int kq = (w << 3) ^ w;
		if(kq > (256<<2)) kq = kq ^ (0x11b << 2);
		if(kq > (256<<1)) kq = kq ^ (0x11b << 1);
		if(kq > 256) kq = kq ^ 0x11b;
		kq = kq & 0xFF;
		return kq;
	}
	
	public static int nhanB(int w) {
		int kq = (w << 3) ^ (w << 1) ^ w;
		if(kq > (256<<2)) kq = kq ^ (0x11b << 2);
		if(kq > (256<<1)) kq = kq ^ (0x11b << 1);
		if(kq > 256) kq = kq ^ 0x11b;
		kq = kq & 0xFF;
		return kq;
	}
	
	public static int nhanD(int w) {
		int kq = (w << 3) ^ (w << 2) ^ w;
		if(kq >= (256<<2)) kq = kq ^ (0x11b << 2);
		if(kq >= (256<<1)) kq = kq ^ (0x11b << 1);
		if(kq >= 256) kq = kq ^ 0x11b;
		kq = kq & 0xFF;
		return kq;
	}
	
	public static int nhanE(int w) {
		int kq = (w << 3) ^ (w << 2) ^ (w << 1);
		if(kq >= (256<<2)) kq = kq ^ (0x11b << 2);
		if(kq >= (256<<1)) kq = kq ^ (0x11b << 1);
		if(kq >= 256) kq = kq ^ 0x11b;
		kq = kq & 0xFF;
		return kq;
	}
	
	public static int InvNhanCot(int w) {
		int kq;
		int byte1 = (w >> 24) & 0xFF;
		int byte2 = (w >> 16) & 0xFF;
		int byte3 = (w >> 8) & 0xFF;
		int byte4 = w & 0xFF;
		
		int kq1 = nhanE(byte1) ^ nhanB(byte2) ^ nhanD(byte3) ^ nhan9(byte4);
		int kq2 = nhan9(byte1) ^ nhanE(byte2) ^ nhanB(byte3) ^ nhanD(byte4);
		int kq3 = nhanD(byte1) ^ nhan9(byte2) ^ nhanE(byte3) ^ nhanB(byte4);
		int kq4 = nhanB(byte1) ^ nhanD(byte2) ^ nhan9(byte3) ^ nhanE(byte4);
		kq = (kq1 << 24) | (kq2 << 16) | (kq3 << 8) | kq4;
		return kq;
	}
	
	public static int[] invMixColums(int[] state ) {
		int[] kq = new int[4];
		for(int i = 0; i < 4; i++) {
			kq[i] = InvNhanCot(state[i]);
		}
		return kq;
	}
	
	
	public static int[] giaiMaAES(int[] c, int[] key) {
		int[] w = keyExpansion(key);
//		int[] addRKey = addRoundKey(c, new int[] {w[40], w[41], w[42], w[43]}); // tạo mảng 4 phần tủ từ w[40] - w[30] cho vòng đầu tiên
//		System.out.printf("\nAddRoundkey: \n"); 
//		showMatrix(addRKey);
		
		// thực hiện invShiftRows sau khi addRoundKey
//		int[] invShRows = invShiftRows(addRKey); 
//		System.out.printf("\nInvShiftRows:\n" ); 
//		showMatrix(invShRows);
		
//		int[] invSubB = InvSubBytes(invShRows);
//		System.out.printf("\nInvSubBytes:\n" ); 
//		showMatrix(invSubB);
		
		int[] state = addRoundKey(c, new int[] {w[40], w[41], w[42], w[43]}); 
		for(int j =1; j <=9; j++) {
			state = invShiftRows(state);
			state = InvSubBytes(state);
			state = addRoundKey(state, new int[] {w[40 - 4 * j], w[41 - 4 * j], w[42 - 4 * j], w[43 - 4 * j]});
			state = invMixColums(state);
		}
		// VÒng thứ 10
		state = invShiftRows(state);
		state = InvSubBytes(state);
		 state = addRoundKey(state, new int[] {w[0], w[1], w[2], w[3]});
		return state;
		
	}
	
	
	public static void main(String[] args) {
		//chuỗi với khóa
	    String inputText = "tui ten la Viet";
	    System.out.println("inputText:"+ inputText);
	    int w0 = 0x2b7e1516, w1 = 0x28aed2a6, w2 = 0xabf71588, w3 = 0x09cf4f3c;
	    int[] Key = {w0, w1, w2, w3};
	    System.out.println("khóa Key:");
	    for (int j = 0; j < 4; j++) {
	        System.out.printf("%08x ", Key[j]);
	    }

	    // Văn bản + padding cho đủ khối bội 16
	    int blockSize = 16;
	    byte[] inputBytes = inputText.getBytes(StandardCharsets.UTF_8);
	    //System.out.println("Mảng byte vào:"+inputBytes);
	    System.out.println("\ninputBytes.length:"+inputBytes.length);
	    int paddingLength = blockSize - (inputBytes.length % blockSize);
	    if (paddingLength == blockSize) {
	        paddingLength = 0; // Nếu không cần padding
	    }
        byte[] paddedInputBytes = Arrays.copyOf(inputBytes, inputBytes.length + paddingLength);
        System.out.println("paddedInputBytes:"+paddedInputBytes.length);
        for (int i = inputBytes.length; i < paddedInputBytes.length; i++) {
            paddedInputBytes[i] = (byte) paddingLength;
        }
        
	    // Chia văn bản thành các khối 16 byte
	    int numberOfBlocks = (int) Math.ceil((double) inputText.length() / blockSize);
	    int[][] blocks = new int[numberOfBlocks][4];
        for (int i = 0; i < numberOfBlocks; i++) {
            for (int j = 0; j < 4; j++) {
                int byteIndex = i * blockSize + j * 4;
                blocks[i][j] = ((paddedInputBytes[byteIndex] & 0xFF) << 24) |
                               ((paddedInputBytes[byteIndex + 1] & 0xFF) << 16) |
                               ((paddedInputBytes[byteIndex + 2] & 0xFF) << 8) |
                               (paddedInputBytes[byteIndex + 3] & 0xFF);     
             // In giá trị dưới dạng hex
                String hexValue = String.format("%08x", blocks[i][j]);
                System.out.println("blocks[" + i + "][" + j + "] = " + hexValue);
            }
        }
        
        byte[] decryptedBytes = new byte[paddedInputBytes.length]; // Lưu trữ kết quả giải mã
        int decryptedIndex = 0;
	    // Mã hóa và giải mã từng khối
	    for (int i = 0; i < numberOfBlocks; i++) {

	        System.out.println();
	        // Chuyển đổi từng giá trị int thành byte và in ra ký tự
	        System.out.print("Nội dung ký tự trong khối: ");
	        for (int j = 0; j < 4; j++) {
	            for (int byteIndex = 3; byteIndex >= 0; byteIndex--) { // Lấy từng byte từ int
	                byte b = (byte) ((blocks[i][j] >> (byteIndex * 8)) & 0xFF);
	                System.out.print((char) b);
	            }
	        }
	        System.out.println();
	        System.out.println();
	        int[] encryptedBlock = maHoaAES(blocks[i], Key); // Mã hóa
	        System.out.printf("\nKhối %d (mã hóa): ", i);
	        showMatrix(encryptedBlock); 
	        
	        System.out.printf("\nKhối %d: ", i);
	        for (int j = 0; j < 4; j++) {
	            System.out.printf("%08x ", blocks[i][j]);
	        }
	        
	        int[] decryptedBlock = giaiMaAES(encryptedBlock, Key); // Sử dụng encryptedBlock để giải mã
	        System.out.printf("\nKhối %d (giải mã):", i);
	        showMatrix(decryptedBlock);
	        System.out.println();
	        
		    System.out.println("\nkhóa Key:");
		    for (int j = 0; j < 4; j++) {
		        System.out.printf("%08x ", Key[j]);
		    }
	        
	        // Chuyển đổi các giá trị trong `decryptedBlock` thành byte
            for (int j = 0; j < 4; j++) {
                decryptedBytes[decryptedIndex++] = (byte) (decryptedBlock[j] >>> 24);
                decryptedBytes[decryptedIndex++] = (byte) (decryptedBlock[j] >>> 16);
                decryptedBytes[decryptedIndex++] = (byte) (decryptedBlock[j] >>> 8);
                decryptedBytes[decryptedIndex++] = (byte) decryptedBlock[j];
            }
	    }
	    
        // Loại bỏ padding sau khi giải mã
//        byte[] resultWithoutPadding = removePadding(decryptedBytes);
//        String outputText = new String(resultWithoutPadding, StandardCharsets.UTF_8);
//        System.out.println("\nVăn bản sau khi giải mã và loại bỏ padding: " + outputText);
	    
	}

	public static byte[] removePadding(byte[] paddedData) {
	    int paddingLength = paddedData[paddedData.length - 1] & 0xFF;
	    if (paddingLength < 1 || paddingLength > paddedData.length) {
	        throw new IllegalArgumentException("Invalid padding length");
	    }
	    for (int i = paddedData.length - paddingLength; i < paddedData.length; i++) {
	        if (paddedData[i] != (byte) paddingLength) {
	            throw new IllegalArgumentException("Invalid padding byte");
	        }
	    }
	    return Arrays.copyOf(paddedData, paddedData.length - paddingLength);
	}



	// Hàm chuyển đổi từ int[] sang byte[]
	private static byte[] intsToBytes(int[] ints) {
	    byte[] bytes = new byte[ints.length * 4];
	    for (int i = 0; i < ints.length; i++) {
	        bytes[i * 4] = (byte) (ints[i] >> 24);
	        bytes[i * 4 + 1] = (byte) (ints[i] >> 16);
	        bytes[i * 4 + 2] = (byte) (ints[i] >> 8);
	        bytes[i * 4 + 3] = (byte) (ints[i]);
	    }
	    return bytes;
	}

    
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

// Phương thức đọc file
    private static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytes);
        fis.close();
        return bytes;
    }

    // Phương thức ghi file
    private static void writeFile(String filePath, int[][] blocks) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        for (int[] block : blocks) {
            for (int value : block) {
                // Chuyển đổi int thành byte[]
                fos.write((value >> 24) & 0xFF);
                fos.write((value >> 16) & 0xFF);
                fos.write((value >> 8) & 0xFF);
                fos.write(value & 0xFF);
            }
        }
        fos.close();
    }
    
    
}
