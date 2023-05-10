package Auto.entity.projection;

public interface UserAuthDataProjection {
    String getUsername();
    boolean getIsAdmin();
    String getPasswordHash();
}
