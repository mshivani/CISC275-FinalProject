package edu.udel.cisc275_15S.advisementadventure;

public class Email {
	int numberInList;
	String sender;
	String date;
	String subject;
	String salutation;
	String content;
	String closing;
	String signature;

	public Email(int numberInList, String sender, String date, String subject,
			String salutation, String content, String closing, String signature) {
		this.numberInList = numberInList;
		this.sender = sender;
		this.date = date;
		this.subject = subject;
		this.salutation = salutation;
		this.content = content;
		this.closing = closing;
		this.signature = signature;
	}

	@Override
	public String toString() {
		return sender + "\n" + date + "\n" + subject + "\n" + salutation + "\n"
				+ content + "\n" + closing + "\n" + signature;
	}

	public String getSender() {
		return sender;
	}

	public String getDate() {
		return date;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public String getSalutation() {
		return salutation;
	}

	public String getClosing() {
		return closing;
	}

	public String getSignature() {
		return signature;
	}

	public int getNumberInList() {
		return numberInList;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
