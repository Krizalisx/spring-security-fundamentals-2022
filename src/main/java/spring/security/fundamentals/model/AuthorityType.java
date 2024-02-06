package spring.security.fundamentals.model;

public enum AuthorityType {
    READ,
    WRITE;

    public String getAuthority() {
        return name();
    }
}
