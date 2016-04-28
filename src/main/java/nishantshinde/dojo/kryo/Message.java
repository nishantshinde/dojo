package nishantshinde.dojo.kryo;

public class Message {
	private int id;
	private String text;

	public Message(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}
