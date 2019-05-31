package kay.rpgcombat.capabilities.interfaces;

import java.util.List;
import java.util.UUID;

public interface IAllies {
    void add(UUID player);
    void add(List<UUID> players);
    void remove(UUID player);
    void remove(List<UUID> players);
    List<UUID> getAllies();
    void setAllies(List<UUID> allies);
    int getAlliesCount();
}
