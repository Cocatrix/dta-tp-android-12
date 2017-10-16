package fr.codevallee.formation.td12;


class User {
    private Integer id;
    private String familyName;
    private String firstName;
    private Integer age;
    private String job;

    public User(Integer id, String familyName, String firstName, Integer age, String job) {
        this.id = id;
        this.familyName = familyName;
        this.firstName = firstName;
        this.age = age;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFamilyName() { return familyName; }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String toString() {
        return this.familyName + " " + this.firstName;
    }
}
