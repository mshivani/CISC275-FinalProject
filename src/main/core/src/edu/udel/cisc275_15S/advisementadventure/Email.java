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

	public Email(int numberInList, String sender, String date, String subject, String salutation, String content, String closing, String signature) {
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
//		String contentString = "";
//		for (int i = 0; i < content.length(); i++) {
//			contentString += content.charAt(i);
//			if (contentString.length() >= EmailScreen.emailLabelWidth) {
//				contentString += "\n";
//			}
//		}
		return sender + "\n" + date + "\n" + subject + "\n" + salutation + "\n" + content + "\n" + closing + "\n" + signature;
	}
	
	public int getNumberInList() {
		return numberInList;
	}

}
