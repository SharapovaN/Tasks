package org.example.proxy;

public class CachingProxyRetriever implements Retriever {
    private LRUCache lruCache = new LRUCache(10000);
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        this.originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        if (lruCache.find(id) != null)
            return lruCache.find(id);
        else {
            Object obj = originalRetriever.retrieve(id);
            lruCache.set(id, obj);
            return obj;
        }
    }
}
