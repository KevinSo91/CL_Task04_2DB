package mainPackage.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;

import mainPackage.entities.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
