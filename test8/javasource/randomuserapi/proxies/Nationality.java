// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package randomuserapi.proxies;

public enum Nationality
{
	AU(new java.lang.String[][] { new java.lang.String[] { "en_US", "AU" } }),
	BR(new java.lang.String[][] { new java.lang.String[] { "en_US", "BR" } }),
	CA(new java.lang.String[][] { new java.lang.String[] { "en_US", "CA" } }),
	CH(new java.lang.String[][] { new java.lang.String[] { "en_US", "CH" } }),
	DE(new java.lang.String[][] { new java.lang.String[] { "en_US", "DE" } }),
	DK(new java.lang.String[][] { new java.lang.String[] { "en_US", "DK" } }),
	ES(new java.lang.String[][] { new java.lang.String[] { "en_US", "ES" } }),
	FI(new java.lang.String[][] { new java.lang.String[] { "en_US", "FI" } }),
	FR(new java.lang.String[][] { new java.lang.String[] { "en_US", "FR" } }),
	GB(new java.lang.String[][] { new java.lang.String[] { "en_US", "GB" } }),
	IE(new java.lang.String[][] { new java.lang.String[] { "en_US", "IE" } }),
	IR(new java.lang.String[][] { new java.lang.String[] { "en_US", "IR" } }),
	NL(new java.lang.String[][] { new java.lang.String[] { "en_US", "NL" } }),
	NZ(new java.lang.String[][] { new java.lang.String[] { "en_US", "NZ" } }),
	TR(new java.lang.String[][] { new java.lang.String[] { "en_US", "TR" } }),
	US(new java.lang.String[][] { new java.lang.String[] { "en_US", "US" } });

	private java.util.Map<java.lang.String, java.lang.String> captions;

	private Nationality(java.lang.String[][] captionStrings)
	{
		this.captions = new java.util.HashMap<java.lang.String, java.lang.String>();
		for (java.lang.String[] captionString : captionStrings)
			captions.put(captionString[0], captionString[1]);
	}

	public java.lang.String getCaption(java.lang.String languageCode)
	{
		if (captions.containsKey(languageCode))
			return captions.get(languageCode);
		return captions.get("en_US");
	}

	public java.lang.String getCaption()
	{
		return captions.get("en_US");
	}
}