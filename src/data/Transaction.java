package data;

/**
 * Transaction data.
 */
public class Transaction {

	private String sender;
	private String receiver;
	private String timestamp;
	private double amount;

	public Transaction(String sender, String receiver, double amount) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.timestamp = String.valueOf(System.currentTimeMillis());
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Transaction that = (Transaction) o;

		if (Double.compare(that.getAmount(), getAmount()) != 0) {
			return false;
		}
		if (!getSender().equals(that.getSender())) {
			return false;
		}
		if (!getReceiver().equals(that.getReceiver())) {
			return false;
		}
		return getTimestamp().equals(that.getTimestamp());
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getSender().hashCode();
		result = 31 * result + getReceiver().hashCode();
		result = 31 * result + getTimestamp().hashCode();
		temp = Double.doubleToLongBits(getAmount());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
