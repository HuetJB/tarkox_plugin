package fr.aureprod.tarkox.instance;

import java.util.HashMap;
import java.util.List;

import org.bukkit.block.Chest;

import fr.aureprod.tarkox.Plugin;
import fr.aureprod.tarkox.datatype.ExtractionArea;
import fr.aureprod.tarkox.datatype.SpawnPosition;

public class TarkoxInstanceParent {
    protected Plugin plugin;
    private String name;
    private Integer durationTime;
    private Integer waitTimeBeforeTp;
    private Integer maxPlayers;
    protected List<ExtractionArea> extractionAreas;
    protected HashMap<String, List<Chest>> chests;
    protected List<SpawnPosition> spawns;

    private Integer currentTime;
    private TarkoxInstanceStatus status;

    public TarkoxInstanceParent (
        Plugin plugin, 
        String name, 
        Integer durationTime,
        Integer waitTimeBeforeTp,
        Integer maxPlayers, 
        List<ExtractionArea> extractionAreas, 
        HashMap<String, List<Chest>> chests, 
        List<SpawnPosition> spawns
    ) {
        this.plugin = plugin;
        this.name = name;
        this.durationTime = durationTime;
        this.waitTimeBeforeTp = waitTimeBeforeTp;
        this.maxPlayers = maxPlayers;
        this.extractionAreas = extractionAreas;
        this.chests = chests;
        this.spawns = spawns;

        this.status = TarkoxInstanceStatus.WAITING;
        this.currentTime = 0;
    }

    public String getName() {
        return this.name;
    }

    protected Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    public String getStatus() {
        switch (this.status) {
            case WAITING:
                return "In waiting";
            case INGAME:
                return "In game";
            default:
                return "An error occured";
        }
    }

    public Integer getLeftTime() {
        return this.getDurationTime() - this.currentTime;
    }

    public Boolean isInGame() {
        return this.status.equals(TarkoxInstanceStatus.INGAME);
    }

    public Boolean isWaiting() {
        return this.status.equals(TarkoxInstanceStatus.WAITING);
    }

    protected void setStatusInGame() {
        this.status = TarkoxInstanceStatus.INGAME;
    }

    protected void setStatusWaiting() {
        this.status = TarkoxInstanceStatus.WAITING;
    }

    protected Integer getCurrentTime() {
        return this.currentTime;
    }
    
    protected void resetCurrentTime() {
        this.currentTime = 0;
    }

    protected void incrementCurrentTime() {
        this.currentTime++;
    }

    protected SpawnPosition getRandomSpawn() {
        int randomIndex = (int) (Math.random() * this.spawns.size());
        return this.spawns.get(randomIndex);
    }

    protected Integer getDurationTime() {
        return this.durationTime;
    }

    protected Integer getWaitTimeBeforeTp() {
        return this.waitTimeBeforeTp;
    }
}
