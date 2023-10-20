public class Person {
	
	private long id;
	private String name;
	private int age;
	private boolean married;
	private static final int NAME_LIMIT = 28;
	public static final int SIZE = 8 + 2 * NAME_LIMIT + 4 + 1;

	public Person(long id, String name, int age, boolean married) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.married = married;
	}

	public long getAge() {
		return this.age;
	}

	public long getld() {
		return this.id;
	}

	public boolean isMarried() {
		return married;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "Person { " + " id- " + id + " + name + age = " + age + " married = " + married + " }";
	}

	public byte[] toBytes() {
		byte[] record = new byte[SIZE];
		int offset = 0;
		PackUtils.packLong(id, record, offset);
		offset += 8;
		PackUtils.packLimitedString(name, NAME_LIMIT, record, offset);
		offset += 2 * NAME_LIMIT;
		PackUtils.packInt(age, record, offset);
		offset += 4;
		PackUtils.packBoolean(married, record, offset);
		return record;
	}

	public static Person fromBytes(byte[] record) {
		int offset = 0;
		long id = PackUtils.unpackLong(record, offset);
		offset += 8;
		String name = PackUtils.unpackLimitedString(NAME_LIMIT, record, offset);
		offset += 2 * NAME_LIMIT;
		int age = PackUtils.unpackInt(record, offset);
		offset += 4;
		boolean married = PackUtils.unpackBoolean(record, offset);
		return new Person(id, name, age, married);
	}
}