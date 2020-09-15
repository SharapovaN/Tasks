package org.example.strategy.strategies;

import org.example.strategy.strategies.Entry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public long getFileSize() {
        long size = 0L;
        try {
            size = Files.size(path);
        }catch (IOException e) {
            e.getMessage();
        }
        return size;
    }

    public void putEntry(Entry entry) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(path));
            os.writeObject(entry);
            os.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Entry getEntry() {
        Entry entry = null;
            try {
                if (getFileSize() > 0) {
                    ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
                    entry = (Entry) ois.readObject();
                    ois.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.getMessage();
            }

        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
