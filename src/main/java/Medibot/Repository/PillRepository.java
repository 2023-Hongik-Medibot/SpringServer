package Medibot.Repository;

import Medibot.Domain.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
public interface PillRepository extends JpaRepository<Pill, Integer> {

    Pill findByShapeAndFrontSignAndAndBackSign(String shape, String frontSign, String backSign);

    List<Pill> findAllByShape(String shape);

    List<Pill> findAllByFrontSignIn(List<Pill> pills);

    Pill findByItemName(String itemName);

    @Query(value = "select p.itemName from pill p where p.serialNumber =?1")
    String findBySerialNumber(int serialNumber);

    Pill findPillBySerialNumber(int serialNumber);


}

