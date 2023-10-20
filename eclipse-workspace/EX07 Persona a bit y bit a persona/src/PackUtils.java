public class PackUtils {
	
	public static void packBoolean(boolean b, byte[] buffer, int offset) {
		if (b) {
			buffer[offset] = (byte) 1;
		} else {
			buffer[offset] = (byte) 0;
		}
	}
	
	public static boolean unpackBoolean(byte[] buffer, int offset) {
		return buffer[offset] == (byte) 1;
	}
	
	public static void packByte(byte b, byte[] buffer, int offset) {
		buffer[offset] = b;
	}
	
	public static byte unpackByte(byte[] buffer, int offset) {
		return buffer[offset];
	}
	
	public static void packChar(char c, byte[] buffer, int offset) {
		buffer[offset] = (byte) ((c >> 8) & 0xFF);
		buffer[offset + 1] = (byte) (c & 0xFF);
	}
	
	public static char unpackChar(byte[] buffer, int offset) {
		return (char) ((buffer[offset] << 8) | (buffer[offset + 1] & 0xFF));
	}
	
	public static void packLimitedString(String str, int maxLength, byte[] buffer, int offset) {
		for (int i = 0; i < maxLength; i++) {
			if (i < str.length()) {
				packChar(str.charAt(i), buffer, offset + 2 * i);
			} else {
				packChar('\0', buffer, offset + 2 * i);
				break;
			}
		}
	}
	
	public static String unpackLimitedString(int maxLength, byte[] buffer, int offset) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char c = unpackChar(buffer, offset + 2 * i);
			if (c == '\0') {
				break;
			}
			result.append(c);
		}
		return result.toString();
	}
	
	public static void packInt(int n, byte[] buffer, int offset) {
		buffer[offset] = (byte) (n >> 24);
		buffer[offset + 1] = (byte) (n >> 16);
		buffer[offset + 2] = (byte) (n >> 8);
		buffer[offset + 3] = (byte) n;
	}
	
	public static int unpackInt(byte[] buffer, int offset) {
		return ((buffer[offset] & 0xFF) << 24) |
				((buffer[offset + 1] & 0xFF) << 16) |
				((buffer[offset + 2] & 0xFF) << 8) |
				(buffer[offset + 3] & 0xFF);
	}
	
	public static void packLong(long n, byte[] buffer, int offset) {
		for (int i = 0; i < 8; i++) {
			buffer[offset + i] = (byte) (n >> (56 - 8 * i));
		}
	}
	
	public static long unpackLong(byte[] buffer, int offset) {
		long result = 0;
		for (int i = 0; i < 8; i++) {
			result |= ((long) (buffer[offset + i] & 0xFF) << (56 - 8 * i));
		}
		return result;
	}
	
	public static void packDouble(double d, byte[] buffer, int offset) {
		long bits = Double.doubleToLongBits(d);
		packLong(bits, buffer, offset);
	}
	
	public static double unpackDouble(byte[] buffer, int offset) {
		long bits = unpackLong(buffer, offset);
		return Double.longBitsToDouble(bits);
	}
}
