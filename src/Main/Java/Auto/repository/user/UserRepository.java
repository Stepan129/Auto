package Auto.repository.user;


import Auto.entity.user.UserEntity;
import Auto.entity.projection.UserAuthDataProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserAuthDataProjection findUserAuthDataByUsername(String username);

    @Modifying
    @Query("DELETE FROM UserEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
