package chain;

import data.Block;
import data.Transaction;

import java.util.LinkedList;
import java.util.List;

/**
 * My block chain.
 */
public class MyBlockChain {

	public List<Block> chain;
	public static final int DIFFICULTY = 5;		// POW difficulty

	/**
	 * Constructor.
	 */
	public MyBlockChain() {
		chain = new LinkedList<>();
	}

	/**
	 * Add a block to the chain.
	 * @param content block content
	 */
	public void addBlock(List<Transaction> content) {
		Block block = null;
		if(chain.isEmpty()) {
			block = new Block(null, "0");
		} else {
			block = new Block(content, chain.get(chain.size() - 1).hash);
		}
		block.pow(DIFFICULTY);
		chain.add(block);
	}

	/**
	 * Validate the chain.
	 * @return true / false
	 */
	public boolean validate() {
		if(chain.size() <= 1) {
			return true;
		}

		StringBuilder validHashBuilder = new StringBuilder();
		for(int i = 0;i < DIFFICULTY;i++) {
			validHashBuilder.append(0);
		}
		String validHash = validHashBuilder.toString();

		for(int i = 1; i < chain.size(); i++) {
			Block cur = chain.get(i);
			Block prev = chain.get(i - 1);
			if(!cur.hash.equals(cur.calcCurrentHash())
					|| !prev.hash.equals(cur.prevHash)
					|| !validHash.equals(cur.hash.substring(0, DIFFICULTY))) {
				throw new RuntimeException(String.format("Hash of block %d is wrong!", i));
			}
		}
		return true;
	}

	/**
	 * Return the length of the chain.
	 * @return chain length
	 */
	public int chainLength() {
		return chain.size();
	}

	public Block getBlock(int index) {
		if(index < 0 || index >= chain.size()) {
			return null;
		}
		return chain.get(index);
	}
}
