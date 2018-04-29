package encryption;

import java.security.MessageDigest;

/**
 * Encryption utils.
 */
public class EncryptUtils {

	public static final String SHA_256 = "SHA-256";
	public static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * My SHA-256 algorithm util.
	 * @param data the data to be encrypted
	 * @return SHA-256 encryption of the input data
	 */
	public static String mySHA256(String data) throws Exception {

		byte[] dataBytes = data.getBytes(DEFAULT_ENCODING);
		byte[] hash = MessageDigest.getInstance(SHA_256).digest(dataBytes);
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hexHash = Integer.toHexString(Math.abs(hash[i]));	// hash[i] could be negative
			if(hexHash.length() == 1) {
				result.append("0" + hexHash);	// unify the length of hex hash to be 2
			} else {
				result.append(hexHash);
			}
		}
		return result.toString();
	}
}
