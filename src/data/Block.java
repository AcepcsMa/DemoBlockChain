package data;

import encryption.EncryptUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Block in the blockchain system.
 */
public class Block {

	public String hash;			// the hash value of current block
	public String prevHash;
	public List<Transaction> content;
	public long timeStamp;
	public int nonce;	// a random nonce used in POW

	/**
	 * Constructor.
	 * @param content the content to be inserted into current block
	 * @param prevHash the hash value of the previous block
	 */
	public Block(List<Transaction> content, String prevHash) {
		this.content = content;
		this.prevHash = prevHash;
		timeStamp = new Date().getTime();
		hash = calcCurrentHash();
		nonce = new Random().nextInt();
	}

	/**
	 * Calculate the hash of current block.
	 * @return hash value of current block
	 */
	public String calcCurrentHash() {
		try {
			return EncryptUtils.mySHA256(String.valueOf(timeStamp)
										+ content.hashCode()
										+ prevHash
										+ nonce);
		} catch (Exception e) {
			throw new RuntimeException("Can not calculate the hash for current block!");
		}
	}

	/**
	 * Proof of work algorithm.
	 * @param difficulty difficulty level which reflects the difficulty of generating new blocks
	 */
	public void pow(int difficulty) {
		hash = calcCurrentHash();

		nonce = new Random().nextInt();
		char[] zeroes = new char[difficulty];
		Arrays.fill(zeroes, '0');
		String validPrefix = new String(zeroes);

		while(!validPrefix.equals(this.hash.substring(0, difficulty))) {
			if(nonce == Integer.MAX_VALUE - 1) {
				nonce = new Random().nextInt();
			} else {
				nonce++;
			}
			hash = calcCurrentHash();
		}
		System.out.println(String.format("POW Success! %s", this.content));
	}
}
