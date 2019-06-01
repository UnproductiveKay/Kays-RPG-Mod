package kay.rpgcombat.capabilities;

import kay.rpgcombat.capabilities.interfaces.IAllies;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Allies implements IAllies {
    private List<UUID> uuids = new ArrayList();
    private List<UUID> requestsTo = new ArrayList();
    private List<UUID> requestsFrom = new ArrayList();

    @Override
    public void add(UUID player) {
        if (player != null && !exists(player))
            uuids.add(player);
    }
    @Override
    public void add(List<UUID> players) {
        for (UUID player: players) {
            if (player != null && !exists(player))
                uuids.add(player);
        }
    }
    private boolean exists(UUID player) {
        for (UUID uuid: uuids) {
            if (player.equals(uuid))
                return true;
        }
        return false;
    }

    @Override
    public void remove(UUID player) {
        uuids.remove(player);
    }

    @Override
    public void remove(List<UUID> players) {
        uuids.removeAll(players);
    }

    @Override
    public List<UUID> getAllies() {
        return uuids;
    }

    @Override
    public void setAllies(List<UUID> allies) {
        uuids = allies;
    }

    @Override
    public int getAlliesCount() {
        return uuids.size();
    }

    @Override
    public void addRequestTo(UUID player) {
        requestsTo.add(player);
    }

    @Override
    public List<UUID> getRequestsTo() {
        return requestsTo;
    }

    @Override
    public void removeRequestTo(UUID player) {
        requestsTo.remove(player);
    }

    @Override
    public void addRequestFrom(UUID player) {
        requestsFrom.add(player);
    }

    @Override
    public List<UUID> getRequestsFrom() {
        return requestsFrom;
    }

    @Override
    public void removeRequestFrom(UUID player) {
        requestsTo.remove(player);
    }
}
