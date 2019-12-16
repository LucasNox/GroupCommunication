package structure;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

/**
 * @file  Message.java
 * @brief Classe de mensagens
*/

public class Message implements Serializable {
	/*******************************************************************
	*   GLOBAL VARIABLES
	*******************************************************************/
	private static final long serialVersionUID = 182715289311949524L;
	
	private String author;
	private String message;
	private LocalTime time;

	/*******************************************************************
		 *   IMPLEMENTATION
		 *******************************************************************/
	public Message(final String author, final String message, final LocalTime time) {
		this.author = author;
		this.message = message;
		this.time = time;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public LocalTime getTime() {
		return this.time;
	}

	public void setTime(final LocalTime time) {
		this.time = time;
	}

	public void makeTime() {
		this.time = LocalTime.now();
	}

	@Override
	public boolean equals(final Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Message)) {
			return false;
		}
		final Message message = (Message) o;
		return Objects.equals(this.author, message.author) && Objects.equals(this.message, message.message) && Objects.equals(this.time, message.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, message, time);
	}

	@Override
	public String toString() {
		final DateTimeFormatter formatter =
		DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
						.withLocale(new Locale("pt", "BR"))
						.withZone(ZoneId.systemDefault());
		final String output = formatter.format( time );

		return "[" + output + "]";
	}

}
