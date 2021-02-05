package com.konrad.dietApp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	@Query(value = "SELECT u.id FROM users u WHERE u.email=?1",nativeQuery = true)
	public int findIdByEmail(String email);
}
