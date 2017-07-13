package ru.itpark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.models.security.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
