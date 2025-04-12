package info.kaminosoft.bean;

public enum Modo{
	DEV_LOCALHOST,
	DEV,
	PROD;

	public static Modo fromString(String text) {
        try {
            return Modo.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
        }
    }
}