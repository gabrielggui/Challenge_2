package br.com.compass.challenge2.repository;

import br.com.compass.challenge2.entity.Organizer;
import br.com.compass.challenge2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    List<Organizer> findByNameContainingIgnoreCase(String name);

    List<Organizer> findByEmailContainingIgnoreCase(String email);

    @Query("SELECT o FROM Organizer o JOIN o.groups g WHERE g.name = :groupName")
    List<Organizer> findByGroupName(@Param("groupName") String groupName);

    @Query("SELECT o FROM Organizer o JOIN o.roles r WHERE r = :role")
    List<Organizer> findByRole(@Param("role") Role role);

    List<Organizer> findAllByOrderByNameAsc();

    List<Organizer> findAllByOrderByEmailAsc();

    List<Organizer> findAllByOrderByIdAsc();

}
