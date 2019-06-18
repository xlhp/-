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
	 * @param name 所要实例化的属性名字
	 * @param value	属性的值
	 * @param obj 属性所属的对象
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
