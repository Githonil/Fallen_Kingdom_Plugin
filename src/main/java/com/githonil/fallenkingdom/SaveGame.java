package com.githonil.fallenkingdom;

import com.githonil.fallenkingdom.teams.TeamInterface;
import com.githonil.fallenkingdom.claims.ClaimInterface;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bukkit.Chunk;
import org.bukkit.Location;

/**
 * This class represent the save of the Fallen Kingdom.
 */
public class SaveGame {
    
    /**
     * This method serializes an object.
     * 
     * @param object The object.
     * @param pathString The file's path.
     */
    private void serialize(Serializable object, String pathString) {
        FileOutputStream file = null;
        ObjectOutputStream objectStream = null;

        try {
            Path path = Path.of(pathString);
            if (!Files.exists(path))
                Files.createFile(path);

            file = new FileOutputStream(pathString);
            objectStream = new ObjectOutputStream(file);

            objectStream.writeObject(object);
        } catch (Exception exception) {}
        finally {
            try {
                file.close();
            } catch (Exception exception) {}

            try {
                objectStream.close();
            } catch (Exception exception) {}
        }
    }



    /**
     * This method deserializes an object.
     * 
     * @param pathString The file's path.
     * @return Return the object.
     */
    private Object deserialize(String pathString) {
        FileInputStream file = null;
        ObjectInputStream objectStream = null;
        Object object = null;

        try {
            file = new FileInputStream(pathString);
            objectStream = new ObjectInputStream(file);

            object = objectStream.readObject();
        } catch (Exception exception) {}
        finally {
            try {
                file.close();
            } catch (Exception exception) {}

            try {
                objectStream.close();
            } catch (Exception exception) {}
        }

        return object;
    }



    /**
     * This method prepares a chunk for the serializable.
     * 
     * @param chunk The chunk
     * @return The chunk prepared.
     */
    private Map<String, Object> serializeChunk(Chunk chunk) {
        return chunk.getBlock(0, 0, 0).getLocation().serialize();
    }



    /**
     * This method prepares a chunk for the deserializable.
     * 
     * @param chunkSerial The chunk
     * @return The chunk prepared.
     */
    private Chunk deserializeChunk(Map<String, Object> chunkSerial) {
        return Location.deserialize(chunkSerial).getChunk();
    }



    /**
     * This method checks if the deserializable is in the good type for the claims.
     * 
     * @param map The object deserializabled.
     * @return Return the object, null if a problem appears.
     */
    private Map<Map<String, Object>, ClaimInterface> checkDeserializableClaims(Map<?, ?> map) {
        if (map == null) return null;

        HashMap<Map<String, Object>, ClaimInterface> result = new HashMap<>();
		for(Map.Entry<?, ?> entry : map.entrySet()) {
			if (!(entry.getValue() instanceof ClaimInterface)) return null;
            if (!(entry.getKey() instanceof Map)) return null;

            Map<?, ?> entryMap = (Map<?, ?>) entry.getKey();
            Map<String, Object> entryResult = new HashMap<>();
            for (Map.Entry<?, ?> entryTwo : entryMap.entrySet()) {
                if (!(entryTwo.getKey() instanceof String)) return null;

                entryResult.put((String) entryTwo.getKey(), entryTwo.getValue());
            }

            result.put(entryResult, (ClaimInterface) entry.getValue());
		}
		
		return result;
    }



    /**
     * This method serializes the claims.
     * 
     * @param claims The claims to save.
     * @param path The path where to save the claims.
     */
    private void serializeClaims(Map<Chunk, ClaimInterface> claims, String path) {
        HashMap<Map<String, Object>, ClaimInterface> claimsSerial = new HashMap<>();
        
        for (Map.Entry<Chunk, ClaimInterface> entry : claims.entrySet()) {
            Map<String, Object> chunkPrepare = this.serializeChunk(entry.getKey());
            claimsSerial.put(chunkPrepare, entry.getValue());
        }

        this.serialize(claimsSerial, path);
    }



    /**
     * This method deserializes the claims.
     * 
     * @param path The path where to load the claims.
     * @return Return the claims loaded.
     */
    public Map<Chunk, ClaimInterface> loadClaims(String path) {
        Map<Map<String, Object>, ClaimInterface> claimsSerial = this.checkDeserializableClaims((Map<?, ?>) this.deserialize(path));
        HashMap<Chunk, ClaimInterface> claims = new HashMap<>();

        if (claimsSerial == null) {
            return claims;
        }

        for (Map.Entry<Map<String, Object>, ClaimInterface> entry : claimsSerial.entrySet()) {
            Chunk chunk = this.deserializeChunk(entry.getKey());
            claims.put(chunk, entry.getValue());
        }
        return claims;
    }



    /**
     * This method checks if the deserializable is in the good type for the teams.
     * 
     * @param map The object deserializabled.
     * @return Return the object, null if a problem appears.
     */
    private Map<UUID, TeamInterface> checkDeserializableTeams(Map<?, ?> map) {
        if (map == null) return null;

        HashMap<UUID, TeamInterface> result = new HashMap<>();
		for(Map.Entry<?, ?> entry : map.entrySet()) {
			if (!(entry.getKey() instanceof UUID) || !(entry.getValue() instanceof TeamInterface)) return null;
            
            result.put((UUID) entry.getKey(), (TeamInterface) entry.getValue());
            System.out.println(result);
        }
		
		return result;
    }



    /**
     * This method saves the Fallen Kingdom.
     * 
     * @param teammatesMap The teams to save.
     * @param claims The claims to save.
     * @param pathTeams The path where to save the teams.
     * @param pathClaims The path where to save the claims.
     */
    public void save(Map<UUID, TeamInterface> teammatesMap, Map<Chunk, ClaimInterface> claims, String pathTeams, String pathClaims) {
        this.serializeClaims(claims, pathClaims);
        this.serialize((Serializable) teammatesMap, pathTeams);
    }



    /**
     * This method loads the Fallen Kingdom's saves.
     * <p>
     * Use this method after the world generation.
     * 
     * @param path The path where to load the teams.
     * @return Return the teams loaded.
     */
    public Map<UUID, TeamInterface> loadTeam(String path) {
        Map<UUID, TeamInterface> teammatesMap = this.checkDeserializableTeams((Map<?, ?>) this.deserialize(path));
        if (teammatesMap == null) teammatesMap = new HashMap<>();
        return teammatesMap;
    }

}