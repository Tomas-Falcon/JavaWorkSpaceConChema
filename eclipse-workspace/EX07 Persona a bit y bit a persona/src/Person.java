public class Person {
    private long id;
    private String name;
    private int age;
    private boolean married;
    private static final int NAME_LIMIT = 20;
    public static final int SIZE = 2 + NAME_LIMIT + 4 + 1;

    public Person(long id, String name, int age, boolean married) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.married = married;
    }

    public int getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public boolean isMarried() {
        return married;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + ", married=" + married + "]";
    }

    public byte[] toBytes() {
        byte[] record = new byte[SIZE];
        int offset = 0;
        offset = PackUtils.packLong(id, record, offset);
        offset = PackUtils.packString(name, record, offset, NAME_LIMIT);
        offset = PackUtils.packInt(age, record, offset);
        offset = PackUtils.packBoolean(married, record, offset);
        return record;
    }
    

    public static Person fromBytes(byte[] record) {
        int offset = 0;
        long id = PackUtils.unpackLong(record, offset);
        offset += 8;
        String name = PackUtils.unpackString(record, offset, NAME_LIMIT);
        offset += NAME_LIMIT;
        int age = PackUtils.unpackInt(record, offset);
        offset += 4;
        boolean married = PackUtils.unpackBoolean(record, offset);
        return new Person(id, name, age, married);
    }
}
