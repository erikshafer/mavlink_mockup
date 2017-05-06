package edu.unomaha.mavlink.repository;

import java.util.List;
import java.util.Set;

import edu.unomaha.mavlink.domain.User;
import edu.unomaha.mavlink.domain.security.UserRole;

public interface UserService {
	User findByUsername(String username);

    User findByEmail(String email);
    
    User findById(Long id);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (User user);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    User saveUser (User user); 
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);
}
