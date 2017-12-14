package net.mwa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.mwa.vo.RoleVO;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleVO, Integer> {
	RoleVO findByRole(String role);

}
