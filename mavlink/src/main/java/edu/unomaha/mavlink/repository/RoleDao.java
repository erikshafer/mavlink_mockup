package edu.unomaha.mavlink.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.mavlink.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
