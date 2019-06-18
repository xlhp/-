package xmlDemo;

public class Information {
	private String NO;
	private String ADDR;
	private String NAME;

	public String toString() {
		return this.NO + "		" + this.ADDR + "	" + this.NAME;
	}
	/**
	 * 
	 * @param name ��Ҫʵ��������������
	 * @param value	���Ե�ֵ
	 * @param obj ���������Ķ���
	 */
	public static void setAll(String name,String value,Information obj) {
		switch (name) {
		case "NO":
				obj.setNo(value);
			break;
		case "ADDR":
				obj.setAddr(value);
			break;
		case "NAME":
				obj.setName(value);
			break;
		default:
			break;
		}
	}

	public String getNo() {
		return NO;
	}

	public void setNo(String no) {
		this.NO = no;
	}

	public String getAddr() {
		return ADDR;
	}

	public void setAddr(String addr) {
		this.ADDR = addr;
	}

	public String getName() {
		return NAME;
	}

	public void setName(String name) {
		this.NAME = name;
	}
}
